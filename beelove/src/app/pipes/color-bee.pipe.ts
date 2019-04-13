import {Pipe, PipeTransform} from '@angular/core';
import {DomSanitizer, SafeHtml} from '@angular/platform-browser';

@Pipe({
  name: 'colorBee'
})
//TODO: sanitizer to display html
export class ColorBeePipe implements PipeTransform {

  constructor(private sanitized: DomSanitizer) {
  }

  transform(text: string, separator): SafeHtml {
    if (text !== undefined) {
      const words = text.split(separator);
      let result = '';
      let i = 0;
      for (const word of words) {
        if (i === 0) {
          result += word;
          i = 1;
        } else {
          result += '<span style="color: #f8b742">' + word + '</span>'
          i = 0;
        }
      }
      return this.sanitized.bypassSecurityTrustHtml(result);
    }
  }

}
