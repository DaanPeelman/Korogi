import { inject, TestBed } from '@angular/core/testing';

import { RelationLoaderService } from './relation-loader.service';
import { HttpClientTestingModule, HttpTestingController, TestRequest } from "@angular/common/http/testing";
import { ModelMapperService } from "../../mappers/model-mapper.service";
import { instance, mock, when } from "ts-mockito";
import { EnrichedResource } from "../../resources/final/enriched-resource";
import { AnimeTestData } from "../../../testing/test-data/anime-test-data";
import { Link } from "../../resources/link";
import { EpisodeTestData } from "../../../testing/test-data/episode-test-data";
import { PersonageTestData } from "../../../testing/test-data/personage-test-data";
import { MultipleResources } from "../../resources/original/multiple-resources";
import { PageMetaData } from "../../resources/page-meta-data";
import { AnimeDTO } from "../../models/anime-dto";
import { EpisodeDTO } from "../../models/episode-dto";
import { PersonageDTO } from "../../models/personage-dto";

describe('RelationLoaderService', () => {
    let relationLoaderService: RelationLoaderService;

    let modelMapperService: ModelMapperService = mock(ModelMapperService);

    let httpMock: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                RelationLoaderService,
                {
                    provide: ModelMapperService,
                    useFactory: () => instance(modelMapperService)
                }
            ],
            imports: [HttpClientTestingModule]
        });
    });

    beforeEach(inject([RelationLoaderService, HttpTestingController], (_relationLoaderService: RelationLoaderService, _httpMock: HttpTestingController) => {
        relationLoaderService = _relationLoaderService;
        httpMock = _httpMock;
    }));

    afterEach(() => {
        httpMock.verify();
    });

    describe("populateWithRelations", () => {
        it("should do http GET requests for every relation that was passed and enrich the resource with the response", () => {
            const anime: AnimeDTO = AnimeTestData.steinsGate();
            const links: Link[] = [
                new Link("relation1", "http://relation1.com"),
                new Link("relation2", "http://relation2.com"),
                new Link("relation3", "http://relation3.com")
            ];

            const relation1Response: any = EpisodeTestData.steinsGate_episode1();
            relation1Response.type = "episode";

            const relation2Personage1: any = PersonageTestData.okabeRintarou();
            relation2Personage1.type = "personage";
            const relation2Personage2: any = PersonageTestData.makiseKurisu();
            relation2Personage2.type = "personage";
            const relation2Response: MultipleResources = {
                links: [],
                content: [
                    relation2Personage1,
                    relation2Personage2
                ],
                page: new PageMetaData(10, 10, 10, 10)
            };

            when(modelMapperService.mapToModel<EpisodeDTO>(relation1Response)).thenReturn(EpisodeTestData.steinsGate_episode1());
            when(modelMapperService.mapToModels<PersonageDTO>(relation2Response)).thenReturn([PersonageTestData.okabeRintarou(), PersonageTestData.makiseKurisu()]);

            relationLoaderService.populateWithRelations(new EnrichedResource<AnimeDTO>(anime, links), [links[0].rel, links[2].rel])
                .subscribe(finalEnrichedResource => {
                    expect(finalEnrichedResource.data).toEqual(AnimeTestData.steinsGate());
                    expect(finalEnrichedResource.links).toEqual(links);

                    expect(finalEnrichedResource.embedded[links[0].rel]).toEqual(EpisodeTestData.steinsGate_episode1());

                    expect(finalEnrichedResource.embedded[links[1].rel]).toBeFalsy();

                    expect(finalEnrichedResource.embedded[links[2].rel].length).toEqual(2);
                    expect(finalEnrichedResource.embedded[links[2].rel]).toContain(PersonageTestData.okabeRintarou());
                    expect(finalEnrichedResource.embedded[links[2].rel]).toContain(PersonageTestData.makiseKurisu());
                });

            const requestToRetrieveRelation1: TestRequest = httpMock.expectOne(links[0].href);
            expect(requestToRetrieveRelation1.request.method).toEqual("GET");

            const requestToRetrieveRelation2: TestRequest = httpMock.expectOne(links[2].href);
            expect(requestToRetrieveRelation2.request.method).toEqual("GET");

            requestToRetrieveRelation1.flush(relation1Response);
            requestToRetrieveRelation2.flush(relation2Response);
        });

        it("should do no http GET requests if no relation was asked to be loaded", () => {
            const anime: AnimeDTO = AnimeTestData.steinsGate();
            const links: Link[] = [
                new Link("relation1", "http://relation1.com"),
                new Link("relation2", "http://relation2.com"),
                new Link("relation3", "http://relation3.com")
            ];

            relationLoaderService.populateWithRelations(new EnrichedResource<AnimeDTO>(anime, links), [])
                .subscribe(finalEnrichedResource => {
                    expect(finalEnrichedResource.data).toEqual(AnimeTestData.steinsGate());
                    expect(finalEnrichedResource.links).toEqual(links);

                    expect(finalEnrichedResource.embedded[links[0].rel]).toBeFalsy();
                    expect(finalEnrichedResource.embedded[links[1].rel]).toBeFalsy();
                    expect(finalEnrichedResource.embedded[links[2].rel]).toBeFalsy();
                });
        });

        it("should do no http GET requests if the relation asked to load is not present in the resource's links", () => {
            const anime: AnimeDTO = AnimeTestData.steinsGate();
            const links: Link[] = [
                new Link("relation1", "http://relation1.com"),
                new Link("relation2", "http://relation2.com"),
                new Link("relation3", "http://relation3.com")
            ];

            relationLoaderService.populateWithRelations(new EnrichedResource<AnimeDTO>(anime, links), ["relation4"])
                .subscribe(finalEnrichedResource => {
                    expect(finalEnrichedResource.data).toEqual(AnimeTestData.steinsGate());
                    expect(finalEnrichedResource.links).toEqual(links);

                    expect(finalEnrichedResource.embedded[links[0].rel]).toBeFalsy();
                    expect(finalEnrichedResource.embedded[links[1].rel]).toBeFalsy();
                    expect(finalEnrichedResource.embedded[links[2].rel]).toBeFalsy();
                    expect(finalEnrichedResource.embedded["relation4"]).toBeFalsy();
                });
        });
    });
});
