package com.prototype.api.employee.infrastructure.out.soapclient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.prototype.api.employee.util.TestUtil.getCreateEmployeeRequest;
import static com.prototype.api.employee.util.TestUtil.getCreateEmployeeResponse;
import static com.prototype.api.employee.util.TestUtil.getCreateFailedEmployeeResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for SOAP Employee client class
 *
 * @author Daniel
 */
class SOAPEmployeeClientTest {

    /**
     * Tested class
     */
    private SOAPEmployeeClient soapEmployeeClient;

    @BeforeEach
    public void init(){
        soapEmployeeClient = mock(SOAPEmployeeClient.class);
    }

    @Test
    void shouldMakeASuccessfulRequest(){

        when(soapEmployeeClient.createEmployee(any())).thenReturn(getCreateEmployeeResponse("",""));

        final var response = soapEmployeeClient.createEmployee(getCreateEmployeeRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("");
        assertThat(response.getErrorResponseMessage()).isEqualTo("");
        assertThat(response.getResponse().getFirstName()).isNotNull();
        assertThat(response.getResponse().getLastName()).isNotNull();
        assertThat(response.getResponse().getCargo()).isNotNull();
        assertThat(response.getResponse().getBirthDate()).isNotNull();
        assertThat(response.getResponse().getSalary()).isPositive();

    }

    @Test
    void shouldMakeASuccessfulRequest_WhenEmployeeAlreadyExists(){

        when(soapEmployeeClient.createEmployee(any())).thenReturn(getCreateEmployeeResponse("Error","The employee already exists"));

        final var response = soapEmployeeClient.createEmployee(getCreateEmployeeRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("Error");
        assertThat(response.getErrorResponseMessage()).isEqualTo("The employee already exists");
        assertThat(response.getResponse().getFirstName()).isNotNull();
        assertThat(response.getResponse().getLastName()).isNotNull();
        assertThat(response.getResponse().getCargo()).isNotNull();
        assertThat(response.getResponse().getBirthDate()).isNotNull();
        assertThat(response.getResponse().getSalary()).isPositive();

    }

    @Test
    void shouldMakeANotSuccessfulRequest_WhenClientErrorOccurs(){

        when(soapEmployeeClient.createEmployee(any())).thenReturn(getCreateFailedEmployeeResponse("Error","Error Message"));

        final var response = soapEmployeeClient.createEmployee(getCreateEmployeeRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("Error");
        assertThat(response.getErrorResponseMessage()).isEqualTo("Error Message");
        assertThat(response.getResponse()).isNull();

    }

}
