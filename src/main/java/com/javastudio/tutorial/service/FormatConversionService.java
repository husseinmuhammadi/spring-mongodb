package com.javastudio.tutorial.service;

import com.javastudio.tutorial.repository.FormatConversionRepository;
import com.spg.uccs.model.format.FormatConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormatConversionService {

    @Autowired
    private FormatConversionRepository formatConversionRepository;

    public Optional<FormatConversion> findByName(String name) {
        return formatConversionRepository.findByName(name);
    }

}
