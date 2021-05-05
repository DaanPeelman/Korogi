package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity class representing a Personage in the database.
 *
 * @author Daan Peelman
 * @see BaseEntity
 * @see PersonageBuilder
 */
@Data
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PUBLIC)
@Builder(builderMethodName = "newPersonage")
@EqualsAndHashCode(callSuper = true, exclude = "anime")
@ToString(callSuper = true)

@Entity
@Table(name = "PERSONAGES")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_PERSONAGE", allocationSize = 1)
public class Personage extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "photo_url")
    private String photoUrl;

    @Builder.Default
    @ManyToMany(fetch = LAZY, cascade = PERSIST)
    @JoinTable(
        name = "ANIME_PERSONAGES",
        joinColumns = @JoinColumn(name = "personage_id"),
        inverseJoinColumns = @JoinColumn(name = "anime_id")
    )
    @OrderBy(Anime_.ID)
    private List<Anime> anime = new ArrayList<>();
}