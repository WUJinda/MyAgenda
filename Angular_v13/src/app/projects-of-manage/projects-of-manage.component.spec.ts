import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectsOfManageComponent } from './projects-of-manage.component';

describe('ProjectsOfManageComponent', () => {
  let component: ProjectsOfManageComponent;
  let fixture: ComponentFixture<ProjectsOfManageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectsOfManageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectsOfManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
