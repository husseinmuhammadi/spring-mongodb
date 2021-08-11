package com.javastudio.tutorial.web.api;

import com.javastudio.tutorial.repository.jpa.FormatConversionJpaRepository;
import com.javastudio.tutorial.repository.mongo.FormatConversionMongoRepository;
import com.javastudio.tutorial.service.FormatConversionService;
import com.spg.uccs.model.conversion.FunctionCall;
import com.spg.uccs.model.conversion.PropertyMapping;
import com.spg.uccs.model.format.FormatConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ConverterResource {

    private Logger logger = LoggerFactory.getLogger(ConverterResource.class);

    @Autowired
    private FormatConversionService formatConversionService;


    @GetMapping(value = "/duplicate/{conversionName}", produces = {"application/json"})
    public ResponseEntity<FormatConversion> duplicateConvertor(@Validated @PathVariable(value = "conversionName") String conversionName) {
        Optional<FormatConversion> conversion = formatConversionService.findByName(conversionName);
        if (conversion.isPresent()) {
            FormatConversion conversion1 = conversion.get();
            formatConversionService.duplicate(conversion1);
            return ResponseEntity.ok(conversion1);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
