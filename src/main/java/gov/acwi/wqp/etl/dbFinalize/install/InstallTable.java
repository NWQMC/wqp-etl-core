package gov.acwi.wqp.etl.dbFinalize.install;

public class InstallTable {
	private String wqpDataSource;
	private String wqpSchemaName;
	private String base_table_name;
	private Integer data_source_id;

	public InstallTable(
			String wqpDataSource,
			String wqpSchemaName,
			String base_table_name,
			Integer data_source_id) {
		this.wqpDataSource = wqpDataSource;
		this.wqpSchemaName = wqpSchemaName;
		this.base_table_name = base_table_name;
		this.data_source_id = data_source_id;
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
	public String getBase_table_name() {
		return base_table_name;
	}
	public void setBase_table_name(String base_table_name) {
		this.base_table_name = base_table_name;
	}
	public Integer getData_source_id() {
		return data_source_id;
	}
	public void setData_source_id(Integer data_source_id) {
		this.data_source_id = data_source_id;
	}
}
