package com.tutorial.jangsin.mapper;

import com.tutorial.jangsin.dto.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * 이 파일은 Mybatis에서 SQL쿼리와 객체를 연결해주는 역할을 한다.
 * SQL 쿼리가 짧으면, XML파일에 정의하지 않고, 이 파일에서 @select 어노테이션을 이용해서 SQL 쿼리를 작성할 수도 있다.
 * 아무래도 파일이 하나가 줄어드니 더 편하게 작업할 수 있을것이다. 이 Mapper라고 하는 부분이 다른 언어에서 사용하는
 * 웹 프레임워크의 model에 해당한다고 생각하면 편할듯하다.
 */
@Mapper
public interface AdminMapper {
    //@select ("Call sp_list_admin()")
    //@Select ("select id,userid,password,nick from tb_admin")
    // 관리자 조회
    List<Admin> listAdmin();

    //@insert("CALL sp_insert_admin(#{userId},#{password},#{nick})")
    // 관리자 추가
    int insertAdmin(@Param("userid") String userid, @Param("password") String password, @Param("nick") String nick);

    //xml파일을 사용하지 않으려면, @Select 어노테이션 주석을 풀터주면 됩니다.
    // 관리자 한명 조회
    Admin getAdmin(@Param("id") Long id);

    /*
     * 관리자 패스워드변경
     */
    int updateAdminPassword(@Param("id") Long id, @Param("password") String password);

    int deleteAdmin(@Param("id") Long id);

    int updateAdmin(@Param("id") Long id, @Param("nick") String nick);
}
