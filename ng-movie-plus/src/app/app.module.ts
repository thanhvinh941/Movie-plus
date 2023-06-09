import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { vi_VN } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import vi from '@angular/common/locales/vi';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgZorroAntdModule } from './common/ng-zorro-antd.module';
import { HomePageComponent } from './view/page/home-page/home-page.component';
import { TicketPageComponent } from './view/page/ticket-page/ticket-page.component';
import { MoviePageComponent } from './view/page/movie-page/movie-page.component';
import { MovieDetailPageComponent } from './view/page/movie-detail-page/movie-detail-page.component';
import { BreadcrumbComponent } from './view/common/breadcrumb/breadcrumb.component';

registerLocaleData(vi);

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    TicketPageComponent,
    MoviePageComponent,
    MovieDetailPageComponent,
    BreadcrumbComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgZorroAntdModule
  ],
  providers: [
    { provide: NZ_I18N, useValue: vi_VN }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
