package gov.acwi.wqp.etl.codes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.codes.assemblage.index.BuildAssemblageIndexesFlowIT;
import gov.acwi.wqp.etl.codes.assemblage.table.SetupAssemblageSwapTableFlowIT;
import gov.acwi.wqp.etl.codes.characteristicName.index.BuildCharacteristicNameIndexesFlowIT;
import gov.acwi.wqp.etl.codes.characteristicName.table.SetupCharacteristicNameSwapTableFlowIT;
import gov.acwi.wqp.etl.codes.characteristicType.index.BuildCharacteristicTypeIndexesFlowIT;
import gov.acwi.wqp.etl.codes.characteristicType.table.SetupCharacteristicTypeSwapTableFlowIT;
import gov.acwi.wqp.etl.codes.country.index.BuildCountryIndexesFlowIT;
import gov.acwi.wqp.etl.codes.country.table.SetupCountrySwapTableFlowIT;
import gov.acwi.wqp.etl.codes.county.index.BuildCountyIndexesFlowIT;
import gov.acwi.wqp.etl.codes.county.table.SetupCountySwapTableFlowIT;
import gov.acwi.wqp.etl.codes.monitoringLoc.index.BuildMonitoringLocIndexesFlowIT;
import gov.acwi.wqp.etl.codes.monitoringLoc.table.SetupMonitoringLocSwapTableFlowIT;
import gov.acwi.wqp.etl.codes.organization.index.BuildOrganizationIndexesFlowIT;
import gov.acwi.wqp.etl.codes.organization.table.SetupOrganizationSwapTableFlowIT;
import gov.acwi.wqp.etl.codes.project.index.BuildProjectIndexesFlowIT;
import gov.acwi.wqp.etl.codes.project.table.SetupProjectSwapTableFlowIT;
import gov.acwi.wqp.etl.codes.projectDim.index.BuildProjectDimIndexesFlowIT;
import gov.acwi.wqp.etl.codes.projectDim.table.SetupProjectDimSwapTableFlowIT;
import gov.acwi.wqp.etl.codes.sampleMedia.index.BuildSampleMediaIndexesFlowIT;
import gov.acwi.wqp.etl.codes.sampleMedia.table.SetupSampleMediaSwapTableFlowIT;
import gov.acwi.wqp.etl.codes.siteType.index.BuildSiteTypeIndexesFlowIT;
import gov.acwi.wqp.etl.codes.siteType.table.SetupSiteTypeSwapTableFlowIT;
import gov.acwi.wqp.etl.codes.state.index.BuildStateIndexesFlowIT;
import gov.acwi.wqp.etl.codes.state.table.SetupStateSwapTableFlowIT;
import gov.acwi.wqp.etl.codes.taxaName.index.BuildTaxaNameIndexesFlowIT;
import gov.acwi.wqp.etl.codes.taxaName.table.SetupTaxaNameSwapTableFlowIT;

public class CreateLookupCodesFlowIT extends BaseFlowIT {

	@Autowired
	@Qualifier(EtlConstantUtils.CREATE_LOOKUP_CODES_FLOW)
	private Flow createLookupCodesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("createLookupCodesFlowTest")
				.start(createLookupCodesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/activitySum/activitySum.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/monitoringLocationSum/monitoringLocationSum.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/organizationSum/organizationSum.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/csv/")
	@DatabaseSetup(value="classpath:/testResult/wqp/resultSum/csv/")

	@ExpectedDatabase(value="classpath:/testResult/wqp/assemblage/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildAssemblageIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/assemblage/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupAssemblageSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/characteristicName/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildCharacteristicNameIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/characteristicName/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupCharacteristicNameSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/characteristicType/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildCharacteristicTypeIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/characteristicType/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupCharacteristicTypeSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/country/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildCountryIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/country/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupCountrySwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/county/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildCountyIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/county/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupCountySwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/monitoringLoc/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildMonitoringLocIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/monitoringLoc/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupMonitoringLocSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/organization/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildOrganizationIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/organization/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupOrganizationSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/project/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildProjectIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/project/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupProjectSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/projectDim/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildProjectDimIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/projectDim/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupProjectDimSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/sampleMedia/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildSampleMediaIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/sampleMedia/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupSampleMediaSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/siteType/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildSiteTypeIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/siteType/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupSiteTypeSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/state/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildStateIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/state/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupStateSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/taxaName/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildTaxaNameIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/taxaName/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupTaxaNameSwapTableFlowIT.EXPECTED_DATABASE_QUERY)

	@ExpectedDatabase(value="classpath:/testResult/wqp/assemblage/assemblage.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/characteristicName/characteristicName.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/characteristicType/characteristicType.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/country/country.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/county/county.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/monitoringLoc/monitoringLoc.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/organization/organization.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/project/project.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/projectDim/projectDim.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/sampleMedia/sampleMedia.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/siteType/siteType.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/state/state.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/taxaName/taxaName.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void createLookupCodesFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
