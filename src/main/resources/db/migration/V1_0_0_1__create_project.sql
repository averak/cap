CREATE TABLE IF NOT EXISTS `project`
(
    `id`               CHAR(26)     NOT NULL,
    `name`             VARCHAR(255) NOT NULL,
    `docker_image_url` VARCHAR(255) NOT NULL,
    `docker_image_tag` VARCHAR(255) NOT NULL,
    `container_port`   INT UNSIGNED NOT NULL,
    `host_port`        INT UNSIGNED NOT NULL,
    `created_at`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;