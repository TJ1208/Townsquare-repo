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
    return this.http.get<Contact[]>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/contact`);
  }

  getAllUserContacts(userId: number): Observable<Contact[]> {
    return this.http.get<Contact[]>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/contact/${userId}`);
  }

  getContactById(contactId: number): Observable<Contact> {
    return this.http.get<Contact>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/contact/id/${contactId}`);
  }

  addContact(contact: Contact): Observable<String> {
    return this.http.post<String>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/contact/add`, contact);
  }

  updateContact(contact: Contact): Observable<String> {
    return this.http.put<String>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/contact/update`, contact);
  }

  deleteContact(contactId: number): Observable<String> {
    return this.http.delete<String>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/contact/delete/${contactId}`);
  }
  
}
