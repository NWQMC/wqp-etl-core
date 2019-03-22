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
select create_swap_table ('stewards', 'wqp', 'station_sum');

create table if not exists nwis_ws_star_country
(country_cd character varying (2)
,country_nm character varying (48)
);

create table if not exists wqx_country
(cntry_uid integer
,cntry_cd character varying (2)
,cntry_name character varying (35)
,cntry_last_change_date date
,usr_uid_last_change integer
);

create table if not exists nwis_ws_star_state
(country_cd character varying (2)
,state_cd character varying (2)
,state_nm character varying (53)
);

create table if not exists wqx_state
(st_uid integer
,cntry_uid integer
,st_cd character varying (2)
,st_name character varying (35)
,st_fips_cd integer
);

create table if not exists nwis_ws_star_county
(country_cd character varying (2)
,state_cd character varying (2)
,county_cd character varying (3)
,county_nm character varying (48)
);

create table if not exists wqx_county
(cnty_uid integer
,st_uid integer
,cnty_name character varying (50)
,cnty_fips_cd character varying (3)
);
