import { Injectable } from '@angular/core';
import { Socket } from 'ngx-socket-io';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SocketService {

  message$ = new BehaviorSubject<any>({});
  constructor(private socket: Socket) { }

  sendMessage(message: any) {
    this.socket.emit('message', message);
  }

  getNewMessage = () => {
    this.socket.on('message', (message: any) => {
      this.message$.next(message);
    });

    return this.message$.asObservable();
  }
}
