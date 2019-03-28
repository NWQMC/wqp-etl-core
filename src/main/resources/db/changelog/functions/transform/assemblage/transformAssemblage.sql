create or replace function transform_assemblage(wqp_data_source character varying, schema_name character varying)
returns void
language plpgsql
as $$
declare
    code_table_name varchar := 'assemblage_swap_' || wqp_data_source;
    source_table_name varchar := 'result_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            code_value)
         select distinct data_source_id,
                         assemblage_sampled_name code_value
           from %I.%I
          where assemblage_sampled_name is not null',
        schema_name, code_table_name, schema_name, source_table_name);
end
$$