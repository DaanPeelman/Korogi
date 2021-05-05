import { map, mergeMap } from "rxjs/operators";
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { BaseRestService } from "../base-rest-service";
import { PagedResources } from "../../resources/final/paged-resources";
import { PageMetaData } from "../../resources/page-meta-data";
import { RelationLoaderService } from "../relation-loader/relation-loader.service";
import { EnrichedResource } from "../../resources/final/enriched-resource";
import { ModelMapperService } from "../model-mapper/model-mapper.service";
import { MultipleResources } from "../../resources/original/multiple-resources";
import { SingleResource } from "../../resources/original/single-resource";
import { AnimeDTO } from "../../models/anime-dto";

@Injectable()
export class AnimeService extends BaseRestService implements IAnimeService {
    private static URL: string = "anime";

    constructor(
        private httpClient: HttpClient,
        private modelMapper: ModelMapperService,
        private relationLoaderService: RelationLoaderService
    ) {
        super(AnimeService.URL);
    }

    findAllAnime(): Observable<PagedResources<AnimeDTO>> {
        return this.httpClient
                   .get<MultipleResources>(this.baseRestEndPoint).pipe(
                map(data => new PagedResources<AnimeDTO>(
                    this.modelMapper.mapToModels(data),
                    PageMetaData.toPageMetaData(data.page)
                ))
            );
    }

    findAnime(
        id: string,
        ...relationsToLoad: string[]
    ): Observable<EnrichedResource<AnimeDTO>> {
        return this.httpClient
                   .get<SingleResource>(`${this.baseRestEndPoint}/${id}`).pipe(
                map(res => new EnrichedResource<AnimeDTO>(this.modelMapper.mapToModel(res), res.links)),
                mergeMap(resource => this.relationLoaderService.populateWithRelations(resource, relationsToLoad))
            );
    }
}

export interface IAnimeService {
    findAllAnime(): Observable<PagedResources<AnimeDTO>>;

    findAnime(
        id: string,
        ...relationsToLoad: string[]
    ): Observable<EnrichedResource<AnimeDTO>>;
}
