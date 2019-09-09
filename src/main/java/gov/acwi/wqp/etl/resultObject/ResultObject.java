package gov.acwi.wqp.etl.resultObject;

import gov.acwi.wqp.etl.activityObject.ActivityObject;

public class ResultObject extends ActivityObject {

	public static final String BASE_TABLE_NAME = "result_object";

	private Integer resultId;

	public Integer getResultId() {
		return resultId;
	}
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
}
