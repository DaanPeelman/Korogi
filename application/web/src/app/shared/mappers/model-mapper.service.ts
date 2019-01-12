import { Injectable } from '@angular/core';
import { AnimeMapper } from "./anime/anime-mapper";
import { PersonageMapper } from "./personage/personage-mapper";
import { EpisodeMapper } from "./episode/episode-mapper";
import { SingleResource } from "../resources/original/single-resource";
import { MultipleResources } from "../resources/original/multiple-resources";

@Injectable()
export class ModelMapperService {
    private mappers: any[] = [];

    constructor() {
        this.mappers["anime"] = new AnimeMapper();
        this.mappers["episode"] = new EpisodeMapper();
        this.mappers["personage"] = new PersonageMapper();
    }

    mapToModel<T>(resourceToMap: SingleResource): T {
        return this.mappers[resourceToMap.type].map(resourceToMap);
    }

    mapToModels<T>(resourceToMap: MultipleResources): T[] {
        return resourceToMap.content.map(resource => this.mapToModel(resource))
    }
}

export interface IMapper<T> {
    map(resource: any): T;
}
