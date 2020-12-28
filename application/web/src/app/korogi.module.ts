import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { KorogiComponent } from "./korogi.component";
import { HeaderModule } from "./shared/components/header/header.module";
import { HttpClientModule } from "@angular/common/http";
import { AnimeService } from "./shared/services/anime/anime.service";
import { RouterModule } from "@angular/router";
import { AnimeModule } from "./anime/anime.module";
import { RelationLoaderService } from "./shared/services/relation-loader/relation-loader.service";
import { ModelMapperService } from "./shared/services/model-mapper/model-mapper.service";
import { MapperModule } from "./shared/mapper/mapper.module";

@NgModule({
    declarations: [
        KorogiComponent
    ],
    imports: [
        BrowserModule,
        HeaderModule,
        HttpClientModule,
        RouterModule.forRoot([], { relativeLinkResolution: 'legacy' }),

        MapperModule.forRoot(),

        AnimeModule
    ],
    providers: [
        RelationLoaderService,
        ModelMapperService,
        AnimeService
    ],
    bootstrap: [KorogiComponent]
})
export class KorogiModule {
}
