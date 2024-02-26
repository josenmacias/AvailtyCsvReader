package org.jose.availity.csvreader.csv;

import org.jose.availity.csvreader.model.AvailityCsvModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvailityCsvReaderTest {
    @Test
    public void testReadCsvToBean() {
        List<AvailityCsvModel> records = AvailityCsvReader.readCsvToBean("Availity.csv");
        assertTrue(records.size() > 0);
    }

    @Test
    public void testReadCsvToBeanNoFile() {
        List<AvailityCsvModel> records = AvailityCsvReader.readCsvToBean("ThisFileDoesNotExist.csv");
        assertEquals(0, records.size());
    }
}
