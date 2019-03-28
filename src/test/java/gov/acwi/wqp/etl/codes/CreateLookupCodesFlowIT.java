package gov.acwi.wqp.etl.codes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.charset.Charset;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;
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
	@Qualifier("createLookupCodesFlow")
	private Flow createLookupCodesFlow;

	@PostConstruct
	public void beforeClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("createLookupCodesFlowTest")
				.start(createLookupCodesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/assemblage/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/characteristicName/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/characteristicType/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/country/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/county/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/monitoringLoc/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/organization/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/project/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/projectDim/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/sampleMedia/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/siteType/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/state/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/taxaName/empty.xml")

	@DatabaseSetup(value="classpath:/testResult/wqp/activitySum/activitySum.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/monitoringLocationSum/monitoringLocationSum.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/organizationSum/organizationSum.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/result.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/resultSum/resultSum.xml")

	@ExpectedDatabase(value="classpath:/testResult/wqp/assemblage/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildAssemblageIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildAssemblageIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/assemblage/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupAssemblageSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupAssemblageSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/characteristicName/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildCharacteristicNameIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildCharacteristicNameIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/characteristicName/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupCharacteristicNameSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupCharacteristicNameSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/characteristicType/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildCharacteristicTypeIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildCharacteristicTypeIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/characteristicType/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupCharacteristicTypeSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupCharacteristicTypeSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/country/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildCountryIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildCountryIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/country/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupCountrySwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupCountrySwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/county/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildCountyIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildCountyIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/county/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupCountySwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupCountySwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/monitoringLoc/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildMonitoringLocIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildMonitoringLocIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/monitoringLoc/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupMonitoringLocSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupMonitoringLocSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/organization/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildOrganizationIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildOrganizationIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/organization/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupOrganizationSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupOrganizationSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/project/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildProjectIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildProjectIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/project/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupProjectSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupProjectSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/projectDim/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildProjectDimIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildProjectDimIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/projectDim/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupProjectDimSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupProjectDimSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/sampleMedia/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildSampleMediaIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildSampleMediaIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/sampleMedia/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupSampleMediaSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupSampleMediaSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/siteType/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildSiteTypeIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildSiteTypeIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/siteType/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupSiteTypeSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupSiteTypeSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/state/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildStateIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildStateIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/state/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupStateSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupStateSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/taxaName/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildTaxaNameIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildTaxaNameIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/taxaName/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupTaxaNameSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
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
