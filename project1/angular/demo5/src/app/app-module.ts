import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';

import { ZXingScannerModule } from '@zxing/ngx-scanner';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login-component/login-component';
import { UploadComponent } from './upload-component/upload-component';
import { DashboardComponent } from './dashboard-component/dashboard-component';
import { Products } from './pages/products/products';
import { Scanner } from './scanner/scanner';


@NgModule({
  declarations: [
    App,
    LoginComponent,
    UploadComponent,
    DashboardComponent,
    Products,
    Scanner
  ],
  imports: [
    BrowserModule,
    ZXingScannerModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideClientHydration(withEventReplay())
  ],
  bootstrap: [App]
})
export class AppModule { }
