package org.elis.eventsmanager.util;

import org.elis.eventsmanager.model.Role;
import org.elis.eventsmanager.model.User;

public class AdminCheck{

    public static boolean checkAuthorization(User admin){
        if(admin.getRole().equals(Role.ADMIN) || admin.getRole().equals(Role.SUPERADMIN) && !admin.isBlocked())
            return true;
        else
            return false;
    }
}
