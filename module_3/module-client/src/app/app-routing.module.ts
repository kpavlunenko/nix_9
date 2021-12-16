import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'users',
    pathMatch: 'full'
  },
  {
    path: 'users',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/user/user.module').then(module => module.UserModule)
  },
  {
    path: 'bankAccounts',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/bank-account/bank-account.module').then(module => module.BankAccountModule)
  },
  {
    path: 'categories',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/category/category.module').then(module => module.CategoryModule)
  },
  {
    path: 'bankOperations',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/bank-operation/bank-operation.module').then(module => module.BankOperationModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
