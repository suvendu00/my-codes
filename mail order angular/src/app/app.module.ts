import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CardComponent } from './components/card/card.component';
import { LocationComponent } from './components/location/location.component';
import { LoginComponent } from './components/login/login.component';
import { RefillComponent } from './components/refill/refill.component';
import { ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SubscriptionComponent } from './components/subscription/subscription.component';
import { DrugComponent } from './components/drug/drug.component';
import { AlertsComponent } from './components/alerts/alerts.component';
// import { DrugComponent } from './components/drug/drug.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    CardComponent,
    LocationComponent,
    LoginComponent,
    RefillComponent,
    DashboardComponent,
    SubscriptionComponent,
    DrugComponent,
    AlertsComponent,
  ],
  
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
