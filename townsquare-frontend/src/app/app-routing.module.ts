import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from './components/error/error.component';
import { FriendRequestsComponent } from './components/friend-requests/friend-requests.component';
import { LoginComponent } from './components/login/login.component';
import { PasswordResetComponent } from './components/password-reset/password-reset.component';
import { PostBoardComponent } from './components/post-board/post-board.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SearchResultsComponent } from './components/search-results/search-results.component';
import { SignupComponent } from './components/signup/signup.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [
  // canActivate: [AuthGuard], data: {roles: ["Admin"]}
  // use above statement if using role-based authentication
  { path: "home", component: PostBoardComponent },
  { path: "login", component: LoginComponent },
  { path: "signup", component: SignupComponent },
  { path: "error", component: ErrorComponent },
  { path: "profile", component: ProfileComponent },
  { path: "user", component: UserComponent },
  { path: "user-search", component: SearchResultsComponent },
  { path: "friend-requests", component: FriendRequestsComponent },
  { path: "password-reset", component: PasswordResetComponent },
  { path: "**", redirectTo: "login" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
