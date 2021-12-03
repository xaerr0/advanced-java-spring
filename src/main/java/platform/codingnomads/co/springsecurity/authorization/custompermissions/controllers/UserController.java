package platform.codingnomads.co.springsecurity.authorization.custompermissions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import platform.codingnomads.co.springsecurity.authorization.custompermissions.models.User;
import platform.codingnomads.co.springsecurity.authorization.custompermissions.services.UserService;

@RestController
public class UserController {

        @Autowired
        UserService userService;

        @GetMapping("/user")
        @PostAuthorize("hasPermission(returnObject, 'READ')")
        public User getEntityById(@RequestParam String email) {
                return userService.getUser(email);
        }

        @GetMapping("/user/delete/{id}")
        @PreAuthorize("hasPermission(#id, 'platform.codingnomads.co.springsecurity.authorization.custompermissions.models.User', 'DELETE')")
        public String deleteEntity(@PathVariable Long id) {
                userService.deleteUser(id);
                return ("deleted user with id: " + id);
        }
}
