package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;
import static org.hibernate.annotations.LazyToOneOption.NO_PROXY;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyToOne;

/**
 * Entity class representing an Episode in the database.
 *
 * @author Daan Peelman
 *
 * @see BaseEntity
 * @see EpisodeBuilder
 */
@Data
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PUBLIC)
@Builder(builderMethodName = "newEpisode")
@EqualsAndHashCode(callSuper = true, exclude = "anime")
@ToString(callSuper = true)

@Entity
@Table(name = "EPISODES")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_EPISODE", allocationSize = 1)
public class Episode extends BaseEntity {
    private static final long serialVersionUID = -970686580041286530L;

    @ManyToOne(fetch = LAZY, cascade = PERSIST)
    @JoinColumn(name = "anime_id", nullable = false)
    @LazyToOne(NO_PROXY) // avoid N+1 queries (by using hibernate-enhance-maven-plugin) for bidirectional OneToOne mapping
    private Anime anime;

    @Column(name = "name")
    private String name;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

    @Column(name = "air_date")
    private LocalDate airDate;
}