package com.solvd.cucumbercarina.database.service.impl;

import com.solvd.cucumbercarina.database.domain.User;
import com.solvd.cucumbercarina.database.domain.UserOrder;
import com.solvd.cucumbercarina.database.persistence.UserMapper;
import com.solvd.cucumbercarina.database.persistence.impl.UserMapperImpl;
import com.solvd.cucumbercarina.database.service.UserOrderService;
import com.solvd.cucumbercarina.database.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserOrderService userOrderService;

    public UserServiceImpl() {
        this.userMapper = new UserMapperImpl();
        this.userOrderService = new UserOrderServiceImpl();
    }

    @Override
    public User create(User user) {
        user.setId(null);
        userMapper.create(user);
        if (user.getUserOrders() != null) {
            List<UserOrder> userOrders = user.getUserOrders().stream().map(userOrder -> userOrderService.create(userOrder, userOrder.getId())).collect(Collectors.toList());
            user.setUserOrders(userOrders);
        }
        return user;
    }

    @Override
    public User update(User user, String name) {
        userMapper.update(user.getId(), name);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        userMapper.delete(id);
    }

    @Override
    public List<User> selectByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public User selectById(Long id) {
        return userMapper.findById(id);
    }
}
