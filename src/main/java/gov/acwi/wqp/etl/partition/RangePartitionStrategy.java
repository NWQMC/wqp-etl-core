package gov.acwi.wqp.etl.partition;

import java.util.List;

public interface RangePartitionStrategy<P extends RangePartition> {

	List<P> getPartitions();
}
