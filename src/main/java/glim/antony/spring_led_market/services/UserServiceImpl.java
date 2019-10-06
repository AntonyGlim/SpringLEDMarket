package glim.antony.spring_led_market.services;

import glim.antony.spring_led_market.entities.Role;
import glim.antony.spring_led_market.entities.User;
import glim.antony.spring_led_market.repositories.RoleRepository;
import glim.antony.spring_led_market.repositories.UserRepository;
import glim.antony.spring_led_market.utils.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    /**Связь нашего пользователя с пользователем Spring*/
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public User save(SystemUser systemUser) {
        User user = new User();
        if (findByUsername(systemUser.getUsername()) != null) {
            throw new RuntimeException("User with username " + systemUser.getUsername() + " is already exist");
        }
        user.setUsername(systemUser.getUsername());
        user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        user.setFirstName(systemUser.getFirstName());
        user.setLastName(systemUser.getLastName());
        user.setEmail(systemUser.getEmail());
        user.setPhone(systemUser.getPhone());
        user.setRoles(Arrays.asList(roleRepository.findOneByName("ROLE_CUSTOMER")));
        return userRepository.save(user);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public User simpleSave(User user) {
        if (findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User with username " + user.getEmail() + " is already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findOneByName("ROLE_CUSTOMER")));
        return userRepository.save(user);
    }


}
