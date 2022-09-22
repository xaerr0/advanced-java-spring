package platform.codingnomads.co.springsecurity.recipeapi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import platform.codingnomads.co.springsecurity.recipeapi.models.securitymodels.CustomUserDetails;
import platform.codingnomads.co.springsecurity.recipeapi.services.userservices.CustomUserDetailService;

@RestController
public class UserController {

    @Autowired
    CustomUserDetailService userDetailService;

    @GetMapping("/current-user")
    public CustomUserDetails getUser(Authentication authentication) {
        return (CustomUserDetails) authentication.getPrincipal();
    }

    @PostMapping("/new-user")
    public ResponseEntity<?> createNewUser(@RequestBody CustomUserDetails userDetails) {
        try {
            return ResponseEntity.ok(userDetailService.createNewUser(userDetails));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
