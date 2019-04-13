import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Product } from '../product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @Input() products: ProductComponent[];
  @Output() product = new EventEmitter<Product>();
  constructor() {}

  ngOnInit() {}

  addProductToCart(product) {
    this.product.emit(product);
  }
}
