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

 Date: 23/03/2020 11:43:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wt_app_category
-- ----------------------------
DROP TABLE IF EXISTS `wt_app_category`;
CREATE TABLE `wt_app_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `app_amount` bigint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wt_app_category
-- ----------------------------
INSERT INTO `wt_app_category` VALUES (1, '产品', 1, 4);
INSERT INTO `wt_app_category` VALUES (2, '开发', 1, 0);
INSERT INTO `wt_app_category` VALUES (3, '售前', 1, 3);
INSERT INTO `wt_app_category` VALUES (4, '运营', 1, 3);

-- ----------------------------
-- Table structure for wt_apps
-- ----------------------------
DROP TABLE IF EXISTS `wt_apps`;
CREATE TABLE `wt_apps`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(255) NULL DEFAULT NULL COMMENT '分类Id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `org_id` bigint(20) NOT NULL COMMENT '组织id',
  `logo_url` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'app logo',
  `url` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'app url',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'app 名称',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `last_modify_time` bigint(20) NOT NULL COMMENT '修改时间',
  `is_delete` int(255) NOT NULL COMMENT '删除状态',
  `sec_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '敏感信息',
  `sort_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `view_permission` int(255) NULL DEFAULT NULL COMMENT '查看权限0全部,1登录,2自己,3指定角色',
  `view_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '查看密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wt_apps
-- ----------------------------
INSERT INTO `wt_apps` VALUES (1, 4, 1, 5, '/upload/logo/kod.png', '来来来', '000', 'jjo', 0, 0, 0, '用户名：admin\n密码：123456', 8, NULL, NULL);
INSERT INTO `wt_apps` VALUES (3, 1, 1, 1, '/upload/logo/chandao.png', 'http://www.italent.cn', '禅道', '禅道', 1577200841053, 1577200841053, 1, '用户名：admin\n密码：123456', 1, NULL, NULL);
INSERT INTO `wt_apps` VALUES (4, 0, 1, 1, '/upload/logo/daily.png', 'http://www.italent.cn', '日报', '日报', 1577200841053, 1577200841053, 1, '', 1, NULL, NULL);
INSERT INTO `wt_apps` VALUES (6, 0, 1, 1, '/upload/logo/iconfont.png', 'http://www.italent.cn', '图标', '图标', 1577200841053, 1577200841053, 1, '', 1, NULL, NULL);
INSERT INTO `wt_apps` VALUES (14, 3, 1, 5, '/upload/logo/gitlab.png', 'lll', 'gitlab', 'll', 0, 0, 0, '', 0, NULL, NULL);
INSERT INTO `wt_apps` VALUES (15, 1, 1, 5, '/upload/logo/chandao.png', 'kk', 'lllll', 'kkk', 0, 0, 0, '', 9, NULL, NULL);
INSERT INTO `wt_apps` VALUES (16, 4, 1, 5, '/upload/logo/chandao.png', 'kk', 'lllll', 'kkk', 0, 0, 0, '', 9, NULL, NULL);
INSERT INTO `wt_apps` VALUES (17, 0, 1, 1, '/upload/logo/daily.png', 'http://www.italent.cn', '日程', '日报', 1577200841053, 1577200841053, 1, '密码：123', 1, NULL, NULL);
INSERT INTO `wt_apps` VALUES (18, 3, 1, 5, '/', 'hhh', 'hhh', 'hhh', 0, 0, 0, '', 4, NULL, NULL);
INSERT INTO `wt_apps` VALUES (19, 3, 1, 5, '/', 'dsdsd', 'eee', 'sdsds', 0, 0, 0, '', 121, NULL, NULL);
INSERT INTO `wt_apps` VALUES (20, 0, 1, 1, NULL, 'http://www.utry.cn', '远传', NULL, 1584883843347, 1584883843347, 0, NULL, 100000, NULL, NULL);
INSERT INTO `wt_apps` VALUES (21, 0, 1, 1, NULL, 'http://www.baidu.com', '百度', NULL, 1584883872073, 1584883872073, 0, NULL, 100000, NULL, NULL);

-- ----------------------------
-- Table structure for wt_organizations
-- ----------------------------
DROP TABLE IF EXISTS `wt_organizations`;
CREATE TABLE `wt_organizations`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `org_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织logo',
  `short_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wt_organizations
-- ----------------------------
INSERT INTO `wt_organizations` VALUES (1, 'U客', 'upload/logo/3.png', 'utrycloud');

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
  `org_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe`(`email`) USING BTREE,
  UNIQUE INDEX `UK_lqjrcobrh9jc8wpcar64q1bfh`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wt_users
-- ----------------------------
INSERT INTO `wt_users` VALUES (1, NULL, 1577200840955, 'robotbird@qq.com', NULL, 1577200840955, '2020-01-29 19:51:15.993', '6db3aabc3ca5f673ac4e2b8221c539d3', 'img/favicon.png', 'robotbird', '440417f0-c621-4e55-b5e3-c56610fe655b', 1);
INSERT INTO `wt_users` VALUES (2, NULL, 1577540632740, '330296409@qq.com', NULL, 1577540632740, '2019-12-28 22:43:21.059', '6db3aabc3ca5f673ac4e2b8221c539d3', 'img/favicon.png', 'yepeng', 'fc1dc5c9-33d7-4f17-9598-cc0490cfcb08', 1);
INSERT INTO `wt_users` VALUES (3, NULL, 1584592735270, 'test@qq.com', NULL, 1584592735270, NULL, '6db3aabc3ca5f673ac4e2b8221c539d3', 'img/favicon.png', 'test', NULL, 1);
INSERT INTO `wt_users` VALUES (4, NULL, 1584592879118, 'test2', NULL, 1584592879118, NULL, '6db3aabc3ca5f673ac4e2b8221c539d3', 'img/favicon.png', 'test2', NULL, 1);
INSERT INTO `wt_users` VALUES (5, NULL, 1584593011554, 'dfsf@qq.com', NULL, 1584593011554, NULL, '6db3aabc3ca5f673ac4e2b8221c539d3', 'img/favicon.png', 'test3', NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
