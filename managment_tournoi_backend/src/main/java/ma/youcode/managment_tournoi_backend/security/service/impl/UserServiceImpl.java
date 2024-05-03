package ma.youcode.managment_tournoi_backend.security.service.impl;

import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import ma.youcode.managment_tournoi_backend.security.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private final AppUserRepository userRepository;

    public UserServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsService userDetailsService() {

      return new UserDetailsService() {
          @Override
          public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

              return userRepository.findByEmailIgnoreCaseOrUsernameIgnoreCase(username, username)
                      .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));

          }
      };
    }
}
