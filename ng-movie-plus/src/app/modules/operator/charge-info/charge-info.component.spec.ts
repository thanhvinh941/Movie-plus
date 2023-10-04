import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargeInfoComponent } from './charge-info.component';

describe('ChargeInfoComponent', () => {
  let component: ChargeInfoComponent;
  let fixture: ComponentFixture<ChargeInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChargeInfoComponent]
    });
    fixture = TestBed.createComponent(ChargeInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
