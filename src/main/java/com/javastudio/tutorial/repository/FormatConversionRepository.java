package com.javastudio.tutorial.repository;

import com.spg.uccs.model.format.FormatConversion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormatConversionRepository extends MongoRepository<FormatConversion, String> {
    Optional<FormatConversion> findByName(String name);
}
