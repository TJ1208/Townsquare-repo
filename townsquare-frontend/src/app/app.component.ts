import { Component } from '@angular/core';
import { AuthService } from './services/auth/auth.service';
import { SocketService } from './services/socket/socket.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'townsquare-fe';
  isLoggedIn: any = this.authService.isLoggedIn();
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.isLoggedIn();
  }

}
