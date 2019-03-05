import { PersonageMapper } from "./personage-mapper";
import { PersonageDTO } from "../../models/personage-dto";
import { PersonageTestData } from "../../../testing/test-data/personage-test-data";

describe("PersonageMapper", () => {
    let mapper: PersonageMapper;

    beforeEach(() => {
        mapper = new PersonageMapper();
    });

    describe("map", () => {
        it("should create a Personage instance with all the values of the JSON object that was passed", () => {
            let expectedPersonage: PersonageDTO = PersonageTestData.makiseKurisu();
            let resource: any = JSON.parse(JSON.stringify(expectedPersonage));
            resource["type"] = "personage";

            let mappedResource: PersonageDTO = mapper.map(resource);

            expect(mappedResource).toEqual(expectedPersonage);
        });
    });
});
