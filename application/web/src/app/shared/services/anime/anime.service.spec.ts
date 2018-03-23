import { inject, TestBed } from '@angular/core/testing';

import { AnimeService } from './anime.service';
import { HttpClientTestingModule, HttpTestingController, TestRequest } from '@angular/common/http/testing';
import { Anime } from '../../models/anime';
import { AnimeMother } from "../../../testing/anime-mother";
import { PagedResources } from "../../resources/paged-resources";
import { EmbeddedResource } from "../../resources/embedded-resource";

describe('AnimeService', () => {
  let animeService: AnimeService;

  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AnimeService],
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
      const responseContent: any = AnimeMother.steinsGate();
      responseContent['links'] = [
        {
          rel: 'self',
          href: 'http:localhost:8080/rest/self'
        }
      ];

      const response = {
        links: [],
        content: [
          responseContent
        ],
        page: {
          size: 10,
          totalElements: 20,
          totalPages: 2,
          number: 1
        }
      };

      animeService.findAllAnime().subscribe((result: PagedResources<Anime>) => {
        expect(result).not.toBeFalsy();
        expect(result.resources).not.toBeFalsy();
        expect(result.resources.length).toEqual(1);
        expect(result.resources[0].content).toEqual(AnimeMother.steinsGate());
        expect(result.resources[0].links).not.toBeFalsy();
        expect(result.resources[0].links.self).toEqual('http:localhost:8080/rest/self');

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
      const response: any = AnimeMother.steinsGate();
      response['links'] = [
        {
          rel: 'self',
          href: 'http:localhost:8080/rest/self'
        }
      ];

      animeService.findAnime(id).subscribe((result: EmbeddedResource<Anime>) => {
        expect(result.content).toEqual(AnimeMother.steinsGate());
        expect(result.links).not.toBeFalsy();
        expect(result.links.self).toEqual('http:localhost:8080/rest/self');
      });

      const request: TestRequest = httpMock.expectOne(`/rest/anime/${id}`);
      expect(request.request.method).toEqual("GET");
      request.flush(response);
    });
  });
});
