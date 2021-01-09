package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Article {

    private Integer id;
    private String title;
    private String content;
    private Integer userId;//文章拥有者-外键
    private Date createTime;//创建日期
}
