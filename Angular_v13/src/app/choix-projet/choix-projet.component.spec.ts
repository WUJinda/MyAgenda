import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoixProjetComponent } from './choix-projet.component';

describe('ChoixProjetComponent', () => {
  let component: ChoixProjetComponent;
  let fixture: ComponentFixture<ChoixProjetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChoixProjetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoixProjetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
