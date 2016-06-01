DROP TABLE IF EXISTS `ast_Distributor_M`;

CREATE TABLE `ast_Distributor_M` ( `distributorcode` VARCHAR(64) NOT NULL, `distributorname` VARCHAR(64) NOT NULL, `regioncode` VARCHAR(64) NOT NULL, `longitude` DOUBLE(10,2) NOT NULL, `lattitude` DOUBLE(10,2) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`distributorcode`));

