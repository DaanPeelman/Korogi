package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;
import static com.korogi.dto.PersonageDTO.newPersonageDTO;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.korogi.dto.PersonageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 * Entity class representing a Personage in the database.
 *
 * @author Daan Peelman
 *
 * @see BaseEntity
 * @see PersonageBuilder
 */
@Getter
@Setter(value = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PUBLIC)
@Builder(builderMethodName = "newPersonage")
@ToString(callSuper = true)
@Entity
@Table(name = "PERSONAGES")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_PERSONAGE")
public class Personage extends BaseEntity {
    @NotBlank
    @Size(max = 128)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(max = 128)
    @Column(name = "last_name")
    private String lastName;

    @URL
    @Size(max = 512)
    @Column(name = "photo_url")
    private String photoUrl;

    @Builder.Default
    @Size(min = 1)
    @ManyToMany(fetch = LAZY, cascade = PERSIST)
    @JoinTable(
            name = "ANIME_PERSONAGES",
            joinColumns = @JoinColumn(name = "personage_id"),
            inverseJoinColumns = @JoinColumn(name = "anime_id")
    )
    private List<Anime> anime = new ArrayList<>();

    public PersonageDTO toDTO() {
        return newPersonageDTO()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .photoUrl(this.photoUrl)
                .build();
    }
}