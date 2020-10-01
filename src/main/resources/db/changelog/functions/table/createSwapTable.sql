create or replace function create_swap_table(wqp_data_source character varying, wqp_schema_name character varying, base_table_name character varying)
returns void
language plpgsql
as $$
declare
    swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
begin
    execute format('create table if not exists %I.%I
                    (like %I.%I including comments including defaults including identity including constraints) with (fillfactor = 100)',
        wqp_schema_name, swap_table_name, wqp_schema_name, base_table_name);
end
$$