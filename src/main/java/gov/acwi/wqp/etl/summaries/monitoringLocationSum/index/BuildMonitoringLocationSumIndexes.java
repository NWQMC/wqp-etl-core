package gov.acwi.wqp.etl.summaries.monitoringLocationSum.index;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuildMonitoringLocationSumIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildMonitoringLocationSumCountryIndex")
	private Tasklet buildMonitoringLocationSumCountryIndex;

	@Autowired
	@Qualifier("buildMonitoringLocationSumCountyIndex")
	private Tasklet buildMonitoringLocationSumCountyIndex;

	@Autowired
	@Qualifier("buildMonitoringLocationSumGeomIndex")
	private Tasklet buildMonitoringLocationSumGeomIndex;

	@Autowired
	@Qualifier("buildMonitoringLocationSumGeom2163Index")
	private Tasklet buildMonitoringLocationSumGeom2163Index;

	@Autowired
	@Qualifier("buildMonitoringLocationSumHuc10Index")
	private Tasklet buildMonitoringLocationSumHuc10Index;

	@Autowired
	@Qualifier("buildMonitoringLocationSumHuc12Index")
	private Tasklet buildMonitoringLocationSumHuc12Index;

	@Autowired
	@Qualifier("buildMonitoringLocationSumHuc2Index")
	private Tasklet buildMonitoringLocationSumHuc2Index;

	@Autowired
	@Qualifier("buildMonitoringLocationSumHuc4Index")
	private Tasklet buildMonitoringLocationSumHuc4Index;

	@Autowired
	@Qualifier("buildMonitoringLocationSumHuc6Index")
	private Tasklet buildMonitoringLocationSumHuc6Index;

	@Autowired
	@Qualifier("buildMonitoringLocationSumHuc8Index")
	private Tasklet buildMonitoringLocationSumHuc8Index;

	@Autowired
	@Qualifier("buildMonitoringLocationSumOrganizationIndex")
	private Tasklet buildMonitoringLocationSumOrganizationIndex;

	@Autowired
	@Qualifier("buildMonitoringLocationSumSiteIdIndex")
	private Tasklet buildMonitoringLocationSumSiteIdIndex;

	@Autowired
	@Qualifier("buildMonitoringLocationSumSiteTypeIndex")
	private Tasklet buildMonitoringLocationSumSiteTypeIndex;

	@Autowired
	@Qualifier("buildMonitoringLocationSumStateIndex")
	private Tasklet buildMonitoringLocationSumStateIndex;

	@Autowired
	@Qualifier("buildMonitoringLocationSumStationIdIndex")
	private Tasklet buildMonitoringLocationSumStationIdIndex;


	@Bean
	public Step buildMonitoringLocationSumCountryIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumCountryIndexStep")
				.tasklet(buildMonitoringLocationSumCountryIndex)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumCountyIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumCountyIndexStep")
				.tasklet(buildMonitoringLocationSumCountyIndex)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumGeomIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumGeomIndexStep")
				.tasklet(buildMonitoringLocationSumGeomIndex)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumGeom2163IndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumGeom2163IndexStep")
				.tasklet(buildMonitoringLocationSumGeom2163Index)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumHuc10IndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumHuc10IndexStep")
				.tasklet(buildMonitoringLocationSumHuc10Index)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumHuc12IndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumHuc12IndexStep")
				.tasklet(buildMonitoringLocationSumHuc12Index)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumHuc2IndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumHuc2IndexStep")
				.tasklet(buildMonitoringLocationSumHuc2Index)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumHuc4IndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumHuc4IndexStep")
				.tasklet(buildMonitoringLocationSumHuc4Index)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumHuc6IndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumHuc6IndexStep")
				.tasklet(buildMonitoringLocationSumHuc6Index)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumHuc8IndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumHuc8IndexStep")
				.tasklet(buildMonitoringLocationSumHuc8Index)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumOrganizationIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumOrganizationIndexStep")
				.tasklet(buildMonitoringLocationSumOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumSiteIdIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumSiteIdIndexStep")
				.tasklet(buildMonitoringLocationSumSiteIdIndex)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumSiteTypeIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumSiteTypeIndexStep")
				.tasklet(buildMonitoringLocationSumSiteTypeIndex)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumStateIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumStateIndexStep")
				.tasklet(buildMonitoringLocationSumStateIndex)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationSumStationIdIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumStationIdIndexStep")
				.tasklet(buildMonitoringLocationSumStationIdIndex)
				.build();
	}


	@Bean
	public Flow buildMonitoringLocationSumIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildMonitoringLocationSumIndexesFlow")
				.start(buildMonitoringLocationSumCountryIndexStep())
				.next(buildMonitoringLocationSumCountyIndexStep())
				.next(buildMonitoringLocationSumGeomIndexStep())
				.next(buildMonitoringLocationSumGeom2163IndexStep())
				.next(buildMonitoringLocationSumHuc10IndexStep())
				.next(buildMonitoringLocationSumHuc12IndexStep())
				.next(buildMonitoringLocationSumHuc2IndexStep())
				.next(buildMonitoringLocationSumHuc4IndexStep())
				.next(buildMonitoringLocationSumHuc6IndexStep())
				.next(buildMonitoringLocationSumHuc8IndexStep())
				.next(buildMonitoringLocationSumOrganizationIndexStep())
				.next(buildMonitoringLocationSumSiteIdIndexStep())
				.next(buildMonitoringLocationSumSiteTypeIndexStep())
				.next(buildMonitoringLocationSumStateIndexStep())
				.next(buildMonitoringLocationSumStationIdIndexStep())
				.build();
	}

}
