package com.korogi.core.domain.testdata;

import static com.korogi.core.domain.Episode.newEpisode;

import java.time.LocalDate;
import com.korogi.core.domain.Episode;

public class EpisodeTestData {
    public static Episode.EpisodeBuilder steinsGateEpisode1_notPeristed() {
        return newEpisode()
            .name("Prologue of the Beginning and Ending")
            .synopsis("Prologue of the Beginning and Ending synopsis here")
            .durationInMinutes(24)
            .airDate(LocalDate.of(2011, 4, 5));
    }
}