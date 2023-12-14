import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-app-footer',
  templateUrl: './app-footer.component.html',
  styleUrl: './app-footer.component.scss'
})
export class AppFooterComponent {



  title = 'angular-recap';
  searchText = 'Votre Recherche';
  isListView: boolean = true;
  handleSearchInput = (event: any) => {
    this.searchText = event.target.value
    
  }
  handleButtonSearchClick = () => {
    console.log(this.searchText)
  }

  constructor(private router: Router) { }
goToHome() {
  this.router.navigate(['']);
}

goToLogin() {
  this.router.navigate(['login']);
}

goToHomeStore() {
  this.router.navigate(['HomeStore']);
}

goToPerson() {
  this.router.navigate(['person/all']);
}

goToProject() {
  this.router.navigate(['project/all']);
}
}
