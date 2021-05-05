import { inject, TestBed } from "@angular/core/testing";

import { ModelMapperService } from "./model-mapper.service";
import { MAPPERS } from "../../mapper/mapper.module";
import { AnimeMapper } from "../../mapper/anime/anime-mapper";
import { anything, instance, mock, reset, verify, when } from "ts-mockito";
import { IMapper } from "../../mapper/mapper";
import { MultipleResources } from "../../resources/original/multiple-resources";

describe("ModelMapperService", () => {
    let modelMapperService: ModelMapperService;

    const mapperMock1: IMapper<any> = mock(AnimeMapper);
    const mapperMock2: IMapper<any> = mock(AnimeMapper);

    const mappedObject1: any = { name: "object1" };
    const mappedObject2: any = { name: "object2" };

    beforeEach(() => {
        reset(mapperMock1);
        reset(mapperMock2);
    });

    beforeEach(() => {
        when(mapperMock1.forType()).thenReturn("mock1");
        when(mapperMock1.map(anything())).thenReturn(mappedObject1);

        when(mapperMock2.forType()).thenReturn("mock2");
        when(mapperMock2.map(anything())).thenReturn(mappedObject2);
    });

    beforeEach(() => {
        TestBed.configureTestingModule(
            {
                providers: [
                    ModelMapperService,
                    {
                        provide: MAPPERS,
                        useFactory: () => instance(mapperMock1),
                        multi: true
                    },
                    {
                        provide: MAPPERS,
                        useFactory: () => instance(mapperMock2),
                        multi: true
                    }
                ]
            }
        );
    });

    beforeEach(inject([ModelMapperService], (_modelMapperService: ModelMapperService) => {
        modelMapperService = _modelMapperService;
    }));

    describe("mapToModel", () => {
        it("should use the mapper for 'mock1' when the type of the resource is 'mock1'", () => {
            const resource: any = {
                type: "mock1"
            };

            expect(modelMapperService.mapToModel(resource)).toBe(mappedObject1);

            verify(mapperMock1.map(resource)).once();
            verify(mapperMock2.map(anything())).never();
        });

        it("should use the mapper for 'mock2' when the type of the resource is 'mock2'", () => {
            const resource: any = {
                type: "mock2"
            };

            expect(modelMapperService.mapToModel(resource)).toBe(mappedObject2);

            verify(mapperMock1.map(anything())).never();
            verify(mapperMock2.map(resource)).once();
        });
    });

    describe("mapToModels", () => {
        it("should use the mapper for 'mock1' when the type of all the resources is 'mock1'", () => {
            const resource1: any = {
                type: "mock1"
            };

            const resource2: any = {
                type: "mock1"
            };

            const resources: MultipleResources = {
                content: [resource1, resource2],
                page: null,
                links: null
            };

            const mappedArray: any[] = modelMapperService.mapToModels(resources);

            expect(mappedArray).toEqual([mappedObject1, mappedObject1]);

            verify(mapperMock1.map(resource1)).once();
            verify(mapperMock1.map(resource2)).once();
            verify(mapperMock2.map(anything())).never();
        });

        it("should use the mapper for 'mock2' when the type of all the resources is 'mock2'", () => {
            const resource1: any = {
                type: "mock2"
            };

            const resource2: any = {
                type: "mock2"
            };

            const resources: MultipleResources = {
                content: [resource1, resource2],
                page: null,
                links: null
            };

            const mappedArray: any[] = modelMapperService.mapToModels(resources);

            expect(mappedArray).toEqual([mappedObject2, mappedObject2]);

            verify(mapperMock1.map(anything())).never();
            verify(mapperMock2.map(resource1)).once();
            verify(mapperMock2.map(resource2)).once();
        });

        it(
            "should use the mapper for 'mock1' and 'mock2' when the passed resources contain a resource of type 'mock1' and a resource of type 'mock2'",
            () => {
                const resource1: any = {
                    type: "mock1"
                };

                const resource2: any = {
                    type: "mock2"
                };

                const resources: MultipleResources = {
                    content: [resource1, resource2],
                    page: null,
                    links: null
                };

                const mappedArray: any[] = modelMapperService.mapToModels(resources);

                expect(mappedArray).toEqual([mappedObject1, mappedObject2]);

                verify(mapperMock1.map(resource1)).once();
                verify(mapperMock1.map(resource2)).never();

                verify(mapperMock2.map(resource1)).never();
                verify(mapperMock2.map(resource2)).once();
            }
        );
    });
});
