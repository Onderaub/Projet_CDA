import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { Platform } from '@ionic/angular';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Planning_CDA';
  constructor(private authService: AuthService, public platform: Platform) {
    // Désactivez l'affichage du header et du footer sur la page de connexion.
 
  }

  ngOnInit() {
    // Réinitialisez l'état du header et du footer lors de la sortie de la page de connexion si nécessaire.
  
  }
  }


