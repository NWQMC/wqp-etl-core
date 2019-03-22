create or replace function transform_monitoring_loc(wqp_data_source character varying, schema_name character varying)
returns void
language plpgsql
as $$
declare
    code_table_name varchar := 'monitoring_loc_swap_' || wqp_data_source;
    source_table_name varchar := 'station_sum_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            code_value,
                            description,
                            organization,
                            text)
         select distinct site_id code_value,
                         station_name description,
                         organization,
                         site_id || '' '' || station_name text
           from %I.%I
          where site_type is not null and
                activity_count > 0',
        schema_name, code_table_name, schema_name, source_table_name);
end
$$