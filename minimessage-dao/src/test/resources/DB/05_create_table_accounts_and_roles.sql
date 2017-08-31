CREATE TABLE `ACCOUNTS_AND_ROLES` (
  `ACCOUNT_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ACCOUNT_ID`,`ROLE_ID`),
  KEY `FKE78C10FC6AF55BF6` (`ACCOUNT_ID`),
  KEY `FKE78C10FCA1C5DAAE` (`ROLE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


#By default when usage MySQL hibernate doesn't create foreign keys
#Source: http://stackoverflow.com/questions/8720635/using-mysql-why-doesnt-hibernate-jpa-create-foreign-key-constraints-for-me

#It's because MySQL does not support foreign key constraints on tables created with ENGINE=MyISAM.
#You need to create (both!) tables with ENGINE=InnoDB. You can do so by tweaking my.cnf,
#and adding a default, or by using a special variable in your JDBC URL:

# jdbc:mysql://localhost/dbname?characterEncoding=utf8&sessionVariables=storage_engine=InnoDB


#Variant №2

# As suggested here Hibernate: Create Mysql InnoDB tables instead of MyISAM you can also change Hibernate dialect to use

# org.hibernate.dialect.MySQL5InnoDBDialect