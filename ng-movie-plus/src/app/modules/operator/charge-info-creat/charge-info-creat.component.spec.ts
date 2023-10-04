import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargeInfoCreatComponent } from './charge-info-creat.component';

describe('ChargeInfoCreatComponent', () => {
  let component: ChargeInfoCreatComponent;
  let fixture: ComponentFixture<ChargeInfoCreatComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChargeInfoCreatComponent]
    });
    fixture = TestBed.createComponent(ChargeInfoCreatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
