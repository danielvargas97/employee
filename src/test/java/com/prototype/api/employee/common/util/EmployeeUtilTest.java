package com.prototype.api.employee.common.util;

import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;

import static com.prototype.api.employee.common.util.EmployeeUtil.extractDateFromLocalDate;
import static com.prototype.api.employee.common.util.EmployeeUtil.extractDateFromXml;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for EmployeeUtil class
 *
 * @author Daniel
 */
class EmployeeUtilTest {


    @Test
    void shouldConvertLocalDateToXMLGregorianDateSuccessfully(){
        final var date = LocalDate.of(2021, 11, 21);
        final var result = extractDateFromLocalDate(date);

        assertThat(result).isNotNull();
        assertThat(result.getDay()).isEqualTo(21);
        assertThat(result.getMonth()).isEqualTo(11);
        assertThat(result.getYear()).isEqualTo(2021);
    }

    @Test
    void shouldConvertXMLGregorianDateToLocalDateSuccessfully() throws DatatypeConfigurationException {
        final var date = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2021,11,21, -5);
        final var result = extractDateFromXml(date);

        assertThat(result).isNotNull();
        assertThat(result.getDayOfMonth()).isEqualTo(21);
        assertThat(result.getMonthValue()).isEqualTo(11);
        assertThat(result.getYear()).isEqualTo(2021);
    }
}
