package com.javastudio.tutorial.repository.jpa;

import com.spg.uccs.model.format.FormatConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormatConversionJpaRepository extends JpaRepository<FormatConversion, String> {
    Optional<FormatConversion> findByName(String name);

    List<FormatConversion> findByParentConversionId(String parentConversionId);
}
