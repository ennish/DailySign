/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50722
Source Host           : 193.112.0.99:3306
Source Database       : daily_sign

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-24 19:33:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bonus_flow
-- ----------------------------
DROP TABLE IF EXISTS `bonus_flow`;
CREATE TABLE `bonus_flow` (
  `bonus_flow_id` int(12) NOT NULL AUTO_INCREMENT,
  `user_id` int(12) DEFAULT NULL,
  `bonus_time` datetime DEFAULT NULL,
  `bonus_amount` int(7) DEFAULT NULL COMMENT '金额',
  `bonus_in_out` int(1) DEFAULT '1' COMMENT '进账(1)、出账(2)',
  `bonus_origin` varchar(20) DEFAULT NULL COMMENT 'bonus从哪来或流向哪里',
  PRIMARY KEY (`bonus_flow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bonus_flow
-- ----------------------------
INSERT INTO `bonus_flow` VALUES ('25', '73', '2018-07-23 10:36:26', '1', '1', '默认分享活动');
INSERT INTO `bonus_flow` VALUES ('26', '73', '2018-07-23 10:36:26', '1', '1', '默认分享活动');
INSERT INTO `bonus_flow` VALUES ('28', '73', '2018-07-23 13:42:42', '1', '1', '默认分享活动');
INSERT INTO `bonus_flow` VALUES ('29', '73', '2018-07-23 13:42:42', '1', '1', '默认分享活动');
INSERT INTO `bonus_flow` VALUES ('30', '73', '2018-07-23 13:42:42', '1', '1', '默认分享活动');
INSERT INTO `bonus_flow` VALUES ('31', '73', '2018-07-23 14:25:55', '1', '1', '默认分享活动');
INSERT INTO `bonus_flow` VALUES ('32', '73', '2018-07-23 14:25:55', '1', '1', '默认分享活动');
INSERT INTO `bonus_flow` VALUES ('33', '73', '2018-07-23 14:25:55', '1', '1', '默认分享活动');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` int(4) NOT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `project_from` datetime DEFAULT NULL,
  `project_to` datetime DEFAULT NULL,
  `project_comment` varchar(255) DEFAULT NULL,
  `share_times_limit` int(4) DEFAULT NULL,
  `bonus_project` int(7) DEFAULT NULL,
  `project_active` int(1) DEFAULT '0',
  `project_task_cycle` int(1) DEFAULT '1' COMMENT '1周 2月 3年',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '默认分享活动', '2018-07-10 15:43:36', '2018-10-11 15:43:39', '默认分享活动', '3', '1', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_log
-- ----------------------------
INSERT INTO `sign_log` VALUES ('12', '74', null, '2018-07-23 11:53:57', null, null, null, null, '0', '0');
INSERT INTO `sign_log` VALUES ('13', '73', null, '2018-07-23 14:25:45', '2018-07-23 14:25:55', null, null, null, '2', '0');

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
  `ex_account` varchar(20) DEFAULT NULL COMMENT '外部绑定账号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_user
-- ----------------------------
INSERT INTO `sign_user` VALUES ('72', 'ojVVo5PNaLBAE7glV9hWaEcltdHU', null, null, null, null, null, null, null, null, null, '10', null);
INSERT INTO `sign_user` VALUES ('73', 'oCAx75RKj8YWmchAp3MT8yFuMqZ0', null, null, null, null, null, null, null, null, null, '5', null);
INSERT INTO `sign_user` VALUES ('74', 'oCAx75a39kH3_olMjp0lbKithnOg', null, null, null, null, null, null, null, null, null, '2', null);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` int(10) NOT NULL,
  `task_project_id` int(4) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `task_sign_num` int(12) DEFAULT NULL COMMENT '月度累计签到次数',
  `task_bonus` int(7) DEFAULT NULL,
  `task_cycle` int(4) DEFAULT '1' COMMENT '任务周期 1周 2月 3年',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '1', '3天任务奖励', '3', '2', '2');
INSERT INTO `task` VALUES ('2', '1', '5天任务奖励', '7', '3', '2');
INSERT INTO `task` VALUES ('3', '1', '7天任务奖励', '10', '4', '2');

-- ----------------------------
-- Table structure for task_log
-- ----------------------------
DROP TABLE IF EXISTS `task_log`;
CREATE TABLE `task_log` (
  `task_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_user_id` int(4) DEFAULT NULL,
  `task_id` int(10) DEFAULT NULL,
  `task_bonus` int(12) DEFAULT NULL,
  `task_time` datetime DEFAULT NULL,
  PRIMARY KEY (`task_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_log
-- ----------------------------
INSERT INTO `task_log` VALUES ('14', '72', '1', '2', '2018-07-20 16:45:37');

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
  `share_obj_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`share_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_share_log
-- ----------------------------
INSERT INTO `user_share_log` VALUES ('34', '74', null, '2018-07-23', 'tGCAx75SjVaXBWWxAkUgRkGKfaTKw', null, null);
INSERT INTO `user_share_log` VALUES ('36', '73', null, '2018-07-23', 'tGCAx75SjVaXBWWxAkUgRkGKfaTKw', null, null);
INSERT INTO `user_share_log` VALUES ('37', '73', null, '2018-07-23', 'tGCAx75UVgrZewJoELTLbbVFNDHKA', null, null);
INSERT INTO `user_share_log` VALUES ('38', '73', null, '2018-07-23', 'tGCAx75cfrp3BnTByLW3haufpvuzc', null, null);

-- ----------------------------
-- Table structure for verify_code
-- ----------------------------
DROP TABLE IF EXISTS `verify_code`;
CREATE TABLE `verify_code` (
  `user_id` int(12) NOT NULL,
  `phone` varchar(20) NOT NULL COMMENT '验证码发送的手机号',
  `code` varchar(10) DEFAULT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
  `verify_type` int(1) DEFAULT NULL COMMENT '验证码类型 1账号绑定',
  `status` int(1) DEFAULT '0' COMMENT '发送状态',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of verify_code
-- ----------------------------

-- ----------------------------
-- Procedure structure for _clearDebug
-- ----------------------------
DROP PROCEDURE IF EXISTS `_clearDebug`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `_clearDebug`()
BEGIN
	TRUNCATE TABLE _debug;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Debug
-- ----------------------------
DROP PROCEDURE IF EXISTS `Debug`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Debug`(IN `msg` text)
BEGIN
	-- 记录日志，其他procedure需要记录日志 CALL _debug(msg)
	-- 清空日志时 调用另一个 PROCEDURE CALL _clearDebug()

	DROP TABLE IF EXISTS _debug;

	CREATE TABLE IF NOT EXISTS _debug(
		`id` INT(20) NOT NULL auto_increment,
		`msg` text DEFAULT NULL,
    `create_at` timestamp default CURRENT_TIMESTAMP,
		PRIMARY KEY(`id`)
	);
	INSERT INTO _debug(`msg`) 
	VALUES(msg);
	
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for p_get_cycle
-- ----------------------------
DROP PROCEDURE IF EXISTS `p_get_cycle`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_get_cycle`()
BEGIN
#根据type返回当前周/月/年的开始时间，结束时间，默认返回当前周
# 一周开始时间为周一，结束时间为周日
# 默认 周
DECLARE startDay date;
DECLARE endDay date;

SELECT IFNULL(project_task_cycle,1)INTO @type FROM project WHERE project_active = 1 LIMIT 1;

CASE @type
WHEN 1 THEN
SELECT DATE_SUB(CURDATE(),INTERVAL WEEKDAY(CURDATE()) DAY),DATE_SUB(CURDATE(),INTERVAL WEEKDAY(CURDATE()) - 6 DAY) INTO startDay,endDay;
WHEN 2 THEN
SELECT CONCAT(DATE_FORMAT(LAST_DAY(NOW()),'%Y-%m-'),'01'),LAST_DAY(NOW()) INTO startDay,endDay;
WHEN 3 THEN
SELECT CONCAT(YEAR(NOW()),'-01-01'),CONCAT(YEAR(NOW()),'-12-31')INTO startDay,endDay;
END CASE;

DROP TEMPORARY TABLE IF EXISTS temp_date;
CREATE TEMPORARY TABLE temp_date(startDay date,endDay date);
INSERT INTO temp_date SELECT startDay,endDay;

SELECT startDay,endDay;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for p_get_cycle1
-- ----------------------------
DROP PROCEDURE IF EXISTS `p_get_cycle1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_get_cycle1`()
BEGIN
#根据type返回当前周/月/年的开始时间，结束时间，默认返回当前周
# 一周开始时间为周一，结束时间为周日
# 默认 周
DECLARE startDay date;
DECLARE endDay date;

SELECT IFNULL(project_task_cycle,1)INTO @type FROM project WHERE project_active = 1 LIMIT 1;

CASE @type
WHEN 1 THEN
SELECT DATE_SUB(CURDATE(),INTERVAL WEEKDAY(CURDATE()) DAY),DATE_SUB(CURDATE(),INTERVAL WEEKDAY(CURDATE()) - 6 DAY) INTO startDay,endDay;
WHEN 2 THEN
SELECT CONCAT(DATE_FORMAT(LAST_DAY(NOW()),'%Y-%m-'),'01'),LAST_DAY(NOW()) INTO startDay,endDay;
WHEN 3 THEN
SELECT CONCAT(YEAR(NOW()),'-01-01'),CONCAT(YEAR(NOW()),'-12-31')INTO startDay,endDay;
END CASE;

DROP TEMPORARY TABLE IF EXISTS temp_date;
CREATE TEMPORARY TABLE temp_date(startDay date,endDay date);
INSERT INTO temp_date SELECT startDay,endDay;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for p_get_task_bonus
-- ----------------------------
DROP PROCEDURE IF EXISTS `p_get_task_bonus`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_get_task_bonus`(IN `taskId` int,IN `userId` int)
BEGIN
	 #用户领取任务奖励
	 DECLARE _code VARCHAR(10)  DEFAULT '1200';
	 DECLARE _message VARCHAR(50) DEFAULT 'OK';


DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
begin
		SET _code = '1500';
		SET _message = 'FAIL';
end;
		CALL p_get_cycle1();

	START TRANSACTION;
	 
		-- 判断用户是否领取过任务奖励
		SELECT count(*) INTO @flag FROM task_log,temp_date temp WHERE task_user_id = userId AND  task_id = taskId and task_time BETWEEN temp.startDay and temp.endDay;

		IF @flag != 0 THEN 
       SET _code = '1500';
			 SET _message = 'bonus has been received or no task has been found';
	  ELSE
			-- 获取该用户周期内签到天数
			SELECT IFNULL(COUNT(*),0) INTO @signDays FROM sign_log sl,temp_date temp WHERE 
				sl.sl_user_id = userId AND sl.sl_finish_time BETWEEN temp.startDay and temp.endDay;
			
			SELECT task_sign_num INTO @num FROM task WHERE task_id = taskId;
			if ISNULL(@num) then 
				set _code = '1500';
				set _message = 'task setting invalid';
			else
				--  判断本周期内签到天数大于或等于任务要求数
				IF @signDays >= @num THEN
						INSERT INTO task_log(task_user_id,task_id,task_bonus,task_time)
						SELECT su.user_id,t.task_id,t.task_bonus, NOW() FROM sign_user su, task t WHERE su.user_id = userId AND t.task_id = taskId; 
						-- 分配奖励
						INSERT INTO bonus_flow(user_id,bonus_time,bonus_amount,bonus_in_out,bonus_origin)
						SELECT su.user_id, NOW(), t.task_bonus, 1, t.task_name FROM sign_user su, task t WHERE su.user_id = userId AND t.task_id = taskId;
				ELSE
						SET _code =  '1500';
						SET _message = 'the number of sign days does not match';
				END IF;

			end if;

		END IF;
		
	COMMIT;
	
	SELECT _code as code,_message as message;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for p_user_sign
-- ----------------------------
DROP PROCEDURE IF EXISTS `p_user_sign`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_user_sign`(IN `user_id` int,IN `project_id` int)
    SQL SECURITY INVOKER
BEGIN

DECLARE _code VARCHAR(10)  DEFAULT '1200';
DECLARE _message VARCHAR(50) DEFAULT 'OK';

DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
  SET _code = '1500';
  SET _message = 'FAIL';

START TRANSACTION;

SELECT IFNULL(sl_status,0) INTO @flag FROM sign_log WHERE sl_user_id = user_id AND TO_DAYS(NOW()) = TO_DAYS(sl_sign_time);

-- 可签到
IF @flag = 1 THEN
  
  SELECT project_name, IFNULL(share_times_limit,0),IFNULL(bonus_project,0)
		INTO @projectName, @timesLimit, @bonusProject
  FROM project WHERE project_from <= NOW() and project_to >= NOW();
  
	-- 是否有活动规则
	if ISNULL(@timesLimit) THEN
		SET _code = '1500';
		SET _message = 'no project found';
	else
		
  INSERT INTO bonus_flow(user_id,bonus_time,bonus_amount,bonus_in_out,bonus_origin)
    SELECT user_id, NOW(), @bonusProject, 1, @projectName FROM sign_user WHERE user_id = user_id;
	
	UPDATE sign_user SET account = IFNULL(account,0) + @bonusProject WHERE user_id = user_id;
   
	end if;
  
	UPDATE sign_log SET sl_status = 2,sl_finish_time=NOW() WHERE sl_user_id = user_id AND TO_DAYS(NOW()) = TO_DAYS(sl_sign_time);
	
ELSE
 
	SET _code = '1500';
  SET _message = 'sign_log status invalid';
  
END IF;

COMMIT;

SELECT _code as CODE , _message as message ;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for v2_get_task_bonus
-- ----------------------------
DROP PROCEDURE IF EXISTS `v2_get_task_bonus`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `v2_get_task_bonus`(IN `taskId` int,IN `userId` int)
BEGIN
	 #用户领取任务奖励
	 
	 DECLARE _code VARCHAR(10)  DEFAULT '1200';
	 DECLARE _message VARCHAR(50) DEFAULT 'OK';
	 
DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		SET _code = '1500';
		SET _message = 'FAIL';
	 
	--  本周期内签到天数大于或等于任务要求数
	START TRANSACTION;
		 SELECT t.task_cycle,t.task_sign_num INTO @cycle,@num FROM task t WHERE task_id = taskId;
		
		SELECT p_get_cycle();
		SELECT startDay,endDay into @startDay, @endDay FROM temp_date;
	
		IF ISNULL(@cycle) THEN 
       SET _code = '1500';
			 SET _message = 'invalid task setting';
	  ELSE
			
			CASE @cycle
				-- cycle is week
				WHEN 1 THEN
						-- 当前周期该任务奖励未领取
						SELECT IFNULL(COUNT(*),0) into @flag FROM task_log WHERE task_id = taskId AND task_time BETWEEN subdate(curdate(),date_format(curdate(),'%w')-1) AND subdate(curdate(),date_format(curdate(),'%w')-7);
						if @flag != 0 then
						-- 查询周期内签到次数
						SELECT IFNULL(COUNT(*),0) INTO @signDays FROM sign_log sl WHERE 
				sl.sl_user_id = userId AND sl.sl_finish_time BETWEEN subdate(curdate(),date_format(curdate(),'%w')-1) AND subdate(curdate(),date_format(curdate(),'%w')-7); 
						end if;
				-- cycle is month
				WHEN 2 THEN
						-- 当前周期该任务奖励未领取
						SELECT IFNULL(COUNT(*),0) into @flag FROM task_log WHERE task_id = taskId AND task_time BETWEEN CONCAT(DATE_FORMAT(CURDATE(),'%Y-%m-'),'01') AND LAST_DAY(CURDATE());
						if @flag != 0 then
						SELECT IFNULL(COUNT(*),0) INTO @signDays FROM sign_log sl WHERE 
				sl.sl_user_id = userId AND sl.sl_finish_time BETWEEN CONCAT(DATE_FORMAT(CURDATE(),'%Y-%m-'),'01') AND LAST_DAY(CURDATE());
						end if;
				-- cycle is year
				WHEN 3 THEN
						SELECT IFNULL(COUNT(*),0) INTO @signDays FROM sign_log sl WHERE 
				sl.sl_user_id = userId AND sl.sl_finish_time BETWEEN  subdate(curdate(),date_format(curdate(),'%w')-1) AND subdate(curdate(),date_format(curdate(),'%w')-7);
						if @flag != 0 then
						-- 当前周期该任务奖励未领取
						SELECT IFNULL(COUNT(*),0) into @flag FROM task_log WHERE task_id = taskId AND task_time BETWEEN DATE_SUB(CURDATE(),INTERVAL dayofyear(now())-1 DAY) AND CONCAT(YEAR(NOW()),'-12-31');
						end if;
			END CASE;
			
			SELECT task_sign_num INTO @num FROM task WHERE task_id = taskId;
			-- task complete,dispatch bonus
			if @signDays > @num THEN
				INSERT INTO task_log(task_user_id,task_id,task_bonus,task_time)
						SELECT su.user_id,t.taskId,t.task_bonus, NOW() FROM sign_user su,task t WHERE su.user_id = @userId and t.task_id = taskId; 
				
			end if;

		END IF;
		
	COMMIT;
	
	SELECT _code as code,_message as message ;

END
;;
DELIMITER ;
