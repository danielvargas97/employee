package com.prototype.api.employee.service;

import com.prototype.api.employee.common.model.controller.request.EmployeeControllerRequest;
import com.prototype.api.employee.common.model.controller.response.EmployeeControllerResponse;
import com.prototype.api.employee.common.model.soap.CreateEmployeeResponse;
import com.prototype.api.employee.infrastructure.out.soapclient.SOAPEmployeeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.prototype.api.employee.common.util.EmployeeRequestValidator.isValidRequest;
import static com.prototype.api.employee.service.EmployeeServiceMapper.buildEmployeeRequest;
import static com.prototype.api.employee.service.EmployeeServiceMapper.buildFailedResponse;
import static com.prototype.api.employee.service.EmployeeServiceMapper.buildSuccessfulResponse;
import static com.prototype.api.employee.service.EmployeeServiceMapper.buildUnexpectedResponse;
import static java.util.Objects.isNull;

/**
 * Employee service implementation
 *
 * @author Daniel
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * Employee SOAP client
     */
    private final SOAPEmployeeClient soapEmployeeClient;

    /**
     * Make a createEmployee request to the client and returns a createEmployee response
     *
     * @param request a {@linkplain EmployeeControllerRequest} object with endpoint request data.
     * @return a {@linkplain EmployeeControllerResponse} object with the response.
     */
    @Override
    public EmployeeControllerResponse createEmployee(final EmployeeControllerRequest request) {

        log.info("Creating employee with Type=[{}], Id[{}]", request.getDocumentType(), request.getDocumentNumber());

        final var responseBuilder = EmployeeControllerResponse.builder();

        if (isValidRequest(request, responseBuilder)) {


            return callEmployeeWebService(request);
        } else {
            return buildInvalidEmployeeResponse(responseBuilder);
        }
    }

    /**
     * Build a {@linkplain EmployeeControllerResponse} in case of and invalid field in {@linkplain EmployeeControllerRequest}.
     *
     * @param responseBuilder a {@linkplain EmployeeControllerResponse.EmployeeControllerResponseBuilder} that handles the response object.
     * @return a {@linkplain EmployeeControllerResponse} object with a message of validation error.
     */
    private EmployeeControllerResponse buildInvalidEmployeeResponse(final EmployeeControllerResponse.EmployeeControllerResponseBuilder responseBuilder) {
        final var response = responseBuilder.build();
        log.error("Validation error with employee: [{}]", response.getErrorMessage());
        return response;
    }

    /**
     * Call the SOAP Employee web service.
     *
     * @param request the controller request.
     * @return a {@linkplain EmployeeControllerResponse} object with WS response.
     */
    private EmployeeControllerResponse callEmployeeWebService(final EmployeeControllerRequest request) {

        try {
            final var soapClientResponse = soapEmployeeClient.createEmployee(buildEmployeeRequest(request));


            return buildEmployeeResponse(soapClientResponse);


        } catch (Exception ex) {

            log.warn("Error calling SOAP Employee creator ", ex);
            return buildUnexpectedResponse("Error", ex.getMessage());
        }

    }

    /**
     * Build a {@linkplain EmployeeControllerResponse} according to the received {@linkplain CreateEmployeeResponse} object.
     *
     * @param soapClientResponse the received {@linkplain CreateEmployeeResponse} object from the Web Service.
     * @return a {@linkplain EmployeeControllerResponse}.
     */
    private EmployeeControllerResponse buildEmployeeResponse(final CreateEmployeeResponse soapClientResponse) {
        log.info("Received response from SOAP Employee service: [{}]", soapClientResponse);
        if (isNull(soapClientResponse.getResponse())) {
            return buildFailedResponse(soapClientResponse.getStatus(), soapClientResponse.getErrorResponseMessage());
        } else {
            return buildSuccessfulResponse(soapClientResponse);
        }
    }
}
