package com.korogi.dto;

import static lombok.AccessLevel.PUBLIC;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = PUBLIC)
@NoArgsConstructor(access = PUBLIC)
@Builder(builderMethodName = "newAnimeDTO")
public class AnimeDTO extends BaseDTO {
    private static final String TYPE = "anime";

    private String nameEnglish;
    private String nameRomanized;
    private LocalDate startAir;
    private LocalDate endAir;
    private String synopsis;
    private String backdropUrl;
    private String posterUrl;

    @Override
    public String type() {
        return TYPE;
    }
}
