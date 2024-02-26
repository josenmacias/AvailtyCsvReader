package org.jose.availity.csvreader.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jose.availity.csvreader.model.AvailityCsvModel;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AvailityCsvReader {
    public static List<AvailityCsvModel> readCsvToBean(final String fileName) {
        final List<AvailityCsvModel> records = new ArrayList<>();
        try {
            CsvToBeanBuilder<AvailityCsvModel> beanBuilder = new CsvToBeanBuilder<>(new InputStreamReader(new FileInputStream(fileName)));
            beanBuilder.withType(AvailityCsvModel.class);
            records.addAll(beanBuilder.build().parse());
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
        return records;
    }
}
