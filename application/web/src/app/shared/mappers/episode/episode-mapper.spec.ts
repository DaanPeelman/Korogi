import { EpisodeMapper } from "./episode-mapper";
import { EpisodeDTO } from "../../models/episode-dto";
import { EpisodeTestData } from "../../../testing/test-data/episode-test-data";

describe("EpisodeMapper", () => {
    let mapper: EpisodeMapper;

    beforeEach(() => {
        mapper = new EpisodeMapper();
    });

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
