import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Anime } from "../models/anime";
import { environment } from "../../../environments/environment";
import "rxjs/add/operator/map";
import "rxjs/add/operator/do";

@Injectable()
export class AnimeService implements IAnimeService {
  private baseUrl: string;

  constructor(
    private httpClient: HttpClient
  ) {
    this.baseUrl = environment.restUrl + "/anime";
  }

  findAllAnime(): Observable<Anime[]> {
    return this.httpClient
      .get(this.baseUrl)
      .map((data: any[]) => Anime.toAnimes(data));
  }

  findAnime(id: string): Observable<Anime> {
    return this.httpClient
      .get(`${this.baseUrl}/${id}`)
      .map((data: any) => Anime.toAnime(data));
  }
}

export interface IAnimeService {
  findAllAnime(): Observable<Anime[]>;
  findAnime(id: string): Observable<Anime>;
}
