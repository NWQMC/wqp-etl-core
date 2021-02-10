/*
    Create a new table 'like' an existing table if it does not already exist.  It is an error if the table already exists.

    Parameters are wrapped in ${} with these names:
    schema_name: Name of the schema to create the table in - required
    table_name: Name of the table to create - required
    like_table_name: The table should be created 'like' this table, including comments, default and identity - required
 */
create table ${schema_name}.${table_name}
    (like ${schema_name}.${like_table_name} including comments including defaults including identity) with (fillfactor = 100)
