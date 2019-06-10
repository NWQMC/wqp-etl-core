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
	private String statisticType;
	private String resultValueType;
	private String weightBasisType;
	private String durationBasis;
	private String temperatureBasisLevel;
	private String particleSize;
	private String precision;
	private String resultComment;
	private String sampleTissueTaxonomicName;
	private String sampleTissueAnatomyName;
	private String analyticalProcedureId;
	private String analyticalProcedureSource;
	private String analyticalMethod;
	private String analyticalMethodName;
	private String analyticalMethodCitation;
	private String labName;
	private String analysisStartDate;
	private String labRemark;
	private String detectionLimit;
	private String detectionLimitUnit;
	private String detectionLimitDesc;
	private String pCode;
	private String analysisPrepDateTx;
	private String biologicalIntent;
	private String resBioIndividualId;
	private String unidentifiedSpeciesIdentifier;
	private String resGroupSummaryCtWt;
	private String resGroupSummaryCtWtUnit;

	public String getBiologicalIntent() {
		return biologicalIntent;
	}
	public void setBiologicalIntent(String biologicalIntent) {
		this.biologicalIntent = biologicalIntent;
	}
	public String getResBioIndividualId() {
		return resBioIndividualId;
	}
	public void setResBioIndividualId(String resBioIndividualId) {
		this.resBioIndividualId = resBioIndividualId;
	}
	public String getUnidentifiedSpeciesIdentifier() {
		return unidentifiedSpeciesIdentifier;
	}
	public void setUnidentifiedSpeciesIdentifier(String unidentifiedSpeciesIdentifier) {
		this.unidentifiedSpeciesIdentifier = unidentifiedSpeciesIdentifier;
	}
	public String getResGroupSummaryCtWt() {
		return resGroupSummaryCtWt;
	}
	public void setResGroupSummaryCtWt(String resGroupSummaryCtWt) {
		this.resGroupSummaryCtWt = resGroupSummaryCtWt;
	}
	public String getResGroupSummaryCtWtUnit() {
		return resGroupSummaryCtWtUnit;
	}
	public void setResGroupSummaryCtWtUnit(String resGroupSummaryCtWtUnit) {
		this.resGroupSummaryCtWtUnit = resGroupSummaryCtWtUnit;
	}
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
	public String getStatisticType() {
		return statisticType;
	}
	public void setStatisticType(String statisticType) {
		this.statisticType = statisticType;
	}
	public String getResultValueType() {
		return resultValueType;
	}
	public void setResultValueType(String resultValueType) {
		this.resultValueType = resultValueType;
	}
	public String getWeightBasisType() {
		return weightBasisType;
	}
	public void setWeightBasisType(String weightBasisType) {
		this.weightBasisType = weightBasisType;
	}
	public String getDurationBasis() {
		return durationBasis;
	}
	public void setDurationBasis(String durationBasis) {
		this.durationBasis = durationBasis;
	}
	public String getTemperatureBasisLevel() {
		return temperatureBasisLevel;
	}
	public void setTemperatureBasisLevel(String temperatureBasisLevel) {
		this.temperatureBasisLevel = temperatureBasisLevel;
	}
	public String getParticleSize() {
		return particleSize;
	}
	public void setParticleSize(String particleSize) {
		this.particleSize = particleSize;
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
	public String getSampleTissueTaxonomicName() {
		return sampleTissueTaxonomicName;
	}
	public void setSampleTissueTaxonomicName(String sampleTissueTaxonomicName) {
		this.sampleTissueTaxonomicName = sampleTissueTaxonomicName;
	}
	public String getSampleTissueAnatomyName() {
		return sampleTissueAnatomyName;
	}
	public void setSampleTissueAnatomyName(String sampleTissueAnatomyName) {
		this.sampleTissueAnatomyName = sampleTissueAnatomyName;
	}
	public String getAnalyticalMethod() {
		return analyticalMethod;
	}
	public void setAnalyticalMethod(String analyticalMethod) {
		this.analyticalMethod = analyticalMethod;
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
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getAnalysisStartDate() {
		return analysisStartDate;
	}
	public void setAnalysisStartDate(String analysisStartDate) {
		this.analysisStartDate = analysisStartDate;
	}
	public String getLabRemark() {
		return labRemark;
	}
	public void setLabRemark(String labRemark) {
		this.labRemark = labRemark;
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
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getAnalysisPrepDateTx() {
		return analysisPrepDateTx;
	}
	public void setAnalysisPrepDateTx(String analysisPrepDateTx) {
		this.analysisPrepDateTx = analysisPrepDateTx;
	}
}
