package com.tutorial.jangsin.dto;

import lombok.Data;

/*
 * 이파일은 DTO,VO,ENTITY 등으로 불리며, 쿼리 결과를 받는 용도이다. lombok에서 제공되는 @Data 어노테이션을 사용하면
 * Getter와 setter 메소드를 자동 생성해주어서, 코드를 깔끔하게 유지할수 있다.
 */
@Data
public class Admin {
    private Long id;
    private String userid;
    private String password;
    private String nick;
}
