package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticated() {
        try {

            String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }

}
