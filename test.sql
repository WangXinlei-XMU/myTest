/*
 Navicat Premium Data Transfer

 Source Server         : oomall
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 27/07/2021 09:33:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `context` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `state` tinyint(0) NULL DEFAULT NULL,
  `back_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_user`(`user_id`) USING BTREE,
  CONSTRAINT `article_user` FOREIGN KEY (`user_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 1, 'efwa', 'efwa', 'a而哇分为', '2021-07-26 20:56:25', '2021-07-22 13:09:27', 2, 'https://pic2.zhimg.com/v2-0cc642bd1a977891d2c3407ff2f55619_r.jpg');
INSERT INTO `article` VALUES (2, 2, 'efewefw', 'gregrew', 'gre', '2021-07-15 13:35:04', '2021-07-15 14:49:16', 0, NULL);
INSERT INTO `article` VALUES (3, 1, 're', 'gr', 'r', '2021-07-26 21:13:39', '2021-07-23 20:08:56', 1, NULL);
INSERT INTO `article` VALUES (4, 2, 'f', 'fw', 'efw', NULL, '2021-06-29 20:09:16', 1, NULL);
INSERT INTO `article` VALUES (5, 2, 'w', 'f', 'efw', '2021-07-23 20:09:34', NULL, 1, NULL);
INSERT INTO `article` VALUES (6, 1, 'ef', 'ewf', 'ewf', '2021-07-23 20:09:54', NULL, 1, NULL);
INSERT INTO `article` VALUES (7, 1, 'ef', 'fwaee', 'fewaf', '2021-07-26 00:50:55', '2021-07-26 00:50:55', 1, NULL);
INSERT INTO `article` VALUES (8, 1, 'title', 'aowfejioa;fceajwijnco', 'efaieowjfoaneaijacw', '2021-07-26 21:16:15', '2021-07-26 21:16:15', 1, NULL);

-- ----------------------------
-- Table structure for article_label
-- ----------------------------
DROP TABLE IF EXISTS `article_label`;
CREATE TABLE `article_label`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `article_id` int(0) NULL DEFAULT NULL,
  `label_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `label_id`(`label_id`) USING BTREE,
  CONSTRAINT `article_label_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `article_label_ibfk_2` FOREIGN KEY (`label_id`) REFERENCES `label` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_label
-- ----------------------------
INSERT INTO `article_label` VALUES (28, 1, 1);

-- ----------------------------
-- Table structure for browse
-- ----------------------------
DROP TABLE IF EXISTS `browse`;
CREATE TABLE `browse`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `article_id` int(0) NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `be_deleted` tinyint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  CONSTRAINT `browse_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `browse_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of browse
-- ----------------------------
INSERT INTO `browse` VALUES (1, 1, 1, '2021-07-26 09:21:17', 1);
INSERT INTO `browse` VALUES (2, 1, 1, '2021-07-26 09:21:29', 1);
INSERT INTO `browse` VALUES (3, 1, 1, '2021-07-26 09:43:29', 1);
INSERT INTO `browse` VALUES (4, 1, 1, '2021-07-26 09:43:29', 1);
INSERT INTO `browse` VALUES (5, 1, 1, '2021-07-26 09:47:13', 1);
INSERT INTO `browse` VALUES (6, 1, 1, '2021-07-26 09:56:29', 1);
INSERT INTO `browse` VALUES (7, 1, 1, '2021-07-26 09:59:27', 1);
INSERT INTO `browse` VALUES (8, 1, 1, '2021-07-26 10:00:42', 1);
INSERT INTO `browse` VALUES (9, 1, 1, '2021-07-26 10:05:44', 1);
INSERT INTO `browse` VALUES (10, 1, 1, '2021-07-26 10:05:48', 1);
INSERT INTO `browse` VALUES (11, 1, 1, '2021-07-26 10:07:34', 1);
INSERT INTO `browse` VALUES (12, 1, 1, '2021-07-26 10:09:49', 1);
INSERT INTO `browse` VALUES (13, 1, 1, '2021-07-26 10:09:56', 1);
INSERT INTO `browse` VALUES (14, 1, 1, '2021-07-26 13:27:14', 1);
INSERT INTO `browse` VALUES (15, 1, 3, '2021-07-26 13:27:26', 1);
INSERT INTO `browse` VALUES (16, 1, 3, '2021-07-26 21:25:03', 0);
INSERT INTO `browse` VALUES (17, 1, 3, '2021-07-26 21:25:06', 0);
INSERT INTO `browse` VALUES (18, 1, 3, '2021-07-26 21:28:19', 0);
INSERT INTO `browse` VALUES (19, 1, 3, '2021-07-27 00:49:58', 0);
INSERT INTO `browse` VALUES (20, 1, 4, '2021-07-27 00:50:04', 0);
INSERT INTO `browse` VALUES (21, 1, 3, '2021-07-27 00:51:09', 0);
INSERT INTO `browse` VALUES (22, 1, 3, '2021-07-27 00:53:12', 0);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `article_id` int(0) NULL DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, 1, 'a', '2021-07-24 19:43:07');
INSERT INTO `comment` VALUES (2, 1, 1, 'efa', '2021-07-24 19:52:21');
INSERT INTO `comment` VALUES (3, 1, 1, '烦烦烦', '2021-07-24 23:38:45');
INSERT INTO `comment` VALUES (4, 1, 1, '和', '2021-07-24 23:39:10');
INSERT INTO `comment` VALUES (5, 1, 1, '风', '2021-07-24 23:52:17');
INSERT INTO `comment` VALUES (6, 1, 1, '顶顶顶', '2021-07-24 23:52:34');
INSERT INTO `comment` VALUES (7, NULL, 1, 'efe', '2021-07-25 00:42:14');
INSERT INTO `comment` VALUES (8, NULL, 1, '界面', '2021-07-25 00:43:12');
INSERT INTO `comment` VALUES (9, NULL, 1, '界面', '2021-07-25 00:43:15');
INSERT INTO `comment` VALUES (10, NULL, 1, '不会', '2021-07-25 00:44:22');
INSERT INTO `comment` VALUES (11, 1, 1, 'fa', '2021-07-25 00:48:09');
INSERT INTO `comment` VALUES (12, 1, 1, '界面', '2021-07-25 00:48:21');
INSERT INTO `comment` VALUES (13, 1, 1, '哈哈', '2021-07-25 00:51:32');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `back_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` tinyint(0) NULL DEFAULT NULL,
  `state` tinyint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'name1', '123456', 'fewfa', 'efaw@fa.com', '12345678911', NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (2, 'admin', 'admin', NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `customer` VALUES (3, NULL, 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (4, NULL, 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (5, NULL, 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (6, NULL, 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (7, NULL, 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (8, NULL, 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (9, NULL, 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (10, NULL, 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (11, NULL, 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (12, 'n2', 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (13, 'n3', 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (14, NULL, 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (15, 'n4', 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (16, 'name', 'ef', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (17, '12', '12', NULL, '1ef@ser.com', '18860030313', NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (18, 'efa', '11', NULL, 'eaf@feaw.com', '12345678911', NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (19, 'efa', '11', NULL, 'eaf@feaw.com', '12345678911', NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (20, 'jj', 'kk', NULL, 'dehu@ewf.com', '12345678911', NULL, NULL, 0, 0);
INSERT INTO `customer` VALUES (21, 'name2', '123456', NULL, 'uehfiu@faiu.com', '12345678911', NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (1, 'label1');
INSERT INTO `label` VALUES (2, 'label2');
INSERT INTO `label` VALUES (3, 'label3');

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `article_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES (1, 1, 1);
INSERT INTO `likes` VALUES (2, 1, 3);

SET FOREIGN_KEY_CHECKS = 1;
