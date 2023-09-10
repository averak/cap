CREATE TABLE IF NOT EXISTS `maintenance`
(
    `id`         CHAR(26)      NOT NULL,
    `open_at`    DATETIME      NOT NULL,
    `close_at`   DATETIME      NOT NULL,
    `memo`       VARCHAR(1023) NULL,
    `created_at` DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;