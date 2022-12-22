import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PostBoardComponent } from './components/post-board/post-board.component';
import { LoginComponent } from './components/login/login.component';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { AuthInterceptor } from './auth/auth.interceptor';
import { AuthService } from './services/auth/auth.service';
import { SignupComponent } from './components/signup/signup.component';
import { ErrorComponent } from './components/error/error.component';
import { ProfileComponent } from './components/profile/profile.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UserComponent } from './components/user/user.component';
import { SearchResultsComponent } from './components/search-results/search-results.component';
import { FriendRequestsComponent } from './components/friend-requests/friend-requests.component';

import { AutocompleteLibModule } from 'angular-ng-autocomplete';
import { ProfileContentComponent } from './components/profile-content/profile-content.component';
import { PasswordResetComponent } from './components/password-reset/password-reset.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PostBoardComponent,
    LoginComponent,
    SignupComponent,
    ErrorComponent,
    ProfileComponent,
    UserComponent,
    SearchResultsComponent,
    FriendRequestsComponent,
    ProfileContentComponent,
    PasswordResetComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    NgbModule,
    AutocompleteLibModule
  ],
  providers: [
    AuthGuard, 
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
