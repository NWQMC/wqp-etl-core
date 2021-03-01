/*
    Drop a constraint from a table if the table and constraint exist.

    Parameters are wrapped in ${} with these names:
    schema_name: Name of the schema the table is in
    table_name: Name of the table to drop the constraint from
    constraint_name: The name of the constraint to drop
 */
alter table if exists ${schema_name}.${table_name} drop constraint if exists ${constraint_name}
