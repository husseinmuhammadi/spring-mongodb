package com.javastudio.tutorial.service;

import com.javastudio.tutorial.repository.jpa.FormatConversionJpaRepository;
import com.javastudio.tutorial.repository.mongo.FormatConversionMongoRepository;
import com.spg.uccs.model.conversion.FunctionCall;
import com.spg.uccs.model.conversion.PropertyMapping;
import com.spg.uccs.model.format.FormatConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FormatConversionService {

    private Logger logger = LoggerFactory.getLogger(FormatConversionService.class);

    @Autowired
    private FormatConversionMongoRepository mongoRepository;

    @Autowired
    private FormatConversionJpaRepository jpaRepository;

    public Optional<FormatConversion> findByName(String name) {
        return mongoRepository.findByName(name);
    }

    public List<FormatConversion> findByParentConversionId(String parentConversionId) {
        return mongoRepository.findByParentConversionId(parentConversionId);
    }

    public void duplicate(FormatConversion conversion) {
        String newId = UUID.randomUUID().toString();
        String newName = conversion.getName() + "_Duplicated";

        duplicateChild(conversion, newId);

        conversion.setId(newId);
        conversion.setName(newName);
        conversion.setParentConversionId(null);
        conversion.setTag("duplicated");

        logger.info("preparing id: {} -> {}, name: {} -> {}", conversion.getId(), newId, conversion.getName(), newName);

        jpaRepository.save(conversion);
        mongoRepository.save(conversion);

        logger.info("completed id: {} -> {}, name: {} -> {}", conversion.getId(), newId, conversion.getName(), newName);
    }

    // TODO: Add extra code for rollback on failure situations
    private void duplicateChild(FormatConversion parent, String newParentId) {
        List<FormatConversion> children = mongoRepository.findByParentConversionId(parent.getId());

        for (FormatConversion conversion : children) {
            String newId = UUID.randomUUID().toString();
            String newName = conversion.getName() + "_Duplicated";

            for (FunctionCall function : parent.getFunctions()) {
                if (function.getName().contains(conversion.getName())) {
                    function.setName(function.getName().replace(conversion.getName(), newName));
                }
            }
            for (PropertyMapping mapping : parent.getMappings()) {
                if (mapping.getSource().contains(conversion.getName())) {
                    mapping.setSource(mapping.getSource().replace(conversion.getName(), newName));
                }
                if (mapping.getTarget().contains(conversion.getName())) {
                    mapping.setTarget(mapping.getTarget().replace(conversion.getName(), newName));
                }
            }

            duplicateChild(conversion, newId);

            conversion.setId(newId);
            conversion.setName(newName);
            conversion.setParentConversionId(newParentId);
            conversion.setTag("duplicated");

            logger.info("preparing id: {} -> {}, name: {} -> {}", parent.getId(), newId, parent.getName(), newName);

            jpaRepository.save(conversion);
            mongoRepository.save(conversion);

            logger.info("completed id: {} -> {}, name: {} -> {}", parent.getId(), newId, parent.getName(), newName);
        }
    }
}

