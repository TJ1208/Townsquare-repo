import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { PostBoardComponent } from './components/post-board/post-board.component';

const routes: Routes = [
  // canActivate: [AuthGuard], data: {roles: ["Admin"]}
  // use above statement if using role-based authentication
  { path: "home", component: PostBoardComponent },
  { path: "login", component: LoginComponent },
  { path: "**", redirectTo: "login" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
