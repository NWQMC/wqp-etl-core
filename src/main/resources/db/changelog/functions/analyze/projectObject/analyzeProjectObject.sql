create or replace function analyze_project_object(wqp_data_source character varying, wqp_schema_name character varying, base_table_name character varying)
returns void
language plpgsql
as $$
declare
    swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
begin
    execute format('analyze %I.%I (data_source_id, data_source, object_id, project_identifier, organization)', wqp_schema_name, swap_table_name);
end
$$