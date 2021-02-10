/*
    Sample chunk of SQL to use as a template for a test
 */
create table ${wqp_schema_name}.${table_name}
    (like ${wqp_schema_name}.${like_table_name} including comments) with (fillfactor = 100)
