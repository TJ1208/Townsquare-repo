import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Contact } from 'src/app/models/Contact';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private http: HttpClient) { }

  getAllContacts(): Observable<Contact[]> {
    return this.http.get<Contact[]>(`https://townsquare-backend.azurewebsites.net/api/contact`);
  }

  getAllUserContacts(userId: number): Observable<Contact[]> {
    return this.http.get<Contact[]>(`https://townsquare-backend.azurewebsites.net/api/contact/${userId}`);
  }

  getContactById(contactId: number): Observable<Contact> {
    return this.http.get<Contact>(`https://townsquare-backend.azurewebsites.net/api/contact/id/${contactId}`);
  }

  addContact(contact: Contact): Observable<String> {
    return this.http.post<String>(`https://townsquare-backend.azurewebsites.net/api/contact/add`, contact);
  }

  updateContact(contact: Contact): Observable<String> {
    return this.http.put<String>(`https://townsquare-backend.azurewebsites.net/api/contact/update`, contact);
  }

  deleteContact(contactId: number): Observable<String> {
    return this.http.delete<String>(`https://townsquare-backend.azurewebsites.net/api/contact/delete/${contactId}`);
  }
  
}
