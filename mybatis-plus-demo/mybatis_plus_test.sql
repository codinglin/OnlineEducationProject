/*
Navicat MySQL Data Transfer

Source Server         : linjiayu
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : mybatis_plus_test

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2021-01-26 13:14:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', 'Tom', '28', 'test3@baomidou.com', null, '0', null, null);
INSERT INTO `user` VALUES ('4', 'Sandy', '21', 'test4@baomidou.com', null, '0', null, null);
INSERT INTO `user` VALUES ('5', 'Billie', '24', 'test5@baomidou.com', null, '0', null, null);
INSERT INTO `user` VALUES ('1', 'mary', '40', 'luck@qq.com', null, '0', '2021-01-26 00:05:56', '2021-01-26 00:07:59');
INSERT INTO `user` VALUES ('2', '东方不败', '200', 'luck@qq.com', '2', '0', '2021-01-26 09:27:05', '2021-01-26 09:52:28');
INSERT INTO `user` VALUES ('1353891429182877698', '岳不群', '30', 'luck@qq.com', '1', '1', '2021-01-26 10:24:05', '2021-01-26 10:24:05');
