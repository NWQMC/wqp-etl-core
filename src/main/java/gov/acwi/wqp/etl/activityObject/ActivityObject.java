package gov.acwi.wqp.etl.activityObject;

import gov.acwi.wqp.etl.BlobObject;

public class ActivityObject extends BlobObject {

	public static final String BASE_TABLE_NAME = "activity_object";

	private Integer activityId;
	private String activity;

	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
}
