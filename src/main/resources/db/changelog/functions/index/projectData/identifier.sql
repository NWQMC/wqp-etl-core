create or replace function build_project_data_swap_identifier_index(wqp_data_source character varying, wqp_schema_name character varying)
returns void
language plpgsql
as $$
declare
	base_table_name varchar := 'project_data';
	swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
	index_name varchar := swap_table_name || '_identifier';
begin
	execute format('create index if not exists %I on %I.%I(project_identifier) with (fillfactor = 100)', index_name, wqp_schema_name, swap_table_name);
end
$$