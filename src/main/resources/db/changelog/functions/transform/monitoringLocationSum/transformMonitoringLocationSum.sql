create or replace function transform_monitoring_location_sum(wqp_data_source character varying, wqp_schema_name character varying, geo_schema_name character varying)
returns void
language plpgsql
as $$
declare
    summary_table_name varchar := 'station_sum_swap_' || wqp_data_source;
    monitoring_location_table_name varchar := 'station_swap_' || wqp_data_source;
    activity_table_name varchar := 'activity_swap_' || wqp_data_source;
    result_table_name varchar := 'result_swap_' || wqp_data_source;
    ml_grouping_table_name varchar := 'ml_grouping_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            data_source,
                            station_id,
                            site_id,
                            station_name,
                            organization,
                            organization_name,
                            site_type,
                            station_type_name,
                            huc,
                            governmental_unit_code,
                            geom,
                            country_name,
                            state_name,
                            county_name,
                            activity_count,
                            activity_count_past_12_months,
                            activity_count_past_60_months,
                            result_count,
                            result_count_past_12_months,
                            result_count_past_60_months,
                            summary_all_months,
                            summary_past_12_months,
                            summary_past_60_months)
         with months_window as (select (now() - interval ''12 month'')::date the_12_months_cutoff,
                                        (now() - interval ''60 month'')::date the_60_months_cutoff
                               ),
              ml_yr_sum_result as (
                                   select data_source_id,
                                          station_id,
                                          max(coalesce(last_updated, event_date)) event_date_all_time,
                                          count(*) result_count,
                                          count(case when event_date >= the_12_months_cutoff then 1 else null end) result_count_past_12_months,
                                          count(case when event_date >= the_60_months_cutoff then 1 else null end) result_count_past_60_months
                                     from %I.%I,
                                          months_window
                                        group by data_source_id, station_id
                                  ),
              ml_yr_sum_activity as (
                                     select data_source_id,
                                            station_id,
                                            count(distinct activity_id) activity_count,
                                            count(distinct case when event_date >= the_12_months_cutoff then activity_id else null end) activity_count_past_12_months,
                                            count(distinct case when event_date >= the_60_months_cutoff then activity_id else null end) activity_count_past_60_months
                                       from %I.%I,
                                            months_window
                                          group by data_source_id, station_id
                                    ),
              ml_period_agg as (
                                select data_source_id, 
                                       station_id,
                                       json_object_agg(characteristic_type, past_12_months) filter (where past_12_months is not null) summary_past_12_months,
                                       json_object_agg(characteristic_type, past_60_months) filter (where past_60_months is not null) summary_past_60_months,
                                       json_object_agg(characteristic_type, all_months) filter (where all_months is not null) summary_all_months
                                  from (
                                        select data_source_id,
                                               station_id,
                                               coalesce(characteristic_type, ''Not Assigned'') characteristic_type,
                                               sum(case when years_window = 1 then total_results else null end) past_12_months,
                                               sum(case when years_window < 6 then total_results else null end) past_60_months,
                                               sum(total_results) all_months
                                          from %I.%I
                                         where grouping_id = 1001
                                            group by data_source_id, station_id, coalesce(characteristic_type, ''Not Assigned'')
                                       ) a
                                    group by data_source_id, station_id
                               )
         select station.data_source_id,
                station.data_source,
                station.station_id,
                station.site_id,
                station.station_name,
                station.organization,
                station.organization_name,
                station.site_type,
                station.station_type_name,
                station.huc,
                station.governmental_unit_code,
                station.geom,
                full_country.country_name,
                full_state.state_name,
                full_county.county_name,
                coalesce(ml_yr_sum_activity.activity_count, 0) activity_count,
                coalesce(ml_yr_sum_activity.activity_count_past_12_months, 0) activity_count_past_12_months,
                coalesce(ml_yr_sum_activity.activity_count_past_60_months, 0) activity_count_past_60_months,
                coalesce(ml_yr_sum_result.result_count, 0) result_count,
                coalesce(ml_yr_sum_result.result_count_past_12_months, 0) result_count_past_12_months,
                coalesce(ml_yr_sum_result.result_count_past_60_months, 0) result_count_past_60_months,
                ml_period_agg.summary_all_months,
                ml_period_agg.summary_past_12_months,
                ml_period_agg.summary_past_60_months
           from %I.%I station
                left join ml_yr_sum_result
                  on station.data_source_id = ml_yr_sum_result.data_source_id and
                     station.station_id = ml_yr_sum_result.station_id
                left join ml_yr_sum_activity
                  on station.data_source_id = ml_yr_sum_activity.data_source_id and
                     station.station_id = ml_yr_sum_activity.station_id
                left join ml_period_agg
                  on station.data_source_id = ml_period_agg.data_source_id and
                     station.station_id = ml_period_agg.station_id
                left join %I.full_country
                  on substring(station.governmental_unit_code, ''^[^:]+'') = full_country.country_code
                left join %I.full_state
                  on substring(station.governmental_unit_code, ''^[^:]+:[^:]+'') = full_state.state_code
                left join %I.full_county
                  on substring(station.governmental_unit_code, ''^[^:]+:[^:]+:[^:]+$'') = full_county.county_code
              order by station.data_source_id,
                       station.station_id',
        wqp_schema_name, summary_table_name, wqp_schema_name, result_table_name, wqp_schema_name, activity_table_name,
        wqp_schema_name, ml_grouping_table_name, wqp_schema_name, monitoring_location_table_name, geo_schema_name, geo_schema_name, geo_schema_name);
end
$$