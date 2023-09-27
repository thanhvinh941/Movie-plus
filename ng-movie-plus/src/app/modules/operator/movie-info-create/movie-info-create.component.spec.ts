import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieInfoCreateComponent } from './movie-info-create.component';

describe('MovieInfoCreateComponent', () => {
  let component: MovieInfoCreateComponent;
  let fixture: ComponentFixture<MovieInfoCreateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovieInfoCreateComponent]
    });
    fixture = TestBed.createComponent(MovieInfoCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
