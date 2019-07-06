package com.korogi.rest.mapper.personage;

import static com.korogi.core.domain.testdata.PersonageTestData.okabeRintarou_notPersisted;
import static org.assertj.core.api.Assertions.assertThat;

import com.korogi.core.domain.Personage;
import com.korogi.core.util.FieldAssertionUtil;
import com.korogi.dto.PersonageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * @author Daan Peelman
 */
class PersonageResourceMapperTest {
    private PersonageResourceMapperImpl mapper = new PersonageResourceMapperImpl();

    @Test
    void toDTOResource() throws Exception {
        Personage personageToMap = okabeRintarou_notPersisted().build();
        Resource<PersonageDTO> mappedResource = mapper.toDTOResource(personageToMap);

        assertThat(mappedResource).isNotNull();
        assertThat(mappedResource.getContent()).isNotNull();
        new FieldAssertionUtil(personageToMap, mappedResource.getContent())
                .expectFieldValue("TYPE", "personage")
                .assertAllFieldValuesAreEqual();

        assertThat(mappedResource.getLinks())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(
                        new Link("/personages/{id}", "self")
                );
    }
}