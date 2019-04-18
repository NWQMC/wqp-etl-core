select create_swap_table ('stewards', 'wqp', 'org_data');
select create_swap_table ('stewards', 'wqp', 'project_data');
select create_swap_table ('stewards', 'wqp', 'station');
select create_swap_table ('stewards', 'wqp', 'activity');
select create_swap_table ('stewards', 'wqp', 'act_metric');
select create_swap_table ('stewards', 'wqp', 'result');
select create_swap_table ('stewards', 'wqp', 'r_detect_qnt_lmt');

select create_swap_table ('stewards', 'wqp', 'activity_sum');
select create_swap_table ('stewards', 'wqp', 'result_sum');
select create_swap_table ('stewards', 'wqp', 'org_grouping');
select create_swap_table ('stewards', 'wqp', 'ml_grouping');
select create_swap_table ('stewards', 'wqp', 'organization_sum');
select create_swap_table ('stewards', 'wqp', 'station_sum');

select create_swap_table ('stewards', 'wqp', 'assemblage');
select create_swap_table ('stewards', 'wqp', 'char_name');
select create_swap_table ('stewards', 'wqp', 'char_type');
select create_swap_table ('stewards', 'wqp', 'country');
select create_swap_table ('stewards', 'wqp', 'county');
select create_swap_table ('stewards', 'wqp', 'monitoring_loc');
select create_swap_table ('stewards', 'wqp', 'organization');
select create_swap_table ('stewards', 'wqp', 'project');
select create_swap_table ('stewards', 'wqp', 'project_dim');
select create_swap_table ('stewards', 'wqp', 'sample_media');
select create_swap_table ('stewards', 'wqp', 'site_type');
select create_swap_table ('stewards', 'wqp', 'state');
select create_swap_table ('stewards', 'wqp', 'taxa_name');

create table if not exists wqx_country
(cntry_uid integer
,cntry_cd character varying (2)
,cntry_name character varying (35)
,cntry_last_change_date date
,usr_uid_last_change integer
);

create table if not exists wqx_state
(st_uid integer
,cntry_uid integer
,st_cd character varying (2)
,st_name character varying (35)
,st_fips_cd integer
);

create table if not exists wqx_county
(cnty_uid integer
,st_uid integer
,cnty_name character varying (50)
,cnty_fips_cd character varying (3)
);
