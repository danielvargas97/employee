package com.prototype.api.employee.common.model.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDate;

/**
 * Employee response data model.
 *
 * @author Daniel
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeData {


    /**
     * Employee firstname
     */
    private String firstName;

    /**
     * Employee lastname
     */
    private String lastName;

    /**
     * Employee document type
     */
    private String documentType;

    /**
     * Employee document number
     */
    private String documentNumber;

    /**
     * Employee birthdate
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    /**
     * Employee job start date
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate jobStartDate;

    /**
     * Employee age.
     */
    private String age;

    /**
     * Years, months and days since job start date.
     */
    private String dateSinceJobStartDate;

    /**
     * Employee job title
     */
    private String cargo;

    /**
     * Employee salary
     */
    private double salary;
}
