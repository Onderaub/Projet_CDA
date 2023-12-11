import { Injectable } from '@angular/core';
import axios from 'axios';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';




export interface PersonGetAllDTO {
  id: number;
  nom: string,
  prenom: string,
  adresse: string,
  email: string,
}
@Injectable({
  providedIn: 'root'
})

export class PersonServiceService {

  constructor(private http: HttpClient) { }

  // Effectuer la requête HTTP pour afficher les clients 
  getAllPerson = async (): Promise<Array<PersonGetAllDTO>> => {
    console.log("listPerson")
    return (await axios.get('http://localhost:9000/planning/user/all')).data;
  }

  findAll = (): Observable<any> => {
    const token = sessionStorage.getItem('token');
    console.log("listPerson")
    // return (await axios.get('http://localhost:9000/dvds/user/all')).data;
    return this.http.get('http://localhost:9000/planning/person/all', {
      headers: { "authorization": `Bearer ${token}` }
    });
  }

  // Effectuer la requête HTTP pour afficher un client
  getOnePerson = async (id: number): Promise<PersonGetAllDTO> => {
    console.log("onePerson")
    return (await axios.get('http://localhost:9000/planning/person/' + id)).data;
  }

 
}