import { forkJoin as observableForkJoin, Observable, of as observableOf } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { EnrichedResource } from "../../resources/final/enriched-resource";
import { ModelMapperService } from "../../mappers/model-mapper.service";
import { SingleResource } from "../../resources/original/single-resource";
import { MultipleResources } from "../../resources/original/multiple-resources";
import { Resource } from "../../resources/original/resource";
import { ResourceUtil } from "../../utils/resource-util";
import { Relation } from "../../resources/relation";

@Injectable()
export class RelationLoaderService {

    constructor(
        private httpClient: HttpClient,
        private modelMapper: ModelMapperService
    ) {
    }

    public populateWithRelations<T>(enrichedResource: EnrichedResource<T>, relationsToLoad: string[]): Observable<EnrichedResource<T>> {
        let observables: Observable<any>[] = [observableOf(enrichedResource)].concat(this.createRelationHttpRequests(enrichedResource, relationsToLoad));

        return observableForkJoin(observables).pipe(
            map((value: any[]) => {
                let resourceToEnrich: EnrichedResource<T> = value[0];
                let loadedRelations: Relation[] = value.splice(1);

                for (let loadedRelation of loadedRelations) {
                    resourceToEnrich.embedded[loadedRelation.relation] = loadedRelation.data;
                }

                return resourceToEnrich;
            })
        );
    }

    private createRelationHttpRequests(enrichedResource: EnrichedResource<any>, relationsToLoad: string[]): Observable<any>[] {
        return enrichedResource.links
            .filter(link => relationsToLoad.indexOf(link.rel) > -1)
            .map(link =>
                this.httpClient.get<Resource>(link.href).pipe(
                    map(resource => new Relation(link.rel, this.mapToModel(resource))),
                    catchError(e => this.returnEmptyRelationOrThrowError(e, link.rel))
                )
            );
    }

    private mapToModel(resource: Resource): any {
        return ResourceUtil.isSingleResource(resource) ? this.modelMapper.mapToModel(<SingleResource>resource) : this.modelMapper.mapToModels(<MultipleResources>resource)
    }

    private returnEmptyRelationOrThrowError(e: HttpErrorResponse, rel: string): Observable<Relation> {
        if (e.status === 404) {
            return observableOf(new Relation(rel, undefined));
        } else {
            throw e;
        }
    }
}
