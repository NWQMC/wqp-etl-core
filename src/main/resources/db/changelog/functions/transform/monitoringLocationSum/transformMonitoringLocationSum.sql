create or replace function transform_monitoring_location_sum(wqp_data_source character varying, schema_name character varying)
returns void
language plpgsql
as $$
declare
    summary_table_name varchar := 'station_sum_swap_' || wqp_data_source;
    monitoring_location_table_name varchar := 'station_swap_' || wqp_data_source;
    activity_table_name varchar := 'activity_swap_' || wqp_data_source;
    result_table_name varchar := 'result_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            data_source,
                            station_id,
                            site_id,
                            organization,
                            site_type,
                            huc,
                            governmental_unit_code,
                            geom,
                            activity_count,
                            result_count)
         select station.data_source_id,
                station.data_source,
                station.station_id,
                station.site_id,
                station.organization,
                station.site_type,
                station.huc,
                station.governmental_unit_code,
                station.geom,
                coalesce(activity.activity_count, 0) activity_count,
                coalesce(result.result_count, 0) result_count
           from %I.%I station
                left join (select station_id, count(*) activity_count
                             from %I.%I
                                group by station_id) activity
                  on station.station_id = activity.station_id
                left join (select station_id, count(*) result_count
                             from %I.%I
                                group by station_id) result
                  on station.station_id = result.station_id',
        schema_name, summary_table_name, schema_name, monitoring_location_table_name, schema_name, activity_table_name, schema_name, result_table_name);
end
$$