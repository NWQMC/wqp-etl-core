create or replace function add_activity_metric_foreign_key_activity(wqp_data_source character varying, wqp_schema_name character varying, base_table_name character varying)
returns void
language plpgsql
as $$
declare
    swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
    foreign_key_name varchar := base_table_name|| '_' || wqp_data_source || '_fk_activity';
    parent_swap_table_name varchar := 'activity_swap_' || wqp_data_source;
begin
    execute format('alter table if exists %I.%I add constraint %I foreign key (data_source_id, station_id, activity_id) references %I.%I (data_source_id, station_id, activity_id)',
                   wqp_schema_name, swap_table_name, foreign_key_name, wqp_schema_name, parent_swap_table_name);
end
$$