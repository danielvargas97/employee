package com.prototype.api.employee.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

/**
 * Employee Utils class
 *
 * @author Daniel
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class EmployeeUtil {

    /**
     * Extract {@linkplain XMLGregorianCalendar} from a {@linkplain LocalDate} object.
     *
     * @param localDate the {@linkplain LocalDate} object.
     * @return a {@linkplain XMLGregorianCalendar} object.
     */
    public static XMLGregorianCalendar extractDateFromLocalDate(final LocalDate localDate) {

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
        } catch (Exception ex) {
            log.warn("Error during converting LocalDate to XMLGregorianCalendar, Message", ex);
            return null;
        }

    }

    /**
     * Extract {@linkplain LocalDate} from a {@linkplain XMLGregorianCalendar} object.
     *
     * @param xmlGregorianCalendar a {@linkplain XMLGregorianCalendar} object.
     * @return a  {@linkplain LocalDate} object with the date converted.
     */
    public static LocalDate extractDateFromXml(final XMLGregorianCalendar xmlGregorianCalendar) {
        return LocalDate.of(xmlGregorianCalendar.getYear(), xmlGregorianCalendar.getMonth(), xmlGregorianCalendar.getDay());
    }

}
