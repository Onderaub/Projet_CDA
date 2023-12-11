import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

 

  is_Auth = (): boolean => {                      //présence du token
    const token = sessionStorage.getItem('token');
    return token != null;
  }
}
