package com.wah.service;

import com.wah.dao.InitDAO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-25
 * Time: 22:44
 */
//
public class DBService {
   private final InitDAO initDAO = new InitDAO();

   public void init() {
       initDAO.init();
   }
}
