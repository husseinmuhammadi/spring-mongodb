package com.javastudio.tutorial.web.api;

import com.javastudio.tutorial.repository.FormatConversionRepository;
import com.spg.uccs.model.format.FormatConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ConverterResource {

    @Autowired
    private FormatConversionRepository formatConversionRepository;

    @GetMapping(value = "/duplicate/{conversionName}", produces = {"application/json"})
    public ResponseEntity<FormatConversion> duplicateConvertor(@Validated @PathVariable(value = "conversionName") String conversionName) {
        Optional<FormatConversion> conversion = formatConversionRepository.findByName(conversionName);
        if (conversion.isPresent()) {
            FormatConversion conversion1 = conversion.get();
            return ResponseEntity.ok(conversion1);
        }
        return new ResponseEntity<FormatConversion>(HttpStatus.NOT_FOUND);
    }
}
