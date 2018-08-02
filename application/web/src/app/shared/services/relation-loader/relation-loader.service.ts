import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/map";
import 'rxjs/add/observable/of';
import 'rxjs/add/observable/forkJoin';
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
  ) { }

  public populateWithRelations<T>(enrichedResource: EnrichedResource<T>, relationsToLoad: string[]): Observable<EnrichedResource<T>> {
    let observables: Observable<any>[] = [ Observable.of(enrichedResource) ].concat(this.createRelationHttpRequests(enrichedResource, relationsToLoad));

    return Observable.forkJoin(observables)
      .map((value: any[]) => {
        let resourceToEnrich: EnrichedResource<T> = value[0];
        let loadedRelations: Relation[] = value.splice(1);

        for (let loadedRelation of loadedRelations) {
          resourceToEnrich.embedded[loadedRelation.relation] = loadedRelation.data;
        }

        return resourceToEnrich;
      });
  }

  private createRelationHttpRequests(enrichedResource: EnrichedResource<any>, relationsToLoad: string[]): Observable<any>[] {
    return enrichedResource.links
      .filter(link => relationsToLoad.indexOf(link.rel) > -1)
      .map(link => this.httpClient.get<Resource>(link.href).map(resource => new Relation(link.rel, this.mapToModel(resource))));
  }

  private mapToModel(resource: Resource): any {
    return ResourceUtil.isSingleResource(resource) ? this.modelMapper.mapToModel(<SingleResource> resource) : this.modelMapper.mapToModels(<MultipleResources> resource)
  }

}
