package com.prototype.api.employee.util;

import com.prototype.api.employee.common.model.controller.request.EmployeeControllerRequest;
import com.prototype.api.employee.common.model.controller.response.EmployeeControllerResponse;
import com.prototype.api.employee.common.model.controller.response.EmployeeData;
import com.prototype.api.employee.common.model.soap.CreateEmployeeRequest;
import com.prototype.api.employee.common.model.soap.CreateEmployeeResponse;
import com.prototype.api.employee.common.model.soap.EmployeeRequest;
import com.prototype.api.employee.common.model.soap.EmployeeResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.prototype.api.employee.common.util.EmployeeUtil.extractDateFromLocalDate;

/**
 * Util for tests
 *
 * @author Daniel
 */
public class TestUtil {


    /**
     * Create a mock {@linkplain EmployeeControllerRequest} object.
     *
     * @return a mock {@linkplain EmployeeControllerRequest} object.
     */
    public static EmployeeControllerRequest createEmployeeControllerRequest() {
        return EmployeeControllerRequest.builder()
                .birthDate(LocalDate.of(1997, 12, 27))
                .jobStartDate(LocalDate.of(2021, 03, 01))
                .salary(100.00)
                .firstName("Alex")
                .lastName("Sanchez")
                .documentNumber("123456789")
                .documentType("CC")
                .cargo("Software Engineer")
                .build();
    }

    /**
     * Create a mock {@linkplain EmployeeControllerRequest} object with null fields.
     *
     * @return a mock {@linkplain EmployeeControllerRequest} object with null fields.
     */
    public static EmployeeControllerRequest.EmployeeControllerRequestBuilder createNotValidEmployeeControllerRequest() {
        return EmployeeControllerRequest.builder()
                .birthDate(LocalDate.of(1997, 12, 27))
                .jobStartDate(LocalDate.of(2021, 03, 01))
                .salary(100.00)
                .firstName("Alex")
                .lastName("Sanchez")
                .documentNumber("")
                .documentType("CC")
                .cargo("Software Engineer");
    }


    /**
     * Create a mock {@linkplain EmployeeControllerResponse} object.
     *
     * @param status status message.
     * @param errorMessage error message
     * @return a mock {@linkplain EmployeeControllerResponse} object.
     */
    public static EmployeeControllerResponse createEmployeeControllerResponse(final String status, final String errorMessage) {

        return EmployeeControllerResponse.builder()
                .errorMessage(errorMessage)
                .status(status)
                .employeeData(buildEmployeeData())
                .build();
    }

    /**
     * Create a mock {@linkplain EmployeeControllerResponse} object when request fails.
     *
     * @param status status message.
     * @param errorMessage error message
     * @return a mock {@linkplain EmployeeControllerResponse} object.
     */
    public static EmployeeControllerResponse createFailedEmployeeControllerResponse(final String status, final String errorMessage) {

        return EmployeeControllerResponse.builder()
                .errorMessage(errorMessage)
                .status(status)
                .build();
    }


    /**
     * Create a mock {@linkplain EmployeeData} object.
     *
     * @return a mock {@linkplain EmployeeData} object.
     */
    private static EmployeeData buildEmployeeData() {
        return EmployeeData.builder()
                .birthDate((LocalDate.of(1997, 12, 27)))
                .jobStartDate((LocalDate.of(2021, 3, 1)))
                .salary(100.0)
                .firstName("Alex")
                .lastName("Sanchez")
                .documentNumber("123456789")
                .age("")
                .dateSinceJobStartDate("")
                .documentType("CC")
                .cargo("Software Engineer")
                .build();
    }


    /**
     * Create a mock {@linkplain CreateEmployeeResponse} object
     *
     * @param status               status
     * @param errorResponseMessage error response message
     * @return a mock {@linkplain CreateEmployeeResponse} object
     */
    public static CreateEmployeeResponse getCreateEmployeeResponse(final String status, final String errorResponseMessage) {
        return CreateEmployeeResponse.builder()
                .status(status)
                .errorResponseMessage(errorResponseMessage)
                .response(createEmployeeResponse())
                .build();
    }

    /**
     * Create a mock {@linkplain CreateEmployeeResponse} object for fail scenarios.
     *
     * @param status               status
     * @param errorResponseMessage error response message
     * @return a mock {@linkplain CreateEmployeeResponse} object
     */
    public static CreateEmployeeResponse getCreateFailedEmployeeResponse(final String status, final String errorResponseMessage) {
        return CreateEmployeeResponse.builder()
                .status(status)
                .errorResponseMessage(errorResponseMessage)
                .build();
    }

    /**
     * Create a mock {@linkplain EmployeeResponse} object.
     *
     * @return a mock {@linkplain EmployeeResponse} object.
     */
    private static EmployeeResponse createEmployeeResponse() {
        return EmployeeResponse.builder()
                .birthDate(extractDateFromLocalDate(LocalDate.of(1997, 12, 27)))
                .jobStartDate(extractDateFromLocalDate(LocalDate.of(2021, 03, 01)))
                .salary(new BigDecimal("100.00"))
                .firstName("Alex")
                .lastName("Sanchez")
                .documentNumber("123456789")
                .age("")
                .dateSinceJobStart("")
                .documentType("CC")
                .cargo("Software Engineer")
                .build();
    }

    /**
     * Create a mock {@linkplain CreateEmployeeRequest} object
     *
     * @return Create a mock {@linkplain CreateEmployeeRequest} object
     */
    public static CreateEmployeeRequest getCreateEmployeeRequest() {

        final var request = EmployeeRequest.builder()
                .birthDate(extractDateFromLocalDate(LocalDate.of(1997,12,27)))
                .jobStartDate(extractDateFromLocalDate(LocalDate.of(2021,03,01)))
                .salary(new BigDecimal("100.00"))
                .firstName("Alex")
                .lastName("Sanchez")
                .documentNumber("123456789")
                .documentType("CC")
                .cargo("Software Engineer")
                .build();

        return CreateEmployeeRequest.builder()
                .request(request)
                .build();
    }
}
