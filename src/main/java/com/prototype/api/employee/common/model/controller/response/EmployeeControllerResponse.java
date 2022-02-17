package com.prototype.api.employee.common.model.controller.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Employee controller response model
 *
 * @author Daniel
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeControllerResponse {

    /**
     * Response status.
     */
    private String status;

    /**
     * Error message
     *
     */
    private String errorMessage;

    /**
     * Employee response data.
     */
    private EmployeeData employeeData;
}
