package org.example.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-11-29
 * Time: 13:22
 */
public class JSONUtilTest {

    @Test//junit单元测试注解，需要写在public void
    public void testSerialize() {
        Map<String,String> map = new HashMap<>();
        map.put("username","张三");
        map.put("password","123");
        String json = JSONUtil.serialize(map);
        System.out.println(json);  //给自己看的，不是单元测试的标准方式
        Assert.assertNotNull(json);
    }
}
