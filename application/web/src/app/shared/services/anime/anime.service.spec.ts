import { inject, TestBed } from '@angular/core/testing';

import { AnimeService } from './anime.service';
import { HttpClientTestingModule, HttpTestingController, TestRequest } from '@angular/common/http/testing';
import { Anime } from '../../models/anime';
import { AnimeTestData } from "../../../testing/test-data/anime-test-data";
import { PagedResources } from "../../resources/final/paged-resources";
import { MultipleResources } from "../../resources/original/multiple-resources";
import { PageMetaData } from "../../resources/page-meta-data";
import { SingleResource } from "../../resources/original/single-resource";
import { Link } from "../../resources/link";
import { anything, capture, instance, mock, when } from "ts-mockito";
import { ModelMapperService } from "../../mappers/model-mapper.service";
import { RelationLoaderService } from "../relation-loader/relation-loader.service";
import { Observable } from "rxjs/Observable";
import { EnrichedResource } from "../../resources/final/enriched-resource";

describe('AnimeService', () => {
  let animeService: AnimeService;

  let modelMapperService: ModelMapperService = mock(ModelMapperService);
  let relationLoaderService: RelationLoaderService = mock(RelationLoaderService);

  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        AnimeService,
        {
          provide: ModelMapperService,
          useFactory: () => instance(modelMapperService)
        },
        {
          provide: RelationLoaderService,
          useFactory: () => instance(relationLoaderService)
        }
      ],
      imports: [HttpClientTestingModule]
    });
  });

  beforeEach(inject([AnimeService, HttpTestingController], (_animeService: AnimeService, _httpMock: HttpTestingController) => {
    animeService = _animeService;
    httpMock = _httpMock;
  }));

  afterEach(() => {
    httpMock.verify();
  });

  describe('findAllAnime', () => {
    it('should return an observable of PagedResources of Anime', () => {
      const responseContent: SingleResource = <any> AnimeTestData.steinsGate();
      responseContent.links = [
        new Link('self', 'http:localhost:8080/rest/self')
      ];

      const response: MultipleResources = {
        links: [],
        content: [
          responseContent
        ],
        page: new PageMetaData(10, 20, 2, 1)
      };

      when(modelMapperService.mapToModels(response)).thenReturn([ AnimeTestData.steinsGate() ]);

      animeService.findAllAnime().subscribe((result: PagedResources<Anime>) => {
        expect(result).not.toBeFalsy();
        expect(result.resources).not.toBeFalsy();
        expect(result.resources.length).toEqual(1);
        expect(result.resources).toContain(AnimeTestData.steinsGate());

        expect(result.metaData).not.toBeFalsy();
        expect(result.metaData.number).toEqual(1);
        expect(result.metaData.size).toEqual(10);
        expect(result.metaData.totalElements).toEqual(20);
        expect(result.metaData.totalPages).toEqual(2);
      });

      const request: TestRequest = httpMock.expectOne(`/rest/anime`);
      expect(request.request.method).toEqual("GET");
      request.flush(response);
    });
  });

  describe('findAnime', () => {
    it('should return an EmbeddedResource of an Anime', () => {
      const id: string = '123';
      const response: SingleResource = <any> AnimeTestData.steinsGate();
      const selfLink: Link = new Link('self', 'http:localhost:8080/rest/self');
      response.links = [selfLink];

      when(modelMapperService.mapToModel<Anime>(response)).thenReturn(AnimeTestData.steinsGate());
      const enrichedResource: EnrichedResource<Anime> = new EnrichedResource<Anime>(AnimeTestData.steinsGate(), [ selfLink ]);
      when(relationLoaderService.populateWithRelations(anything(), anything())).thenReturn(Observable.of(enrichedResource));

      animeService.findAnime(id).subscribe((result: EnrichedResource<Anime>) => {
        const [capturedEnrichedResource, capturedRelationsToLoad] = capture(relationLoaderService.populateWithRelations).last();

        expect((<EnrichedResource<Anime>> capturedEnrichedResource).data).toEqual(AnimeTestData.steinsGate());
        expect((<EnrichedResource<Anime>> capturedEnrichedResource).links.length).toEqual(1);
        expect((<EnrichedResource<Anime>> capturedEnrichedResource).links).toContain(selfLink);

        expect((<string[]> capturedRelationsToLoad).length).toEqual(0);

        expect(result).toEqual(enrichedResource);
      });

      const request: TestRequest = httpMock.expectOne(`/rest/anime/${id}`);
      expect(request.request.method).toEqual("GET");
      request.flush(response);
    });
  });
});
