package com.prototype.api.employee.common.model.controller.request;

import lombok.Getter;

import java.util.Arrays;

/**
 * Employee Document Type model.
 *
 * @author Daniel
 */
@Getter
public enum EmployeeDocumentType {

    /**
     * Cedula ciudadania
     */
    CC,

    /**
     * Tarjeta Identidad
     */
    TI,

    /**
     * Cedula extranjeria
     */
    CE,

    /**
     * Pasaporte
     */
    PP;

    /**
     * Validate the document type by searching in the enum.
     *
     * @param documentType the request document type.
     * @return the document type. In case of not found, its blank.
     */
    public static String getDocumentType(final String documentType) {
        return Arrays.stream(EmployeeDocumentType.values())
                .filter(employeeDocumentType -> employeeDocumentType.name().equals(documentType))
                .findFirst()
                .map(Enum::name)
                .orElse("");
    }
}
