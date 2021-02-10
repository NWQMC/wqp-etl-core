package gov.acwi.wqp.etl.result.table;

import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.partition.*;
import gov.acwi.wqp.etl.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A simple wrapper around a RangePartitionStrategy that uses the Config Service to provide
 * config values.
 */
@Component
public class ResultPartitionStrategy implements RangePartitionStrategy<PgDateRangePart>  {

	private final DateRangePartitionStrategy strategy;

	@Autowired
	public ResultPartitionStrategy(ConfigurationService config) {
		String baseName = Result.BASE_TABLE_NAME + "_" + config.getEtlDataSource();

		if (
				config.getEtlResultPartitionStartDate() != null &&
						config.getEtlResultPartitionOneYearBreak() != null &&
						config.getEtlResultPartitionQuarterBreak() != null &&
						config.getEtlResultPartitionEndDate() != null) {

			strategy = new DateRangePartitionStrategy(config.getEtlRunTime(), baseName,
					config.getEtlResultPartitionStartDate(), config.getEtlResultPartitionOneYearBreak(),
					config.getEtlResultPartitionQuarterBreak(), config.getEtlResultPartitionEndDate());
		} else {

			strategy = new DateRangePartitionStrategy(config.getEtlRunTime(), baseName);

		}
	}


	@Override
	public List<PgDateRangePart> getPartitions() {
		return strategy.getPartitions();
	}
}
