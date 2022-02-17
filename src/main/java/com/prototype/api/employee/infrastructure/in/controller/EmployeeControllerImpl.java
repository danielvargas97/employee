package com.prototype.api.employee.infrastructure.in.controller;

import com.prototype.api.employee.common.model.controller.request.EmployeeControllerRequest;
import com.prototype.api.employee.common.model.controller.response.EmployeeControllerResponse;
import com.prototype.api.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of the employee controller
 *
 * @author Daniel Vargas
 */

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
public class EmployeeControllerImpl implements EmployeeController {

    /**
     * Employee service.
     */
    private final EmployeeService employeeService;

    /**
     * Creates an employee given an employee request
     *
     * @param request the employee data
     * @return The created employee data
     */
    @Override
    @GetMapping()
    public EmployeeControllerResponse createEmployee(@RequestBody final EmployeeControllerRequest request) {
        return employeeService.createEmployee(request);
    }


}
