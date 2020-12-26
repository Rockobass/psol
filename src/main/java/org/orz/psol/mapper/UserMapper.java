package org.orz.psol.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.orz.psol.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.orz.psol.model.dbModel.Puser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-12-11
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select count(username) from user where username='${username}'")
    int countUsername(String username);

    @Select("select count(id_number) from puser where id_number='${id}'")
    int countId(String id);

    @Select("select count(telephone) from puser where telephone='${number}'")
    int countPhone(String number);

    @Insert("insert into puser (id, nickname, name, id_number, telephone) value ('${id}', '${nickname}', '${name}', '${id_number}', '${telephone}')")
    int addPuser(String id, String nickname, String name, String id_number, String telephone);

    @Select("select id, nickname, name, id_number, telephone from puser where id='${id}'")
    Puser getPuserById(String id);
}
