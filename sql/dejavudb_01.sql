/*
 Navicat Premium Data Transfer

 Source Server         : asja
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : dejavudb

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 16/04/2021 16:20:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bargain
-- ----------------------------
CREATE DATABASE `dejavudb`;
DROP TABLE IF EXISTS `bargain`;
CREATE TABLE `bargain`  (
  `uid` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `start_time` datetime(0) NOT NULL,
  `end_time` datetime(0) NOT NULL,
  PRIMARY KEY (`uid`, `car_id`) USING BTREE,
  INDEX `carid`(`car_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `car_id` int(11) NOT NULL,
  `model_name` char(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `guide_price` decimal(12, 2) NOT NULL,
  `manufacturer` char(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `service_life` decimal(12, 2) NOT NULL,
  `mileage` decimal(12, 2) NOT NULL,
  `displacement` decimal(12, 2) NOT NULL,
  `region_code` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `price` decimal(12, 2) NOT NULL,
  `state` int(11) NOT NULL,
  `body_type` int(11) NOT NULL,
  `fuel_type` int(11) NOT NULL,
  `gear_box` int(11) NOT NULL,
  `power` decimal(12, 2) NOT NULL,
  `not_repaired_damage` int(11) NOT NULL,
  `create_date` datetime(0) NOT NULL,
  PRIMARY KEY (`car_id`) USING BTREE,
  INDEX `user_id`(`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subscription
-- ----------------------------
DROP TABLE IF EXISTS `subscription`;
CREATE TABLE `subscription`  (
  `car_id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`uid`, `car_id`) USING BTREE,
  INDEX `car_id`(`car_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction`  (
  `buyer_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  `deal_price` decimal(10, 2) NOT NULL,
  `deal_time` datetime(0) NOT NULL,
  `sale_id` int(11) NOT NULL,
  PRIMARY KEY (`sale_id`) USING BTREE,
  INDEX `buy_id`(`buyer_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL,
  `phonenumber` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mail` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` char(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
