CREATE TABLE `world_record`
(
    `id`              BIGINT(20)    NOT NULL AUTO_INCREMENT,
    `date_of_record`  DATE          NULL DEFAULT NULL,
    `description`     VARCHAR(255)  NULL DEFAULT NULL COLLATE 'utf8_hungarian_ci',
    `unit_of_measure` VARCHAR(255)  NULL DEFAULT NULL COLLATE 'utf8_hungarian_ci',
    `value`           DOUBLE(22, 0) NULL DEFAULT NULL,
    `recorder_id`     BIGINT(20)    NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FKn4ssg32wttn2o9vw65cfuub93` (`recorder_id`) USING BTREE,
    CONSTRAINT `FKn4ssg32wttn2o9vw65cfuub93` FOREIGN KEY (`recorder_id`) REFERENCES `guinessapp`.`recorder` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)