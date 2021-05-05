import { PersonageMapper } from "./personage-mapper";
import { PersonageDTO } from "../../models/personage-dto";
import { PersonageTestData } from "../../../testing/test-data/personage-test-data";
import { inject, TestBed } from "@angular/core/testing";

describe("PersonageMapper", () => {
    let mapper: PersonageMapper;

    beforeEach(() => {
        TestBed.configureTestingModule(
            {
                providers: [
                    PersonageMapper
                ]
            }
        );
    });

    beforeEach(inject([PersonageMapper], (_mapper: PersonageMapper) => {
        mapper = _mapper;
    }));

    describe("map", () => {
        it("should create a Personage instance with all the values of the JSON object that was passed", () => {
            const expectedPersonage: PersonageDTO = PersonageTestData.makiseKurisu();
            const resource: any = JSON.parse(JSON.stringify(expectedPersonage));
            resource["type"] = "personage";

            const mappedResource: PersonageDTO = mapper.map(resource);

            expect(mappedResource).toEqual(expectedPersonage);
        });
    });
});
