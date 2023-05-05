package com.solvd.cucumbercarina.database.persistence;

import com.solvd.cucumbercarina.database.domain.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOrderMapper {


    void create (@Param("userOrder")UserOrder userOrder, @Param("userId") Long id);

    void delete (Long id);

    List<UserOrder> findById(Long id);

    List<UserOrder> findByUserId(Long id);
}
