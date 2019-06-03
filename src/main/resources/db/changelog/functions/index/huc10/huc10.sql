create or replace function build_huc_10_index(wqp_data_source character varying, wqp_schema_name character varying, base_table_name character varying)
returns void
language plpgsql
as $$
declare
    swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
    index_name varchar := swap_table_name || '_huc_10';
begin
    execute format('create index if not exists %I on %I.%I(substring(huc, ''[0-9]{10}'')) with (fillfactor = 100)', index_name, wqp_schema_name, swap_table_name);
end
$$
