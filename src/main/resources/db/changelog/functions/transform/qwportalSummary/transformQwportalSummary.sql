create or replace function transform_qwportal_summary(wqp_data_source character varying, wqp_schema_name character varying, nwis_or_epa character varying)
returns void
language plpgsql
as $$
declare
    summary_table_name varchar := 'qwportal_summary_swap_' || wqp_data_source;
    activity_sum_table_name varchar := 'activity_sum_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            fips_state_code,
                            fips_county_code,
                            fips_state_and_county,
                            huc8,
                            min_date,
                            max_date,
                            samples_past_12_months,
                            samples_past_60_months,
                            samples_all_time,
                            nwis_or_epa
                           )
         with translated_data as (
                                  select data_source_id,
                                         substring(governmental_unit_code from ''__:#"__#":___'' for ''#'' ) fips_state_code,
                                         substring(governmental_unit_code from ''__:__:#"___#"'' for ''#'' ) fips_county_code,
                                         substring(huc from 1 for 8) huc8,
                                         event_date,
                                         case when event_date >= (now() - interval ''12 month'')::date then 1 else null end is_samples_past_12_months,
                                         case when event_date >= (now() - interval ''60 month'')::date then 1 else null end is_samples_past_60_months
                                    from %I.%I
                                   where substring(governmental_unit_code from ''#"__:__#":___'' for ''#'') between ''US:01'' and ''US:56'' and
                                         event_date between to_date(''01-JAN-1875'', ''DD-MON-YYYY'') and now() and
                                         huc is not null
                                 )
         select data_source_id,
                fips_state_code,
                fips_county_code,
                coalesce(fips_state_code, '''') || coalesce(fips_county_code, '''') fips_state_and_county,
                huc8,
                min(event_date) min_date,
                max(event_date) max_date,
                count(is_samples_past_12_months) samples_past_12_months,
                count(is_samples_past_60_months) samples_past_60_months,
                count(*) samples_all_time,
                %L nwis_or_epa
           from translated_data
          where fips_state_code is not null and
                fips_county_code is not null
              group by data_source_id,
                       fips_state_code,
                       fips_county_code,
                       huc8',
        wqp_schema_name, summary_table_name, wqp_schema_name, activity_sum_table_name, nwis_or_epa);
end
$$
