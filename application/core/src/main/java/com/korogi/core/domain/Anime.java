package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "newAnime")
@ToString(callSuper = true)
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
    private Anime prequal;

    @OneToOne(fetch = LAZY, cascade = ALL, mappedBy = "prequal")
    private Anime sequal;

    private Anime(AnimeBuilder builder) {
        super(builder);
        this.animeType = builder.animeType;
        this.nameEnglish = builder.nameEnglish;
        this.nameRomanized = builder.nameRomanized;
        this.startAir = builder.startAir;
        this.endAir = builder.endAir;
        this.synopsis = builder.synopsis;
        setPrequal(builder.prequal);
        setSequal(builder.sequal);
    }

    /**
     * Sets the prequal to the given prequal and sets its sequal to this Anime if needed.
     *
     * @param prequal the new prequal
     */
    void setPrequal(Anime prequal) {
        Anime previousPrequal = this.prequal;

        this.prequal = prequal;

        if (previousPrequal != null && previousPrequal.sequal == this) {
            previousPrequal.setSequal(null);
        }

        if (prequal != null && prequal.sequal != this) {
            prequal.setSequal(this);
        }
    }

    /**
     * Sets the sequal to the given sequal and sets it prequal to this Anime if needed.
     *
     * @param sequal the new sequal
     */
    void setSequal(Anime sequal) {
        Anime previousSequal = this.sequal;
        this.sequal = sequal;

        if (previousSequal != null && previousSequal.prequal == this) {
            previousSequal.setPrequal(null);
        }

        if (sequal != null && sequal.prequal != this) {
            sequal.setPrequal(this);
        }
    }

    /**
     * Creates a new AnimeBuilder to create a new Anime.
     *
     * @return a new AnimeBuilder
     */
    public static AnimeBuilder newAnime() {
        return new AnimeBuilder();
    }

    /**
     * Creates a new AnimeBuilder with the fields of a given Anime.<br />
     * <br />
     * Use this to create a copy or update an Anime.
     *
     * @param anime the Anime instance to copy the field values from
     *
     * @return a new AnimeBuilder instantiated with the same field values as the Anime that got passed
     */
    public static AnimeBuilder newAnime(Anime anime) {
        return new AnimeBuilder(anime);
    }

    /**
     * Builder class for building <code>Anime</code> entities.
     *
     * @author Daan Peelman
     *
     * @see Anime
     * @see BaseEntityBuilder
     */
    public static class AnimeBuilder extends BaseEntityBuilder<Anime> {
        private AnimeBuilder() {
            super();
        }

        private AnimeBuilder(Anime anime) {
            super(anime);
            this.animeType = anime.animeType;
            this.nameEnglish = anime.nameEnglish;
            this.nameRomanized = anime.nameRomanized;
            this.startAir = anime.startAir;
            this.endAir = anime.endAir;
            this.synopsis = anime.synopsis;
            this.prequal = anime.prequal;
            this.sequal = anime.sequal;
        }

        @Override
        public Anime build() {
            Anime anime = new Anime(this);
            anime.validate();

            return anime;
        }
    }
}