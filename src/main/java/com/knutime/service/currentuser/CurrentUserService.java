package com.knutime.service.currentuser;

import com.knutime.domain.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
