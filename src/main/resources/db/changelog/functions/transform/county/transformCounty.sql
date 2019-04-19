create or replace function transform_county(wqp_data_source character varying, wqp_schema_name character varying)
returns void
language plpgsql
as $$
declare
    code_table_name varchar := 'county_swap_' || wqp_data_source;
    source_table_name varchar := 'station_sum_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            code_value,
                            description,
                            description_wo_country_state)
         select distinct data_source_id,
                         substring(governmental_unit_code, ''^[^:]+:[^:]+:[^:]+$'') code_value,
                         substring(governmental_unit_code, ''^[^:]+'') || '', '' || state_name || '', '' || county_name description,
                         county_name description_wo_country_state
           from %I.%I
          where substring(governmental_unit_code, ''^[^:]+:[^:]+:[^:]+$'') is not null',
        wqp_schema_name, code_table_name, wqp_schema_name, source_table_name);
end
$$