create or replace function transform_project_dim(wqp_data_source character varying, wqp_schema_name character varying)
returns void
language plpgsql
as $$
declare
    code_table_name varchar := 'project_dim_swap_' || wqp_data_source;
    source_table_name varchar := 'activity_sum_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            code_value,
                            project_dim_value)
         select data_source_id,
                code_value,
                project_dim_value
           from (select distinct
                        a.data_source_id,
                        trim(b.split_project_id) code_value,
                        a.project_id project_dim_value
                   from %I.%I a,
                        unnest(string_to_array(a.project_id, '';'')) b(split_project_id)
                  where project_id is not null) a',
        wqp_schema_name, code_table_name, wqp_schema_name, source_table_name);
end
$$
