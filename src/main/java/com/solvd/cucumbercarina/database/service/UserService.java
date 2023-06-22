package com.solvd.cucumbercarina.database.service;

import com.solvd.cucumbercarina.database.domain.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user, String name);

    void deleteById(Long id);

    List<User> selectByName(String name);

    User selectById(Long id);
}
