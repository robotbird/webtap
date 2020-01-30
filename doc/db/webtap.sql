/*
 Navicat Premium Data Transfer

 Source Server         : 本机mysql
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : webtap

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 30/01/2020 22:03:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wt_app_category
-- ----------------------------
DROP TABLE IF EXISTS `wt_app_category`;
CREATE TABLE `wt_app_category`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_id` bigint(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wt_apps
-- ----------------------------
DROP TABLE IF EXISTS `wt_apps`;
CREATE TABLE `wt_apps`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(255) NULL DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  `logo_url` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` bigint(20) NOT NULL,
  `last_modify_time` bigint(20) NOT NULL,
  `is_delete` int(255) NOT NULL,
  `sec_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `sort_num` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wt_apps
-- ----------------------------
INSERT INTO `wt_apps` VALUES (1, 1, 1, 1, '/upload/logo/italent.png', 'http://www.italent.cn', 'Italent', 'Italent', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (2, 1, 1, 1, '/upload/logo/baoleiji.png', 'http://www.italent.cn', '堡垒机', '堡垒机', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (3, 1, 1, 1, '/upload/logo/chandao.png', 'http://www.italent.cn', '禅道', '禅道', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (4, 1, 1, 1, '/upload/logo/daily.png', 'http://www.italent.cn', '日报', '日报', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (5, 1, 1, 1, '/upload/logo/gitlab.png', 'http://www.italent.cn', 'GitLab', 'GitLab', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (6, 1, 1, 1, '/upload/logo/iconfont.png', 'http://www.italent.cn', '图标', '图标', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (7, 1, 1, 1, '/upload/logo/italent.png', 'http://www.italent.cn', 'Italent', 'Italent', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (8, 1, 1, 1, '/upload/logo/jenkins.png', 'http://www.italent.cn', 'Jenkins', 'Jenkins', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (9, 1, 1, 1, '/upload/logo/kod.png', 'http://www.italent.cn', '文档管理', '文档管理', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (10, 1, 1, 1, '/upload/logo/Nexus.png', 'http://www.italent.cn', 'Nexus', 'Nexus', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (11, 1, 1, 1, '/upload/logo/on.png', 'http://www.italent.cn', '流程设计', '流程设计', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (12, 1, 1, 1, '/upload/logo/openticket.png', 'http://www.italent.cn', '工单', '工单', 1577200841053, 1577200841053, 1, '1', 1);
INSERT INTO `wt_apps` VALUES (13, 1, 1, 1, '/upload/logo/ops.png', 'http://www.italent.cn', '运维工作', '运维工作', 1577200841053, 1577200841053, 1, '1', 1);

-- ----------------------------
-- Table structure for wt_organizations
-- ----------------------------
DROP TABLE IF EXISTS `wt_organizations`;
CREATE TABLE `wt_organizations`  (
  `id` bigint(20) NOT NULL,
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `short_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wt_organizations
-- ----------------------------
INSERT INTO `wt_organizations` VALUES (1, '浙江远传', 'https://www.utry.cn/assets/addons/cms/images/icons/hd_logo_1.png', 'utry');

-- ----------------------------
-- Table structure for wt_users
-- ----------------------------
DROP TABLE IF EXISTS `wt_users`;
CREATE TABLE `wt_users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `background_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `last_modify_time` bigint(20) NOT NULL,
  `out_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pass_word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `profile_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `validata_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe`(`email`) USING BTREE,
  UNIQUE INDEX `UK_lqjrcobrh9jc8wpcar64q1bfh`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wt_users
-- ----------------------------
INSERT INTO `wt_users` VALUES (1, NULL, 1577200840955, 'robotbird@qq.com', NULL, 1577200840955, '2020-01-29 19:51:15.993', 'e10adc3949ba59abbe56e057f20f883e', 'img/favicon.png', 'robotbird', '440417f0-c621-4e55-b5e3-c56610fe655b');

SET FOREIGN_KEY_CHECKS = 1;
