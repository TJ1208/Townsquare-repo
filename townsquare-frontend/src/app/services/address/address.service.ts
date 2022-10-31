import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Address } from 'src/app/models/Address';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http: HttpClient) { }

  getAllAddresses(): Observable<Address[]> {
    return this.http.get<Address[]>(`http://localhost:8181/api/address`);
  }

  getAllUserAddressses(userId: number): Observable<Address[]> {
    return this.http.get<Address[]>(`http://localhost:8181/api/address/${userId}`);
  }

  getAddressById(addressId: number): Observable<Address> {
    return this.http.get<Address>(`http://localhost:8181/api/address/id/${addressId}`);
  }

  addAddress(address: Address): Observable<String> {
    return this.http.post<String>(`http://localhost:8181/api/address/add`, address);
  }

  updateAddress(address: Address): Observable<String> {
    return this.http.put<String>(`http://localhost:8181/api/address/update`, address);
  }

  deleteAddress(addressId: number): Observable<String> {
    return this.http.delete<String>(`http://localhost:8181/api/address/delete/${addressId}`);
  }
  
}
