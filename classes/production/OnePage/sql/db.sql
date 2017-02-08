
DROP TABLE IF EXISTS onepage.location;

CREATE TABLE onepage.location (
  `locationId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `latitude` double NOT NULL COMMENT 'latitude',
  `longitude` double NOT NULL COMMENT 'longitude',
  `name` varchar(500) NOT NULL COMMENT 'location name',
  `address` varchar(1000) NOT NULL COMMENT 'address',
  `createdAt` datetime NOT NULL COMMENT 'created datetime',
  `createdBy` varchar(50) NOT NULL COMMENT 'creator',
  `deleted` tinyint(1) NOT NULL COMMENT '1:deleted',
  PRIMARY KEY (`locationId`),
  KEY `onelocation_idx01` (`latitude`, `longitude`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='location';

DROP TABLE IF EXISTS onepage.user;

CREATE TABLE onepage.user (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `email` varchar(200) NOT NULL COMMENT 'email',
  `createdAt` datetime NOT NULL COMMENT 'created datetime',
  `createdBy` varchar(50) NOT NULL COMMENT 'creator',
  `deleted` tinyint(1) NOT NULL COMMENT '1:deleted',
  PRIMARY KEY (`userId`),
  KEY `onepage_user_idx01` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user';

DROP TABLE IF EXISTS onepage.page;

CREATE TABLE onepage.page (
  `pageId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `locationId` bigint(20) NOT NULL COMMENT 'locationId',
  `userId` bigint(20) NOT NULL COMMENT 'userId',
  `content` text NOT NULL COMMENT 'content',
  `createdAt` datetime NOT NULL COMMENT 'created datetime',
  `createdBy` varchar(50) NOT NULL COMMENT 'creator',
  `modifiedAt` datetime NOT NULL COMMENT 'modified datetime',
  `modifiedBy` varchar(50) NOT NULL COMMENT 'modifier',
  `deleted` tinyint(1) NOT NULL COMMENT '1:deleted',
  PRIMARY KEY (`pageId`),
  KEY `onepage_page_idx01` (`locationId`),
  KEY `onepage_page_idx02` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='page';

DROP TABLE IF EXISTS onepage.page_image;

CREATE TABLE onepage.page_image (
  `pageImageId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `pageId` bigint(20) NOT NULL COMMENT 'pageId',
  `locationId` bigint(20) NOT NULL COMMENT 'locationId',
  `objectkey` varchar(1024) NOT NULL COMMENT 'objectkey',
  `url` varchar(2000) NOT NULL COMMENT 'objectkey',
  `name` varchar(1000) NOT NULL COMMENT 'name',
  `createdAt` datetime NOT NULL COMMENT 'created datetime',
  `createdBy` varchar(50) NOT NULL COMMENT 'creator',
  `modifiedAt` datetime NOT NULL COMMENT 'modified datetime',
  `modifiedBy` varchar(50) NOT NULL COMMENT 'modifier',
  `deleted` tinyint(1) NOT NULL COMMENT '1:deleted',
  PRIMARY KEY (`pageImageId`),
  KEY `onepage_page_image_idx01` (`pageId`),
  KEY `onepage_page_image_idx02` (`locationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='page_image';

DROP TABLE IF EXISTS onepage.heart;

CREATE TABLE onepage.heart (
  `heartId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `pageId` bigint(20) NOT NULL COMMENT 'pageId',
  `userId` bigint(20) NOT NULL COMMENT 'userId',
  `createdAt` datetime NOT NULL COMMENT 'created datetime',
  `createdBy` varchar(50) NOT NULL COMMENT 'creator',
  `deleted` tinyint(1) NOT NULL COMMENT '1:deleted',
  PRIMARY KEY (`heartId`),
  KEY `onepage_heart_idx01` (`pageId`),
  KEY `onepage_heart_idx02` (`pageId`, `userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='heart';

DROP TABLE IF EXISTS onepage.location_image;

CREATE TABLE onepage.location_image (
  `locationImageId` bigInt(20) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `locationId` bigint(20) NOT NULL COMMENT 'locationId',
  `objectkey` varchar(1024) NOT NULL COMMENT 'objecyKey',
  `url` varchar(2000) NOT NULL COMMENT 'url',
  `name` varchar(1000) NOT NULL COMMENT 'name',
  `englishName` varchar(1000) NOT NULL COMMENT 'englishName',
  `dayType` varchar(50) NOT NULL COMMENT 'dayType',
  `createdAt` datetime NOT NULL COMMENT 'created datetime',
  `createdBy` varchar(50) NOT NULL COMMENT 'createor',
	`modifiedAt` datetime NOT NULL COMMENT 'modified datetime',
	`modifiedBy` varchar(50) NOT NULL COMMENT 'modifier',
	`deleted` tinyint(1) NOT NULL COMMENT '1:deleted',
    PRIMARY KEY(`locationImageId`),
    KEY `onepage_locationImage_idx01` (`locationId`),
    KEY `onepage_locationImage_idx02` (`locationId`,`weatherType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='locationImage';
