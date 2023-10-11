import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SiteInfoListComponent } from './site-info-list.component';

describe('SiteInfoListComponent', () => {
  let component: SiteInfoListComponent;
  let fixture: ComponentFixture<SiteInfoListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SiteInfoListComponent]
    });
    fixture = TestBed.createComponent(SiteInfoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
