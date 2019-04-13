import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrdersComponent } from 'src/app/orders/orders.component';
import { RouterModule, Routes, Router } from '@angular/router';
import { WarehouseComponent } from 'src/app/warehouse/warehouse.component';
import { LoginComponent } from 'src/app/user-handler/login/login.component';


const appRoutes: Routes = [
  {
    path: '',
    component: OrdersComponent
  },
  {
    path: 'warehouse',
    component: WarehouseComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
];


@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, {
    })
  ],
  exports: [RouterModule]
})
export class BackofficeRoutesModule { }
