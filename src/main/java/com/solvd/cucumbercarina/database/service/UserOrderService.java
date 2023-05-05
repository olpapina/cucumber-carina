package com.solvd.cucumbercarina.database.service;

import com.solvd.cucumbercarina.database.domain.UserOrder;

import java.util.List;

public interface UserOrderService {
    UserOrder create(UserOrder userOrder, Long id);

    void deleteById(Long id);

    List<UserOrder> selectById(Long id);

    List<UserOrder> selectByUserId(Long id);
}
