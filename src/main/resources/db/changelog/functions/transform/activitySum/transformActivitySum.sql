create or replace function transform_activity_sum(wqp_data_source character varying, schema_name character varying)
returns void
language plpgsql
as $$
declare
    summary_table_name varchar := 'activity_sum_swap_' || wqp_data_source;
    activity_table_name varchar := 'activity_swap_' || wqp_data_source;
    result_table_name varchar := 'result_swap_' || wqp_data_source;
    activity_metric_table_name varchar := 'act_metric_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            data_source,
                            station_id,
                            site_id,
                            event_date,
                            sample_media,
                            organization,
                            site_type,
                            huc,
                            governmental_unit_code,
                            geom,
                            activity_id,
                            project_id,
                            act_metric_count,
                            result_count)
         select activity.data_source_id,
                activity.data_source,
                activity.station_id,
                activity.site_id,
                activity.event_date,
                activity.sample_media,
                activity.organization,
                activity.site_type,
                activity.huc,
                activity.governmental_unit_code,
                activity.geom,
                activity.activity_id,
                activity.project_id,
                coalesce(act_metric.act_metric_count,0),
                coalesce(result.result_count,0)
           from %I.%I activity
                left join (select station_id, activity_id, count(*) result_count
                             from %I.%I
                                group by station_id, activity_id) result
                  on activity.station_id = result.station_id and
                     activity.activity_id = result.activity_id
                left join (select station_id, activity_id, count(*) act_metric_count
                             from %I.%I
                                group by station_id, activity_id) act_metric
                  on activity.station_id = act_metric.station_id and
                     activity.activity_id = act_metric.activity_id',
        schema_name, summary_table_name, schema_name, activity_table_name, schema_name, result_table_name, schema_name, activity_metric_table_name);
end
$$