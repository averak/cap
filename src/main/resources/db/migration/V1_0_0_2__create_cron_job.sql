CREATE TABLE IF NOT EXISTS `cron_job`
(
    `id`               CHAR(26)      NOT NULL,
    `project_id`       CHAR(26)      NOT NULL,
    `expression`       VARCHAR(255)  NOT NULL,
    `command`          VARCHAR(1023) NOT NULL,
    `docker_image_url` VARCHAR(255)  NOT NULL,
    `docker_image_tag` VARCHAR(255)  NOT NULL,
    `created_at`       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK__cron_job__project_id`
        FOREIGN KEY (`project_id`)
            REFERENCES `project` (`id`)
            ON DELETE CASCADE
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;