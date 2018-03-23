import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimeDetailComponent } from './anime-detail.component';
import { RouterTestingModule } from "@angular/router/testing";
import { AnimeService } from "../../shared/services/anime/anime.service";
import { instance, mock } from "ts-mockito";
import { CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";

describe('AnimeDetailComponent', () => {
  let component: AnimeDetailComponent;
  let fixture: ComponentFixture<AnimeDetailComponent>;

  let animeServiceMock = mock(AnimeService);

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ],
      imports: [
        RouterTestingModule
      ],
      declarations: [
        AnimeDetailComponent
      ],
      providers: [
        {
          provide: AnimeService,
          useFactory: () => instance(animeServiceMock)
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimeDetailComponent);
    component = fixture.componentInstance;
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
