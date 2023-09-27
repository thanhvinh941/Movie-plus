import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieInfoListComponent } from './movie-info-list.component';

describe('MovieInfoListComponent', () => {
  let component: MovieInfoListComponent;
  let fixture: ComponentFixture<MovieInfoListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovieInfoListComponent]
    });
    fixture = TestBed.createComponent(MovieInfoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
