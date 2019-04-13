import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Product } from '../product';
import { CheckoutService } from './checkout.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  @Input() products: Product[];
  @Output() outProducts = new EventEmitter<Product[]>();

  checkoutForm: FormGroup;

  constructor(
    config: NgbModalConfig,
    private modalService: NgbModal,
    private checkoutService: CheckoutService,
    private notifierService: NotifierService) {
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {
    this.checkoutForm = new FormGroup({
      userData: new FormGroup({
        firstName: new FormControl(null, [Validators.required]),
        lastName: new FormControl(null, [Validators.required]),
        emailAddress: new FormControl(null, [Validators.required, Validators.email])
      }),
      address: new FormGroup({
        city: new FormControl(null, [Validators.required]),
        postalCode: new FormControl(null, [Validators.required]),
        phoneNumber: new FormControl(null, [
          Validators.required,
          Validators.minLength(6)
        ]),
        fullAddress: new FormControl(null, [
          Validators.required,
          Validators.minLength(6)
        ])
      }),
      products: new FormGroup({
        productId: new FormControl(null, [Validators.required]),
        productName: new FormControl(null, [Validators.required]),
        quantity: new FormControl(null, [Validators.required]),
        price: new FormControl(null, [Validators.required]),
        total: new FormControl(null, [Validators.required])
      })
    });
  }
  onSubmit() {
    this.checkoutForm.value['products'] = this.products;
    const order = JSON.stringify(this.checkoutForm.value);
    this.checkoutService.placeOrder(order).subscribe(
      data => {
        this.notifierService.notify('success', 'Comanda dumneavoastra a fost plasata cu success');
        sessionStorage.clear();
        this.modalService.dismissAll();
        this.outProducts.emit([]);
      },
      error => {
        this.notifierService.notify('error', 'Comanda dvs nu a putut fi procesata, va rugam reveniti!');
        console.log(error);
      });
  }
  open(content) {
    this.modalService.open(content, { size: 'lg' });
  }
}
