import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonComponent } from './AdminStage/person/person.component';
import { IonicModule } from '@ionic/angular';

const routes: Routes = [
{path:"Person",
component: PersonComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes),IonicModule.forRoot()],
  
  exports: [RouterModule],
  

})

export class AppRoutingModule {
  
 }