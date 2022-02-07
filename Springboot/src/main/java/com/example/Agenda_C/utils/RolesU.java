package com.example.Agenda_C.utils;

public class RolesU {

    // This is useful so that we could have access to the employees,admins and managers without
    // creating a class for them
    public final static Long EMPLOYEE_ID = 1L;
    public final static Long MANAGER_ID = 2L;
    public final static Long ADMIN_ID = 3L;


    public final static String EMPLOYEE_LABEL = "EMPLOYEE";
    public final static String MANAGER_LABEL = "MANAGER";
    public final static String ADMIN_LABEL = "ADMIN";

    public Long label2id(String role){
        if(role == EMPLOYEE_LABEL){return EMPLOYEE_ID;}
        if(role == MANAGER_LABEL){return MANAGER_ID;}
        if(role==ADMIN_LABEL){return ADMIN_ID;}
        return 0L;
    }
}
