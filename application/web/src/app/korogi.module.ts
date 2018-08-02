import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { KorogiComponent } from "./korogi.component";
import { HeaderModule } from "./shared/header/header.module";
import { HttpClientModule } from "@angular/common/http";
import { AnimeService } from "./shared/services/anime/anime.service";
import { RouterModule } from "@angular/router";
import { AnimeModule } from "./anime/anime.module";
import { animeRoutes } from "./anime/anime.routes";
import { RelationLoaderService } from "./shared/services/relation-loader/relation-loader.service";
import { ModelMapperService } from "./shared/mappers/model-mapper.service";

@NgModule({
  declarations: [
    KorogiComponent
  ],
  imports: [
    BrowserModule,
    HeaderModule,
    HttpClientModule,
    RouterModule.forRoot(animeRoutes),

    AnimeModule
  ],
  providers: [
    RelationLoaderService,
    ModelMapperService,
    AnimeService
  ],
  bootstrap: [KorogiComponent]
})
export class KorogiModule { }
