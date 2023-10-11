import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SiteInfoDetailComponent } from './site-info-detail.component';

describe('SiteInfoDetailComponent', () => {
  let component: SiteInfoDetailComponent;
  let fixture: ComponentFixture<SiteInfoDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SiteInfoDetailComponent]
    });
    fixture = TestBed.createComponent(SiteInfoDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
