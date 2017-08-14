package com.knutime.service.currentuser;

import com.knutime.domain.user.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
