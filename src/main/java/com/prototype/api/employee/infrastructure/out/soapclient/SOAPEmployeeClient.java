package com.prototype.api.employee.infrastructure.out.soapclient;


import com.prototype.api.employee.common.model.soap.CreateEmployeeRequest;
import com.prototype.api.employee.common.model.soap.CreateEmployeeResponse;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * SOAP client for consuming Employee web service
 *
 * @author Daniel
 */
@FeignClient(name = "employee", url = "${soap-client.url}", configuration = SOAPConfiguration.class)
public interface SOAPEmployeeClient {

    /**
     * Create a request to create an employee to WebService
     *
     * @param request a {@linkplain CreateEmployeeRequest} object with request data.
     * @return a {@linkplain CreateEmployeeResponse} with the response of the service.
     */
    @PostMapping( value = "/ws" , consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    CreateEmployeeResponse createEmployee(CreateEmployeeRequest request);
}
