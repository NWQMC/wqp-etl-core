create or replace function transform_org_grouping(wqp_data_source character varying, schema_name character varying)
returns void
language plpgsql
as $$
declare
    summary_table_name varchar := 'org_grouping_swap_' || wqp_data_source;
    result_table_name varchar := 'result_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            data_source,
                            organization,
                            the_year,
                            characteristic_type,
                            characteristic_name,
                            total_monitoring_locations,
                            total_activities,
                            total_results,
                            last_result_date,
                            grouping_id)
         select data_source_id,
                min(data_source) data_source,
                organization,
                to_char(event_date, ''yyyy'') the_year,
                characteristic_type,
                characteristic_name,
                count(distinct site_id) total_monitoring_locations,
                count(distinct activity_id) total_activities,
                count(*) total_results,
                max(last_updated) last_result_date,
                ((grouping (to_char(event_date, ''yyyy'')))::char || (grouping (characteristic_type))::char || (grouping (characteristic_name))::char)::int grouping_id
           from %I.%I
             group by grouping sets(
                                    (data_source_id, organization, to_char(event_date, ''yyyy'')),
                                    (data_source_id, organization, to_char(event_date, ''yyyy''), characteristic_type),
                                    (data_source_id, organization, to_char(event_date, ''yyyy''), characteristic_type, characteristic_name)
                                   )',
        schema_name, summary_table_name, schema_name, result_table_name);
end
$$