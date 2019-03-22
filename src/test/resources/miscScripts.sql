select '    <wqx_country cntry_uid="' || cntry_uid || '" cntry_cd="' || cntry_cd || '" cntry_name="' || cntry_name || '" cntry_last_change_date="' || cntry_last_change_date || '" usr_uid_last_change="' || usr_uid_last_change || '" />'
  from country
    order by cntry_uid;

select '    <wqx_state st_uid="' || st_uid || '" cntry_uid="' || cntry_uid || '" st_cd="' || st_cd || '" st_name="' || st_name || '" st_fips_cd="' || st_fips_cd || '" />'
  from state
  where cntry_uid = 1
    order by st_uid;

select '    <wqx_county cnty_uid="' || cnty_uid || '" st_uid="' || st_uid || '" cnty_name="' || cnty_name || '" cnty_fips_cd="' || cnty_fips_cd || '" />'
  from county
  where st_uid = 15
    order by cnty_uid;

select '    <nwis_ws_star_state country_cd="' || country_cd || '" state_cd="' || state_cd || '" state_nm="' || state_nm || '" />'
  from state
  where country_cd = 'US'
    order by country_cd, state_cd;

select '    <nwis_ws_star_county country_cd="' || country_cd || '" state_cd="' || state_cd || '" county_cd="' || county_cd || '" county_nm="' || county_nm || '" />'
  from county
  where state_cd = '18'
    order by country_cd, state_cd;
