import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { AnimeDetailModule } from "./anime-detail/anime-detail.module";
import { animeRoutes } from "./anime.routes";
import { RouterModule } from "@angular/router";

@NgModule(
    {
        imports: [
            CommonModule,
            RouterModule.forChild(animeRoutes),

            AnimeDetailModule
        ],
        declarations: []
    }
)
export class AnimeModule {
}
