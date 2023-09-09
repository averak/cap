package net.averak.cap.adapter.dao.mapper.extend;

import net.averak.cap.adapter.dao.entity.base.ProjectEntity;
import net.averak.cap.adapter.dao.entity.extend.ProjectWithCronJobsEntity;
import net.averak.cap.adapter.dao.mapper.base.ProjectBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProjectMapper extends ProjectBaseMapper {

    List<ProjectWithCronJobsEntity> selectAll();

    Optional<ProjectWithCronJobsEntity> selectById(final String id);

    void upsert(final ProjectEntity entity);

}
