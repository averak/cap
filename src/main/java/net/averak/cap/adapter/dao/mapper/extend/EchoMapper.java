package net.averak.cap.adapter.dao.mapper.extend;

import net.averak.cap.adapter.dao.entity.base.EchoEntity;
import net.averak.cap.adapter.dao.mapper.base.EchoBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EchoMapper extends EchoBaseMapper {

    void bulkUpsert(@Param("echos") final List<EchoEntity> echos);

}
