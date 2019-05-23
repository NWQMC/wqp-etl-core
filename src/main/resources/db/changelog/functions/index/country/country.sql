create or replace function build_country_index(wqp_data_source character varying, wqp_schema_name character varying, base_table_name character varying)
returns void
language plpgsql
as $$
declare
    swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
    index_name varchar := swap_table_name || '_country';
begin
    execute format('create index if not exists %I on %I.%I(substring(governmental_unit_code, ''^[^:]+'')) with (fillfactor = 100)', index_name, wqp_schema_name, swap_table_name);
end
$$
