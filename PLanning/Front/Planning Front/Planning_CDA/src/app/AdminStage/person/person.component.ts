import { Component, NgModule, OnInit } from '@angular/core';
import { PersonServiceService } from '../../person-service.service';
import { NgForm } from '@angular/forms';

export interface Person {
  id: number,
  nom: string,
  prenom: string,
  adresse: string,
}

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})

export class PersonComponent implements OnInit {
  persons: Array<Person> = [];
  personToShow: Array<Person> = [];
  personToUpDate: Person = {
    id: 0,
    nom: '',
    prenom: '',
    adresse: '',
  };

  constructor(public personService: PersonServiceService) {}

  ngOnInit() {
    // this.clients = await this.clientServiceService.getAllClient()
    this.personService.findAll().subscribe((response)=>{
      console.log(response)
      this.personToShow = response;
    });
    
  }

  onSubmit(form : NgForm) {
    // Ajouter ici la logique de traitement du formulaire, par exemple, envoyer les données au serveur.
    console.log('Données soumises :', this.personToUpDate);
  }

  handleUpdateClickButton = (id: number) => {
    console.log(this.personToShow)
    const foundPerson = this.personToShow.find((person) => person.id === id);
    if (foundPerson !== undefined) {
      this.personToUpDate = foundPerson;
    }
  }
}

