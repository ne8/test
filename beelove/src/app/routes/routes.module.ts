import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from '../home-page/home-page.component';
import { ShopListComponent } from '../shop/shop-list/shop-list.component';
import { HoneyComponent } from '../honey/honey/honey.component';
import { PageNotFoundComponent } from '../page-not-found/page-not-found.component';
import { LoginComponent } from '../users/login/login.component';
import { RegisterComponent } from '../users/register/register.component';

const appRoutes: Routes = [
  {
    path: '',
    component: HomePageComponent
  },
  {
    path: 'shop',
    component: ShopListComponent
  },
  {
    path: 'despre-miere',
    component: HoneyComponent
  },
  {
    path: 'not-found',
    component: PageNotFoundComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: '**',
    redirectTo: 'not-found'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, {
      // enableTracing: true
    })
  ],
  exports: [RouterModule]
})
export class AppRoutesModule {}
