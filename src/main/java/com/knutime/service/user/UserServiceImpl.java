package com.knutime.service.user;

import com.knutime.domain.user.CurrentUser;
import com.knutime.domain.user.Role;
import com.knutime.domain.user.User;
import com.knutime.domain.user.UserCreateForm;
import com.knutime.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Optional<User> getUserByNickName(String nickName) {
        LOGGER.debug("Getting user by nickname={}", nickName);
        return userRepository.findOneByNickName(nickName);
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll();
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setNickName(form.getNickName());
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public void reloadCurrentUser(User user) {
        CurrentUser currentUser = new CurrentUser(user);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(), currentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
