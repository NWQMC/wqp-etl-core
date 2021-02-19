/*
    Creates basic index if it does not already exist.

    Parameters are wrapped in ${} with these names:
    index_name:  Name of the index to create (caller is responsible for ensuring the name is unique)
    schema_name: Name of the schema the table is in
    table_name: Name of the table on which the index is created
    column_name: Name of the column to create the index on
    fill_factor: Integer number for the index fill factor.  Typically 100 for reporting tables (see PG docs)
 */
create index if not exists ${index_name} on ${schema_name}.${table_name}(${column_name}) with (fillfactor = ${fill_factor})
