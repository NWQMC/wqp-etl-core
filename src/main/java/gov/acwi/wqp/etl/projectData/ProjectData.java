package gov.acwi.wqp.etl.projectData;

import gov.acwi.wqp.etl.orgData.OrgData;

public class ProjectData extends OrgData {

	public static final String BASE_TABLE_NAME = "project_data";

	private String projectIdentifier;
	private String projectName;
	private String description;

	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
