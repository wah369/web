package org.example.service;

import org.example.dao.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-18
 * Time: 11:00
 */
@Service
@Scope("prototype")
public class LoginService {
    @Autowired
    private LoginDAO loginDAO;

    /*public void login(String username, String password) {
        if (id == 1)
            User user = loginDAO.query(username);
        else if (id == 2)
            UserDAO.query(...);
    }*/
}
