import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Anime } from "../../models/anime";
import "rxjs/add/operator/map";
import "rxjs/add/operator/do";
import { BaseRestService } from "../base-rest-service";

@Injectable()
export class AnimeService extends BaseRestService implements IAnimeService {
  constructor(
    private httpClient: HttpClient
  ) {
    super("anime");
  }

  findAllAnime(): Observable<Anime[]> {
    return this.httpClient
      .get(this.baseRestEndPoint)
      .map((data: any[]) => Anime.toAnimes(data));
  }

  findAnime(id: string): Observable<Anime> {
    return this.httpClient
      .get(`${this.baseRestEndPoint}/${id}`)
      .map((data: any) => Anime.toAnime(data));
  }
}

export interface IAnimeService {
  findAllAnime(): Observable<Anime[]>;
  findAnime(id: string): Observable<Anime>;
}
