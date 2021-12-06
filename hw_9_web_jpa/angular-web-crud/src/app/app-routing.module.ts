import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
