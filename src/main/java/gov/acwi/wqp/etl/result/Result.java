package gov.acwi.wqp.etl.result;

import gov.acwi.wqp.etl.activity.Activity;

public class Result extends Activity {

	public static final String BASE_TABLE_NAME = "result";

	private Integer resultId;
	private String resultDetectionConditionTx;
	private String characteristicName;
	private String characteristicType;
	private String sampleFractionType;
	private String resultMeasureValue;
	private String resultUnit;
	private String resultValueStatus;
	private String resultValueType;
	private String precision;
	private String resultComment;
	private String analyticalProcedureId;
	private String analyticalProcedureSource;
	private String analyticalMethodName;
	private String analyticalMethodCitation;
	private String detectionLimit;
	private String detectionLimitUnit;
	private String detectionLimitDesc;

	public Integer getResultId() {
		return resultId;
	}
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	public String getResultDetectionConditionTx() {
		return resultDetectionConditionTx;
	}
	public void setResultDetectionConditionTx(String resultDetectionConditionTx) {
		this.resultDetectionConditionTx = resultDetectionConditionTx;
	}
	public String getCharacteristicName() {
		return characteristicName;
	}
	public void setCharacteristicName(String characteristicName) {
		this.characteristicName = characteristicName;
	}
	public String getCharacteristicType() {
		return characteristicType;
	}
	public void setCharacteristicType(String characteristicType) {
		this.characteristicType = characteristicType;
	}
	public String getSampleFractionType() {
		return sampleFractionType;
	}
	public void setSampleFractionType(String sampleFractionType) {
		this.sampleFractionType = sampleFractionType;
	}
	public String getResultMeasureValue() {
		return resultMeasureValue;
	}
	public void setResultMeasureValue(String resultMeasureValue) {
		this.resultMeasureValue = resultMeasureValue;
	}
	public String getResultUnit() {
		return resultUnit;
	}
	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}
	public String getResultValueStatus() {
		return resultValueStatus;
	}
	public void setResultValueStatus(String resultValueStatus) {
		this.resultValueStatus = resultValueStatus;
	}
	public String getResultValueType() {
		return resultValueType;
	}
	public void setResultValueType(String resultValueType) {
		this.resultValueType = resultValueType;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	public String getResultComment() {
		return resultComment;
	}
	public void setResultComment(String resultComment) {
		this.resultComment = resultComment;
	}
	public String getAnalyticalProcedureId() {
		return analyticalProcedureId;
	}
	public void setAnalyticalProcedureId(String analyticalProcedureId) {
		this.analyticalProcedureId = analyticalProcedureId;
	}
	public String getAnalyticalProcedureSource() {
		return analyticalProcedureSource;
	}
	public void setAnalyticalProcedureSource(String analyticalProcedureSource) {
		this.analyticalProcedureSource = analyticalProcedureSource;
	}
	public String getAnalyticalMethodName() {
		return analyticalMethodName;
	}
	public void setAnalyticalMethodName(String analyticalMethodName) {
		this.analyticalMethodName = analyticalMethodName;
	}
	public String getAnalyticalMethodCitation() {
		return analyticalMethodCitation;
	}
	public void setAnalyticalMethodCitation(String analyticalMethodCitation) {
		this.analyticalMethodCitation = analyticalMethodCitation;
	}
	public String getDetectionLimit() {
		return detectionLimit;
	}
	public void setDetectionLimit(String detectionLimit) {
		this.detectionLimit = detectionLimit;
	}
	public String getDetectionLimitUnit() {
		return detectionLimitUnit;
	}
	public void setDetectionLimitUnit(String detectionLimitUnit) {
		this.detectionLimitUnit = detectionLimitUnit;
	}
	public String getDetectionLimitDesc() {
		return detectionLimitDesc;
	}
	public void setDetectionLimitDesc(String detectionLimitDesc) {
		this.detectionLimitDesc = detectionLimitDesc;
	}
}
