package codes.praise.submission.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with id: " + id));
    }

    public User getUserByUsername(String username) {
        return userRepository.
                findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with username: " + username));
    }
}
