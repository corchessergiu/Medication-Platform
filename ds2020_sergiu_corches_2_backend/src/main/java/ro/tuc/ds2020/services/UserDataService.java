package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.UserData;
import ro.tuc.ds2020.repositories.UserDataRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
@Service
public class UserDataService  implements org.springframework.security.core.userdetails.UserDetailsService{

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData user = userDataRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),emptyList());
    }
    public Optional<UserData> findUserById(UUID id) {
        return userDataRepository.findById(id);
    }

    public UserData findUserByUsername(String username) {
        return userDataRepository.findByUsername(username);
    }


    public List<UserData> getUsers(){
        return this.userDataRepository.findAll();
    }

}
