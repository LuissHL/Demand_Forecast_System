import { Routes } from '@angular/router';
import { UploadComponent } from './pages/upload/upload';
import { ConfigurationComponent } from './pages/configurations/configurations';

export const routes: Routes = [
  { path: '', redirectTo: 'forecast', pathMatch: 'full' },
  { path: 'forecast', component: UploadComponent },
  { path: 'configuration', component: ConfigurationComponent },
];
