package org.elis.eventsmanager.util;

import org.elis.eventsmanager.model.Role;
import org.elis.eventsmanager.model.User;

public class SuperAdminCheck{

    public static boolean checkAuthorization(User superAdmin){
        if(superAdmin.getRole().equals(Role.SUPERADMIN) && !superAdmin.isBlocked())
            return true;
        else
            return false;
    }
}
