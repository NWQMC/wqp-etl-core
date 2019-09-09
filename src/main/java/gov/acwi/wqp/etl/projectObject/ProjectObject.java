package gov.acwi.wqp.etl.projectObject;

import gov.acwi.wqp.etl.BlobObject;

public class ProjectObject extends BlobObject {

	public static final String BASE_TABLE_NAME = "project_object";

	private String projectIdentifier;

	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
}
