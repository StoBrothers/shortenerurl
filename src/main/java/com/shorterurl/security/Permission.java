package com.shorterurl.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * All permissions.
 * 
 * @author Sergey Stotskiy
 */
public enum Permission {

    // @formatter:off
    VOTES_CREATE,
    VOTES_INFO,
    DISHE_CREATE,
    DISHE_VIEW,
    DISHE_UPDATE,
    DISHE_DELETE,
    RESTAURANT_VIEW,
    CABINET_ADMIN,
    REF_ADMINS_VIEW,
    EDIT_SETTINGS,
    REF_MENU,
    REF_ACCOUNTS_DELETE,
    REF_ACCOUNTS_EDIT,
    REF_ACCOUNTS_RELATE_WITH_EMPLOYEE,
    REF_ACCOUNTS_VIEW;

    

    public static final List<Permission> APP_COMMON = Collections
        .unmodifiableList(Arrays.asList(
            VOTES_INFO,
            DISHE_VIEW,
            RESTAURANT_VIEW
         ));
    // @formatter:on

}
