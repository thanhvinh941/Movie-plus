import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgZorroAntdModule } from './common/ng-zorro-antd.module';
import { HomePageComponent } from './view/page/home-page/home-page.component';
import { TicketPageComponent } from './view/page/ticket-page/ticket-page.component';
import { MoviePageComponent } from './view/page/movie-page/movie-page.component';
import { MovieDetailPageComponent } from './view/page/movie-detail-page/movie-detail-page.component';
import { BreadcrumbComponent } from './view/common/breadcrumb/breadcrumb.component';
import { AuthInterceptor } from './shared/services/auth-service/AuthInterceptor';
import { HomeComponent } from './modules/member/page/home/home.component';
import { OperatorModule } from './modules/operator/operator.module';
import { MemberModule } from './modules/member/member.module';
import { MovieInfoData } from './modules/operator/data/movie-info.data';
import { GenreTypeData } from './modules/operator/data/genre-type.data';
import { TransferAlphabet } from './common/custom-pipe/TransferAlphabet';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    TicketPageComponent,
    MoviePageComponent,
    MovieDetailPageComponent,
    BreadcrumbComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgZorroAntdModule,
    ReactiveFormsModule,
    OperatorModule,
    MemberModule,
  ],
  providers: [
    { provide: NZ_I18N, useValue: en_US },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    MovieInfoData,
    GenreTypeData,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
