drop table if exists wqp.org_data_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'org_data');
drop table if exists wqp.project_data_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'project_data');
drop table if exists wqp.station_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'station');
drop table if exists wqp.bio_hab_metric_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'bio_hab_metric');
drop table if exists wqp.activity_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'activity');
drop table if exists wqp.act_metric_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'act_metric');
drop table if exists wqp.result_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'result');
drop table if exists wqp.r_detect_qnt_lmt_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'r_detect_qnt_lmt');
drop table if exists wqp.prj_ml_weighting_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'prj_ml_weighting');

drop table if exists wqp.activity_sum_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'activity_sum');
drop table if exists wqp.result_sum_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'result_sum');
drop table if exists wqp.org_grouping_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'org_grouping');
drop table if exists wqp.ml_grouping_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'ml_grouping');
drop table if exists wqp.organization_sum_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'organization_sum');
drop table if exists wqp.station_sum_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'station_sum');
drop table if exists wqp.qwportal_summary_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'qwportal_summary');

drop table if exists wqp.assemblage_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'assemblage');
drop table if exists wqp.char_name_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'char_name');
drop table if exists wqp.char_type_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'char_type');
drop table if exists wqp.country_testsr cascade;
select create_swap_table ('testsrc', 'wqp', 'country');
drop table if exists wqp.county_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'county');
drop table if exists wqp.monitoring_loc_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'monitoring_loc');
drop table if exists wqp.organization_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'organization');
drop table if exists wqp.project_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'project');
drop table if exists wqp.project_dim_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'project_dim');
drop table if exists wqp.sample_media_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'sample_media');
drop table if exists wqp.site_type_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'site_type');
drop table if exists wqp.state_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'state');
drop table if exists wqp.taxa_name_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'taxa_name');

drop table if exists wqp.activity_object_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'activity_object');
drop table if exists wqp.project_object_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'project_object');
drop table if exists wqp.result_object_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'result_object');
drop table if exists wqp.station_object_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'station_object');

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
