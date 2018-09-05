package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.korogi.core.domain.enumeration.AnimeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 * Entity class representing an Anime in the database.
 *
 * @author Daan Peelman
 *
 * @see BaseEntity
 * @see AnimeBuilder
 */
@Getter
@Setter(value = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PUBLIC)
@Builder(builderMethodName = "newAnime")
@ToString(callSuper = true, exclude = { "sequal", "episodes", "personages" })
@Entity
@Table(name = "ANIME")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_ANIME")
public class Anime extends BaseEntity {
    private static final long serialVersionUID = -2472387246309958379L;

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

    @URL
    @NotBlank
    @Size(max = 512)
    @Column(name = "backdrop_url")
    private String backdropUrl;

    @URL
    @NotBlank
    @Size(max = 512)
    @Column(name = "poster_url")
    private String posterUrl;

    @OneToOne(fetch = LAZY, cascade = PERSIST)
    @JoinColumn(name = "prequal_id")
    @LazyToOne(LazyToOneOption.NO_PROXY) // avoid N+1 queries (by using hibernate-enhance-maven-plugin) for bidirectional OneToOne mapping
    private Anime prequal;

    @OneToOne(fetch = LAZY, cascade = PERSIST, mappedBy = "prequal")
    @LazyToOne(LazyToOneOption.NO_PROXY) // avoid N+1 queries (by using hibernate-enhance-maven-plugin) for bidirectional OneToOne mapping
    private Anime sequal;

    @Builder.Default
    @OneToMany(fetch = LAZY, mappedBy = "anime", cascade = ALL)
    private List<Episode> episodes = new ArrayList<>();

    @Builder.Default
    @Size(min = 1)
    @ManyToMany(fetch = LAZY, cascade = PERSIST)
    @JoinTable(
            name = "ANIME_PERSONAGES",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "personage_id")
    )
    private List<Personage> personages = new ArrayList<>();

    public boolean hasPrequal() {
        return this.prequal != null;
    }

    public boolean hasSequal() {
        return this.sequal != null;
    }
}