package org.jose.availity.csvreader;

import org.jose.availity.csvreader.csv.AvailityCsvReader;
import org.jose.availity.csvreader.csv.AvailityCsvWriter;
import org.jose.availity.csvreader.logic.RecordManager;
import org.jose.availity.csvreader.model.AvailityCsvModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AvailityCsvReaderApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(AvailityCsvReaderApplication.class);

    public AvailityCsvReaderApplication() {
    }

    public static void main(String[] args) {
        log.info("Starting the Application");
        SpringApplication.run(AvailityCsvReaderApplication.class, args);
        log.info("Application Ended");
    }

    public void run(String... args) throws Exception {
        try {
            final List<AvailityCsvModel> records = AvailityCsvReader.readCsvToBean("Availity.csv");
            final RecordManager recordManager = new RecordManager(records);
            recordManager.getCompanies().forEach((ic) -> AvailityCsvWriter.writeCsvFromModel(ic, recordManager.getRecordByCompany(ic)));
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
    }
}
