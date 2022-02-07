export interface User {
  id: number;
  username: string;
  email: string;
  firstname: string;
  lastname: string;
  fullname: string;
  enabled: boolean;
  role: Role;
  createdAt: string;
  manager : User;
  tokenSignature:string
}

export interface AddUser {
  username: string;
  email: string;
  firstname: string;
  lastname: string;
  password: '123456';
  enabled: boolean;
  roleId: number;
  managerId : number;
}

export interface Updateuser {
  email: string;
  firstname: string;
  lastname: string;
  enabled: boolean;
  roleId: number;
  managerId:number
}


export interface Role{
  id:number;
  label:string;
} 

export interface Register{
  username: string;
  firstname: string;
  lastname: string;
  email: string;
  managerId: number;
  roleId: number;
}

export interface Login{
  username: string;
  password: string
}

export interface Project {
  id:number;
  title: string;
  description: string;
  manager_id: number;
}

export interface ProjectEntity{
  id: number;
  description: string;
  title: string;
  manager: User;
}

export interface Time{
  id: number;
  dateEnd: Date,
  dateOfProject: string;
  dateStart: Date;
  project:ProjectEntity;
}

export interface TimeRequest{
  date_start: Date;
  date_end: Date;
  projectId: number;
}
