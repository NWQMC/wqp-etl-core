create or replace function install_new_data(wqp_data_source character varying, wqp_schema_name character varying, base_table_name character varying, data_source_id integer)
returns void
language plpgsql
as $$
declare
    old_partition_name varchar := base_table_name || '_' || wqp_data_source || '_old';
    swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
    partition_name varchar := base_table_name || '_' || wqp_data_source;
    partition_count int;
    indexes record;
begin

    /* Attempt to drop the old partition, just in case it is hanging around from a previous ETL. */
    execute format('drop table if exists %I.%I', wqp_schema_name, old_partition_name);

    /* Add a check constraint to the new data conforms to the partitioning - This should prevent a validation during the ATTATCH Partion from taking an ACCESS EXCLUSIVE lock on the base_table_name. */
    execute format('alter table %I.%I add constraint check_partition check (data_source_id = %L)', wqp_schema_name, swap_table_name, data_source_id);

    /* Check if there is a matching partition on the table, if so, DETACH it. */
    execute format('select count(*) from information_schema.tables where table_schema = %L and table_name = %L', wqp_schema_name, partition_name) into partition_count;
    if partition_count > 0 then
        execute format('alter table if exists %I.%I detach partition %I.%I', wqp_schema_name, base_table_name, wqp_schema_name, partition_name);
    end if;

    /* ATTACH the new partition to the table. */
    execute format('alter table if exists %I.%I attach partition %I.%I for values in (%L)', wqp_schema_name, base_table_name, wqp_schema_name, swap_table_name, data_source_id);

    /* Rename the detached partition to old, */
    execute format('alter table if exists %I.%I rename to %I', wqp_schema_name, partition_name, old_partition_name);

    /* So we can rename the attached partion to reflect the current state. */
    execute format('alter table if exists %I.%I rename to %I', wqp_schema_name, swap_table_name, partition_name);

    /* Same with the indexes - change the names on the detached partition to old, *. */
    for indexes in execute format('select indexname from pg_indexes where tablename = %L', old_partition_name) loop
        execute format('alter index if exists %I rename to %I', indexes.indexname, indexes.indexname || '_old');
    end loop;

    /* So we can rename the indexes of the attached partition to reflect the currect state. */
    for indexes in execute format('select indexname from pg_indexes where tablename = %L', partition_name) loop
        execute format('alter index if exists %I rename to %I', indexes.indexname, replace(indexes.indexname, '_swap', ''));
    end loop;

    /* Drop the constraint as it is no longer needed once the partition has been attached. */
    execute format('alter table %I.%I drop constraint if exists check_partition', wqp_schema_name, partition_name);

end
$$