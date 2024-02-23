package org.jose.availity.csvreader.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailityCsvModel {
    @CsvBindByName(column = "User Id")
    private String userId;
    @CsvBindByName(column = "First Name")
    private String firstName;

    @CsvBindByName(column = "Last Name")
    private String lastName;
    @CsvBindByName(column = "Version")
    private Integer version;
    @CsvBindByName(column = "Insurance Company")
    private String insuranceCompany;

    @Override
    public String toString() {
        return "AvailityCsvModel{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", version=" + version +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                '}';
    }
}
