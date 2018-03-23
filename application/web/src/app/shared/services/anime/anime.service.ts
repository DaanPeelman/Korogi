import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Anime } from "../../models/anime";
import "rxjs/add/operator/map";
import "rxjs/add/operator/do";
import { BaseRestService } from "../base-rest-service";
import { EmbeddedResource } from "../../resources/embedded-resource";
import { PagedResources } from "../../resources/paged-resources";
import { PageMetaData } from "../../resources/page-meta-data";

@Injectable()
export class AnimeService extends BaseRestService implements IAnimeService {
  private static URL: string = "anime";

  constructor(
    private httpClient: HttpClient
  ) {
    super(AnimeService.URL);
  }

  findAllAnime(): Observable<PagedResources<Anime>> {
    return this.httpClient
      .get(this.baseRestEndPoint)
      .map((data: any[]) => new PagedResources<Anime>(this.toEmbeddedResources(data['content']), PageMetaData.toPageMetaData(data['page'])));
  }

  findAnime(id: string): Observable<EmbeddedResource<Anime>> {
    return this.httpClient
      .get(`${this.baseRestEndPoint}/${id}`)
      .map((data: any) => this.toEmbeddedResource(data));
  }

  private toEmbeddedResources(anime: Anime[]): EmbeddedResource<Anime>[] {
    return anime.map((singleAnime) => this.toEmbeddedResource(singleAnime));
  }

  private toEmbeddedResource(anime: Anime): EmbeddedResource<Anime> {
    return new EmbeddedResource(Anime.toAnime(anime), anime['embedded'], this.toLinksMap(anime['links']));
  }

  private toLinksMap(objects: any[]): { [key:string]: string; } {
    const map: { [key:string]: string; } = {};

    for (const object of objects) {
      map[object['rel']] = object['href'];
    }

    return map;
  }
}

export interface IAnimeService {
  findAllAnime(): Observable<PagedResources<Anime>>;
  findAnime(id: string): Observable<EmbeddedResource<Anime>>;
}
