create or replace function add_project_ml_weighting_foreign_key_project_data(wqp_data_source character varying, wqp_schema_name character varying, base_table_name character varying)
returns void
language plpgsql
as $$
declare
    swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
    foreign_key_name varchar := base_table_name|| '_' || wqp_data_source || '_fk_project_data';
    parent_swap_table_name varchar := 'project_data_swap_' || wqp_data_source;
begin
    execute format('alter table if exists %I.%I add constraint %I foreign key (data_source_id, project_id) references %I.%I (data_source_id, project_id)',
                   wqp_schema_name, swap_table_name, foreign_key_name, wqp_schema_name, parent_swap_table_name);
end
$$