import { AnimeMapper } from "./anime-mapper";
import { AnimeTestData } from "../../../testing/test-data/anime-test-data";
import { AnimeDTO } from "../../models/anime-dto";
import { inject, TestBed } from "@angular/core/testing";

describe("AnimeMapper", () => {
    let mapper: AnimeMapper;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                AnimeMapper
            ]
        });
    });

    beforeEach(inject([AnimeMapper], (_mapper: AnimeMapper) => {
        mapper = _mapper;
    }));

    describe("map", () => {
        it("should create an Anime instance with all the values of the JSON object that was passed", () => {
            let expectedAnime: AnimeDTO = AnimeTestData.steinsGate();
            let resource: any = JSON.parse(JSON.stringify(expectedAnime));
            resource["type"] = "anime";

            let mappedResource: AnimeDTO = mapper.map(resource);

            expect(mappedResource).toEqual(expectedAnime);
        });
    });
});
