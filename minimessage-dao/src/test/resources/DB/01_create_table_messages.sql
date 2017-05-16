CREATE TABLE `messages` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `created` datetime DEFAULT NULL,
    `text` varchar(255) DEFAULT NULL,
    `account_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK131AF14C6AF55BF6` (`account_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
