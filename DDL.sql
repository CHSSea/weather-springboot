##创建weather表
CREATE TABLE `weather` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `dn` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `temperature` varchar(255) DEFAULT NULL,
  `wind` varchar(255) DEFAULT NULL,
  `windSize` varchar(255) DEFAULT NULL,
  `unknow` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

