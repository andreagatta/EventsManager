package org.elis.eventsmanager.util;

import org.elis.eventsmanager.dto.response.UserResponse;

public class UtilPath {

    //---------------ROLE--------------
    private static final String ADMIN = "/admin";
    private static final String SUPERADMIN = "/superadmin";
    private static final String VENDOR = "/vendor";
    private static final String CLIENT = "/client";


    //-------------TABLES-------------------
    private static final String PLACE="/place";
    private static final String EVENT="/event";
    private static final String USER="/user";
    private static final String TICKET="/ticket";

    //---------COMMANDS--------------
    private static final String FIND_ALL= "/find_all";
    private static final String CREATE = "/create";
    private static final String ADD = "/add";
    private static final String REMOVE = "/remove";

//----------------URL ENDPOINTS---------------
    public static final String FIND_ALL_PLACE_CLIENT = PLACE+CLIENT+FIND_ALL;
    public static final String FIND_ALL_PLACE_ADMIN = PLACE+ADMIN+FIND_ALL;
    public static final String CREATE_PLACE_ADMIN = PLACE+ADMIN+CREATE;
    public static final String FIND_ALL_USER_ADMIN = USER+ADMIN+FIND_ALL;
    public static final String CREATE_EVENT_VENDOR = EVENT+VENDOR+CREATE;
    public static final String CHANGE_ROLE_USER_SUPERADMIN = USER+SUPERADMIN+"/change_role";

    public static final String BLOCK_USER_ADMIN = USER+ADMIN+"/block";
    public static final String UNLOCK_USER_ADMIN = USER+ADMIN+"/unlock";

    public static final String SIGNIN_USER_CLIENT = USER+CLIENT+"/signin";
    public static final String LOGIN_USER_CLIENT = USER+CLIENT+"/login";

    public static final String CREATE_TICKET_VENDOR = TICKET+VENDOR+CREATE;


    //------------URL FILTER CHAIN MANAGER---------------

    public static final String USER_SUPERADMIN = USER+SUPERADMIN+"/**";
    public static final String USER_ADMIN = USER+ADMIN+"/**";
    public static final String USER_VENDOR = USER+VENDOR+"/**";
    public static final String USER_CLIENT = USER+CLIENT+"/**";

    public static final String PLACE_SUPERADMIN = PLACE+SUPERADMIN+"/**";
    public static final String PLACE_ADMIN = PLACE+ADMIN+"/**";
    public static final String PLACE_VENDOR = PLACE+VENDOR+"/**";
    public static final String PLACE_CLIENT = PLACE+CLIENT+"/**";

    public static final String EVENT_SUPERADMIN = EVENT+SUPERADMIN+"/**";
    public static final String EVENT_ADMIN = EVENT+ADMIN+"/**";
    public static final String EVENT_VENDOR = EVENT+VENDOR+"/**";
    public static final String EVENT_CLIENT = EVENT+CLIENT+"/**";






}
