/*
    Create a new partitioned table (a table which has partitions) 'like' an existing table.  It is an error if the table already exists.

    Parameters are wrapped in ${} with these names:
    schema_name: Name of the schema to create the table in - required
    table_name: Name of the table to create - required
    like_table_name: The table should be created 'like' this table, including comments, default and identity - required
    partition_column:  The column to do a range partition on - required
 */
create table ${schema_name}.${table_name} (
    like ${schema_name}.${like_table_name} including comments including defaults including identity including constraints
) PARTITION BY RANGE (${partition_column})
