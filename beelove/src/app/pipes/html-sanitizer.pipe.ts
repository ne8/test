import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({
  name: 'HTML'
})
export class HtmlSanitizerPipe implements PipeTransform {
  constructor(private sanitized: DomSanitizer) {
  }
  transform(value: string, args?: any): any {
    return this.sanitized.bypassSecurityTrustHtml(value);  }

}
