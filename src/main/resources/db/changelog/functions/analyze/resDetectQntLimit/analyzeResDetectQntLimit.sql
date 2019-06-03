create or replace function analyze_res_detect_qnt_limit(wqp_data_source character varying, wqp_schema_name character varying, base_table_name character varying)
returns void
language plpgsql
as $$
declare
    swap_table_name varchar := base_table_name || '_swap_' || wqp_data_source;
begin
    execute format('analyze %I.%I (data_source_id, data_source, station_id, site_id, event_date, analytical_method, p_code, activity, characteristic_name, characteristic_type, sample_media, organization, site_type, huc, governmental_unit_code, geom, activity_id, project_id, result_id, assemblage_sampled_name, sample_tissue_taxonomic_name)', wqp_schema_name, swap_table_name);
end
$$