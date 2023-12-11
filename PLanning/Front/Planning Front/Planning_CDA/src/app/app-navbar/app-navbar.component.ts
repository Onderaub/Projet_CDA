import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-app-navbar',
  templateUrl: './app-navbar.component.html',
  styleUrl: './app-navbar.component.scss'
})
export class AppNavbarComponent implements OnInit {

  constructor(protected authService: AuthService,
    private route: Router){}

  ngOnInit(): void {
     
  }

  removeToken = () => {
    sessionStorage.removeItem('token');
    this.route.navigate(['']);
  }
}