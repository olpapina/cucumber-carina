package com.solvd.cucumbercarina.database.persistence;

import com.solvd.cucumbercarina.database.domain.User;

import java.util.List;

public interface UserMapper {

    void create (User user);

    void update (Long id, String name);

    void delete (Long id);

    User findById(Long id);

    List<User> findByName(String name);


}
