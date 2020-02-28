import { BrowserModule } from '@angular/platform-browser';
import { APP_INITIALIZER, NgModule } from '@angular/core';

import { KorogiComponent } from "./korogi.component";
import { HeaderModule } from "./shared/components/header/header.module";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { AnimeService } from "./shared/services/anime/anime.service";
import { RouterModule } from "@angular/router";
import { AnimeModule } from "./anime/anime.module";
import { RelationLoaderService } from "./shared/services/relation-loader/relation-loader.service";
import { ModelMapperService } from "./shared/services/model-mapper/model-mapper.service";
import { MapperModule } from "./shared/mapper/mapper.module";
import { KeycloakAngularModule, KeycloakService } from "keycloak-angular";

@NgModule({
    declarations: [
        KorogiComponent
    ],
    imports: [
        KeycloakAngularModule,

        BrowserModule,
        HeaderModule,
        HttpClientModule,
        RouterModule.forRoot([]),

        MapperModule.forRoot(),

        AnimeModule
    ],
    providers: [
        {
            provide: APP_INITIALIZER,
            useFactory: initializer,
            multi: true,
            deps: [KeycloakService, HttpClient]
        },

        RelationLoaderService,
        ModelMapperService,
        AnimeService
    ],
    bootstrap: [KorogiComponent]
})
export class KorogiModule {
}

export function initializer(keycloak: KeycloakService, httpClient: HttpClient): () => Promise<any> {
    return (): Promise<any> => keycloak.init({config: "rest/config/keycloak"});
}
