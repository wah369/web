package test.example.mapper;

import org.junit.Test;
import org.example.Application;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-21
 * Time: 16:38
 */
//指定为Spring环境中的单元测试
@RunWith(SpringRunner.class)
//指定为SpringBoot环境的单元测试，Application为启动类
@SpringBootTest(classes = Application.class)
//使用事务，在SpringBoot的单元测试中会自动回滚
@Transactional
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectById(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void selectByCondition() {
        List<User> users = userMapper.selectByCondition("%",1,3);
        System.out.println(users);
    }


}
