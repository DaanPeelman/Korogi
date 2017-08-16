import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { KorogiComponent } from "./korogi.component";

@NgModule({
  declarations: [
    KorogiComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [KorogiComponent]
})
export class KorogiModule { }
