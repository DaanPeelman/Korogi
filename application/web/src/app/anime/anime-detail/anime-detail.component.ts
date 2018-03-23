import { Component, OnInit } from '@angular/core';
import { Anime } from "../../shared/models/anime";
import { AnimeService } from "../../shared/services/anime/anime.service";
import { ActivatedRoute, Params } from "@angular/router";
import { EmbeddedResource } from "../../shared/resources/embedded-resource";

@Component({
  selector: 'korogi-anime-detail',
  templateUrl: './anime-detail.component.html',
  styleUrls: ['./anime-detail.component.scss']
})
export class AnimeDetailComponent implements OnInit {
  anime: Anime;

  constructor(
    private route: ActivatedRoute,
    private animeService: AnimeService
  ) { }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.animeService.findAnime(params['id']).subscribe((resource: EmbeddedResource<Anime>) => {
        this.anime = resource.content;
      });
    });
  }
}
