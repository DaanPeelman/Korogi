import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KorogiComponent } from './korogi.component';
import { CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";

describe('KorogiComponent', () => {
  let component: KorogiComponent;
  let fixture: ComponentFixture<KorogiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ],
      declarations: [
        KorogiComponent
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KorogiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
