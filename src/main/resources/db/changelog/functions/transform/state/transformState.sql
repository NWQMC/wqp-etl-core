create or replace function transform_state(wqp_data_source character varying, schema_name character varying)
returns void
language plpgsql
as $$
declare
    code_table_name varchar := 'state_swap_' || wqp_data_source;
    source_table_name varchar := 'station_sum_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            code_value,
                            description_with_country,
                            description_with_out_country)
         select distinct data_source_id,
                         state_code code_value,
                         country_code || '', '' || state_name description_with_country,
                         state_name description_with_out_country
           from %I.%I
          where state_code is not null',
        schema_name, code_table_name, schema_name, source_table_name);
end
$$