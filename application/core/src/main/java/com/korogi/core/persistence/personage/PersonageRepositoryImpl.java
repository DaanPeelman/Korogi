package com.korogi.core.persistence.personage;

import com.korogi.core.domain.Personage;
import com.korogi.core.persistence.BaseEntityRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * Implementation of PersonageRepository.
 *
 * @author Daan Peelman
 *
 * @see PersonageRepository
 * @see Personage
 */
@Repository
public class PersonageRepositoryImpl extends BaseEntityRepositoryImpl<Personage> implements PersonageRepository {
    public PersonageRepositoryImpl() {
        super(Personage.class);
    }
}