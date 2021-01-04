import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnouncementsShowComponent } from './announcements-show.component';

describe('AnnouncementsShowComponent', () => {
  let component: AnnouncementsShowComponent;
  let fixture: ComponentFixture<AnnouncementsShowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnnouncementsShowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnouncementsShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
