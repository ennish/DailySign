/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : daily_sign

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-09 20:36:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bonus_flow
-- ----------------------------
DROP TABLE IF EXISTS `bonus_flow`;
CREATE TABLE `bonus_flow` (
  `bonus_flow_id` int(12) NOT NULL,
  `user_id` int(12) DEFAULT NULL,
  `bonus_time` datetime DEFAULT NULL,
  `bonus_amount` int(7) DEFAULT NULL COMMENT '金额',
  `bonus_in_out` int(1) DEFAULT '1' COMMENT '进账(1)、出账(2)',
  `bonus_origin` varchar(20) DEFAULT NULL COMMENT 'bonus从哪来或流向哪里',
  PRIMARY KEY (`bonus_flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` int(4) NOT NULL,
  `project_name` varchar(255) NOT NULL,
  `project_from` datetime NOT NULL,
  `project_end` datetime NOT NULL,
  `project_comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sign_log
-- ----------------------------
DROP TABLE IF EXISTS `sign_log`;
CREATE TABLE `sign_log` (
  `sl_id` int(12) NOT NULL AUTO_INCREMENT,
  `sl_user_id` int(12) DEFAULT NULL,
  `sl_project_id` int(4) DEFAULT NULL,
  `sl_sign_time` datetime DEFAULT NULL COMMENT '开始签到任务时间',
  `sl_finish_time` datetime DEFAULT NULL COMMENT '签到完成时间',
  `sl_city` varchar(255) DEFAULT NULL,
  `sl_loc_x` decimal(10,7) DEFAULT NULL,
  `sl_loc_y` decimal(10,7) DEFAULT NULL,
  `sl_status` int(1) DEFAULT '0' COMMENT '0未完成 1可完成 2已完成 ',
  `sl_bonus` int(8) DEFAULT '0' COMMENT '签到奖励',
  PRIMARY KEY (`sl_id`),
  KEY `sign_log_ibfk_1` (`sl_user_id`),
  CONSTRAINT `sign_log_ibfk_1` FOREIGN KEY (`sl_user_id`) REFERENCES `sign_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sign_user
-- ----------------------------
DROP TABLE IF EXISTS `sign_user`;
CREATE TABLE `sign_user` (
  `user_id` int(12) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(28) NOT NULL,
  `nick_name` varchar(20) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  `province` varchar(10) DEFAULT NULL,
  `country` varchar(10) DEFAULT NULL,
  `avatar_url` varchar(100) DEFAULT NULL COMMENT '头像url',
  `union_id` varchar(29) DEFAULT NULL,
  `phone_no` int(20) DEFAULT NULL,
  `account` int(7) DEFAULT '0' COMMENT '红包金额',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_share_log
-- ----------------------------
DROP TABLE IF EXISTS `user_share_log`;
CREATE TABLE `user_share_log` (
  `share_id` int(12) NOT NULL AUTO_INCREMENT,
  `share_user_id` int(12) DEFAULT NULL,
  `share_sl_id` int(12) DEFAULT NULL,
  `share_date` date DEFAULT NULL COMMENT '分享日期',
  `share_obj` varchar(255) DEFAULT NULL COMMENT '分享目标，如有多个用#分割',
  `share_obj_avatar_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`share_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
