package com.erudio.bookservice.response;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CambioResponse implements Serializable {

    private Long id;
    private String from;
    private String to;
    private Double conversionFactor;
    private Double convertedValue;
    private String environment;

    public CambioResponse() {}

    public CambioResponse(Long id, String from, String to, Double conversionFactor, Double convertedValue, String environment) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionFactor = conversionFactor;
        this.convertedValue = convertedValue;
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public Double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Double convertedValue) {
        this.convertedValue = convertedValue;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CambioResponse cambio = (CambioResponse) o;
        return Objects.equals(getId(), cambio.getId()) && Objects.equals(getFrom(), cambio.getFrom()) && Objects.equals(getTo(), cambio.getTo()) && Objects.equals(getConversionFactor(), cambio.getConversionFactor()) && Objects.equals(getConvertedValue(), cambio.getConvertedValue()) && Objects.equals(getEnvironment(), cambio.getEnvironment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFrom(), getTo(), getConversionFactor(), getConvertedValue(), getEnvironment());
    }
}
