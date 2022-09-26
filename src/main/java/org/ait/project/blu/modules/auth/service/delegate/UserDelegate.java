package org.ait.project.blu.modules.auth.service.delegate;

import org.ait.project.blu.modules.auth.model.entity.Users;

public interface UserDelegate {

    Users save(Users users);

    Users findByUsername(String username);

}
