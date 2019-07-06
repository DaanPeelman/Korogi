package com.korogi.rest.mapper.episode;

import static com.korogi.core.domain.testdata.EpisodeTestData.steinsGateEpisode1_notPeristed;
import static org.assertj.core.api.Assertions.assertThat;

import com.korogi.core.domain.Episode;
import com.korogi.core.util.FieldAssertionUtil;
import com.korogi.dto.EpisodeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * @author Daan Peelman
 */
class EpisodeResourceMapperTest {
    private EpisodeResourceMapperImpl mapper = new EpisodeResourceMapperImpl();

    @Test
    void toDTOResource() throws Exception {
        Episode episodeToMap = steinsGateEpisode1_notPeristed().build();
        Resource<EpisodeDTO> mappedResource = mapper.toDTOResource(episodeToMap);

        assertThat(mappedResource).isNotNull();
        assertThat(mappedResource.getContent()).isNotNull();
        new FieldAssertionUtil(episodeToMap, mappedResource.getContent())
                .expectFieldValue("TYPE", "episode")
                .assertAllFieldValuesAreEqual();

        assertThat(mappedResource.getLinks())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(
                        new Link("/episodes/{id}", "self")
                );
    }
}