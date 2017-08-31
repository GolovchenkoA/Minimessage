
# subscriber_id must have foreign key to table user_accounts column id
# publisher_id must have foreign key to table user_accounts column id
CREATE TABLE `SUBSCRIBERS` (
  `SUBSCRIBER_ID` bigint(20) NOT NULL,
  `PUBLISHER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`SUBSCRIBER_ID`,`PUBLISHER_ID`),
  KEY `FKB28154EB2C15FDC7` (`PUBLISHER_ID`),
  KEY `FKB28154EBF898ABDB` (`SUBSCRIBER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
