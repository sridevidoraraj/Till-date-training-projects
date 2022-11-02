CREATE DATABASE IF NOT EXISTS lmi_db;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client`
(
    `id`                 bigint(20) NOT NULL,
    `name`     varchar(45)         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


DROP TABLE IF EXISTS `client_config`;
CREATE TABLE `client_config`
(
    `id`                  bigint(20)  NOT NULL AUTO_INCREMENT,
    `key_params`             varchar(100)          DEFAULT NULL,
    `value`       varchar(100)          DEFAULT NULL,
    `fk_client_id` bigint(20),
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_client` FOREIGN KEY (`fk_client_id`) REFERENCES `lmi_db`.`client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
