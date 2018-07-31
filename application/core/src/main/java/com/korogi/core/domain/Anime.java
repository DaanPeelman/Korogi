package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;
import static com.korogi.dto.AnimeDTO.newAnimeDTO;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.korogi.core.domain.enumeration.AnimeType;
import com.korogi.dto.AnimeDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Entity class representing an Anime in the database.
 *
 * @author Daan Peelman
 *
 * @see BaseEntity
 * @see AnimeBuilder
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true, exclude = { "sequal" })
@Entity
@Table(name = "ANIME")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_ANIME")
public class Anime extends BaseEntity {
    private static final long serialVersionUID = -2077542736467695994L;

    @NotNull
    @Enumerated(STRING)
    @Column(name = "anime_type")
    private AnimeType animeType;

    @NotBlank
    @Size(max = 512)
    @Column(name = "name_en")
    private String nameEnglish;

    @NotBlank
    @Size(max = 512)
    @Column(name = "name_ro")
    private String nameRomanized;

    @Column(name = "start_air")
    private LocalDate startAir;

    @Column(name = "end_air")
    private LocalDate endAir;

    @NotBlank
    @Column(name = "synopsis")
    private String synopsis;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "prequal_id")
    @LazyToOne(LazyToOneOption.NO_PROXY) // avoid N+1 queries (by using hibernate-enhance-maven-plugin) for bidirectional OneToOne mapping
    private Anime prequal;

    @OneToOne(fetch = LAZY, cascade = ALL, mappedBy = "prequal")
    @LazyToOne(LazyToOneOption.NO_PROXY) // avoid N+1 queries (by using hibernate-enhance-maven-plugin) for bidirectional OneToOne mapping
    private Anime sequal;

    @Builder(builderMethodName = "newAnime")
    public Anime(AnimeType animeType, String nameEnglish, String nameRomanized, LocalDate startAir, LocalDate endAir, String synopsis, Anime prequal, Anime sequal) {
        this.animeType = animeType;
        this.nameEnglish = nameEnglish;
        this.nameRomanized = nameRomanized;
        this.startAir = startAir;
        this.endAir = endAir;
        this.synopsis = synopsis;
        this.prequal = prequal;
        this.sequal = sequal;
    }

    public AnimeDTO toDTO() {
        return newAnimeDTO()
                .nameEnglish(nameEnglish)
                .nameRomanized(nameRomanized)
                .startAir(startAir)
                .endAir(endAir)
                .synopsis(synopsis)
                .backdropUrl("assets/images/backdrop.jpg")
                .posterUrl("assets/poster.jpg")
                .build();
    }
}