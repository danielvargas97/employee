package com.prototype.api.employee.service;

import com.prototype.api.employee.common.model.controller.request.EmployeeControllerRequest;
import com.prototype.api.employee.common.model.controller.response.EmployeeControllerResponse;
import com.prototype.api.employee.common.model.controller.response.EmployeeData;
import com.prototype.api.employee.common.model.soap.CreateEmployeeRequest;
import com.prototype.api.employee.common.model.soap.CreateEmployeeResponse;
import com.prototype.api.employee.common.model.soap.EmployeeRequest;
import com.prototype.api.employee.common.model.soap.EmployeeResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.prototype.api.employee.common.util.EmployeeUtil.extractDateFromLocalDate;
import static com.prototype.api.employee.common.util.EmployeeUtil.extractDateFromXml;

/**
 * Employee mapper class.
 *
 * @author Daniel
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeServiceMapper {


    /**
     * Create a {@linkplain CreateEmployeeRequest} object.
     *
     * @param request a {@linkplain EmployeeControllerRequest} object with endpoint request data.
     * @return a {@linkplain CreateEmployeeRequest} object with SOAP Employee request data.
     */
    public static CreateEmployeeRequest buildEmployeeRequest(final EmployeeControllerRequest request) {

        final var soapRequest = EmployeeRequest.builder()
                .documentNumber(request.getDocumentNumber())
                .documentType(request.getDocumentType())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .cargo(request.getCargo())
                .salary(BigDecimal.valueOf(request.getSalary()))
                .birthDate(extractDateFromLocalDate(request.getBirthDate()))
                .jobStartDate(extractDateFromLocalDate(request.getJobStartDate()))
                .build();


        return CreateEmployeeRequest.builder().request(soapRequest)
                .build();
    }


    /**
     * Create a {@linkplain EmployeeControllerResponse} object with the received {@linkplain CreateEmployeeResponse} object.
     *
     * @param response the received {@linkplain CreateEmployeeResponse}  response object.
     * @return a {@linkplain EmployeeControllerResponse} response object.
     */
    public static EmployeeControllerResponse buildSuccessfulResponse(final CreateEmployeeResponse response) {
        return EmployeeControllerResponse.builder()
                .status(response.getStatus())
                .errorMessage(response.getErrorResponseMessage())
                .employeeData(buildEmployeeData(response.getResponse()))
                .build();
    }


    /**
     * Create the {@linkplain EmployeeData} object from the {@linkplain EmployeeResponse} object.
     *
     * @param response a  {@linkplain EmployeeResponse} object with employee data.
     * @return a {@linkplain EmployeeData} object with employee data.
     */
    private static EmployeeData buildEmployeeData(final EmployeeResponse response) {
        return EmployeeData.builder()
                .documentNumber(response.getDocumentNumber())
                .birthDate(extractDateFromXml(response.getBirthDate()))
                .documentType(response.getDocumentType())
                .firstName(response.getFirstName())
                .lastName(response.getLastName())
                .cargo(response.getCargo())
                .jobStartDate(extractDateFromXml(response.getJobStartDate()))
                .dateSinceJobStartDate(response.getDateSinceJobStart())
                .age(response.getAge())
                .salary(response.getSalary().doubleValue())
                .build();
    }

    /**
     * Create a {@linkplain EmployeeControllerResponse} object without employee data because of failed response.
     *
     * @param status               the error status
     * @param errorResponseMessage the error response message
     * @return a {@linkplain EmployeeControllerResponse} object without employee data because of failed response.
     */
    public static EmployeeControllerResponse buildFailedResponse(final String status, final String errorResponseMessage) {
        return EmployeeControllerResponse.builder()
                .status(status)
                .errorMessage(errorResponseMessage)
                .build();
    }

    /**
     * Create a {@linkplain EmployeeControllerResponse} object without employee data because of unexpected response.
     *
     * @param error   the error status
     * @param message the error response message
     * @return a {@linkplain EmployeeControllerResponse} object without employee data because of unexpected response.
     */
    public static EmployeeControllerResponse buildUnexpectedResponse(final String error, final String message) {
        return EmployeeControllerResponse.builder()
                .status(error)
                .errorMessage(message)
                .build();
    }
}
