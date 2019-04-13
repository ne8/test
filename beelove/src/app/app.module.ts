import { BrowserModule } from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule, ErrorHandler} from '@angular/core';

import { AppComponent } from './app.component';
import {MenuComponent} from './menu/menu/menu.component';
import { ImageSliderComponent } from './slider/image-slider/image-slider.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { ColorBeePipe } from './pipes/color-bee.pipe';
import { FooterComponent } from './footer/footer.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AppRoutesModule } from './routes/routes.module';
import { ShopListComponent } from './shop/shop-list/shop-list.component';
import { CartComponent } from './shop/cart/cart.component';
import { ProductComponent } from './shop/product/product.component';
import { HoneyComponent } from './honey/honey/honey.component';
import { HtmlSanitizerPipe } from './pipes/html-sanitizer.pipe';
import { BlogPostComponent } from './honey/blog-post/blog-post.component';
import { CheckoutComponent } from './shop/checkout/checkout.component';
import { NgbModalModule} from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RegisterComponent } from './users/register/register.component';
import { LoginComponent } from './users/login/login.component';
import { TokenInterceptor } from './users/token-interceptor.service';
import { ErrorInterceptor } from './users/error-interceptor.service';
import { FakeBackendInterceptorService } from './users/fake-backend-interceptor.service';
import { NotifierModule } from 'angular-notifier';
import { ErrorsHandler } from './error-handling/errors-handler';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ImageSliderComponent,
    ColorBeePipe,
    FooterComponent,
    HomePageComponent,
    ShopListComponent,
    CartComponent,
    ProductComponent,
    HoneyComponent,
    HtmlSanitizerPipe,
    BlogPostComponent,
    CheckoutComponent,
    PageNotFoundComponent,
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutesModule,
    NgbModalModule,
    ReactiveFormsModule,
    NotifierModule.withConfig({
      position: {
        horizontal: {
          position: 'right',
          distance: 125
        },
        vertical: {
          position: 'top',
          distance: 120,
        }
      },

    })
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: FakeBackendInterceptorService, multi: true},
    {provide: ErrorHandler, useClass: ErrorsHandler},

  ],
  bootstrap: [AppComponent],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]

})
export class AppModule { }
