package org.jose.availity.csvreader.csv;

import org.jose.availity.csvreader.logic.RecordManager;
import org.jose.availity.csvreader.model.AvailityCsvModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvailityCsvWriterTest {

    @Test
    public void writeCsvFromModel() {
        final String companyName = "Allstate";
        List<AvailityCsvModel> records = AvailityCsvReader.readCsvToBean("Availity.csv");
        assertTrue(records.size() > 0);

        final RecordManager recordManager = new RecordManager(records);
        final List<AvailityCsvModel> filteredRecords = recordManager.getRecordByCompany(companyName);
        assertTrue(filteredRecords.size() > 0);

        AvailityCsvWriter.writeCsvFromModel(companyName, filteredRecords);
        final File file = new File(companyName + ".csv");
        assertTrue(file.exists());
    }

    @Test
    public void writeCsvFromModelCompanyDoesNotExist() {
        final String companyName = "ThisCompanyDoesNotExist";
        List<AvailityCsvModel> records = AvailityCsvReader.readCsvToBean("Availity.csv");
        assertTrue(records.size() > 0);

        RecordManager recordManager = new RecordManager(records);
        List<AvailityCsvModel> filteredRecords = recordManager.getRecordByCompany(companyName);
        assertEquals(0, filteredRecords.size());

        AvailityCsvWriter.writeCsvFromModel(companyName, filteredRecords);
        File file = new File(companyName + ".csv");
        assertFalse(file.exists());
    }

    @Test
    public void writeCsvFromModelNoCompany() {
        final String companyName = "";
        AvailityCsvWriter.writeCsvFromModel(companyName, null);
        File file = new File(companyName + ".csv");
        assertFalse(file.exists());
    }
}
