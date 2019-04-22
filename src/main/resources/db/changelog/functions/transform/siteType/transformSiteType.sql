create or replace function transform_site_type(wqp_data_source character varying, wqp_schema_name character varying)
returns void
language plpgsql
as $$
declare
    code_table_name varchar := 'site_type_swap_' || wqp_data_source;
    source_table_name varchar := 'station_sum_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            code_value)
         select distinct data_source_id,
                         site_type code_value
           from %I.%I
          where site_type is not null',
        wqp_schema_name, code_table_name, wqp_schema_name, source_table_name);
end
$$