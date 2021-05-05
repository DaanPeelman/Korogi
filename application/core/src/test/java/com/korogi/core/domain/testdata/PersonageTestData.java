package com.korogi.core.domain.testdata;

import static com.korogi.core.domain.Personage.newPersonage;

import com.korogi.core.domain.Personage;

public class PersonageTestData {
    public static Personage.PersonageBuilder okabeRintarou_notPersisted() {
        return newPersonage()
            .firstName("Okabe")
            .lastName("Rintarou")
            .photoUrl("http://photo.url.be");
    }
}