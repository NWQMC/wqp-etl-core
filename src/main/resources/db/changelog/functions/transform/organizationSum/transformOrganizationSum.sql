create or replace function transform_organization_sum(wqp_data_source character varying, wqp_schema_name character varying)
returns void
language plpgsql
as $$
declare
    summary_table_name varchar := 'organization_sum_swap_' || wqp_data_source;
    org_data_table_name varchar := 'org_data_swap_' || wqp_data_source;
    result_table_name varchar := 'result_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            data_source,
                            organization_id,
                            organization,
                            organization_name,
                            organization_url,
                            all_time_last_result,
                            all_time_site_count,
                            all_time_activity_count,
                            five_year_last_result,
                            five_year_site_count,
                            five_year_activity_count,
                            current_year_last_result,
                            current_year_site_count,
                            current_year_activity_count,
                            organization_type)
         with year_window as (select make_date(extract (year from (now() - interval ''48 month'')::date)::int, 1, 1) five_year_cutoff,
                                     make_date(extract (year from now())::int, 1, 1) current_year_cutoff
                             )
         select org_data.data_source_id,
                org_data.data_source,
                org_data.organization_id,
                org_data.organization,
                org_data.organization_name,
                ''/provider/'' || org_data.data_source || ''/'' || org_data.organization organization_url,
                org_sum.event_date_all_time all_time_last_result,
                coalesce(org_sum.site_count_all_time, 0) all_time_site_count,
                coalesce(org_sum.activity_count_all_time, 0) all_time_activity_count,
                org_sum.event_date_five_year five_year_last_result,
                coalesce(org_sum.site_count_five_year, 0) five_year_site_count,
                coalesce(org_sum.activity_count_five_year, 0) five_year_activity_count,
                org_sum.event_date_current_year current_year_last_result,
                coalesce(org_sum.site_count_current_year, 0) current_year_site_count,
                coalesce(org_sum.activity_count_current_year, 0) current_year_activity_count,
                org_data.organization_type
           from %I.%I org_data
                left join (select organization,
                                  max(coalesce (last_updated, event_date)) event_date_all_time,
                                  count(distinct site_id) site_count_all_time,
                                  count(distinct activity_id) activity_count_all_time,
                                  max(case when event_date >= five_year_cutoff then coalesce(last_updated, event_date) else null end) event_date_five_year,
                                  count(distinct case when event_date >= five_year_cutoff then site_id else null end) site_count_five_year,
                                  count(distinct case when event_date >= five_year_cutoff then activity_id else null end) activity_count_five_year,
                                  max(case when event_date >= current_year_cutoff then coalesce(last_updated, event_date) else null end) event_date_current_year,
                                  count(distinct case when event_date >= current_year_cutoff then site_id else null end) site_count_current_year,
                                  count(distinct case when event_date >= current_year_cutoff then activity_id else null end) activity_count_current_year
                             from %I.%I,
                                  year_window
                                group by organization) org_sum
                  on org_data.organization = org_sum.organization',
        wqp_schema_name, summary_table_name, wqp_schema_name, org_data_table_name, wqp_schema_name, result_table_name);
end
$$