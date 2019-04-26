package gov.acwi.wqp.etl.dbFinalize.install;

public class InstallTable {
	private String wqpDataSource;
	private String wqpSchemaName;
	private String baseTableName;
	private Integer dataSourceId;

	public InstallTable(
			String wqpDataSource,
			String wqpSchemaName,
			String baseTableName,
			Integer dataSourceId) {
		this.wqpDataSource = wqpDataSource;
		this.wqpSchemaName = wqpSchemaName;
		this.baseTableName = baseTableName;
		this.dataSourceId = dataSourceId;
	}

	public String getWqpDataSource() {
		return wqpDataSource;
	}
	public void setWqpDataSource(String wqpDataSource) {
		this.wqpDataSource = wqpDataSource;
	}
	public String getWqpSchemaName() {
		return wqpSchemaName;
	}
	public void setWqpSchemaName(String wqpSchemaName) {
		this.wqpSchemaName = wqpSchemaName;
	}
	public String getBaseTableName() {
		return baseTableName;
	}
	public void setBaseTableName(String baseTableName) {
		this.baseTableName = baseTableName;
	}
	public Integer getDataSourceId() {
		return dataSourceId;
	}
	public void setDataSourceId(Integer dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
}
