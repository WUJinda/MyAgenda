import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login, Project, Role, Time, TimeRequest, User } from '../model/user.model';
import { Observable } from "rxjs/index";
import { Register } from '../model/user.model';


const baseUrl = 'http://localhost:8090';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  public apiUser = baseUrl +"/user/"+localStorage.getItem("token");
  public apiManager = baseUrl +"/manager/"+localStorage.getItem("token");

  constructor(private http: HttpClient) { }

  getUsers() : Observable<any> {
    return this.http.get(baseUrl + 'all-users');
  }
  public getToken(): string {
    return sessionStorage.getItem('TOKEN') || '';
  }
  //  Pour Employee
  choixProjet(timeRequest: TimeRequest):Observable<any>{
    return this.http.post<any>(`${this.apiUser}/choose-proj`,timeRequest)
  }

  getAllTimes():Observable<Time[]>{
    return this.http.get<Time[]>(`${this.apiUser}/times/all`)
  }

  getAllUsersProjects():Observable<any>{
    return this.http.get<Project[]>(`${this.apiUser}/projects`)
  }


  //  Register

  public home():Observable<any> {
    return this.http.get<any>(`${baseUrl}/`)
  }
  
  public signUp(register: Register):Observable<Register> {
    return this.http.post<Register>(`${baseUrl}/auth/`,register)

  }

  public login(login: Login):Observable<any> {
    return this.http.post<any>(`${baseUrl}/auth/signin`,login)

  }

  public logout(token:string):Observable<any>{
    return this.http.patch<any>(`${baseUrl}/auth/logout/${token}`,{})
  }

// LoggedInUserAndRoleService
  public getLoggedInUser():Observable<any> {
    return this.http.get<any>(`${baseUrl}/users/loggedInUserId`);
  }

  public getRoleUser(user:User):Observable<Role>{
    return this.http.get<Role>(`${baseUrl}/users/getRole/${user.id}`)
  }

  // Manager
  public getAll():Observable<User[]>{
    return this.http.get<User[]>(`${this.apiManager}/users-of-manager`);
  }

  public getAllProjects():Observable<Project[]>{
    return this.http.get<Project[]>(`${this.apiManager}/projects`);
  }

  public addUserAdmin(register:Register):Observable<User>{
    return this.http.post<User>(`${this.apiManager}/add-user`,register)
  }

  public createProject(project:Project){
    return this.http.post<Project>(`${this.apiManager}/create-projet`,project)
  }

  public getProjects():Observable<Project[]>{
    return this.http.get<Project[]>(`${this.apiManager}/projects`)
  }

}