import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { KorogiComponent } from "./korogi.component";
import { HeaderModule } from "./shared/header/header.module";
import { HttpClientModule } from "@angular/common/http";
import { AnimeService } from "./shared/services/anime.service";
import { RouterModule } from "@angular/router";
import { AnimeModule } from "./anime/anime.module";
import { animeRoutes } from "./anime/anime.routes";

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
    AnimeService
  ],
  bootstrap: [KorogiComponent]
})
export class KorogiModule { }
