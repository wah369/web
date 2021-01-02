package org.example.util;

import org.example.model.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-18
 * Time: 12:13
 */
@Component
public class UserFactory implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setUsername("卡卡西");
        user.setPassword("789");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
