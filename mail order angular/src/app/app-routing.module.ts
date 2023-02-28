import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RefillComponent } from './components/refill/refill.component';

import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SubscriptionComponent } from './components/subscription/subscription.component';
import { DrugComponent } from './components/drug/drug.component';
import { AlertsComponent } from './components/alerts/alerts.component';
const routes: Routes = [{path: 'home', component: HomeComponent},
                        {path: '', component: LoginComponent},
                        {path: 'refill', component: RefillComponent},
                        {path: 'dashboard', component: DashboardComponent},
                        {path: 'subscription', component:SubscriptionComponent},
                        {path: 'drug', component:DrugComponent},
                        {path: 'alert', component:AlertsComponent}
                      ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
