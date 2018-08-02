import { inject, TestBed } from '@angular/core/testing';

import { ModelMapperService } from './model-mapper.service';
import { Anime } from "../models/anime";
import { AnimeTestData } from "../../testing/test-data/anime-test-data";
import { EpisodeTestData } from "../../testing/test-data/episode-test-data";
import { Episode } from "../models/episode";
import { PersonageTestData } from "../../testing/test-data/personage-test-data";
import { Personage } from "../models/personage";

describe('ModelMapperService', () => {
  let modelMapperService: ModelMapperService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ModelMapperService]
    });
  });

  beforeEach(inject([ModelMapperService], (_modelMapperService: ModelMapperService) => {
    modelMapperService = _modelMapperService;
  }));

  describe("mapToModel", () => {
    it("should map to an Anime model when the type is 'anime'", () => {
      const resource: any = AnimeTestData.steinsGate();
      resource.type = "anime";

      const convertedResource: Anime = modelMapperService.mapToModel(resource);

      expect(convertedResource).not.toBeFalsy();
      expect(convertedResource instanceof Anime).toEqual(true);
      expect(convertedResource).toEqual(AnimeTestData.steinsGate());
    });
  });

  it("should map to an Episode model when the type is 'episode'", () => {
    const resource: any = EpisodeTestData.steinsGate_episode1();
    resource.type = "episode";

    const convertedResource: Episode = modelMapperService.mapToModel(resource);

    expect(convertedResource).not.toBeFalsy();
    expect(convertedResource instanceof Episode).toEqual(true);
    expect(convertedResource).toEqual(EpisodeTestData.steinsGate_episode1());
  });

  it("should map to a Personage model when the type is 'personage'", () => {
    const resource: any = PersonageTestData.okabeRintarou();
    resource.type = "personage";

    const convertedResource: Personage = modelMapperService.mapToModel(resource);

    expect(convertedResource).not.toBeFalsy();
    expect(convertedResource instanceof Personage).toEqual(true);
    expect(convertedResource).toEqual(PersonageTestData.okabeRintarou());
  });

  describe("mapToModels", () => {
    it("should map to an array of Anime when the contents of the resource have the type 'anime'", () => {
      const steinsGate: any = AnimeTestData.steinsGate();
      steinsGate.type = "anime";

      const naruto: any = AnimeTestData.naruto();
      naruto.type = "anime";

      const resources: any = {
        content: [
          steinsGate,
          naruto
        ]
      };

      const convertedResources: Anime[] = modelMapperService.mapToModels(resources);

      expect(convertedResources).not.toBeFalsy();
      expect(convertedResources.length).toEqual(2);
      expect(convertedResources[0] instanceof Anime).toEqual(true);
      expect(convertedResources[1] instanceof Anime).toEqual(true);
      expect(convertedResources).toContain(AnimeTestData.steinsGate());
      expect(convertedResources).toContain(AnimeTestData.naruto());
    });

    it("should map to an array of Episode when the contents of the resource have the type 'episode'", () => {
      const steinsGateEpisode1: any = EpisodeTestData.steinsGate_episode1();
      steinsGateEpisode1.type = "episode";

      const steinsGateEpisode2: any = EpisodeTestData.steinsGate_episode2();
      steinsGateEpisode2.type = "episode";

      const resources: any = {
        content: [
          steinsGateEpisode1,
          steinsGateEpisode2
        ]
      };

      const convertedResources: Episode[] = modelMapperService.mapToModels(resources);

      expect(convertedResources).not.toBeFalsy();
      expect(convertedResources.length).toEqual(2);
      expect(convertedResources[0] instanceof Episode).toEqual(true);
      expect(convertedResources[1] instanceof Episode).toEqual(true);
      expect(convertedResources).toContain(EpisodeTestData.steinsGate_episode1());
      expect(convertedResources).toContain(EpisodeTestData.steinsGate_episode2());
    });

    it("should map to an array of Personage when the contents of the resource have the type 'personage'", () => {
      const okabeRintarou: any = PersonageTestData.okabeRintarou();
      okabeRintarou.type = 'personage';

      const makiseKurisu: any = PersonageTestData.makiseKurisu();
      makiseKurisu.type = 'personage';

      const resources: any = {
        content: [
          okabeRintarou,
          makiseKurisu
        ]
      };

      const convertedResources: Personage[] = modelMapperService.mapToModels(resources);

      expect(convertedResources).not.toBeFalsy();
      expect(convertedResources.length).toEqual(2);
      expect(convertedResources[0] instanceof Personage).toEqual(true);
      expect(convertedResources[1] instanceof Personage).toEqual(true);
      expect(convertedResources).toContain(PersonageTestData.okabeRintarou());
      expect(convertedResources).toContain(PersonageTestData.makiseKurisu());
    });
  });
});
