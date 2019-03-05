import { Inject, Injectable } from '@angular/core';
import { SingleResource } from "../../resources/original/single-resource";
import { MultipleResources } from "../../resources/original/multiple-resources";
import { IMapper } from "../../mapper/mapper";
import { MAPPERS } from "../../mapper/mapper.module";

@Injectable()
export class ModelMapperService {
    private mappersByType: Map<string, IMapper<any>> = new Map<string, IMapper<any>>();

    constructor(
        @Inject(MAPPERS) mappers: IMapper<any>[]
    ) {
        for (let mapper of mappers) {
            this.mappersByType.set(mapper.forType(), mapper);
        }
    }

    mapToModel<T>(resourceToMap: SingleResource): T {
        return this.mappersByType.get(resourceToMap.type).map(resourceToMap);
    }

    mapToModels<T>(resourceToMap: MultipleResources): T[] {
        return resourceToMap.content.map(resource => this.mapToModel(resource))
    }
}
