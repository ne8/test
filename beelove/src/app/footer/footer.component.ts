import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {
  facebookUrl = 'https://www.facebook.com/stuco.ro/';
  instagramUrl = 'https://www.instagram.com/stucostuco/';
  twitterUrl = 'https://twitter.com/StucoStuco1';
  snapachtUrL = 'tel://+40731898657';
  whatsappUrl = 'tel://+40731898657';
  phoneNumber = '+40731898657';
  emailAddress = 'none';
  // tumblrUrl = 'https://www.tumblr.com/search/stuco1234';
  // youtubeUrl = 'youtube.com';
  // pinterestUrl = 'pintereset.com';
  companyName = 'Stuco';
  constructor() {}

  ngOnInit() {}
}
