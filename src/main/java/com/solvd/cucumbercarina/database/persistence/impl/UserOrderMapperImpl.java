package com.solvd.cucumbercarina.database.persistence.impl;

import com.solvd.cucumbercarina.database.domain.UserOrder;
import com.solvd.cucumbercarina.utils.ConnectionFactory;
import com.solvd.cucumbercarina.database.persistence.UserOrderMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserOrderMapperImpl implements UserOrderMapper {

    @Override
    public void create(UserOrder userOrder, Long id) {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            UserOrderMapper userOrderMapper = session.getMapper(UserOrderMapper.class);
            userOrderMapper.create(userOrder, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            UserOrderMapper userOrderMapper = session.getMapper(UserOrderMapper.class);
            userOrderMapper.delete(id);
        }
    }

    @Override
    public List<UserOrder> findById(Long id) {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            UserOrderMapper userOrderMapper = session.getMapper(UserOrderMapper.class);
            return userOrderMapper.findById(id);
        }
    }

    @Override
    public List<UserOrder> findByUserId(Long userId) {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            UserOrderMapper userOrderMapper = session.getMapper(UserOrderMapper.class);
            return userOrderMapper.findByUserId(userId);
        }
    }
}
