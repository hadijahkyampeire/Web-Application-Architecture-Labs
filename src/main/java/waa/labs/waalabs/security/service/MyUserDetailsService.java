package waa.labs.waalabs.security.service;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import waa.labs.waalabs.repo.UserRepo;
import waa.labs.waalabs.security.MyUserDetails;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepo.findUserByEmail(email);
        var userDetails = new MyUserDetails(user);
        return userDetails;
    }
}
