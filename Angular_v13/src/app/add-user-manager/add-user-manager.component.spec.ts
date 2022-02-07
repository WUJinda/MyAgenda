import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUserManagerComponent } from './add-user-manager.component';

describe('AddUserManagerComponent', () => {
  let component: AddUserManagerComponent;
  let fixture: ComponentFixture<AddUserManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddUserManagerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddUserManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
