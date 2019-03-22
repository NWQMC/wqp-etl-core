create or replace function transform_project_dim(wqp_data_source character varying, schema_name character varying)
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
                        data_source_id,
                        trim(replace(regexp_substr(project_id, ''(.*?)(;|$)'', 1, levels.column_value),'';'','''')) code_value,
                        project_id project_dim_value
                   from %I.%I
                        table(cast(multiset(select level from dual connect by level <= regexp_count(project_id, '';'', 1, ''i'') + 1) as sys.odcinumberlist)) levels
                  where project_id is not null) a',
        schema_name, code_table_name, schema_name, source_table_name);
end
$$