package org.weweb.mapper;

import org.weweb.model.TUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface TUserMapper extends Mapper<TUser> {
     TUser queryById(Map<String,Object> map);

}