package grupo10.dsw.Services;


import grupo10.dsw.Entities.Role;
import grupo10.dsw.Entities.User;
import grupo10.dsw.Repositories.UserRepository;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(User userDto) {
        var user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public User update(User user, UUID userId) {
        User userToUpdate = userRepository.findById( userId).orElseThrow();

        userToUpdate.setUsername(user.getUsername());
        return userRepository.save(userToUpdate);
    }
}
