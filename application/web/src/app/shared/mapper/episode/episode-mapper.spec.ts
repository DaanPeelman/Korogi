import { EpisodeMapper } from "./episode-mapper";
import { EpisodeDTO } from "../../models/episode-dto";
import { EpisodeTestData } from "../../../testing/test-data/episode-test-data";
import { inject, TestBed } from "@angular/core/testing";

describe("EpisodeMapper", () => {
    let mapper: EpisodeMapper;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                EpisodeMapper
            ]
        });
    });

    beforeEach(inject([EpisodeMapper], (_mapper: EpisodeMapper) => {
        mapper = _mapper;
    }));

    describe("map", () => {
        it("should create an Episode instance with all the values of the JSON object that was passed", () => {
            let expectedEpisode: EpisodeDTO = EpisodeTestData.steinsGate_episode1();
            let resource: any = JSON.parse(JSON.stringify(expectedEpisode));
            resource["type"] = "episode";

            let mappedResource: EpisodeDTO = mapper.map(resource);

            expect(mappedResource).toEqual(expectedEpisode);
        });
    });
});
