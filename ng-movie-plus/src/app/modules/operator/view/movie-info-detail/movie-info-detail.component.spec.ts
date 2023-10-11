import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieInfoDetailComponent } from './movie-info-detail.component';

describe('MovieInfoDetailComponent', () => {
  let component: MovieInfoDetailComponent;
  let fixture: ComponentFixture<MovieInfoDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovieInfoDetailComponent]
    });
    fixture = TestBed.createComponent(MovieInfoDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
