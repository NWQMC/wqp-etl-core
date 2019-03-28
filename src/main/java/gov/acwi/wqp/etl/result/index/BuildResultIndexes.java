package gov.acwi.wqp.etl.result.index;

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
public class BuildResultIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

//	@Autowired
//	@Qualifier("buildResultCountryIndex")
//	private Tasklet buildResultCountryIndex;
//
//	@Autowired
//	@Qualifier("buildResultCountyIndex")
//	private Tasklet buildResultCountyIndex;
//
//	@Autowired
//	@Qualifier("buildResultGeomIndex")
//	private Tasklet buildResultGeomIndex;
//
//	@Autowired
//	@Qualifier("buildResultHuc10Index")
//	private Tasklet buildResultHuc10Index;
//
//	@Autowired
//	@Qualifier("buildResultHuc12Index")
//	private Tasklet buildResultHuc12Index;
//
//	@Autowired
//	@Qualifier("buildResultHuc2Index")
//	private Tasklet buildResultHuc2Index;
//
//	@Autowired
//	@Qualifier("buildResultHuc4Index")
//	private Tasklet buildResultHuc4Index;
//
//	@Autowired
//	@Qualifier("buildResultHuc6Index")
//	private Tasklet buildResultHuc6Index;
//
//	@Autowired
//	@Qualifier("buildResultHuc8Index")
//	private Tasklet buildResultHuc8Index;

	@Autowired
	@Qualifier("buildResultOrganizationIndex")
	private Tasklet buildResultOrganizationIndex;

//	@Autowired
//	@Qualifier("buildResultSiteIndex")
//	private Tasklet buildResultSiteIndex;
//
//	@Autowired
//	@Qualifier("buildResultSiteTypeIndex")
//	private Tasklet buildResultSiteTypeIndex;
//
//	@Autowired
//	@Qualifier("buildResultStateIndex")
//	private Tasklet buildResultStateIndex;
//
//	@Autowired
//	@Qualifier("buildResultStationIndex")
//	private Tasklet buildResultStationIndex;
//
//
//	@Bean
//	public Step buildResultCountryIndexStep() {
//		return stepBuilderFactory.get("buildResultCountryIndexStep")
//				.tasklet(buildResultCountryIndex)
//				.build();
//	}
//
//	@Bean
//	public Step buildResultCountyIndexStep() {
//		return stepBuilderFactory.get("buildResultCountyIndexStep")
//				.tasklet(buildResultCountyIndex)
//				.build();
//	}
//
//	@Bean
//	public Step buildResultGeomIndexStep() {
//		return stepBuilderFactory.get("buildResultGeomIndexStep")
//				.tasklet(buildResultGeomIndex)
//				.build();
//	}
//
//	@Bean
//	public Step buildResultHuc10IndexStep() {
//		return stepBuilderFactory.get("buildResultHuc10IndexStep")
//				.tasklet(buildResultHuc10Index)
//				.build();
//	}
//
//	@Bean
//	public Step buildResultHuc12IndexStep() {
//		return stepBuilderFactory.get("buildResultHuc12IndexStep")
//				.tasklet(buildResultHuc12Index)
//				.build();
//	}
//
//	@Bean
//	public Step buildResultHuc2IndexStep() {
//		return stepBuilderFactory.get("buildResultHuc2IndexStep")
//				.tasklet(buildResultHuc2Index)
//				.build();
//	}
//
//	@Bean
//	public Step buildResultHuc4IndexStep() {
//		return stepBuilderFactory.get("buildResultHuc4IndexStep")
//				.tasklet(buildResultHuc4Index)
//				.build();
//	}
//
//	@Bean
//	public Step buildResultHuc6IndexStep() {
//		return stepBuilderFactory.get("buildResultHuc6IndexStep")
//				.tasklet(buildResultHuc6Index)
//				.build();
//	}
//
//	@Bean
//	public Step buildResultHuc8IndexStep() {
//		return stepBuilderFactory.get("buildResultHuc8IndexStep")
//				.tasklet(buildResultHuc8Index)
//				.build();
//	}

	@Bean
	public Step buildResultOrganizationIndexStep() {
		return stepBuilderFactory.get("buildResultOrganizationIndexStep")
				.tasklet(buildResultOrganizationIndex)
				.build();
	}

//	@Bean
//	public Step buildResultSiteIndexStep() {
//		return stepBuilderFactory.get("buildResultSiteIndexStep")
//				.tasklet(buildResultSiteIndex)
//				.build();
//	}
//
//	@Bean
//	public Step buildResultSiteTypeIndexStep() {
//		return stepBuilderFactory.get("buildResultSiteTypeIndexStep")
//				.tasklet(buildResultSiteTypeIndex)
//				.build();
//	}
//
//	@Bean
//	public Step buildResultStateIndexStep() {
//		return stepBuilderFactory.get("buildResultStateIndexStep")
//				.tasklet(buildResultStateIndex)
//				.build();
//	}
//
//	@Bean
//	public Step buildResultStationIndexStep() {
//		return stepBuilderFactory.get("buildResultStationIndexStep")
//				.tasklet(buildResultStationIndex)
//				.build();
//	}


	@Bean
	public Flow buildResultIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildResultIndexesFlow")
				.start(buildResultOrganizationIndexStep())
//				.start(buildResultCountryIndexStep())
//				.next(buildResultCountyIndexStep())
//				.next(buildResultGeomIndexStep())
//				.next(buildResultHuc10IndexStep())
//				.next(buildResultHuc12IndexStep())
//				.next(buildResultHuc2IndexStep())
//				.next(buildResultHuc4IndexStep())
//				.next(buildResultHuc6IndexStep())
//				.next(buildResultHuc8IndexStep())
//				.next(buildResultOrganizationIndexStep())
//				.next(buildResultSiteIndexStep())
//				.next(buildResultSiteTypeIndexStep())
//				.next(buildResultStateIndexStep())
//				.next(buildResultStationIndexStep())
				.build();
	}

}
