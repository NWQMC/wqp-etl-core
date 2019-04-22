# wqp-etl-core

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/970e9b1661e34fe2917af21d121ca58e)](https://app.codacy.com/app/usgs_wma_dev/wqp-etl-core?utm_source=github.com&utm_medium=referral&utm_content=NWQMC/wqp-etl-core&utm_campaign=Badge_Grade_Dashboard)

[![Build Status](https://travis-ci.org/NWQMC/wqp-etl-core.svg?branch=master)](https://travis-ci.org/NWQMC/wqp-etl-core)

### Environment variables
Create an application.yml file in the project directory containing the following (shown are example values - they should match the values you used in creating the etlDB):

```
WQP_DATABASE_ADDRESS: <localhost>
WQP_DATABASE_PORT: <5437>
WQP_DATABASE_NAME: <wqp_db>
WQP_SCHEMA_NAME: <wqp>
WQP_SCHEMA_OWNER_USERNAME: <wqp_core>
WQP_SCHEMA_OWNER_PASSWORD: <changeMe>

NWIS_DATABASE_ADDRESS: <localhost>
NWIS_DATABASE_PORT: <5437>
NWIS_DATABASE_NAME: <wqp_db>
NWIS_SCHEMA_OWNER_USERNAME: <nwis_ws_star>
NWIS_SCHEMA_OWNER_PASSWORD: <changeMe>

ETL_OWNER_USERNAME: <wqp_core>
GEO_SCHEMA_NAME: <nwis>
ETL_DATA_SOURCE_ID: <0>
ETL_DATA_SOURCE: <TESTSRC>
QWPORTAL_SUMMARY_ETL: <true>
NWIS_OR_EPA: <E>

```

#### Environment variable definitions
