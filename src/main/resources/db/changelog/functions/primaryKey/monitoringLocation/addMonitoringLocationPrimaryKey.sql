create or replace function add_monitoring_location_primary_key(wqp_data_source character varying, wqp_schema_name character varying, base_table_name character varying)
returns void
language plpgsql
as $$
declare
    swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
    primary_key_name varchar := swap_table_name || '_pk';
begin
    execute format('alter table if exists %I.%I add constraint %I primary key (data_source_id, station_id) with (fillfactor = 100)',
                   wqp_schema_name, swap_table_name, primary_key_name);
end
$$