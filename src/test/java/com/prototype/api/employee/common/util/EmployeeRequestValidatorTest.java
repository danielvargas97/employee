package com.prototype.api.employee.common.util;

import com.prototype.api.employee.common.model.controller.response.EmployeeControllerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.prototype.api.employee.common.util.EmployeeRequestValidator.ERROR_STATUS;
import static com.prototype.api.employee.common.util.EmployeeRequestValidator.NON_VALID_BIRTH_DATE;
import static com.prototype.api.employee.common.util.EmployeeRequestValidator.NON_VALID_CARGO;
import static com.prototype.api.employee.common.util.EmployeeRequestValidator.NON_VALID_DOC_NUMBER;
import static com.prototype.api.employee.common.util.EmployeeRequestValidator.NON_VALID_DOC_TYPE;
import static com.prototype.api.employee.common.util.EmployeeRequestValidator.NON_VALID_FIRST_NAME;
import static com.prototype.api.employee.common.util.EmployeeRequestValidator.NON_VALID_JOB_START_DATE;
import static com.prototype.api.employee.common.util.EmployeeRequestValidator.NON_VALID_SALARY;
import static com.prototype.api.employee.common.util.EmployeeRequestValidator.isValidRequest;
import static com.prototype.api.employee.util.TestUtil.createNotValidEmployeeControllerRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for Employee Request Validator
 *
 * @author Daniel
 */
class EmployeeRequestValidatorTest {

    private EmployeeControllerResponse.EmployeeControllerResponseBuilder responseBuilder;

    @BeforeEach
    public void init() {
        responseBuilder = EmployeeControllerResponse.builder();
    }


    @Test
    void shouldRejectRequest_WhenNameIsNullOrBlank() {
        final var request = createNotValidEmployeeControllerRequest()
                .firstName("")
                .build();
        final var isValid =  isValidRequest(request, responseBuilder);
        final var response = responseBuilder.build();

        assertFalse(isValid);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo(ERROR_STATUS);
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo(NON_VALID_FIRST_NAME);

    }

    @Test
    void shouldRejectRequest_WhenJobStartDateIsNullOrBlank() {
        final var request = createNotValidEmployeeControllerRequest()
                .jobStartDate(null)
                .build();
        final var isValid =  isValidRequest(request, responseBuilder);
        final var response = responseBuilder.build();

        assertFalse(isValid);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo(ERROR_STATUS);
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo(NON_VALID_JOB_START_DATE);

    }

    @Test
    void shouldRejectRequest_WhenBirthDateIsNullOrBlank() {
        final var request = createNotValidEmployeeControllerRequest()
                .birthDate(null)
                .build();
        final var isValid =  isValidRequest(request, responseBuilder);
        final var response = responseBuilder.build();

        assertFalse(isValid);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo(ERROR_STATUS);
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo(NON_VALID_BIRTH_DATE);

    }

    @Test
    void shouldRejectRequest_WhenCargoIsNullOrBlank() {
        final var request = createNotValidEmployeeControllerRequest()
                .cargo("")
                .build();
        final var isValid =  isValidRequest(request, responseBuilder);
        final var response = responseBuilder.build();

        assertFalse(isValid);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo(ERROR_STATUS);
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo(NON_VALID_CARGO);

    }

    @Test
    void shouldRejectRequest_WhenSalaryIsNegative() {
        final var request = createNotValidEmployeeControllerRequest()
                .salary(-1000)
                .build();
        final var isValid =  isValidRequest(request, responseBuilder);
        final var response = responseBuilder.build();

        assertFalse(isValid);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo(ERROR_STATUS);
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo(NON_VALID_SALARY);

    }

    @Test
    void shouldRejectRequest_WhenDocTypeIsNull() {
        final var request = createNotValidEmployeeControllerRequest()
                .documentType(null)
                .build();
        final var isValid =  isValidRequest(request, responseBuilder);
        final var response = responseBuilder.build();

        assertFalse(isValid);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo(ERROR_STATUS);
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo(NON_VALID_DOC_TYPE);

    }

    @Test
    void shouldRejectRequest_WhenDocTypeIsNotValid() {
        final var request = createNotValidEmployeeControllerRequest()
                .documentType("ABC")
                .build();
        final var isValid =  isValidRequest(request, responseBuilder);
        final var response = responseBuilder.build();

        assertFalse(isValid);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo(ERROR_STATUS);
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo(NON_VALID_DOC_TYPE);

    }



    @Test
    void shouldRejectRequest_WhenDocNumberIsNullOrBlank() {
        final var request = createNotValidEmployeeControllerRequest()
                .documentNumber("")
                .build();
        final var isValid =  isValidRequest(request, responseBuilder);
        final var response = responseBuilder.build();

        assertFalse(isValid);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo(ERROR_STATUS);
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo(NON_VALID_DOC_NUMBER);

    }

    @Test
    void shouldRejectRequest_WhenDocNumberIsInvalid() {
        final var request = createNotValidEmployeeControllerRequest()
                .documentNumber("+++123+")
                .build();
        final var isValid =  isValidRequest(request, responseBuilder);
        final var response = responseBuilder.build();

        assertFalse(isValid);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo(ERROR_STATUS);
        assertThat(response.getErrorMessage()).isNotNull().isEqualTo(NON_VALID_DOC_NUMBER);

    }
}
