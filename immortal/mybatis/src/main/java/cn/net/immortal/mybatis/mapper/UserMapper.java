package cn.net.immortal.mybatis.mapper;

import cn.net.immortal.mybatis.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> getUserList(Long id);
}
