import { Component, OnInit } from '@angular/core';
import { Anime } from "../../shared/models/anime";
import { AnimeService } from "../../shared/services/anime.service";
import { ActivatedRoute, Params } from "@angular/router";

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
    console.log("baka");

    this.route.params.subscribe((params: Params) => {
      this.animeService.findAnime(params['id']).subscribe((anime: Anime) => {
        this.anime = anime;
      });
    });
  }
}
