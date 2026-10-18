import { Routes } from '@angular/router';
import { authGuard } from '../auth/auth.guard';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { LoginComponent } from '../login/login.component';
import { ReportComponent } from '../report/report.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [authGuard] },
  { path: 'report', component: ReportComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: 'login' },
];
