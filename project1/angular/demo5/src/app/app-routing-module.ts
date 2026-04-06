import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login-component/login-component';
import { DashboardComponent } from './dashboard-component/dashboard-component';
import { UploadComponent } from './upload-component/upload-component';
import { Products } from './pages/products/products';
import { Scanner } from './scanner/scanner';


const routes: Routes = [

  { path: '', component: LoginComponent },
  { path: 'upload', component: UploadComponent },
  { path: 'dashboard', component: DashboardComponent },
    { path: 'scanner', component: Scanner },
  { path: 'products', component: Products }

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})




export class AppRoutingModule { }
