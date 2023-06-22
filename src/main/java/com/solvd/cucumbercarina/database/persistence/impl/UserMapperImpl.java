package com.solvd.cucumbercarina.database.persistence.impl;

import com.solvd.cucumbercarina.database.domain.User;
import com.solvd.cucumbercarina.utils.ConnectionFactory;
import com.solvd.cucumbercarina.database.persistence.UserMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserMapperImpl implements UserMapper {
    @Override
    public void create(User user) {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.create(user);
        }

    }

    @Override
    public void update(Long id, String name) {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.update(id, name);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            UserMapper carServiceRepository = session.getMapper(UserMapper.class);
            carServiceRepository.delete(id);
        }
    }

    @Override
    public User findById(Long id) {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            return userMapper.findById(id);
        }
    }

    @Override
    public List<User> findByName(String name) {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            return userMapper.findByName(name);
        }
    }
}
