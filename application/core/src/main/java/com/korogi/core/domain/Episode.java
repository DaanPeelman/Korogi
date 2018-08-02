package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;
import static com.korogi.dto.EpisodeDTO.newEpisodeDTO;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.korogi.dto.EpisodeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Entity class representing an Episode in the database.
 *
 * @author Daan Peelman
 *
 * @see BaseEntity
 * @see EpisodeBuilder
 */
@Getter
@Setter(value = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PUBLIC)
@Builder(builderMethodName = "newEpisode")
@ToString(callSuper = true)
@Entity
@Table(name = "EPISODES")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_EPISODE")
public class Episode extends BaseEntity {
    private static final long serialVersionUID = -970686580041286530L;

    @NotNull
    @ManyToOne(fetch = LAZY, cascade = PERSIST)
    @JoinColumn(name = "anime_id", nullable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY) // avoid N+1 queries (by using hibernate-enhance-maven-plugin) for bidirectional OneToOne mapping
    private Anime anime;

    @NotBlank
    @Size(max = 128)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "synopsis")
    private String synopsis;

    @NotNull
    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

    @NotNull
    @Column(name = "air_date")
    private LocalDate airDate;

    public EpisodeDTO toDTO() {
        return newEpisodeDTO()
                .name(this.name)
                .synopsis(this.synopsis)
                .durationInMinutes(this.durationInMinutes)
                .airDate(this.airDate)
                .build();
    }
}