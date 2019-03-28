create or replace function transform_ml_grouping(wqp_data_source character varying, schema_name character varying)
returns void
language plpgsql
as $$
declare
    summary_table_name varchar := 'ml_grouping_swap_' || wqp_data_source;
    result_table_name varchar := 'result_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            data_source,
                            station_id,
                            the_year,
                            years_window,
                            characteristic_type,
                            characteristic_name,
                            total_activities,
                            total_results,
                            last_result_date,
                            grouping_id)
         select data_source_id,
                min(data_source) data_source,
                station_id,
                the_year,
                years_window,
                characteristic_type,
                characteristic_name,
                count(distinct activity_id) total_activities,
                count(*) total_results,
                max(last_updated) last_result_date,
                ((grouping (the_year))::char || (grouping (years_window))::char || (grouping (characteristic_type))::char || (grouping (characteristic_name))::char)::int grouping_id
           from (
                 select data_source_id,
                        data_source,
                        station_id,
                        extract (year from event_date) the_year,
                        characteristic_type,
                        characteristic_name,
                        activity_id,
                        last_updated,
                        case 
                           when event_date > (now() - interval ''12 month'') then 1
                           when event_date > (now() - interval ''60 month'') then 5
                           else 9
                        end years_window
                   from %I.%I
                ) a
             group by grouping sets(
                                    (data_source_id, station_id, the_year, characteristic_type, characteristic_name),
                                    (data_source_id, station_id, years_window, characteristic_type)
                                   )',
        schema_name, summary_table_name, schema_name, result_table_name);
end
$$