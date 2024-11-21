package com.github.michele.cianni.gradesubmission.service;

import com.github.michele.cianni.gradesubmission.entity.User;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    User save(User user);
}
