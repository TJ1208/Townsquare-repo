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
    return this.http.get<Address[]>(`https://townsquare-backend.azurewebsites.net/api/address`);
  }

  getAllUserAddressses(userId: number): Observable<Address[]> {
    return this.http.get<Address[]>(`https://townsquare-backend.azurewebsites.net/api/address/${userId}`);
  }

  getAddressById(addressId: number): Observable<Address> {
    return this.http.get<Address>(`https://townsquare-backend.azurewebsites.net/api/address/id/${addressId}`);
  }

  addAddress(address: Address): Observable<String> {
    return this.http.post<String>(`https://townsquare-backend.azurewebsites.net/api/address/add`, address);
  }

  updateAddress(address: Address): Observable<String> {
    return this.http.put<String>(`https://townsquare-backend.azurewebsites.net/api/address/update`, address);
  }

  deleteAddress(addressId: number): Observable<String> {
    return this.http.delete<String>(`https://townsquare-backend.azurewebsites.net/api/address/delete/${addressId}`);
  }
  
}
