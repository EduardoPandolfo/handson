package com.eduardokp.handson.primefaces.usuario;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class InfoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String info;
    private String authorities;
    private String name;
    private boolean admin;

    @PostConstruct
    public void init() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        name = "" + authentication.getName();
        authorities = "" + authentication.getAuthorities();
        admin = authorities.contains("ROLE_ADMIN");
        info =  "Logado como: " + name;
    }

    public String getInfo() {
        return info;
    }

    public String getAuthorities() {
        return authorities;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return admin;
    }
}
