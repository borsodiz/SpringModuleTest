package app.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OfficerService implements UserDetailsService {

    private PasswordEncoder encoder;


    public OfficerService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public void saveUser (String username, String password) {
        String encodedPw = encoder.encode(password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
