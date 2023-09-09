package net.averak.cap.adapter.dao.entity.extend;

import net.averak.cap.adapter.dao.entity.base.CronJobEntity;
import net.averak.cap.adapter.dao.entity.base.ProjectEntity;

import java.util.List;

public class ProjectAndCronJobsEntity {

    public ProjectEntity project;

    public List<CronJobEntity> cronJobs;

}
