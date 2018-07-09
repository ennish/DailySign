package com.enn.mapper;

import com.enn.model.BonusFlow;
import com.enn.model.SignUser;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author hacker
 */
public interface BonusFlowMapper extends Mapper<BonusFlow> {
    /**
     *
     *
     * @author tw
     *
     * @param user
     * @return
     */
    @Select("select count(1) from sign_user where user_id = #{userId} or open_id = #{openId}")
    int isUserExists(SignUser user);
}
