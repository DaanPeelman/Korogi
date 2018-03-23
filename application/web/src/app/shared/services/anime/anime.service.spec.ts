import { inject, TestBed } from '@angular/core/testing';

import { AnimeService } from './anime.service';
import { HttpClientTestingModule, HttpTestingController, TestRequest } from '@angular/common/http/testing';
import { Anime } from '../../models/anime';
import { AnimeMother } from "../../../testing/anime-mother";

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
    it('should return an observable of an array of Anime', () => {
      const animesToReturn: Anime[] = [AnimeMother.steinsGate()];

      animeService.findAllAnime().subscribe((result: Anime[]) => {
        expect(result.length).toEqual(1);
        expect(result).toEqual(animesToReturn);
      });

      const request: TestRequest = httpMock.expectOne(`/rest/anime`);
      expect(request.request.method).toEqual("GET");
      request.flush(animesToReturn);
    });
  });

  describe('findAnime', () => {
    it('should return an observable of an Anime', () => {
      const id: string = '123';
      const animeToReturn: Anime = AnimeMother.steinsGate();

      animeService.findAnime(id).subscribe((result: Anime) => {
        expect(result).toEqual(animeToReturn);
      });

      const request: TestRequest = httpMock.expectOne(`/rest/anime/${id}`);
      expect(request.request.method).toEqual("GET");
      request.flush(animeToReturn);
    });
  });
});
