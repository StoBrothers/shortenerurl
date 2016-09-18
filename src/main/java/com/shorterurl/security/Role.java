package com.shorterurl.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Sets;

/**
 * All roles in project with permissions.
 *
 * @author Sergey Stotskiy
 */
public enum Role {

    // @formatter:off
    ADMIN("Admin", "Admin", Arrays.asList(
        Permission.CABINET_ADMIN

    )),

    APP_HEAD("АПП User", "User",
        Stream.concat(
            Permission.APP_COMMON.stream(),
            Arrays.asList(
                Permission.REF_MENU
            ).stream())
        .collect(Collectors.toList()));
    // @formatter:on

    private Set<Permission> permissions;
    private String shortName;
    private String fullName;

    Role(String shortName, String fullName, Collection<Permission> permissions) {
        this(permissions);
        this.fullName = fullName;
        this.shortName = shortName;

    }

    Role(Collection<Permission> permissions) {
        this.permissions = Collections.unmodifiableSet(Sets.newHashSet(permissions));
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public String shortName() {
        return shortName;
    }

    public String fullName() {
        return fullName;
    }
}