import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { WarehouseComponent } from './warehouse/warehouse.component';
import { OrdersComponent } from './orders/orders.component';
import { BackofficeRoutesModule } from './routes/routes/routes.module';
import { LoginComponent } from './user-handler/login/login.component';
import { MenuComponent } from './menu/menu.component';
import {MatMenuModule, MatPaginatorModule, MatSortModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material';
@NgModule({
  declarations: [
    AppComponent,
    WarehouseComponent,
    OrdersComponent,
    LoginComponent,
    MenuComponent,

  ],
  imports: [
    BrowserModule,
    BackofficeRoutesModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
