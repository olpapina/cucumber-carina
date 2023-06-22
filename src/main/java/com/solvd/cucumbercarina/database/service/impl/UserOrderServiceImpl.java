package com.solvd.cucumbercarina.database.service.impl;

import com.solvd.cucumbercarina.database.domain.UserOrder;
import com.solvd.cucumbercarina.database.persistence.UserOrderMapper;
import com.solvd.cucumbercarina.database.persistence.impl.UserOrderMapperImpl;
import com.solvd.cucumbercarina.database.service.UserOrderService;

import java.util.List;

public class UserOrderServiceImpl implements UserOrderService {

    private final UserOrderMapper userOrderMapper;

    public UserOrderServiceImpl() {
        this.userOrderMapper = new UserOrderMapperImpl();
    }
    @Override
    public UserOrder create(UserOrder userOrder, Long userId) {
        userOrder.setId(null);
        userOrderMapper.create(userOrder, userId);
        return userOrder;
    }

    @Override
    public void deleteById(Long id) {
        userOrderMapper.delete(id);
    }

    @Override
    public List<UserOrder> selectById(Long id) {
        return userOrderMapper.findById(id);
    }

    @Override
    public List<UserOrder> selectByUserId(Long userId) {
        return userOrderMapper.findByUserId(userId);
    }
}
