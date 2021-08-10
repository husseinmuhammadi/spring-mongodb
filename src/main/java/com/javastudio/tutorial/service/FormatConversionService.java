package com.javastudio.tutorial.service;

import com.javastudio.tutorial.repository.jpa.FormatConversionJpaRepository;
import com.javastudio.tutorial.repository.mongo.FormatConversionMongoRepository;
import com.spg.uccs.model.format.FormatConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FormatConversionService {
    @Autowired
    private FormatConversionMongoRepository mongoRepository;

    @Autowired
    private FormatConversionJpaRepository jpaRepository;

    public Optional<FormatConversion> findByName(String name) {
        return mongoRepository.findByName(name);
    }

    public void save(FormatConversion conversion) {
        jpaRepository.save(conversion);
        mongoRepository.save(conversion);
    }

    public List<FormatConversion> findByParentConversionId(String parentConversionId) {
        return mongoRepository.findByParentConversionId(parentConversionId);
    }
}
