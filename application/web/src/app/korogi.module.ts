import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { KorogiComponent } from "./korogi.component";
import { HeaderModule } from "./shared/components/header/header.module";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { AnimeService } from "./shared/services/anime/anime.service";
import { RouterModule } from "@angular/router";
import { AnimeModule } from "./anime/anime.module";
import { RelationLoaderService } from "./shared/services/relation-loader/relation-loader.service";
import { ModelMapperService } from "./shared/services/model-mapper/model-mapper.service";
import { MapperModule } from "./shared/mapper/mapper.module";
import { LoginModule } from "./login/login.module";
import { AuthenticationInterceptor } from "./shared/intercepters/authentication/authentication.interceptor";

@NgModule({
              declarations: [
                  KorogiComponent
              ],
              imports: [
                  BrowserModule,
                  HeaderModule,
                  HttpClientModule,
                  RouterModule.forRoot([], { relativeLinkResolution: "legacy" }),

                  MapperModule.forRoot(),

                  AnimeModule,
                  LoginModule
              ],
              providers: [
                  {
                      provide: HTTP_INTERCEPTORS,
                      useClass: AuthenticationInterceptor,
                      multi: true
                  },

                  RelationLoaderService,
                  ModelMapperService,
                  AnimeService
              ],
              bootstrap: [
                  KorogiComponent
              ]
          })
export class KorogiModule {
}
