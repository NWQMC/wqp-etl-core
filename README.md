# wqp-etl-core

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/970e9b1661e34fe2917af21d121ca58e)](https://app.codacy.com/app/usgs_wma_dev/wqp-etl-core?utm_source=github.com&utm_medium=referral&utm_content=NWQMC/wqp-etl-core&utm_campaign=Badge_Grade_Dashboard)

[![Build Status](https://travis-ci.org/NWQMC/wqp-etl-core.svg?branch=master)](https://travis-ci.org/NWQMC/wqp-etl-core)

Core functionality for the Water Quality Portal ETL processes.

## Development
This is a Spring Batch/Boot project. All of the normal caveats relating to a Spring Batch/Boot application apply.

### Dependencies
This application utilizes a PostgreSQL database. The Docker Hub image usgswma/wqp_db:etl can be used for testing.

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
CONTEXTS: external,ci,schemaLoad

```

#### Environment variable definitions
* **WQP_DATABASE_ADDRESS** - Host name or IP address of the PostgreSQL database.
* **WQP_DATABASE_PORT** - Port the PostgreSQL Database is listening on.
* **WQP_DATABASE_NAME** - Name of the PostgreSQL database containing the schema.
* **WQP_SCHEMA_NAME** - Name of the schema holding database objects.
* **WQP_SCHEMA_OWNER_USERNAME** - Role which owns the database objects.
* **WQP_SCHEMA_OWNER_PASSWORD** - Password for the **WQP_SCHEMA_OWNER_USERNAME** role.

* **NWIS_DATABASE_ADDRESS** - Host name or IP address of the PostgreSQL database.
* **NWIS_DATABASE_PORT** - Port the PostgreSQL Database is listening on.
* **NWIS_DATABASE_NAME** - Name of the PostgreSQL database containing the schema.
* **NWIS_SCHEMA_OWNER_USERNAME** - Role which owns the **NWIS_SCHEMA_NAME** database objects.
* **NWIS_SCHEMA_OWNER_PASSWORD** - Password for the **NWIS_SCHEMA_OWNER_USERNAME** role.

* **ETL_OWNER_USERNAME** - Role which owns the source schema database objects.
* **GEO_SCHEMA_NAME** - Name of the schema holding geospatial lookup database objects.
* **ETL_DATA_SOURCE_ID** - Database ID of the data_source (data_source_id from the **WQP_SCHEMA_NAME**.data_source table).
* **ETL_DATA_SOURCE** - Data Source name (text from the **WQP_SCHEMA_NAME**.data_source table).
* **QWPORTAL_SUMMARY_ETL** - Does the ETL populate the qwportal_summary table? true or false.
* **NWIS_OR_EPA** - If **QWPORTAL_SUMMARY_ETL** is true, is this an NIWS (N) or STORET WQX (E) ETL.
* **CONTEXTS** - Only used when running the Docker database. Should always be external,ci,schemaLoad.

### Testing
This project contains JUnit tests. Maven can be used to run them (in addition to the capabilities of your IDE).

To run the unit tests of the application use:

```
mvn package
```

To additionally start up a Docker database and run the integration tests of the application use:

```
mvn verify
```
