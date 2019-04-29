package gov.acwi.wqp.etl.dbFinalize.install;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.activity.Activity;
import gov.acwi.wqp.etl.activityMetric.ActivityMetric;
import gov.acwi.wqp.etl.biologicalHabitatMetric.BiologicalHabitatMetric;
import gov.acwi.wqp.etl.codes.assemblage.Assemblage;
import gov.acwi.wqp.etl.codes.characteristicName.CharacteristicName;
import gov.acwi.wqp.etl.codes.characteristicType.CharacteristicType;
import gov.acwi.wqp.etl.codes.country.Country;
import gov.acwi.wqp.etl.codes.county.County;
import gov.acwi.wqp.etl.codes.monitoringLoc.MonitoringLoc;
import gov.acwi.wqp.etl.codes.organization.Organization;
import gov.acwi.wqp.etl.codes.project.Project;
import gov.acwi.wqp.etl.codes.projectDim.ProjectDim;
import gov.acwi.wqp.etl.codes.sampleMedia.SampleMedia;
import gov.acwi.wqp.etl.codes.siteType.SiteType;
import gov.acwi.wqp.etl.codes.state.State;
import gov.acwi.wqp.etl.codes.taxaName.TaxaName;
import gov.acwi.wqp.etl.monitoringLocation.MonitoringLocation;
import gov.acwi.wqp.etl.orgData.OrgData;
import gov.acwi.wqp.etl.projectData.ProjectData;
import gov.acwi.wqp.etl.projectMLWeighting.ProjectMLWeighting;
import gov.acwi.wqp.etl.resDetectQntLimit.ResDetectQntLimit;
import gov.acwi.wqp.etl.result.Result;
import gov.acwi.wqp.etl.summaries.activitySum.ActivitySum;
import gov.acwi.wqp.etl.summaries.mlGrouping.MlGrouping;
import gov.acwi.wqp.etl.summaries.monitoringLocationSum.MonitoringLocationSum;
import gov.acwi.wqp.etl.summaries.orgGrouping.OrgGrouping;
import gov.acwi.wqp.etl.summaries.organizationSum.OrganizationSum;
import gov.acwi.wqp.etl.summaries.qwportalSummary.QwportalSummary;
import gov.acwi.wqp.etl.summaries.resultSum.ResultSum;

@Component
@StepScope
public class Install implements Tasklet {

	private final JdbcTemplate jdbcTemplate;
	private final String wqpDataSource;
	private final String wqpSchemaName;
	private final Integer wqpDataSourceId;
	private final ParameterizedPreparedStatementSetter<InstallTable> ppss;

	@Autowired
	public Install(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE_ID) Integer wqpDataSourceId,
			ParameterizedPreparedStatementSetter<InstallTable> ppss) {
		this.jdbcTemplate = jdbcTemplate;
		this.wqpDataSource = wqpDataSource;
		this.wqpSchemaName = wqpSchemaName;
		this.wqpDataSourceId = wqpDataSourceId;
		this.ppss = ppss;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		String sql = "select * from install_new_data(?,?,?,?)";
		Collection<InstallTable> batchArgs = getInstallTables();
		int batchSize = batchArgs.size();
		jdbcTemplate.batchUpdate(sql, batchArgs, batchSize, ppss);
		return RepeatStatus.FINISHED;
	}

	private Collection<InstallTable> getInstallTables() {
		Collection<InstallTable> installTables = new LinkedList<>();
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, OrgData.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, ProjectData.BASE_TABLE_NAME, wqpDataSourceId));
//TODO - WQP-1442		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, ProjectObject.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, MonitoringLocation.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, BiologicalHabitatMetric.BASE_TABLE_NAME, wqpDataSourceId));
//TODO - WQP-1442		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, MonitoringLocationObject.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, Activity.BASE_TABLE_NAME, wqpDataSourceId));
//TODO - WQP-1442		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, ActivityObject.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, ActivityMetric.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, Result.BASE_TABLE_NAME, wqpDataSourceId));
//TODO - WQP-1442		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, ResultObject.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, ResDetectQntLimit.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, ProjectMLWeighting.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, ActivitySum.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, ResultSum.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, OrgGrouping.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, MlGrouping.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, OrganizationSum.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, MonitoringLocationSum.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, QwportalSummary.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, Assemblage.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, CharacteristicName.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, CharacteristicType.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, Country.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, County.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, MonitoringLoc.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, Organization.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, ProjectDim.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, Project.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, SampleMedia.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, SiteType.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, State.BASE_TABLE_NAME, wqpDataSourceId));
		installTables.add(new InstallTable(wqpDataSource, wqpSchemaName, TaxaName.BASE_TABLE_NAME, wqpDataSourceId));
		return installTables;
	}

}
