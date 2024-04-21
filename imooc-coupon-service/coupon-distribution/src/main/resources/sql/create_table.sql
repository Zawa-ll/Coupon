-- Create coupon table
CREATE TABLE IF NOT EXISTS `coupon_data`.`coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'self-addressing primary key',
  `template_id` int(11) NOT NULL DEFAULT '0' COMMENT 'Primary key of the associated coupon template',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'user who receives',
  `coupon_code` varchar(64) NOT NULL DEFAULT '' COMMENT 'coupon code',
  `assign_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT 'Collection time',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT 'Collection time',
  PRIMARY KEY (`id`),
  KEY `idx_template_id` (`template_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Coupons (records of what users have received)';

-- Empty table data
-- truncate coupon;
