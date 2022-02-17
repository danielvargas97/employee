package com.prototype.api.employee.service;

import com.prototype.api.employee.common.model.controller.request.EmployeeControllerRequest;
import com.prototype.api.employee.common.model.controller.response.EmployeeControllerResponse;

/**
 * Interface that define the Employee service
 *
 * @author Daniel
 */
public interface EmployeeService {

    /**
     * Make a createEmployee request to the client and returns a createEmployee response
     *
     * @param request a {@linkplain EmployeeControllerRequest} object with endpoint request data.
     * @return a {@linkplain EmployeeControllerResponse} object with the response.
     */
    EmployeeControllerResponse createEmployee(EmployeeControllerRequest request);
}
