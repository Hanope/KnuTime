package com.knutime.service.user;

import com.knutime.domain.User;
import com.knutime.domain.UserCreateForm;
import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByNickName(String nickName);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
}
