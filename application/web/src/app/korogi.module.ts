import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { KorogiComponent } from "./korogi.component";
import { HeaderModule } from "./shared/header/header.module";

@NgModule({
  declarations: [
    KorogiComponent
  ],
  imports: [
    BrowserModule,
    HeaderModule
  ],
  providers: [],
  bootstrap: [KorogiComponent]
})
export class KorogiModule { }
