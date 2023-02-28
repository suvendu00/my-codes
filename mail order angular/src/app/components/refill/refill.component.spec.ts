import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefillComponent } from './refill.component';

describe('DashboardComponent', () => {
  let component: RefillComponent;
  let fixture: ComponentFixture<RefillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RefillComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RefillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
