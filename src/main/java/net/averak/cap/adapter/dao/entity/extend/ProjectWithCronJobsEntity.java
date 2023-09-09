package net.averak.cap.adapter.dao.entity.extend;

import net.averak.cap.adapter.dao.entity.base.CronJobEntity;
import net.averak.cap.adapter.dao.entity.base.ProjectEntity;

import java.util.List;

public class ProjectWithCronJobsEntity extends ProjectEntity {

    public List<CronJobEntity> cronJobs;

    public ProjectWithCronJobsEntity(final String id, final String name, final String dockerImageUrl, final String dockerImageTag,
        final Integer containerPort, final Integer hostPort, final String containerEnvironmentVariables) {
        super(id, name, dockerImageUrl, dockerImageTag, containerPort, hostPort, containerEnvironmentVariables);
    }

}
