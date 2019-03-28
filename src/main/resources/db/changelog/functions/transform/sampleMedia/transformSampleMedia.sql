create or replace function transform_sample_media(wqp_data_source character varying, schema_name character varying)
returns void
language plpgsql
as $$
declare
    code_table_name varchar := 'sample_media_swap_' || wqp_data_source;
    source_table_name varchar := 'activity_sum_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            code_value)
         select distinct data_source_id,
                         sample_media code_value
           from %I.%I
          where sample_media is not null',
        schema_name, code_table_name, schema_name, source_table_name);
end
$$