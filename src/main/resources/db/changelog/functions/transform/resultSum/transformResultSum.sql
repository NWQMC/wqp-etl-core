create or replace function transform_result_sum(wqp_data_source character varying, wqp_schema_name character varying)
returns void
language plpgsql
as $$
declare
    summary_table_name varchar := 'result_sum_swap_' || wqp_data_source;
    result_table_name varchar := 'result_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            data_source,
                            station_id,
                            site_id,
                            event_date,
                            analytical_method,
                            p_code,
                            characteristic_name,
                            characteristic_type,
                            sample_media,
                            organization,
                            site_type,
                            huc,
                            governmental_unit_code,
                            geom,
                            activity_id,
                            project_id,
                            assemblage_sampled_name,
                            sample_tissue_taxonomic_name,
                            result_count)
         select result.data_source_id,
                result.data_source,
                result.station_id,
                result.site_id,
                result.event_date,
                result.analytical_method,
                result.p_code,
                result.characteristic_name,
                result.characteristic_type,
                result.sample_media,
                result.organization,
                result.site_type,
                result.huc,
                result.governmental_unit_code,
                result.geom,
                result.activity_id,
                result.project_id,
                result.assemblage_sampled_name,
                result.sample_tissue_taxonomic_name,
                count(*) result_count
           from %I.%I result
            group by result.data_source_id,
                     result.data_source,
                     result.station_id,
                     result.site_id,
                     result.event_date,
                     result.analytical_method,
                     result.p_code,
                     result.characteristic_name,
                     result.characteristic_type,
                     result.sample_media,
                     result.organization,
                     result.site_type,
                     result.huc,
                     result.governmental_unit_code,
                     result.geom,
                     result.activity_id,
                     result.project_id,
                     result.assemblage_sampled_name,
                     result.sample_tissue_taxonomic_name',
        wqp_schema_name, summary_table_name, wqp_schema_name, result_table_name);
end
$$