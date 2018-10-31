import { Component, OnInit } from '@angular/core';
import { Anime } from "../../shared/models/anime";
import { AnimeService } from "../../shared/services/anime/anime.service";
import { ActivatedRoute, Params } from "@angular/router";
import { EnrichedResource } from "../../shared/resources/final/enriched-resource";
import { Episode } from "../../shared/models/episode";
import { Personage } from "../../shared/models/personage";
import { Title } from "@angular/platform-browser";

@Component({
  selector: 'korogi-anime-detail',
  templateUrl: './anime-detail.component.html',
  styleUrls: ['./anime-detail.component.scss']
})
export class AnimeDetailComponent implements OnInit {
  anime: Anime;
  prequal: Anime;
  sequal: Anime;
  episodes: Episode[];
  personages: Personage[];

  constructor(
    private route: ActivatedRoute,
    private animeService: AnimeService,
    private titleService: Title
  ) { }

  ngOnInit() {
    this.route.params
      .flatMap((params: Params) => this.animeService.findAnime(params['id'], Anime.RELATION_PREQUAL, Anime.RELATION_SEQUAL, Anime.RELATION_EPISODES, Anime.RELATION_PERSONAGES))
      .subscribe((resource: EnrichedResource<Anime>) => this.setData(resource));
  }

  private setData(resource: EnrichedResource<Anime>): void {
    this.titleService.setTitle(`${resource.data.nameEnglish} - Korogi`);

    this.anime = resource.data;
    this.prequal = resource.embedded[Anime.RELATION_PREQUAL];
    this.sequal = resource.embedded[Anime.RELATION_SEQUAL];
    this.episodes = resource.embedded[Anime.RELATION_EPISODES];
    this.personages = resource.embedded[Anime.RELATION_PERSONAGES];
  }
}
