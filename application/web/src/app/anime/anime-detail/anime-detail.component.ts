import { Component, OnInit } from '@angular/core';
import { AnimeService } from "../../shared/services/anime/anime.service";
import { ActivatedRoute, Params } from "@angular/router";
import { EnrichedResource } from "../../shared/resources/final/enriched-resource";
import { Title } from "@angular/platform-browser";
import { AnimeDTO, AnimeRelation, EpisodeDTO, PersonageDTO } from "../../generated/models";

@Component({
  selector: 'korogi-anime-detail',
  templateUrl: './anime-detail.component.html',
  styleUrls: ['./anime-detail.component.scss']
})
export class AnimeDetailComponent implements OnInit {
  anime: AnimeDTO;
  prequal: AnimeDTO;
  sequal: AnimeDTO;
  episodes: EpisodeDTO[];
  personages: PersonageDTO[];

  constructor(
    private route: ActivatedRoute,
    private animeService: AnimeService,
    private titleService: Title
  ) { }

  ngOnInit() {
    this.route.params
      .flatMap((params: Params) => this.animeService.findAnime(params['id'], AnimeRelation.PREQUAL, AnimeRelation.SEQUAL, AnimeRelation.EPISODES, AnimeRelation.PERSONAGES))
      .subscribe((resource: EnrichedResource<AnimeDTO>) => this.setData(resource));
  }

  private setData(resource: EnrichedResource<AnimeDTO>): void {
    this.titleService.setTitle(`${resource.data.nameEnglish} - Korogi`);

    this.anime = resource.data;
    this.prequal = resource.embedded[AnimeRelation.PREQUAL];
    this.sequal = resource.embedded[AnimeRelation.SEQUAL];
    this.episodes = resource.embedded[AnimeRelation.EPISODES];
    this.personages = resource.embedded[AnimeRelation.PERSONAGES];
  }
}
