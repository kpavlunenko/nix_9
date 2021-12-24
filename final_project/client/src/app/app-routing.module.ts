import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'companies',
    pathMatch: 'full'
  },
  {
    path: 'companies',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/company/company.module').then(module => module.CompanyModule)
  }
  ,
  {
    path: 'counterparties',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/counterparty/counterparty.module').then(module => module.CounterpartyModule)
  },
  {
    path: 'agreements',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/agreement/agreement.module').then(module => module.AgreementModule)
  },
  {
    path: 'business_directions',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/business-direction/business-direction.module').then(module => module.BusinessDirectionModule)
  },
  {
    path: 'nomenclatures',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/nomenclature/nomenclature.module').then(module => module.NomenclatureModule)
  },
  {
    path: 'currencies',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/currency/currency.module').then(module => module.CurrencyModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
