package grupo10.dsw.Controlers;



import grupo10.dsw.Entities.User;
import grupo10.dsw.Services.AuthService;
import grupo10.dsw.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final AuthService authService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user){
        User userC = authService.register(user);
        return new ResponseEntity<User>(userC, HttpStatus.CREATED);

    }


    @PreAuthorize("authentication.principal.id.equals(#userId) OR hasRole('ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable UUID userId){
        User userUpdated = authService.update(user, userId);

        return ResponseEntity.ok(userUpdated);
    }

    @PreAuthorize("authentication.principal.id.equals(#userId) OR hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable UUID userId){
        try{
            userService.delete(userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("authentication.principal.id.equals(#userId) OR hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable UUID userId){
        User user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/findAll")
    public ResponseEntity<?> getAll(){
        List<User> user = userService.findAll();
        return ResponseEntity.ok(user);
    }


}
