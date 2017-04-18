CREATE TABLE `accounts_and_roles` (
  `ACCOUNT_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ACCOUNT_ID`,`ROLE_ID`),
  KEY `FKE78C10FC6AF55BF6` (`ACCOUNT_ID`),
  KEY `FKE78C10FCA1C5DAAE` (`ROLE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;