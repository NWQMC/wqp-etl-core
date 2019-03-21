create or replace function build_station_id_index(wqp_data_source character varying, schema_name character varying, base_table_name character varying)
returns void
language plpgsql
as $$
declare
	swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
	index_name varchar := swap_table_name || '_station_id';
begin
	execute format('create index if not exists %I on %I.%I(station_id) with (fillfactor = 100)', index_name, schema_name, swap_table_name);
end
$$