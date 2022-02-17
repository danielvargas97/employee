package com.prototype.api.employee.service;

import com.prototype.api.employee.infrastructure.out.soapclient.SOAPEmployeeClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.prototype.api.employee.common.util.EmployeeRequestValidator.NON_VALID_DOC_NUMBER;
import static com.prototype.api.employee.util.TestUtil.createEmployeeControllerRequest;
import static com.prototype.api.employee.util.TestUtil.createNotValidEmployeeControllerRequest;
import static com.prototype.api.employee.util.TestUtil.getCreateEmployeeResponse;
import static com.prototype.api.employee.util.TestUtil.getCreateFailedEmployeeResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for EmployeeService
 */
class EmployeeServiceTest {

    /**
     * Tested class
     */
    private EmployeeService employeeService;

    /**
     * SOAP Employee client
     */
    private SOAPEmployeeClient soapEmployeeClient;

    @BeforeEach
    public void init() {
        soapEmployeeClient = mock(SOAPEmployeeClient.class);
        employeeService = new EmployeeServiceImpl(soapEmployeeClient);
    }

    @Test
    void shouldReturnSuccessfulCreateClientResponse() {

        when(soapEmployeeClient.createEmployee(any())).thenReturn(getCreateEmployeeResponse("", ""));

        final var response = employeeService.createEmployee(createEmployeeControllerRequest());

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
    void shouldReturnSuccessfulCreateClientResponse_WhenEmployeeAlreadyExists() {

        when(soapEmployeeClient.createEmployee(any())).thenReturn(getCreateEmployeeResponse("Error", "The employee already exists"));

        final var response = employeeService.createEmployee(createEmployeeControllerRequest());

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
    void shouldReturnUnSuccessfulCreateClientResponse_WhenErrorInWebServiceOccurs() {

        when(soapEmployeeClient.createEmployee(any())).thenReturn(getCreateFailedEmployeeResponse("Error", "Message"));

        final var response = employeeService.createEmployee(createEmployeeControllerRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo("Error");
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo("Message");
        assertThat(response.getEmployeeData()).isNull();
    }

    @Test
    void shouldReturnUnSuccessfulCreateClientResponse_WhenValidationFails() {

        final var response = employeeService.createEmployee(createNotValidEmployeeControllerRequest().build());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo("Error");
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo(NON_VALID_DOC_NUMBER);
    }

    @Test
    void shouldReturnUnSuccessfulCreateClientResponse_WhenExceptionIsThrown() {

        when(soapEmployeeClient.createEmployee(any())).thenThrow(new NullPointerException("Message"));

        final var response = employeeService.createEmployee(createEmployeeControllerRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo("Error");
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo("Message");
    }


}
