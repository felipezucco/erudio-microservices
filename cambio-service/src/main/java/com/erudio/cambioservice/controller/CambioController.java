package com.erudio.cambioservice.controller;

import com.erudio.cambioservice.model.Cambio;
import com.erudio.cambioservice.repository.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "Cambio Endpoint")
@RestController
@RequestMapping("cambio-service")
public class CambioController {

    @Autowired
    private Environment environment;

    @Autowired
    private CambioRepository repository;

    @Operation(summary = "Find cambio through amount/from/to")
    @GetMapping(value = "/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable("amount") BigDecimal amount,
                            @PathVariable("from") String from,
                            @PathVariable("to") String to) {
        Cambio cambio = repository.findByFromAndTo(from, to);
        if (cambio == null) {
            throw new RuntimeException("Currency unsupported");
        }

        String port = environment.getProperty("local.server.port");
        BigDecimal conversionFactory = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactory.multiply(amount);
        cambio.setEnvironment(port);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
        return cambio;
    }
}
