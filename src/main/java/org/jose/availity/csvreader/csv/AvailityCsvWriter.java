package org.jose.availity.csvreader.csv;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jose.availity.csvreader.model.AvailityCsvModel;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

@Slf4j
public class AvailityCsvWriter {
    public static void writeCsvFromModel(final String insuranceCompany, final List<AvailityCsvModel> records) {
        if (Objects.nonNull(records) && !records.isEmpty()) {
            try {
                Writer writer = new FileWriter(insuranceCompany + ".csv");
                StatefulBeanToCsv<AvailityCsvModel> sbc = new StatefulBeanToCsvBuilder<AvailityCsvModel>(writer)
                        .withSeparator(CSVWriter.DEFAULT_SEPARATOR).build();
                sbc.write(records);
                writer.close();
            } catch (Exception e) {
                log.error(e.toString(), e);
            }
        }
    }
}
