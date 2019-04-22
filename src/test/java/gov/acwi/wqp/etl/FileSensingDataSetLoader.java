package gov.acwi.wqp.etl;

import java.io.InputStream;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

public class FileSensingDataSetLoader extends AbstractDataSetLoader {

	@Override
	protected IDataSet createDataSet(Resource resource) throws Exception {
		if (resource.getFilename().endsWith("xml")) {
			return createXmlDataSet(resource);
		} else {
			return createCsvDataset(resource);
		}
	}

	private IDataSet createCsvDataset(Resource resource) throws Exception {
		return createReplacementDataSet(new CsvURLDataSet(resource.getURL()));
	}

	private IDataSet createXmlDataSet(Resource resource) throws Exception {
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(true);
		try (InputStream inputStream = resource.getInputStream()) {
			return createReplacementDataSet(builder.build(inputStream));
		}
	}

	private IDataSet createReplacementDataSet(IDataSet iDataSet) {
		ReplacementDataSet replacementDataSet = new ReplacementDataSet(iDataSet);

		replacementDataSet.addReplacementSubstring("[DATE-LE-12-MONTHS]", BaseFlowIT.TEST_DATE_MINUS_12_MONTHS.toString());
		replacementDataSet.addReplacementSubstring("[DATE-GT-12-MONTHS]", BaseFlowIT.TEST_DATE_MINUS_12_MONTHS_1_DAY.toString());
		replacementDataSet.addReplacementSubstring("[DATE-LE-60-MONTHS]", BaseFlowIT.TEST_DATE_MINUS_60_MONTHS.toString());
		replacementDataSet.addReplacementSubstring("[DATE-GT-60-MONTHS]", BaseFlowIT.TEST_DATE_MINUS_60_MONTHS_1_DAY.toString());

		replacementDataSet.addReplacementSubstring("[YEAR-LE-12-MONTHS]", String.valueOf(BaseFlowIT.TEST_DATE_MINUS_12_MONTHS.getYear()));
		replacementDataSet.addReplacementSubstring("[YEAR-GT-12-MONTHS]", String.valueOf(BaseFlowIT.TEST_DATE_MINUS_12_MONTHS_1_DAY.getYear()));
		replacementDataSet.addReplacementSubstring("[YEAR-LE-60-MONTHS]", String.valueOf(BaseFlowIT.TEST_DATE_MINUS_60_MONTHS.getYear()));
		replacementDataSet.addReplacementSubstring("[YEAR-GT-60-MONTHS]", String.valueOf(BaseFlowIT.TEST_DATE_MINUS_60_MONTHS_1_DAY.getYear()));

		return replacementDataSet;
	}

}
