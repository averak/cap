CREATE TABLE IF NOT EXISTS `project`
(
    `id`                              CHAR(26)     NOT NULL,
    `name`                            VARCHAR(255) NOT NULL,
    `docker_image_url`                VARCHAR(255) NOT NULL,
    `docker_image_tag`                VARCHAR(255) NOT NULL,
    `container_environment_variables` JSON         NOT NULL,
    `container_port`                  INT UNSIGNED NOT NULL,
    `host_port`                       INT UNSIGNED NOT NULL,
    `is_deleted`                      BOOLEAN      NOT NULL DEFAULT FALSE,
    `created_at`                      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`                      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `_virtual_name`                   VARCHAR(255) GENERATED ALWAYS AS (IF(`is_deleted` = FALSE, `name`, NULL)) VIRTUAL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UQ__project__name` (`_virtual_name` ASC) VISIBLE
)
    ENGINE = InnoDB;