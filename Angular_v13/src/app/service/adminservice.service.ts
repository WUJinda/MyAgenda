import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Register,Project, User } from '../model/user.model';


@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private baseUrl = 'http://localhost:8090';
  private adminUrl = 'http://localhost:8090/admin/'+localStorage.getItem("token");

  constructor(private http: HttpClient) { }

  public getUsers() : Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/users/all`);
  }

  public addUser(user: User) : Observable<User> {
    return this.http.post<User>(`${this.adminUrl}/add-user`,user);
  }

  public updateUser(user: User) : Observable<User> {
    return this.http.patch<User>(`${this.baseUrl}/admin/edit-profile`,user);
  }

  public deleteUser(username: string) : Observable<void> {
    return this.http.delete<void>(`${this.adminUrl}/delete/${username}`);
  }

  public usersManager(managerId: number) : Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/admin/users-manager/${managerId}`);
  }

  public editRole(userId: number) : Observable<User> {
    return this.http.patch<User>(`${this.baseUrl}/admin/edit-role/${userId}`, userId);
  }

  public changeAff(managerId: number) : Observable<Project> {
    return this.http.patch<Project>(`${this.baseUrl}/admin/change-affec/${managerId}`, managerId);
  }


}
