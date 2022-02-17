package com.prototype.api.employee.infrastructure.out.soapclient;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jaxb.JAXBContextFactory;
import feign.soap.SOAPDecoder;
import feign.soap.SOAPEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration to use SOAP in Feign client
 *
 * @author Daniel
 */
@Configuration
public class SOAPConfiguration {

    /**
     * JAXB factory
     */
    private static final JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder()
            .withMarshallerJAXBEncoding("UTF-8")
            .withMarshallerSchemaLocation("${}")
            .build();

    /**
     * Feign Encoder
     *
     * @return a {@linkplain Encoder} object.
     */
    @Bean
    public Encoder feignEncoder(){
        return new SOAPEncoder(jaxbFactory);
    }

    /**
     * Feign Decoder
     *
     * @return a {@linkplain Decoder} object.
     */
    @Bean
    public Decoder soapDecoder(){
        return new SOAPDecoder(jaxbFactory);
    }

}
