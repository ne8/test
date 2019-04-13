import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductsDataService } from './providers/products-data.service';

@Component({
  selector: 'app-shop-list',
  templateUrl: './shop-list.component.html',
  styleUrls: ['./shop-list.component.css']
})
export class ShopListComponent implements OnInit {
  addedProductsToCart: Product[];
  availableProducts: Product[];
  constructor(public productDataService: ProductsDataService) {
    this.addedProductsToCart = [];
    this.availableProducts = [];
  }

  ngOnInit() {
    // this.addToCart();
    this.productDataService.getProucts().subscribe((result: any) => {
      this.availableProducts = result;
    });
  }

  handleProduct(product) {
    if (!this.addedProductsToCart.includes(product)) {
      this.addedProductsToCart.push(product);
    } else {
      const indexOfProduct = this.addedProductsToCart.indexOf(product);
      this.addedProductsToCart[indexOfProduct].unit++;
    }
  }
  handleCheckout(event) {
    if (event === 'resolved') {
      this.addedProductsToCart = [];
    }
  }
}
