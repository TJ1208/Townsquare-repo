import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { PostBoardComponent } from './components/post-board/post-board.component';

const routes: Routes = [
  { path: "home", component: PostBoardComponent },
  { path: "login", component: LoginComponent },
  { path: "**", redirectTo: "home" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
