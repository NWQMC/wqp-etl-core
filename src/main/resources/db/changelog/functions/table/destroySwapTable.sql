create or replace function destroy_swap_table(wqp_data_source character varying, wqp_schema_name character varying, base_table_name character varying)
returns void
language plpgsql
as $$
declare
    swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
begin
    execute format('drop table if exists %I.%I cascade', wqp_schema_name, swap_table_name);
end
$$