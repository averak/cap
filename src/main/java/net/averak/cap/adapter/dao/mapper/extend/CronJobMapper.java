package net.averak.cap.adapter.dao.mapper.extend;

import net.averak.cap.adapter.dao.entity.base.CronJobEntity;
import net.averak.cap.adapter.dao.mapper.base.CronJobBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CronJobMapper extends CronJobBaseMapper {

    void bulkInsert(final List<CronJobEntity> entities);

}
