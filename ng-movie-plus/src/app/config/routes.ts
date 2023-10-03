import { Routes, CanActivate } from '@angular/router';
import { AuthGuard } from '../shared/auth/AuthGuard';
import { HomeComponent } from '../modules/member/page/home/home.component';

export const ROUTES: Routes = [
  { path: '', component: HomeComponent },
  { 
    path: 'profile',
    // component: ProfileComponent,
    canActivate: [AuthGuard] 
  },
  { path: '**', redirectTo: '' }
];