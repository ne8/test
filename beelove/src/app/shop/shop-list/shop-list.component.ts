import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductsDataService } from './providers/products-data.service';
import { NotifierService } from 'angular-notifier';
import { e } from '@angular/core/src/render3';

@Component({
  selector: 'app-shop-list',
  templateUrl: './shop-list.component.html',
  styleUrls: ['./shop-list.component.css']
})
export class ShopListComponent implements OnInit {
  addedProductsToCart: Product[];
  availableProducts: Product[];
  constructor(private productDataService: ProductsDataService,
    private notifierService: NotifierService) {
    this.addedProductsToCart = [];
    this.availableProducts = [];
  }

  ngOnInit() {
    // this.addToCart();
    this.productDataService.getProucts().subscribe((result: any) => {
      this.availableProducts = result;
    });
  }

  handleProduct(product: Product) {
    if (!this.addedProductsToCart.includes(product)) {
      this.addedProductsToCart.push(product);
    } else {
      const indexOfProduct = this.addedProductsToCart.indexOf(product);
      if (this.addedProductsToCart[indexOfProduct].unit > product.availableUnits) {
        this.notifierService.notify("error", "Cantitatea de produse comandate nu se mai afla in stoc, contactati-ne telefonic!");
      } else{
        this.addedProductsToCart[indexOfProduct].unit++;
      }
    }
  }
  handleCheckout(event) {
    if (event === 'resolved') {
      this.addedProductsToCart = [];
    }
  }
}
