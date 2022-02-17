package com.prototype.api.employee.infrastructure.in.controller;

import com.prototype.api.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.prototype.api.employee.common.util.EmployeeRequestValidator.NON_VALID_DOC_NUMBER;
import static com.prototype.api.employee.util.TestUtil.createEmployeeControllerRequest;
import static com.prototype.api.employee.util.TestUtil.createEmployeeControllerResponse;
import static com.prototype.api.employee.util.TestUtil.createFailedEmployeeControllerResponse;
import static com.prototype.api.employee.util.TestUtil.createNotValidEmployeeControllerRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for EmployeeController class
 *
 * @author Daniel
 */
class EmployeeControllerTest {

    /**
     * Tested class.
     */
    private EmployeeController employeeController;

    /**
     * Employee service.
     */
    private EmployeeService employeeService;

    @BeforeEach
    public void init() {
        employeeService = mock(EmployeeService.class);
        employeeController = new EmployeeControllerImpl(employeeService);
    }

    @Test
    void shouldReturnEmployeeResponseSuccessfully() {
        when(employeeService.createEmployee(any())).thenReturn(createEmployeeControllerResponse("", ""));

        final var response = employeeController.createEmployee(createEmployeeControllerRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo("");
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo("");
        assertThat(response.getEmployeeData()).isNotNull();
        assertThat(response.getEmployeeData()).satisfies(employeeData -> {
            assertThat(employeeData.getDocumentNumber()).isNotNull();
            assertThat(employeeData.getDocumentType()).isNotNull();
            assertThat(employeeData.getSalary()).isNotNull().isPositive();
            assertThat(employeeData.getCargo()).isNotNull();
        });
    }

    @Test
    void shouldReturnEmployeeResponseSuccessfully_WhenEmployeeAlreadyExists() {

        when(employeeService.createEmployee(any())).thenReturn(createEmployeeControllerResponse("Error", "The employee already exists"));

        final var response = employeeController.createEmployee(createEmployeeControllerRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo("Error");
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo("The employee already exists");
        assertThat(response.getEmployeeData()).isNotNull();
        assertThat(response.getEmployeeData()).satisfies(employeeData -> {
            assertThat(employeeData.getDocumentNumber()).isNotNull();
            assertThat(employeeData.getDocumentType()).isNotNull();
            assertThat(employeeData.getSalary()).isNotNull().isPositive();
            assertThat(employeeData.getCargo()).isNotNull();
        });
    }

    @Test
    void shouldReturnNotSuccessfulResponse_WhenAnExceptionIsThrown(){
        when(employeeService.createEmployee(any())).thenReturn(createFailedEmployeeControllerResponse("Error", "Message"));

        final var response = employeeController.createEmployee(createEmployeeControllerRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo("Error");
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo("Message");
        assertThat(response.getEmployeeData()).isNull();

    }

    @Test
    void shouldReturnNotSuccessfulResponse_WhenValidationFails(){

        when(employeeService.createEmployee(any())).thenReturn(createFailedEmployeeControllerResponse("Error", NON_VALID_DOC_NUMBER));
        final var response = employeeController.createEmployee(createNotValidEmployeeControllerRequest().build());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo("Error");
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo(NON_VALID_DOC_NUMBER);
        assertThat(response.getEmployeeData()).isNull();

    }
}
