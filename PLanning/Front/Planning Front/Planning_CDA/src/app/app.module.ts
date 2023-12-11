import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app.routes';
import { AppComponent } from './app.component';
import { RouteReuseStrategy } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PersonComponent } from './AdminStage/person/person.component';
import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { AppFooterComponent } from './app-footer/app-footer.component';
import { AppNavbarComponent } from './app-navbar/app-navbar.component';


@NgModule({
  declarations: [
    AppComponent,
    PersonComponent,
  AppFooterComponent,
  AppNavbarComponent],
 
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    IonicModule.forRoot(),
    FormsModule, 
    HttpClientModule
  ],
  providers: [{ provide: 
    RouteReuseStrategy, 
    useClass: IonicRouteStrategy }],
  
  bootstrap: [AppComponent]
})

export class AppModule { }
