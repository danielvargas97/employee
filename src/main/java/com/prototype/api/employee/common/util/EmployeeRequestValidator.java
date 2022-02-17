package com.prototype.api.employee.common.util;

import com.prototype.api.employee.common.model.controller.request.EmployeeControllerRequest;
import com.prototype.api.employee.common.model.controller.response.EmployeeControllerResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.prototype.api.employee.common.model.controller.request.EmployeeDocumentType.getDocumentType;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Employee controller request validator
 *
 * @author Daniel
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeRequestValidator {

    /**
     * Non valid document type message.
     */
    public static final String NON_VALID_DOC_TYPE = "The document type is not valid";

    /**
     * Nonv valid document number message.
     */
    public static final String NON_VALID_DOC_NUMBER = "The document number is not valid";

    /**
     * Non valid salary message.
     */
    public static final String NON_VALID_SALARY = "The salary is not valid";

    /**
     * Non valid firstname.
     */
    public static final String NON_VALID_FIRST_NAME = "The first name is not valid";

    /**
     * Non valid lastname.
     */
    public static final String NON_VALID_LAST_NAME = "The last name is not valid";

    /**
     * Non valid birthdate message.
     */
    public static final String NON_VALID_BIRTH_DATE = "The birth date is not valid";

    /**
     * Non valid job start date message.
     */
    public static final String NON_VALID_JOB_START_DATE = "The job start date is not valid";

    /**
     * Non valid job title message.
     */
    public static final String NON_VALID_CARGO = "The cargo is not valid";

    /**
     * Error status message.
     */
    public static final String ERROR_STATUS = "Error";

    /**
     * Validate the employee request.
     *
     * @param request         the employee request.
     * @param responseBuilder the employee response builder.
     * @return flag that indicates if the request is valid.
     */
    public static boolean isValidRequest(final EmployeeControllerRequest request, final EmployeeControllerResponse.EmployeeControllerResponseBuilder responseBuilder) {
        return isValidName(request, responseBuilder) &&
                isValidBirthDate(request, responseBuilder) &&
                isValidJobStartDate(request, responseBuilder) &&
                isValidSalary(request, responseBuilder) &&
                isValidCargo(request, responseBuilder) &&
                isValidDocumentType(request, responseBuilder) &&
                isValidDocumentNumber(request, responseBuilder);
    }

    /**
     * Validate the name in the request
     *
     * @param request         the employee request.
     * @param responseBuilder the employee response builder.
     * @return flag that indicates if the name is valid.
     */
    private static boolean isValidName(final EmployeeControllerRequest request, final EmployeeControllerResponse.EmployeeControllerResponseBuilder responseBuilder) {

        if (isNull(request.getFirstName()) || isBlank(request.getFirstName())) {
            responseBuilder.status(ERROR_STATUS);
            responseBuilder.errorMessage(NON_VALID_FIRST_NAME);
            return false;
        }

        if (isNull(request.getFirstName()) || isBlank(request.getLastName())) {
            responseBuilder.status(ERROR_STATUS);
            responseBuilder.errorMessage(NON_VALID_LAST_NAME);
            return false;
        }

        return true;
    }

    /**
     * Validate the salary in the request
     *
     * @param request         the employee request.
     * @param responseBuilder the employee response builder.
     * @return flag that indicates if the salary is valid.
     */
    private static boolean isValidSalary(final EmployeeControllerRequest request, final EmployeeControllerResponse.EmployeeControllerResponseBuilder responseBuilder) {

        if (isNegative(request.getSalary())) {
            responseBuilder.status(ERROR_STATUS);
            responseBuilder.errorMessage(NON_VALID_SALARY);
            return false;
        }

        return true;
    }

    /**
     * Validate the birth date in the request
     *
     * @param request         the employee request.
     * @param responseBuilder the employee response builder.
     * @return flag that indicates if the birth date is valid.
     */
    private static boolean isValidBirthDate(final EmployeeControllerRequest request, final EmployeeControllerResponse.EmployeeControllerResponseBuilder responseBuilder) {
        if (isNull(request.getBirthDate())) {
            responseBuilder.status(ERROR_STATUS);
            responseBuilder.errorMessage(NON_VALID_BIRTH_DATE);
            return false;
        }
        return true;
    }

    /**
     * Validate the job start date in the request
     *
     * @param request         the employee request.
     * @param responseBuilder the employee response builder.
     * @return flag that indicates if the job start date is valid.
     */
    private static boolean isValidJobStartDate(final EmployeeControllerRequest request, final EmployeeControllerResponse.EmployeeControllerResponseBuilder responseBuilder) {
        if (isNull(request.getJobStartDate()) || isGreaterThanToday(request) || isBeforeBirth(request)) {
            responseBuilder.status(ERROR_STATUS);
            responseBuilder.errorMessage(NON_VALID_JOB_START_DATE);
            return false;
        }
        return true;
    }

    /**
     * Validate the cargo (job title) in the request
     *
     * @param request         the employee request.
     * @param responseBuilder the employee response builder.
     * @return flag that indicates if the cargo is valid.
     */
    private static boolean isValidCargo(final EmployeeControllerRequest request, final EmployeeControllerResponse.EmployeeControllerResponseBuilder responseBuilder) {
        if (isNull(request.getCargo()) || isBlank(request.getCargo())) {
            responseBuilder.status(ERROR_STATUS);
            responseBuilder.errorMessage(NON_VALID_CARGO);
            return false;
        }
        return true;
    }

    /**
     * Validate the document type in the request
     *
     * @param request         the employee request.
     * @param responseBuilder the employee response builder.
     * @return flag that indicates if the document type is valid.
     */
    private static boolean isValidDocumentType(final EmployeeControllerRequest request, final EmployeeControllerResponse.EmployeeControllerResponseBuilder responseBuilder) {
        if(isNull(request.getDocumentType()) || isBlank(getDocumentType(request.getDocumentType()))){
            responseBuilder.status(ERROR_STATUS);
            responseBuilder.errorMessage(NON_VALID_DOC_TYPE);
            return false;
        }
        return true;
    }

    /**
     * Validate the document number in the request
     *
     * @param request         the employee request.
     * @param responseBuilder the employee response builder.
     * @return flag that indicates if the document number is valid.
     */
    private static boolean isValidDocumentNumber(final EmployeeControllerRequest request, final EmployeeControllerResponse.EmployeeControllerResponseBuilder responseBuilder) {

        if (isNull(request.getDocumentNumber()) || !request.getDocumentNumber().matches("[A-Za-z0-9]+")) {
            responseBuilder.status(ERROR_STATUS);
            responseBuilder.errorMessage(NON_VALID_DOC_NUMBER);
            return false;
        }

        return true;
    }


    /**
     * Validates if the date is before the employee birth date
     *
     * @param request the employee request object.
     * @return flag that determines if the date is before the employee birth date.
     */
    private static boolean isBeforeBirth(final EmployeeControllerRequest request) {
        return request.getJobStartDate().isBefore(request.getBirthDate());
    }

    /**
     * Validates if the date is greater than today.
     *
     * @param request the employee request object.
     * @return flag that determines if the date is greater than today.
     */
    private static boolean isGreaterThanToday(final EmployeeControllerRequest request) {
        return request.getJobStartDate().isAfter(LocalDate.now());
    }

    /**
     * Validates if the salary is negative.
     *
     * @param salary the employee salary.
     * @return flag that determines if the salary is not positive.
     */
    private static boolean isNegative(final double salary) {
        return salary <= 0;
    }
}