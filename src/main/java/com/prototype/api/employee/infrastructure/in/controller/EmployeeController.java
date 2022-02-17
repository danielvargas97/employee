package com.prototype.api.employee.infrastructure.in.controller;

import com.prototype.api.employee.common.model.controller.request.EmployeeControllerRequest;
import com.prototype.api.employee.common.model.controller.response.EmployeeControllerResponse;

/**
 * Interface for modeling the employee controller
 *
 * @author Daniel Vargas
 */
public interface EmployeeController {

    /**
     * Creates an employee given an employee request
     *
     * @param request the employee data
     * @return  The created employee data
     */
    EmployeeControllerResponse createEmployee(EmployeeControllerRequest request);
}
