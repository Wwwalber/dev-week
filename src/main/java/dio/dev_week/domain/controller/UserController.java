package dio.dev_week.domain.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dio.dev_week.domain.model.User;
import dio.dev_week.domain.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/users") // expose the endpoints
public class UserController {

    // controller will call the service layer (by the interfaces)
    private final UserService userService;

    // constructor to inject by constructor // será injetado pelo spring
    public UserController(UserService userService) {
        this.userService = userService;
    }
            // class do Spring Web como resposta
    @GetMapping("/{id}")  // end poit que recebe id, usa pra busca o usuario, então retorna
    public ResponseEntity<User> findById(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("path")
    public ResponseEntity<User> create(@RequestBody User userToCreate){
        var userCreated = userService.create(userToCreate);
                // cem API devemos retornar a localização do recurso
                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId()).toUri();
        return ResponseEntity.created(location).body(userCreated);
    }
    
}
