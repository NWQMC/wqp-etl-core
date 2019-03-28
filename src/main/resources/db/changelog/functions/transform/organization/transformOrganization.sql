create or replace function transform_organization(wqp_data_source character varying, schema_name character varying)
returns void
language plpgsql
as $$
declare
    code_table_name varchar := 'organization_swap_' || wqp_data_source;
    source_table_name varchar := 'organization_sum_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            code_value,
                            description)
         select distinct data_source_id,
                         organization code_value,
                         organization_name description
           from %I.%I
          where organization is not null',
        schema_name, code_table_name, schema_name, source_table_name);
end
$$