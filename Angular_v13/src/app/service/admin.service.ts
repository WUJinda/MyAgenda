import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project, User, Updateuser, AddUser } from '../model/user.model';


@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private baseUrl = 'http://localhost:8090';
  public adminUrl = 'http://localhost:8090/admin';

  constructor(private http: HttpClient) { }

  public getUsers() : Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/users/all`);
  }

  public addUser(user: AddUser) : Observable<User> {
    return this.http.post<User>(`${this.adminUrl}/${localStorage.getItem("token")}/add-user`,user);
  }

  public updateUser(id: number, updateuser: Updateuser) : Observable<User> {
    return this.http.patch<User>(`${this.adminUrl}/${localStorage.getItem("token")}/${id}`,{updateuser});
  }

  public deleteUser(username: string) : Observable<void> {
    return this.http.delete<void>(`${this.adminUrl}/${localStorage.getItem("token")}/delete/${username}`);
  }

  public deleteUsers(usernames: string[]) : Observable<User[]> {
    return this.http.delete<User[]>(`${this.adminUrl}/${localStorage.getItem("token")}/delete-multi`);
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
