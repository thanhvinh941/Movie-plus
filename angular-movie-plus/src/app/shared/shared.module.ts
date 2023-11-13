import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import {
  MemberFooterComponent,
  MemberHeaderComponent,
  OperatorFooterComponent,
  OperatorHeaderComponent,
} from './layout';
import { NgZorroAntdModule } from './ng-zorro-antd.module';
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    NgZorroAntdModule,
  ],
  declarations: [
    MemberHeaderComponent,
    MemberFooterComponent,
    OperatorHeaderComponent,
    OperatorFooterComponent,
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    MemberHeaderComponent,
    MemberFooterComponent,
    OperatorHeaderComponent,
    OperatorFooterComponent,
    NgZorroAntdModule,
  ],
})
export class SharedModule {}
