package org.jose.availity.csvreader.logic;

import org.jose.availity.csvreader.model.AvailityCsvModel;

import java.util.*;
import java.util.stream.Collectors;

public class RecordManager {
    private List<AvailityCsvModel> records;

    public RecordManager(final List<AvailityCsvModel> records) {
        this.records = Objects.requireNonNullElse(records, new ArrayList<>());
        filterRecords();
    }

    public void filterRecords() {
        final Map<String, AvailityCsvModel> recordMap = new HashMap<>();
        for (AvailityCsvModel record : records) {
            String key = record.getUserId() + "||" + record.getInsuranceCompany();
            if (!recordMap.containsKey(key) || (recordMap.containsKey(key) && record.getVersion() > recordMap.get(key).getVersion())) {
                recordMap.put(key, record);
            }
        }
        records = new ArrayList<>(recordMap.values());
    }

    public Map<String, List<AvailityCsvModel>> getRecordMapByCompany() {
        return records.stream().collect(Collectors.groupingBy(AvailityCsvModel::getInsuranceCompany));
    }

    public Set<String> getCompanies() {
        return records.parallelStream().map(AvailityCsvModel::getInsuranceCompany).collect(Collectors.toSet());
    }

    public List<AvailityCsvModel> getRecordByCompany(final String companyName) {
        Comparator<AvailityCsvModel> compareByLastName = Comparator.comparing(AvailityCsvModel::getLastName);
        Comparator<AvailityCsvModel> compareByFirstName = Comparator.comparing(AvailityCsvModel::getFirstName);
        Comparator<AvailityCsvModel> compareByName = compareByLastName.thenComparing(compareByFirstName);
        List<AvailityCsvModel> records = this.getRecordMapByCompany().getOrDefault(companyName, new ArrayList<>());
        return records.stream().sorted(compareByName).collect(Collectors.toList());
    }
}
