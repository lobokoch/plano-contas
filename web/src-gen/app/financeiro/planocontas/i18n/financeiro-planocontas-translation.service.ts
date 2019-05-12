import { HttpClientWithToken } from './../../../security/http-client-token';
import { Injectable } from '@angular/core';
import * as localTranslations from 'src/app/financeiro/planocontas/i18n/pt-br.json';

@Injectable()
export class FinanceiroPlano_contasTranslationService {

  // translations: Object;

  constructor(private http: HttpClientWithToken) {
    // this.loadTranslations();
  }

  /* loadTranslations() {
    this.http.get('src/app/financeiro/planocontas/i18n/pt-br.json')
    .toPromise()
    .then(response => {
      this.translations = response;
    })
    .catch(error => {
      console.log(`Error loading translations: ${error}`);
    });
  } */

  public getTranslation(key: string): string {
      if (localTranslations) {
        const translation = (<any>localTranslations).default[key];
        if (translation) {
          return translation;
        }
      }
      return key;
  }

}
