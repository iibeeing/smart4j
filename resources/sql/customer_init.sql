SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` ( `id` bigint(20) NOT NULL AUTO_INCREMENT,`name` varchar(255) DEFAULT NULL,`contact` varchar(255) DEFAULT NULL,`telephone` varchar(255) DEFAULT NULL,`email` varchar(255) DEFAULT NULL,`remark` text,PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
INSERT INTO `customer` VALUES ('1', 'customer1', 'Jack', '13512345678', 'Jack@Jack.com', null);
INSERT INTO `customer` VALUES ('2', 'customer2', 'Rose', '13623456789', 'rose@gmail.com', null);
