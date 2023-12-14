import { ComponentFixture, TestBed, async, fakeAsync, flush, tick, waitForAsync } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { DebugElement, NgModule, inject } from '@angular/core';
import { PersonComponent } from './person.component';
import { PersonGetAllDTO, PersonServiceService } from '../../person-service.service';
import { of } from 'rxjs';

class MockClientService {
  findAll() {
    return of([]);
  }

  getOnePerson(id: number) {
    return of({ id, nom: 'Nom', prenom: 'Prenom', adresse: 'Adresse' } as PersonGetAllDTO);
  }
}

describe('ClientComponent', () => {
  let component: PersonComponent;
  let fixture: ComponentFixture<PersonComponent>;
  let debugElement: DebugElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PersonComponent],
      providers: [{ provide: PersonServiceService, useClass: MockClientService }],
      imports: [FormsModule]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonComponent);
    component = fixture.componentInstance;
    debugElement = fixture.debugElement;
  });

  it('should fetch persons on ngOnInit', () => {
    spyOn(component.personService, 'findAll').and.callThrough();
    component.ngOnInit();
    expect(component.personService.findAll).toHaveBeenCalled();
  });

  it('should handle update click button', waitForAsync(() => {
    spyOn(component.personService, 'findAll').and.returnValue(of([{ id: 1, nom: 'Abesse', prenom: 'Al', adresse: '123 impasse de la plage' }]));

    component.ngOnInit();

    component.handleUpdateClickButton(1);

    // Vérifier que les données de la personne à mettre à jour sont correctement chargées dans le formulaire
    expect(component.personToUpDate).toEqual({
      id: 1,
      nom: 'Abesse',
      prenom: 'Al',
      adresse: '123 impasse de la plage',
      
    });
  }));
});
