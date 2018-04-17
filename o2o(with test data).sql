/*
 Navicat MySQL Data Transfer

 Source Server         : rootAliyun
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 120.77.255.10:3306
 Source Schema         : o2o

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 17/04/2018 17:32:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area`  (
  `area_id` int(5) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `priority` int(2) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`area_id`) USING BTREE,
  UNIQUE INDEX `UK_AREA`(`area_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
INSERT INTO `tb_area` VALUES (3, '东苑', 12, '2017-06-04 19:12:58', '2017-06-04 19:12:58');
INSERT INTO `tb_area` VALUES (4, '南苑', 10, '2017-06-04 19:13:09', '2017-06-04 19:13:09');
INSERT INTO `tb_area` VALUES (5, '西苑', 9, '2017-06-04 19:13:18', '2017-06-04 19:13:18');
INSERT INTO `tb_area` VALUES (6, '北苑', 7, '2017-06-04 19:13:29', '2017-06-04 19:13:29');

-- ----------------------------
-- Table structure for tb_award
-- ----------------------------
DROP TABLE IF EXISTS `tb_award`;
CREATE TABLE `tb_award`  (
  `award_id` int(10) NOT NULL AUTO_INCREMENT,
  `award_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `award_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `award_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `point` int(10) NOT NULL DEFAULT 0,
  `priority` int(2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0,
  `shop_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`award_id`) USING BTREE,
  INDEX `fk_award_shop_idx`(`shop_id`) USING BTREE,
  CONSTRAINT `fk_award_shop_idx` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_head_line
-- ----------------------------
DROP TABLE IF EXISTS `tb_head_line`;
CREATE TABLE `tb_head_line`  (
  `line_id` int(100) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `line_link` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `line_img` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `priority` int(2) NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`line_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_head_line
-- ----------------------------
INSERT INTO `tb_head_line` VALUES (11, '1', '1', '/upload/images/item/headtitle/2017061320315746624.jpg', 1, 1, '2017-06-13 20:31:57', '2017-06-13 20:31:57');
INSERT INTO `tb_head_line` VALUES (12, '2', '2', '/upload/images/item/headtitle/2017061320371786788.jpg', 2, 1, '2017-06-13 20:37:17', '2017-06-13 20:37:17');
INSERT INTO `tb_head_line` VALUES (14, '3', '3', '/upload/images/item/headtitle/2017061320393452772.jpg', 3, 1, '2017-06-13 20:39:34', '2017-06-13 20:39:34');
INSERT INTO `tb_head_line` VALUES (15, '4', '4', '/upload/images/item/headtitle/2017061320400198256.jpg', 4, 1, '2017-06-13 20:40:01', '2017-06-13 20:40:01');

-- ----------------------------
-- Table structure for tb_local_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_local_auth`;
CREATE TABLE `tb_local_auth`  (
  `local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `user_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`local_auth_id`) USING BTREE,
  UNIQUE INDEX `uk_local_profile`(`user_name`) USING BTREE,
  INDEX `fk_local_profile`(`user_id`) USING BTREE,
  CONSTRAINT `fk_local_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_local_auth
-- ----------------------------
INSERT INTO `tb_local_auth` VALUES (10, 9, 'testbind', '555elq65be9q529lq62l52095s562l25', '2018-04-14 17:03:20', '2018-04-15 21:50:35');

-- ----------------------------
-- Table structure for tb_person_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_person_info`;
CREATE TABLE `tb_person_info`  (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `userType` int(2) NOT NULL DEFAULT 1,
  `profile_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_person_info
-- ----------------------------
INSERT INTO `tb_person_info` VALUES (8, '李翔', '1', NULL, 1, 'http://wx.qlogo.cn/mmopen/XZumId0qMA815ApfWI2zibDnRMahic6SU0wHib2HgGJj5narL2ymRaI4Kn2Tx2Q8UfkicibvjVicu3De6fDYRMfo0uGW0SGicibxVnJ9/0', '2017-06-04 19:01:09', '2017-06-04 19:01:09', 1);
INSERT INTO `tb_person_info` VALUES (9, '龙州一条街客服', '1', NULL, 1, 'http://wx.qlogo.cn/mmopen/icF4iau8Sj7b0FiakC6ibBoTPmkvLpIX9YhWkNyEIGYfzYyqBiag2M3q2rnxSlXAh95UDHdWgywvEW5bN5FBzFPFazxBzqHTRqNwn/0', '2017-06-04 21:20:43', '2017-06-04 21:20:43', 1);
INSERT INTO `tb_person_info` VALUES (10, 'king', '2', NULL, 1, 'http://wx.qlogo.cn/mmopen/XZumId0qMA815ApfWI2zibDLckaAaV6QgcBJP0saJSDTuicZBd35HzPXUebLPSlexCIPJsLs3w6lG0xmwn3EZNicj04dJh4We7C/0', '2017-06-07 01:36:16', '2017-06-07 01:36:16', 1);
INSERT INTO `tb_person_info` VALUES (11, '音策', '2', NULL, 1, 'http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKCWfIBicEwS3U0legxxQd5XFpZibBXVPyz0wphvvtaXqiblzQF2GqE28c7j8FGpuYqBCg1QRJThEzuw/0', '2017-09-18 23:39:38', '2017-09-18 23:39:38', 1);
INSERT INTO `tb_person_info` VALUES (15, 'dinry WJ', '1', NULL, 1, 'http://thirdwx.qlogo.cn/mmopen/vi_32/41ia4gWFicXl8FuA4gVykqnBToPLpiax4RINlpMxIwicrjVN7KqeZqib1jLziarZXFrEJR3xWHRjWiaVuIHiaOYkJX4bVg/132', '2018-04-12 19:46:06', NULL, 1);
INSERT INTO `tb_person_info` VALUES (16, '测试一下', NULL, NULL, 1, NULL, '2018-04-16 17:46:07', NULL, 1);

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`  (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_desc` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img_addr` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `normal_price` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `promotion_price` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0,
  `product_category_id` int(11) NULL DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT 0,
  `point` int(10) NULL DEFAULT 0,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `fk_product_procate_idx`(`product_category_id`) USING BTREE,
  INDEX `fk_product_shop_idx`(`shop_id`) USING BTREE,
  CONSTRAINT `fk_product_procate` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES (9, '正式的商品2', '正式的商品2', '/upload/images/item/shop/4/2018030620003375650.jpg', '2', '2', 202, '2018-02-23 16:25:08', '2018-04-16 14:35:50', 1, 8, 4, 2);
INSERT INTO `tb_product` VALUES (10, '新增商品1', '描述', '/upload/images/item/shop/4/2018022517130166435.jpg', '20', '15', 200, '2018-02-25 17:13:02', '2018-02-25 17:13:02', 1, 6, 4, 0);
INSERT INTO `tb_product` VALUES (11, '新增商品2', '新增商品', '', '2', '1', 2, NULL, NULL, 1, 6, 4, 0);
INSERT INTO `tb_product` VALUES (12, '新增商品3', '新增商品', '', '2', '1', 1, NULL, NULL, 1, 6, 4, 0);
INSERT INTO `tb_product` VALUES (13, '新增商品4', '新增商品', '', '2', '1', 1, NULL, NULL, 1, 6, 4, 0);
INSERT INTO `tb_product` VALUES (14, '新增商品5', '新增商品', '', '2', '1', 1, NULL, '2018-04-15 21:56:09', 1, 6, 4, 0);
INSERT INTO `tb_product` VALUES (15, '牛奶', '好喝！', '\\upload\\images\\item\\shop\\4\\2018041620153740104.jpg', '20', '10', 100, '2018-04-16 20:15:38', '2018-04-16 20:15:38', 1, 5, 4, 2);
INSERT INTO `tb_product` VALUES (16, '冰红茶', 'haohe', '\\upload\\images\\item\\shop\\4\\2018041620474226640.jpg', '12', '8', 100, '2018-04-16 20:47:42', '2018-04-16 20:47:42', 1, 5, 4, 12);

-- ----------------------------
-- Table structure for tb_product_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category`  (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `priority` int(2) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`product_category_id`) USING BTREE,
  INDEX `fk_procate_shop_idx`(`shop_id`) USING BTREE,
  CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_product_category
-- ----------------------------
INSERT INTO `tb_product_category` VALUES (5, '商品类别1', 0, '2018-02-21 13:56:42', 4);
INSERT INTO `tb_product_category` VALUES (6, '商品类别2', 5, '2018-02-21 13:56:42', 4);
INSERT INTO `tb_product_category` VALUES (7, '新增类别1', 200, NULL, 4);
INSERT INTO `tb_product_category` VALUES (8, '新增类别2', 300, NULL, 4);

-- ----------------------------
-- Table structure for tb_product_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_img`;
CREATE TABLE `tb_product_img`  (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img_desc` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(2) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `product_id` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`product_img_id`) USING BTREE,
  INDEX `fk_proimg_product_idx`(`product_id`) USING BTREE,
  CONSTRAINT `fk_proimg_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_product_img
-- ----------------------------
INSERT INTO `tb_product_img` VALUES (9, '/upload/images/item/shop/4/2018022517130297738.jpg', NULL, NULL, '2018-02-25 17:13:03', 10);
INSERT INTO `tb_product_img` VALUES (10, '/upload/images/item/shop/4/2018022517130260460.jpg', NULL, NULL, '2018-02-25 17:13:03', 10);
INSERT INTO `tb_product_img` VALUES (11, '/upload/images/item/shop\\4\\2018022517130377302.jpg', NULL, NULL, '2018-02-25 17:13:03', 10);
INSERT INTO `tb_product_img` VALUES (18, '/upload/images/item/shop/4/2018030620003526924.jpg', NULL, NULL, '2018-03-06 20:00:38', 9);
INSERT INTO `tb_product_img` VALUES (19, '/upload/images/item/shop/4/2018030620003724021.jpg', NULL, NULL, '2018-03-06 20:00:38', 9);
INSERT INTO `tb_product_img` VALUES (20, '\\upload\\images\\item\\shop\\4\\2018041620153868517.jpg', NULL, NULL, '2018-04-16 20:15:38', 15);
INSERT INTO `tb_product_img` VALUES (21, '\\upload\\images\\item\\shop\\4\\2018041620153836375.jpg', NULL, NULL, '2018-04-16 20:15:38', 15);
INSERT INTO `tb_product_img` VALUES (22, '\\upload\\images\\item\\shop\\4\\2018041620474296687.jpg', NULL, NULL, '2018-04-16 20:47:43', 16);
INSERT INTO `tb_product_img` VALUES (23, '\\upload\\images\\item\\shop\\4\\2018041620474257764.jpg', NULL, NULL, '2018-04-16 20:47:43', 16);

-- ----------------------------
-- Table structure for tb_product_sell_daily
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_sell_daily`;
CREATE TABLE `tb_product_sell_daily`  (
  `product_id` int(100) NULL DEFAULT NULL,
  `shop_id` int(10) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `total` int(10) NULL DEFAULT 0,
  UNIQUE INDEX `product_id`(`product_id`, `shop_id`, `create_time`) USING BTREE,
  INDEX `fk_product_sell_product`(`product_id`) USING BTREE,
  INDEX `fk_product_sell_shop`(`shop_id`) USING BTREE,
  CONSTRAINT `fk_product_sell_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_sell_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_shop
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop`  (
  `shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(10) NOT NULL COMMENT '店铺创建人',
  `area_id` int(5) NULL DEFAULT NULL,
  `shop_category_id` int(11) NULL DEFAULT NULL,
  `shop_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `shop_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `shop_addr` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `shop_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `priority` int(3) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0,
  `advice` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`shop_id`) USING BTREE,
  INDEX `fk_shop_area_idx`(`area_id`) USING BTREE,
  INDEX `fk_shop_profile_idx`(`owner_id`) USING BTREE,
  INDEX `fk_shop_shopcate_idx`(`shop_category_id`) USING BTREE,
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------
INSERT INTO `tb_shop` VALUES (1, 8, 3, 10, '测试', 'mytest1', 'testaddr1', '13810524526', '/upload\\images\\item\\test.jpg', NULL, '2018-01-28 15:17:24', '2018-01-28 15:23:53', 1, '审核通过');
INSERT INTO `tb_shop` VALUES (4, 9, 4, 20, '正式的店铺名称', '正式', '正式的地址', '13810524522', '/upload\\images\\item\\shop\\4\\2018022316480998321.jpg', NULL, '2018-02-01 16:00:39', '2018-02-23 16:48:10', 1, '审核通过');
INSERT INTO `tb_shop` VALUES (5, 9, 3, 20, 'mytest4', 'mytest4', 'testaddr4', '13810524523', '/upload\\images\\item\\shop\\5\\2018020614554991281.jpg', NULL, '2018-02-06 14:55:49', '2018-02-06 14:55:49', 1, '审核通过');
INSERT INTO `tb_shop` VALUES (6, 8, 5, 20, '咖啡店', 'test', 'test', '12312341234', '/upload\\images\\item\\shop\\6\\2018020713574148268.jpg', NULL, '2018-02-07 13:57:41', '2018-02-07 13:57:41', 0, NULL);
INSERT INTO `tb_shop` VALUES (8, 8, 6, 13, 'mytest1', 'mytest1', 'testaddr1', '13810524526', '/upload\\images\\item\\test.jpg', NULL, '2018-03-10 20:18:15', '2018-03-10 20:18:15', 1, '审核通过');
INSERT INTO `tb_shop` VALUES (9, 8, 4, 14, 'mytest2', 'mytest1', 'testaddr2', '13810524526', '/upload\\images\\item\\test.jpg', NULL, '2018-03-10 20:18:30', '2018-03-10 20:18:30', 1, '审核通过');
INSERT INTO `tb_shop` VALUES (10, 8, 3, 15, 'mytest3', 'mytest1', 'testaddr3', '13810524526', '/upload\\images\\item\\test.jpg', NULL, '2018-03-10 20:18:43', '2018-03-10 20:18:43', 1, '审核通过');

-- ----------------------------
-- Table structure for tb_shop_auth_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_auth_map`;
CREATE TABLE `tb_shop_auth_map`  (
  `shop_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `title_flag` int(2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`shop_auth_id`) USING BTREE,
  UNIQUE INDEX `employee_id`(`employee_id`, `shop_id`) USING BTREE,
  INDEX `fk_shop_auth_map_shop`(`shop_id`) USING BTREE,
  INDEX `uk_shop_auth_map`(`employee_id`, `shop_id`) USING BTREE,
  CONSTRAINT `fk_shop_auth_map_employee` FOREIGN KEY (`employee_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_shop_auth_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_shop_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_category`;
CREATE TABLE `tb_shop_category`  (
  `shop_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `shop_category_desc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `shop_category_img` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_edit_time` datetime(0) NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`) USING BTREE,
  INDEX `fk_procate_self_idx`(`parent_id`) USING BTREE,
  CONSTRAINT `fk_procate_self` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_shop_category
-- ----------------------------
INSERT INTO `tb_shop_category` VALUES (10, '二手市场', '二手商品交易', '/upload/images/item/shopcategory/2017061223272255687.png', 100, '2017-06-04 20:10:58', '2017-06-12 23:27:22', NULL);
INSERT INTO `tb_shop_category` VALUES (11, '美容美发', '美容美发', '/upload/images/item/shopcategory/2017061223273314635.png', 99, '2017-06-04 20:12:57', '2017-06-12 23:27:33', NULL);
INSERT INTO `tb_shop_category` VALUES (12, '美食饮品', '美食饮品', '/upload/images/item/shopcategory/2017061223274213433.png', 98, '2017-06-04 20:15:21', '2017-06-12 23:27:42', NULL);
INSERT INTO `tb_shop_category` VALUES (13, '休闲娱乐', '休闲娱乐', '/upload/images/item/shopcategory/2017061223275121460.png', 97, '2017-06-04 20:19:29', '2017-06-12 23:27:51', NULL);
INSERT INTO `tb_shop_category` VALUES (14, '旧车', '旧车', '/upload/images/item/shopcategory/2017060420315183203.png', 80, '2017-06-04 20:31:51', '2017-06-04 20:31:51', 10);
INSERT INTO `tb_shop_category` VALUES (15, '二手书籍', '二手书籍', '/upload/images/item/shopcategory/2017060420322333745.png', 79, '2017-06-04 20:32:23', '2017-06-04 20:32:23', 10);
INSERT INTO `tb_shop_category` VALUES (17, '护理', '护理', '/upload/images/item/shopcategory/2017060420372391702.png', 76, '2017-06-04 20:37:23', '2017-06-04 20:37:23', 11);
INSERT INTO `tb_shop_category` VALUES (18, '理发', '理发', '/upload/images/item/shopcategory/2017060420374775350.png', 74, '2017-06-04 20:37:47', '2017-06-04 20:37:47', 11);
INSERT INTO `tb_shop_category` VALUES (20, '大排档', '大排档', '/upload/images/item/shopcategory/2017060420460491494.png', 59, '2017-06-04 20:46:04', '2017-06-04 20:46:04', 12);
INSERT INTO `tb_shop_category` VALUES (22, '奶茶店', '奶茶店', '/upload/images/item/shopcategory/2017060420464594520.png', 58, '2017-06-04 20:46:45', '2017-06-04 20:46:45', 12);
INSERT INTO `tb_shop_category` VALUES (24, '密室逃生', '密室逃生', '/upload/images/item/shopcategory/2017060420500783376.png', 56, '2017-06-04 20:50:07', '2017-06-04 21:45:53', 13);
INSERT INTO `tb_shop_category` VALUES (25, 'KTV', 'KTV', '/upload/images/item/shopcategory/2017060420505834244.png', 57, '2017-06-04 20:50:58', '2017-06-04 20:51:14', 13);
INSERT INTO `tb_shop_category` VALUES (27, '培训教育', '培训教育', '/upload/images/item/shopcategory/2017061223280082147.png', 96, '2017-06-04 21:51:36', '2017-06-12 23:28:00', NULL);
INSERT INTO `tb_shop_category` VALUES (28, '租赁市场', '租赁市场', '/upload/images/item/shopcategory/2017061223281361578.png', 95, '2017-06-04 21:53:52', '2017-06-12 23:28:13', NULL);
INSERT INTO `tb_shop_category` VALUES (29, '程序设计', '程序设计', '/upload/images/item/shopcategory/2017060421593496807.png', 50, '2017-06-04 21:59:34', '2017-06-04 21:59:34', 27);
INSERT INTO `tb_shop_category` VALUES (30, '声乐舞蹈', '声乐舞蹈', '/upload/images/item/shopcategory/2017060421595843693.png', 49, '2017-06-04 21:59:58', '2017-06-04 21:59:58', 27);
INSERT INTO `tb_shop_category` VALUES (31, '演出道具', '演出道具', '/upload/images/item/shopcategory/2017060422114076152.png', 45, '2017-06-04 22:11:40', '2017-06-04 22:11:40', 28);
INSERT INTO `tb_shop_category` VALUES (32, '交通工具', '交通工具', '/upload/images/item/shopcategory/2017060422121144586.png', 44, '2017-06-04 22:12:11', '2017-06-04 22:12:11', 28);

-- ----------------------------
-- Table structure for tb_user_award_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_award_map`;
CREATE TABLE `tb_user_award_map`  (
  `user_award_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `award_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `operator_id` int(10) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `used_status` int(2) NOT NULL DEFAULT 0,
  `point` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`user_award_id`) USING BTREE,
  INDEX `fk_user_award_map_award`(`award_id`) USING BTREE,
  INDEX `fk_user_award_map_profile`(`user_id`) USING BTREE,
  INDEX `fk_user_award_map_shop`(`shop_id`) USING BTREE,
  INDEX `fk_user_award_map_operator`(`operator_id`) USING BTREE,
  CONSTRAINT `fk_user_award_map_award` FOREIGN KEY (`award_id`) REFERENCES `tb_award` (`award_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_award_map_operator` FOREIGN KEY (`operator_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_award_map_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_award_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_product_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_product_map`;
CREATE TABLE `tb_user_product_map`  (
  `user_product_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL,
  `product_id` int(100) NULL DEFAULT NULL,
  `shop_id` int(10) NULL DEFAULT NULL,
  `operator_id` int(10) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `point` int(10) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_product_id`) USING BTREE,
  INDEX `fk_user_product_map_profile`(`user_id`) USING BTREE,
  INDEX `fk_user_product_map_product`(`product_id`) USING BTREE,
  INDEX `fk_user_product_map_shop`(`shop_id`) USING BTREE,
  INDEX `fk_user_product_map_operator`(`operator_id`) USING BTREE,
  CONSTRAINT `fk_user_product_map_operator` FOREIGN KEY (`operator_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_product_map_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_product_map_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_product_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_shop_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_shop_map`;
CREATE TABLE `tb_user_shop_map`  (
  `user_shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `point` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`user_shop_id`) USING BTREE,
  UNIQUE INDEX `uq_user_shop`(`user_id`, `shop_id`) USING BTREE,
  INDEX `fk_user_shop_shop`(`shop_id`) USING BTREE,
  CONSTRAINT `fk_user_shop_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_shop_user` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_wechat_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_wechat_auth`;
CREATE TABLE `tb_wechat_auth`  (
  `wechat_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `open_id` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`wechat_auth_id`) USING BTREE,
  UNIQUE INDEX `open_id_UNIQUE`(`open_id`) USING BTREE,
  INDEX `fk_oauth_profile`(`user_id`) USING BTREE,
  INDEX `uk_oauth`(`open_id`(255)) USING BTREE,
  CONSTRAINT `fk_oauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_wechat_auth
-- ----------------------------
INSERT INTO `tb_wechat_auth` VALUES (4, 8, 'ovLbns-gxJHqC-UTPQKvgEuENl-E', '2017-06-04 19:01:09');
INSERT INTO `tb_wechat_auth` VALUES (5, 9, 'ovLbns9oD5K4g712TW63dgSHxC3o', '2017-06-04 21:20:43');
INSERT INTO `tb_wechat_auth` VALUES (6, 10, 'ovLbnsz16NtYSt2bCoJktXOGlzyg', '2017-06-07 01:36:16');
INSERT INTO `tb_wechat_auth` VALUES (7, 11, 'ovLbns4Z7ueIBJNmgVfpDTQQLCRA', '2017-09-18 23:39:38');
INSERT INTO `tb_wechat_auth` VALUES (11, 15, 'oPBjk1R6xF2d5dGjGwMVsJE4M-XI', '2018-04-12 19:46:06');

SET FOREIGN_KEY_CHECKS = 1;
