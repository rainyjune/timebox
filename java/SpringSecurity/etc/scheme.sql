
DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(255) default NULL,
  `value` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert  into `resource`(`id`,`type`,`value`) values (1,'URL','/**');

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

insert  into `role`(`id`,`name`,`description`) values (1,'ROLE_USER','ROLE_USER'),(2,'ROLE_ADMIN','ROLE_ADMIN');

DROP TABLE IF EXISTS `role_resource`;

CREATE TABLE `role_resource` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY  (`role_id`,`resource_id`),
  KEY `FKAEE599B751827FA1` (`role_id`),
  KEY `FKAEE599B7EFD18D21` (`resource_id`),
  CONSTRAINT `FKAEE599B751827FA1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKAEE599B7EFD18D21` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `role_resource`(`role_id`,`resource_id`) values (1,1),(2,1);

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `disabled` int(1) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

insert  into `user`(`id`,`name`,`password`,`disabled`) values (1,'downpour','21232f297a57a5a743894a0e4a801fc3',0),(2,'robbin','21232f297a57a5a743894a0e4a801fc3',0);

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY  (`user_id`,`role_id`),
  KEY `FK143BF46AF6AD4381` (`user_id`),
  KEY `FK143BF46A51827FA1` (`role_id`),
  CONSTRAINT `FK143BF46A51827FA1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK143BF46AF6AD4381` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `user_role`(`user_id`,`role_id`) values (1,1),(1,2),(2,1);
