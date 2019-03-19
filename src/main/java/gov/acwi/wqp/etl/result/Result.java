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

	@Override
	public String toString() {
		StringBuilder rtn = new StringBuilder("Result [");
		rtn.append(super.toString());
		rtn.append("] resultId=").append(resultId == null ? "{null}" : resultId.toString());
		rtn.append(", resultDetectionConditionTx=").append(resultDetectionConditionTx == null ? "{null}" : resultDetectionConditionTx.toString());
		rtn.append(", characteristicName=").append(characteristicName == null ? "{null}" : characteristicName.toString());
		rtn.append(", characteristicType=").append(characteristicType == null ? "{null}" : characteristicType.toString());
		rtn.append(", sampleFractionType=").append(sampleFractionType == null ? "{null}" : sampleFractionType.toString());
		rtn.append(", resultMeasureValue=").append(resultMeasureValue == null ? "{null}" : resultMeasureValue.toString());
		rtn.append(", resultUnit=").append(resultUnit == null ? "{null}" : resultUnit.toString());
		rtn.append(", resultValueStatus=").append(resultValueStatus == null ? "{null}" : resultValueStatus.toString());
		rtn.append(", resultValueType=").append(resultValueType == null ? "{null}" : resultValueType.toString());
		rtn.append(", precision=").append(precision == null ? "{null}" : precision.toString());
		rtn.append(", resultComment=").append(resultComment == null ? "{null}" : resultComment.toString());
		rtn.append(", analyticalProcedureId=").append(analyticalProcedureId == null ? "{null}" : analyticalProcedureId.toString());
		rtn.append(", analyticalProcedureSource=").append(analyticalProcedureSource == null ? "{null}" : analyticalProcedureSource.toString());
		rtn.append(", analyticalMethodName=").append(analyticalMethodName == null ? "{null}" : analyticalMethodName.toString());
		rtn.append(", analyticalMethodCitation=").append(analyticalMethodCitation == null ? "{null}" : analyticalMethodCitation.toString());
		rtn.append(", detectionLimit=").append(detectionLimit == null ? "{null}" : detectionLimit.toString());
		rtn.append(", detectionLimitUnit=").append(detectionLimitUnit == null ? "{null}" : detectionLimitUnit.toString());
		rtn.append(", detectionLimitDesc=").append(detectionLimitDesc == null ? "{null}" : detectionLimitDesc.toString());
		return rtn.append("]").toString();
	}
}
