import { InjectionToken, ModuleWithProviders, NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { AnimeMapper } from "./anime/anime-mapper";
import { EpisodeMapper } from "./episode/episode-mapper";
import { PersonageMapper } from "./personage/personage-mapper";

@NgModule(
    {
        declarations: [],
        imports: [
            CommonModule
        ]
    }
)
export class MapperModule {
    static forRoot(): ModuleWithProviders<MapperModule> {
        return {
            ngModule: MapperModule,
            providers: [
                { provide: MAPPERS, useClass: AnimeMapper, multi: true },
                { provide: MAPPERS, useClass: EpisodeMapper, multi: true },
                { provide: MAPPERS, useClass: PersonageMapper, multi: true }
            ]
        };
    }
}

export const MAPPERS = new InjectionToken("mapper");
