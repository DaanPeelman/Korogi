import { Routes } from "@angular/router";
import { AnimeDetailComponent } from "./anime-detail/anime-detail.component";
import { ANIME_DETAIL_PAGE } from "../shared/constants/pages.constant";

export const animeRoutes: Routes = [
    {
        path: ANIME_DETAIL_PAGE,
        component: AnimeDetailComponent
    }
];
