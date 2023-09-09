package net.averak.cap.adapter.dao.mapper.extend;

import net.averak.cap.adapter.dao.entity.extend.ProjectAndCronJobsEntity;
import net.averak.cap.adapter.dao.mapper.base.ProjectBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper extends ProjectBaseMapper {

    List<ProjectAndCronJobsEntity> selectAll();

}
