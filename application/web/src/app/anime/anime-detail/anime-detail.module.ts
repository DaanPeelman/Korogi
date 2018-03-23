import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnimeDetailComponent } from "./anime-detail.component";

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [AnimeDetailComponent],
  exports: [AnimeDetailComponent]
})
export class AnimeDetailModule { }
