
CREATE TABLE IF NOT EXISTS `coupon_data`.`coupon_path` (
  `id` int(11) NOT NULL AUTO_INCREMENT 
  `path_pattern` varchar(200) NOT NULL DEFAULT '' 
  `http_method` varchar(20) NOT NULL DEFAULT '' 
  `path_name` varchar(50) NOT NULL DEFAULT '' 
  `service_name` varchar(50) NOT NULL DEFAULT '' 
  `op_mode` varchar(20) NOT NULL DEFAULT '' 
  PRIMARY KEY (`id`),
  KEY `idx_path_pattern` (`path_pattern`),
  KEY `idx_servivce_name` (`service_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 

CREATE TABLE IF NOT EXISTS `coupon_data`.`coupon_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT
  `role_name` varchar(128) NOT NULL DEFAULT '' 
  `role_tag` varchar(128) NOT NULL DEFAULT '' 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 


CREATE TABLE IF NOT EXISTS `coupon_data`.`coupon_role_path_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT 
  `role_id` int(11) NOT NULL DEFAULT '0'
  `path_id` int(11) NOT NULL DEFAULT '0'
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_path_id` (`path_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 

-- 创建 User 与 Role 的映射关系表
CREATE TABLE IF NOT EXISTS `coupon_data`.`coupon_user_role_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT 
  `user_id` bigint(20) NOT NULL DEFAULT '0' 
  `role_id` int(11) NOT NULL DEFAULT '0' 
  PRIMARY KEY (`id`),
  KEY `key_role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 
