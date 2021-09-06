CREATE TABLE `recorder` (
                            `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                            `date_of_birth` DATE NULL DEFAULT NULL,
                            `name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_hungarian_ci',
                            PRIMARY KEY (`id`) USING BTREE
);