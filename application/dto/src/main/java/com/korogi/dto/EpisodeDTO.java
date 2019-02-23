package com.korogi.dto;

import static lombok.AccessLevel.PUBLIC;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = PUBLIC)
@NoArgsConstructor(access = PUBLIC)
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newEpisodeDTO")
public class EpisodeDTO extends BaseDTO {
    private static final String TYPE = "episode";

    private String name;
    private String synopsis;
    private Integer durationInMinutes;
    private LocalDate airDate;

    @Override
    public String type() {
        return TYPE;
    }
}