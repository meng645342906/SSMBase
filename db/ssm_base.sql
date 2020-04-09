/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.234_3306
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : 192.168.0.234:3306
 Source Schema         : ssm_base

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 09/08/2018 15:58:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_data
-- ----------------------------
DROP TABLE IF EXISTS `dict_data`;
CREATE TABLE `dict_data`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应的值',
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `seq` int(11) NOT NULL COMMENT '排序，由小到大',
  `update_time` int(10) NOT NULL,
  `dict_type_id` int(11) NOT NULL COMMENT '字典类型ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dict_data
-- ----------------------------
INSERT INTO `dict_data` VALUES (4, '内容1', '1', '11111', 1, 1533527263, 3);
INSERT INTO `dict_data` VALUES (5, '内容2', '2', '', 2, 1521595748, 3);
INSERT INTO `dict_data` VALUES (6, '内容3', '3', '', 3, 1521595742, 3);

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type` VALUES (1, '兴趣爱好', '用户选择兴趣爱好', 0);
INSERT INTO `dict_type` VALUES (3, '测试内容', '测试内容描述1', 1533527254);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '系统用户ID',
  `from` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '来源 url',
  `ip` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户端IP',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `date` datetime(0) DEFAULT NULL COMMENT '记录时间',
  `err_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '异常信息',
  `err_code` int(10) DEFAULT 0 COMMENT '状态码，0：正常',
  `class_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'controller类名',
  `method_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '方法名',
  `start_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `spend_time` bigint(20) DEFAULT NULL COMMENT '耗时，毫秒',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '提供的参数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sys_EVENT`(`uid`) USING BTREE,
  CONSTRAINT `sys_log_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 329 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_res`;
CREATE TABLE `sys_res`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(111) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `icon_cls` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'am-icon-file',
  `seq` int(11) DEFAULT 1,
  `type` int(1) DEFAULT 2 COMMENT '1 功能 2 权限',
  `modifydate` varchar(255) DEFAULT NULL,
  `enabled` int(1) DEFAULT 1 COMMENT '是否启用 1：启用  0：禁用',
  `level` int(11) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 183 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_res
-- ----------------------------
INSERT INTO `sys_res` VALUES (1, NULL, '系统管理', '系统管理', '', 'fa-cogs', 10, 1, NULL, 1, 0);
INSERT INTO `sys_res` VALUES (2, 1, '资源管理', NULL, '/sys/res', 'fa-list', 1, 1, NULL, 1, 1);
INSERT INTO `sys_res` VALUES (3, 1, '角色管理', NULL, '/sys/role', 'fa-list', 10, 1, NULL, 1, 1);
INSERT INTO `sys_res` VALUES (4, 1, '用户管理', NULL, '/sys/user', 'fa-users', 11, 1, NULL, 1, 1);
INSERT INTO `sys_res` VALUES (9, 4, '用户删除', NULL, '/sys/user/delete', 'fa-list', 1, 2, NULL, 1, 2);
INSERT INTO `sys_res` VALUES (12, 4, '搜索用户', NULL, '/sys/user/serach', 'fa-list', 1, 2, NULL, 1, 2);
INSERT INTO `sys_res` VALUES (18, 2, '资源删除', NULL, '/sys/res/delete', 'fa-list', 11, 2, NULL, 1, 0);
INSERT INTO `sys_res` VALUES (19, 2, '资源保存', NULL, '/sys/res/save', 'fa-list', 11, 2, NULL, 1, 0);
INSERT INTO `sys_res` VALUES (28, 3, '角色删除', NULL, '/sys/role/delete', 'fa-list', 11, 2, NULL, 1, 0);
INSERT INTO `sys_res` VALUES (29, 3, '角色保存', NULL, '/sys/role/save', 'fa-list', 11, 2, NULL, 1, 0);
INSERT INTO `sys_res` VALUES (63, 4, '冻结用户', NULL, '/sys/user/freeze', 'fa-list', 11, 2, NULL, 1, 0);
INSERT INTO `sys_res` VALUES (146, 4, '用户列表', NULL, '/sys/user/list', 'fa-list', 8, 2, NULL, 1, 0);
INSERT INTO `sys_res` VALUES (147, 4, '用户保存', NULL, '/sys/user/save', 'fa-list', 10, 2, NULL, 1, 0);
INSERT INTO `sys_res` VALUES (150, 1, '操作日志', NULL, '/sys/log', 'fa-list', 11, 1, NULL, 1, 0);
INSERT INTO `sys_res` VALUES (152, NULL, '控制台', '1234', '/', 'fa-desktop', 1, 1, '2015-02-10 16:09:40', 1, 0);
INSERT INTO `sys_res` VALUES (181, 1, '数据字典', NULL, '/dict', 'fa-list', 12, 1, NULL, 1, 0);
INSERT INTO `sys_res` VALUES (182, 181, '查看数据字典列表', NULL, '/dict/list', 'fa-list', 1, 2, NULL, 1, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `des` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `seq` int(11) DEFAULT 1,
  `create_date` datetime(0) DEFAULT NULL,
  `status` int(11) DEFAULT 1 COMMENT '0-禁用  1-启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '超级管理员', 1, '2015-05-05 14:24:26', 1);
INSERT INTO `sys_role` VALUES (2, '日志管理员', '只有查看日志的权限', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `res_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sys_ROLE_RES_RES_ID`(`res_id`) USING BTREE,
  INDEX `FK_sys_ROLE_RES_ROLE_ID`(`role_id`) USING BTREE,
  CONSTRAINT `sys_role_res_ibfk_1` FOREIGN KEY (`res_id`) REFERENCES `sys_res` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_res_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4177 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
INSERT INTO `sys_role_res` VALUES (4141, 1, 1);
INSERT INTO `sys_role_res` VALUES (4142, 2, 1);
INSERT INTO `sys_role_res` VALUES (4143, 18, 1);
INSERT INTO `sys_role_res` VALUES (4144, 19, 1);
INSERT INTO `sys_role_res` VALUES (4145, 3, 1);
INSERT INTO `sys_role_res` VALUES (4146, 28, 1);
INSERT INTO `sys_role_res` VALUES (4147, 29, 1);
INSERT INTO `sys_role_res` VALUES (4148, 4, 1);
INSERT INTO `sys_role_res` VALUES (4149, 9, 1);
INSERT INTO `sys_role_res` VALUES (4150, 12, 1);
INSERT INTO `sys_role_res` VALUES (4151, 63, 1);
INSERT INTO `sys_role_res` VALUES (4152, 146, 1);
INSERT INTO `sys_role_res` VALUES (4153, 147, 1);
INSERT INTO `sys_role_res` VALUES (4154, 150, 1);
INSERT INTO `sys_role_res` VALUES (4155, 181, 1);
INSERT INTO `sys_role_res` VALUES (4156, 182, 1);
INSERT INTO `sys_role_res` VALUES (4157, 152, 1);
INSERT INTO `sys_role_res` VALUES (4175, 1, 2);
INSERT INTO `sys_role_res` VALUES (4176, 150, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `des` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(1) DEFAULT 1 COMMENT '#1 不在线 2.封号状态 ',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '/images/guest.jpg',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime(0) DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'cookieid',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_index`(`name`) USING BTREE,
  UNIQUE INDEX `token_index`(`token`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'fe58cc7ab4ba9f207be0bf82efc8147e136f6b52', '系统管理员', '系统管理员', 1, '/sys/static/i/9.jpg', 'admin@admin.com', '2015-07-15 18:00:27', '123456789', 'pbxugx9UXhczR5sUdBT74Sf5ogd1rIvcbbDyUDuINvGvxYlEkXRtvttgsrmilvNb');
INSERT INTO `sys_user` VALUES (2, '123', '6fc3b9ada8c1e50b400cd6998ff7be76ea3ae312', '123', '123', NULL, NULL, '123', NULL, '123', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_SYSTME_USER_ROLE_USER_ID`(`user_id`) USING BTREE,
  INDEX `FK_SYSTME_USER_ROLE_ROLE_ID`(`role_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 340 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (338, 1, 1);
INSERT INTO `sys_user_role` VALUES (339, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
