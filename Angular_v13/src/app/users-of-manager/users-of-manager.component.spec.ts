import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersOfManagerComponent } from './users-of-manager.component';

describe('UsersOfManagerComponent', () => {
  let component: UsersOfManagerComponent;
  let fixture: ComponentFixture<UsersOfManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersOfManagerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersOfManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
