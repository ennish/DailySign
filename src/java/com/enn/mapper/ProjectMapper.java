package com.enn.mapper;

import com.enn.model.Project;
import com.enn.model.Task;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 活动或规则
 * @author hacker
 */
public interface ProjectMapper extends Mapper<Project> {

    @Select("select * from project where project_active = 1 order by project_from")
    List<Project> selectActive();
}
