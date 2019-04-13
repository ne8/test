import { Component, OnInit } from '@angular/core';
import { SliderDataService } from '../providers/slider-data.service';
import { interval } from 'rxjs';
import { AboutUsDataService } from '../providers/about-us-data.service';

@Component({
  selector: 'app-image-slider',
  templateUrl: './image-slider.component.html',
  styleUrls: ['./image-slider.component.css']
})
export class ImageSliderComponent implements OnInit {
  selectedIndex: number;
  transform: number;
  sliderArray: object[];
  refreshInterval: number;

  aboutUsArray: AboutUs[];
  aboutUsBanner: Banner;

  constructor(
    private sliderDataService: SliderDataService,
    private aboutUsDataService: AboutUsDataService
  ) {
    this.sliderArray = [];
    this.selectedIndex = 0;
    this.transform = 100;
    this.refreshInterval = 10000;
    this.aboutUsBanner = new Banner();
    this.aboutUsArray = new Array();
  }

  ngOnInit() {
    this.sliderDataService.getData().subscribe((result: SliderImage[]) => {
      this.sliderArray = result;
    });
    interval(this.refreshInterval).subscribe(x => {
      if (this.selectedIndex + 2 > this.sliderArray.length) {
        this.selectedIndex = 0;
      } else {
        this.selectedIndex++;
      }
    });

    this.aboutUsDataService.getAboutUsContent().subscribe((result: AboutUs[] ) => {
      this.aboutUsArray = result;
    });
    this.aboutUsDataService.getAboutUsBannerContent().subscribe((result: Banner) => {
        this.aboutUsBanner = result;
    });
  }

  selected(x) {
    if (this.selectedIndex > this.sliderArray.length) {
      this.selectedIndex = 0;
    }
    this.selectedIndex = x;
  }

  keySelected(x) {
    this.selectedIndex = x;
  }
}

interface SliderImage {
  imageLocation: string;
  alt: string;
  title: string;
  description: string;
}



class AboutUs {
  imgLocation: string;
  alt: string;
  subtitle: string;
  text: string;
}

class Banner {
  title: string;
  subtitle: string;
}
