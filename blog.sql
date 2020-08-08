SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `synopsis` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL,
  `view_count` int(11) NULL DEFAULT 0,
  `comment_count` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sequence` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '/icon/java.png', 'JAVA', 1);
INSERT INTO `category` VALUES (2, '/icon/cpp.png', 'C++', 2);
INSERT INTO `category` VALUES (3, '/icon/python.png', 'Python', 3);
INSERT INTO `category` VALUES (4, '/icon/golang.png', 'Golang', 4);
INSERT INTO `category` VALUES (5, '/icon/android.png', 'Android', 5);
INSERT INTO `category` VALUES (6, '/icon/linux.png', 'Linux', 6);
INSERT INTO `category` VALUES (7, '/icon/windows.png', 'Windows', 7);
INSERT INTO `category` VALUES (8, '/icon/miniprogram.png', '小程序', 8);
INSERT INTO `category` VALUES (9, '/icon/spring.png', 'Spring', 9);
INSERT INTO `category` VALUES (10, '/icon/springboot.png', 'SpringBoot', 10);
INSERT INTO `category` VALUES (11, '/icon/springcloud.png', 'SpringCloud', 11);
INSERT INTO `category` VALUES (12, '/icon/springsecurity.png', 'SpringSecurity', 12);
INSERT INTO `category` VALUES (13, '/icon/mybatis.png', 'Mybatis', 13);
INSERT INTO `category` VALUES (14, '/icon/hibernate.png', 'Hibernate', 14);
INSERT INTO `category` VALUES (15, '/icon/kafka.png', 'Kafka', 15);
INSERT INTO `category` VALUES (16, '/icon/zookeeper.png', 'Zookeeper', 16);
INSERT INTO `category` VALUES (17, '/icon/html.png', 'HTML', 17);
INSERT INTO `category` VALUES (18, '/icon/css.png', 'CSS', 18);
INSERT INTO `category` VALUES (19, '/icon/javascript.png', 'JavaScript', 19);
INSERT INTO `category` VALUES (20, '/icon/typescript.png', 'TypeScript', 20);
INSERT INTO `category` VALUES (21, '/icon/vue.png', 'Vue', 21);
INSERT INTO `category` VALUES (22, '/icon/nuxt.png', 'Nuxt', 22);
INSERT INTO `category` VALUES (23, '/icon/elementui.png', 'ElementUI', 23);
INSERT INTO `category` VALUES (24, '/icon/bootstrap.png', 'Bootstrap', 24);
INSERT INTO `category` VALUES (25, '/icon/nginx.png', 'Nginx', 25);
INSERT INTO `category` VALUES (26, '/icon/nodejs.png', 'Nodejs', 26);
INSERT INTO `category` VALUES (27, '/icon/mysql.png', 'Mysql', 27);
INSERT INTO `category` VALUES (28, '/icon/redis.png', 'Redis', 28);
INSERT INTO `category` VALUES (29, '/icon/git.png', 'Git', 29);
INSERT INTO `category` VALUES (30, '/icon/maven.png', 'Maven', 30);
INSERT INTO `category` VALUES (31, '/icon/docker.png', 'Docker', 31);
INSERT INTO `category` VALUES (32, '/icon/algorithm.png', '算法题解', 32);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `article_id` int(11) NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_used` timestamp(0) NOT NULL,
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin');
INSERT INTO `role` VALUES (2, 'member');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'kwxy1314@qq.com', 'Linter', '$2a$10$en2JxasguDAPugsYRPdHGOTazazkihMqq3gC9yi8a8yBeQhpwFgCu');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, '1', '1');

SET FOREIGN_KEY_CHECKS = 1;
