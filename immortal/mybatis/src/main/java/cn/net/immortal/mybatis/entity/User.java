package cn.net.immortal.mybatis.entity;

import lombok.Data;

/**
 * 用户
 */
@Data
public class User {

    /**
     * id
     */
    private Long id;

    /**
     * 账号id
     */
    private Long accountId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮件
     */
    private String email;
}
