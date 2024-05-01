package ma.youcode.managment_tournoi_backend.security.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCaseOrUsernameIgnoreCase(username, username)
                    .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));

    }
}
