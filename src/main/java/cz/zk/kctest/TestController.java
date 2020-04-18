package cz.zk.kctest;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class TestController {

    private final HttpServletRequest request;

    public TestController(HttpServletRequest request) {
        this.request = request;
    }



    @GetMapping(value = "/users")
    public String handleUserInfoRequest(Model model) {
        return ("Ringo, George, Paul, John");
    }

    @GetMapping(value = "/users/id")
    public String handleUserId() {
        return ("Nested /users/id request .....");
    }

    @GetMapping(value = "/songs")
    public String handleSongsInfoRequest() {
        return String.format ("Email verified: %s ", getEmailVerified());
    }

    @PutMapping(value = "/songs")
    public String handleSongsPutRequest() {
        return ("Songs PUT request");
    }

    @GetMapping(value = "/open")
    public String handleOpenInfoRequest() {
        return ("Opened data !");
    }

    private boolean getEmailVerified() {
        KeycloakSecurityContext cont = getKeycloakSecurityContext();
        return cont.getToken().getEmailVerified();
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }


}