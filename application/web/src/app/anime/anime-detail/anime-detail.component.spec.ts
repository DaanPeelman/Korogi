import { async, ComponentFixture, fakeAsync, inject, TestBed, tick } from '@angular/core/testing';

import { AnimeDetailComponent } from './anime-detail.component';
import { AnimeService } from "../../shared/services/anime/anime.service";
import { instance, mock, when } from "ts-mockito";
import { CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs/Observable";
import { Anime } from "../../shared/models/anime";
import { EnrichedResource } from "../../shared/resources/final/enriched-resource";
import { AnimeTestData } from "../../testing/test-data/anime-test-data";
import { StubUtil } from "../../testing/util/stub-util";
import { EpisodeTestData } from "../../testing/test-data/episode-test-data";
import { PersonageTestData } from "../../testing/test-data/personage-test-data";

describe('AnimeDetailComponent', () => {
  let component: AnimeDetailComponent;
  let fixture: ComponentFixture<AnimeDetailComponent>;

  let animeServiceMock: AnimeService = mock(AnimeService);

  let activatedRouteStub;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ],
      imports: [],
      declarations: [
        AnimeDetailComponent
      ],
      providers: [
        {
          provide: AnimeService,
          useFactory: () => instance(animeServiceMock)
        },
        {
          provide: ActivatedRoute,
          useFactory: () => StubUtil.subActivatedRoute()
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(inject([ActivatedRoute], (_activatedRouteStub: ActivatedRoute) =>{
    activatedRouteStub = _activatedRouteStub;
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimeDetailComponent);
    component = fixture.componentInstance;
  });

  describe("ngOnInit", () => {
    it("should call the AnimeService to load the Anime with the identifier retrieved from the path and set all the fields on the component", fakeAsync(() => {
      const id: string = "1";
      const params = [];
      params["id"] = id;

      const embedded = [];
      embedded[Anime.RELATION_PREQUAL] = AnimeTestData.naruto();
      embedded[Anime.RELATION_SEQUAL] = AnimeTestData.steinsGateEgoisticPoriomania();
      embedded[Anime.RELATION_EPISODES] = [EpisodeTestData.steinsGate_episode1(), EpisodeTestData.steinsGate_episode2()];
      embedded[Anime.RELATION_PERSONAGES] = [PersonageTestData.okabeRintarou(), PersonageTestData.makiseKurisu()];

      const enrichedResource: EnrichedResource<Anime> = new EnrichedResource<Anime>(AnimeTestData.steinsGate(), []);
      enrichedResource.embedded = embedded;

      when(animeServiceMock.findAnime(id, Anime.RELATION_PREQUAL, Anime.RELATION_SEQUAL, Anime.RELATION_EPISODES, Anime.RELATION_PERSONAGES)).thenReturn(Observable.of(enrichedResource));

      component.ngOnInit();

      activatedRouteStub.params.next(params);

      tick();

      expect(component.anime).toEqual(AnimeTestData.steinsGate());
      expect(component.prequal).toEqual(AnimeTestData.naruto());
      expect(component.sequal).toEqual(AnimeTestData.steinsGateEgoisticPoriomania());
      expect(component.episodes.length).toEqual(2);
      expect(component.episodes).toContain(EpisodeTestData.steinsGate_episode1());
      expect(component.episodes).toContain(EpisodeTestData.steinsGate_episode2());
      expect(component.personages.length).toEqual(2);
      expect(component.personages).toContain(PersonageTestData.okabeRintarou());
      expect(component.personages).toContain(PersonageTestData.makiseKurisu());
    }));
  });
});
