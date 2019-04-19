create or replace function transform_project(wqp_data_source character varying, wqp_schema_name character varying)
returns void
language plpgsql
as $$
declare
    code_table_name varchar := 'project_swap_' || wqp_data_source;
    source_table_name varchar := 'project_dim_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            code_value)
         select distinct data_source_id,
                         code_value
           from %I.%I
          where code_value is not null',
        wqp_schema_name, code_table_name, wqp_schema_name, source_table_name);
end
$$