create or replace function install_new_data(wqp_data_source character varying, schema_name character varying, base_table_name character varying, data_source_id integer)
returns void
language plpgsql
as $$
declare
	old_partition_name varchar := base_table_name || '_' || wqp_data_source || '_old';
	swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
	partition_name varchar := base_table_name || '_' || wqp_data_source;
	indexes record;
begin
	execute format('drop table if exists %I.%I', schema_name, old_partition_name);

	execute format('alter table if exists %I.%I detach partition %I.%I', schema_name, base_table_name, schema_name, partition_name);

	execute format('alter table if exists %I.%I attach partition %I.%I for values in (%L)', schema_name, base_table_name, schema_name, swap_table_name, data_source_id);

	execute format('alter table if exists %I.%I rename to %I', schema_name, partition_name, old_partition_name);

	execute format('alter table if exists %I.%I rename to %I', schema_name, swap_table_name, partition_name);

	for indexes in execute format('select indexname from pg_indexes where tablename = %L', old_partition_name) loop
		execute format('alter index if exists %I rename to %I', indexes.indexname, indexes.indexname || '_old');
	end loop;

	for indexes in execute format('select indexname from pg_indexes where tablename = %L', partition_name) loop
		execute format('alter index if exists %I rename to %I', indexes.indexname, replace(indexes.indexname, '_swap', ''));
	end loop;
end
$$