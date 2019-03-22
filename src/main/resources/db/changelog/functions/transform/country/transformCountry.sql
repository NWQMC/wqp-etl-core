create or replace function transform_country(wqp_data_source character varying, schema_name character varying)
returns void
language plpgsql
as $$
declare
    code_table_name varchar := 'country_swap_' || wqp_data_source;
    source_table_name varchar := 'station_sum_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            code_value,
                            description)
         select distinct data_source_id,
                         country_code code_value,
                         country_name description
           from %I.%I
          where country_code is not null',
        schema_name, code_table_name, schema_name, source_table_name);
end
$$