/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 10.3.13-MariaDB : Database - recycledb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`recycledb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `recycledb`;

/*Table structure for table `tbl_captcha` */

CREATE TABLE `tbl_captcha` (
  `phone_no` varchar(20) NOT NULL COMMENT '电话号码',
  `captcha_code` varchar(20) DEFAULT NULL COMMENT '短信验证码',
  `send_time` varchar(20) DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`phone_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_captcha` */

insert  into `tbl_captcha`(`phone_no`,`captcha_code`,`send_time`) values ('18366129150','512375','2019-07-03 23:07:51');
insert  into `tbl_captcha`(`phone_no`,`captcha_code`,`send_time`) values ('18615539629','938242','2019-07-03 23:03:52');
insert  into `tbl_captcha`(`phone_no`,`captcha_code`,`send_time`) values ('18666215001','071332','2019-06-20 08:38:29');

/*Table structure for table `tbl_device` */

CREATE TABLE `tbl_device` (
  `eid` int(20) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(20) DEFAULT NULL COMMENT 'yyyy-mm-dd hh:mi:ss',
  `remark` varchar(200) DEFAULT NULL,
  `device_id` varchar(30) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL COMMENT '0正常，1故障，2满溢，3不可用',
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_device` */

insert  into `tbl_device`(`eid`,`create_time`,`remark`,`device_id`,`latitude`,`longitude`,`status`) values (4,'2019-07-03 23:52:00','','B1-FC-36-3D-EB-F9',36.67545,117.144487,'2');
insert  into `tbl_device`(`eid`,`create_time`,`remark`,`device_id`,`latitude`,`longitude`,`status`) values (5,'2019-07-03 23:52:00','','B1-FC-36-3D-EB-F9',36.676236,117.142268,'1');
insert  into `tbl_device`(`eid`,`create_time`,`remark`,`device_id`,`latitude`,`longitude`,`status`) values (6,'2019-07-03 23:52:00','','B1-FC-36-3D-EB-F9',36.6744,117.141091,'0');

/*Table structure for table `tbl_know_dtl` */

CREATE TABLE `tbl_know_dtl` (
  `hid` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_path` varchar(200) DEFAULT NULL COMMENT '图像路径',
  `device_id` varchar(20) DEFAULT NULL COMMENT '发送图片设备',
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `know_name` varchar(20) DEFAULT NULL COMMENT '识别物品名称',
  `know_time` varchar(20) DEFAULT NULL COMMENT '识别时间',
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_know_dtl` */

insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (1,'D:\\recovery\\image/9751caf3f3414a6eb586b1e283808c20.jpg','111111',1,'拖鞋','2019-05-21 22:23:50');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (2,'D:\\recovery\\image\\11ed25a31a1d40c89cc4a9a05c0ff563.jpg','d86f908d0aa6364a',4,'机器设备','2019-06-17 23:05:38');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (3,'D:\\recovery\\image\\2d248f35cabb4c29ac2763bd2cd39c87.jpg','d86f908d0aa6364a',4,'蚊帐','2019-06-17 23:06:45');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (4,'D:\\recovery\\image\\a88f2ef39ea94b139e3b8aa14656a468.jpg','d86f908d0aa6364a',4,'电子原器件','2019-06-17 23:08:21');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (5,'D:\\recovery\\image\\f354cf9f597c49b3891a71362e5dc279.jpg','d86f908d0aa6364a',4,'线缆','2019-06-17 23:11:12');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (6,'D:\\recovery\\image\\f188fdd2457c463c946b45a81e5e30c6.jpg','d86f908d0aa6364a',4,'拖鞋','2019-06-17 23:12:52');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (7,'D:\\recovery\\image\\ed459fa7809d432eab7db8c13c910e7b.jpg','d86f908d0aa6364a',4,'拖鞋','2019-06-17 23:13:19');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (8,'D:\\recovery\\image\\2cc07ffd011a46178848d0c2b8c2de7e.jpg','d86f908d0aa6364a',4,'拖鞋','2019-06-17 23:15:16');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (9,'D:\\recovery\\image\\2ede7d6cf9c14db48bd1ce1bd914d2f3.jpg','d86f908d0aa6364a',4,'拖鞋','2019-06-17 23:36:11');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (10,'D:\\recovery\\image\\3ed1cd708b404f959860ae72d889a070.jpg','d86f908d0aa6364a',4,'拖鞋','2019-06-17 23:42:31');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (11,'D:\\recovery\\image\\3d07730078da42eaa64ecd43069a7457.jpg','d86f908d0aa6364a',4,'拖鞋','2019-06-17 23:45:01');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (12,'D:\\recovery\\image\\3392680e25984e1cb4f6df248562a661.jpg','111111',4,'美女','2019-06-24 08:46:34');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (13,'D:\\recovery\\image\\9092ea36b9cc4e9fb7dcfb6cc2291ea3.jpg','111111',4,'拖鞋','2019-06-24 08:47:30');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (14,'D:\\recovery\\image\\a5ea7272aa2c47ea967f0093ee9837c8.jpg','111111',4,'拖鞋','2019-06-24 08:55:24');
insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (15,'D:\\recovery\\image\\51b8b446244240809a8de7ef6d63b940.jpg','94:e0:d6:8e:a2:c0',8,'拖鞋','2019-07-03 23:01:56');

/*Table structure for table `tbl_know_obj` */

CREATE TABLE `tbl_know_obj` (
  `kid` bigint(20) NOT NULL AUTO_INCREMENT,
  `know_name` varchar(20) DEFAULT NULL COMMENT '识别物品名称',
  `obj_type` bigint(20) DEFAULT NULL COMMENT '物品分类ID',
  PRIMARY KEY (`kid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_know_obj` */

insert  into `tbl_know_obj`(`kid`,`know_name`,`obj_type`) values (3,'拖鞋',1);
insert  into `tbl_know_obj`(`kid`,`know_name`,`obj_type`) values (4,'机器设备',0);
insert  into `tbl_know_obj`(`kid`,`know_name`,`obj_type`) values (5,'蚊帐',0);
insert  into `tbl_know_obj`(`kid`,`know_name`,`obj_type`) values (6,'电子原器件',0);
insert  into `tbl_know_obj`(`kid`,`know_name`,`obj_type`) values (7,'线缆',0);
insert  into `tbl_know_obj`(`kid`,`know_name`,`obj_type`) values (8,'美女',0);

/*Table structure for table `tbl_obj_type` */

CREATE TABLE `tbl_obj_type` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物品ID',
  `object_name` varchar(20) DEFAULT NULL COMMENT '物品名称',
  `unit_price` bigint(20) DEFAULT NULL COMMENT '单价',
  `remark` varchar(200) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_obj_type` */

insert  into `tbl_obj_type`(`oid`,`object_name`,`unit_price`,`remark`) values (1,'拖鞋',10,'');

/*Table structure for table `tbl_order` */

CREATE TABLE `tbl_order` (
  `tid` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) DEFAULT NULL COMMENT '投递编号',
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `device_id` varchar(20) DEFAULT NULL COMMENT '设备唯一标识',
  `bin_no` varchar(20) DEFAULT NULL COMMENT '内桶编号',
  `order_type` bigint(20) DEFAULT NULL COMMENT '物品类型',
  `weight` bigint(20) DEFAULT NULL COMMENT '重量',
  `price` bigint(20) DEFAULT NULL COMMENT '价格',
  `amount` bigint(20) DEFAULT NULL COMMENT '收益',
  `delivery_time` varchar(20) DEFAULT NULL COMMENT '投递时间',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_order` */

/*Table structure for table `tbl_token` */

CREATE TABLE `tbl_token` (
  `token` varchar(60) NOT NULL,
  `eid` varchar(20) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL COMMENT 'yyyy-mm-dd hh:mi:ss\r\n有效期10小时',
  `status` varchar(1) DEFAULT NULL COMMENT '0有效，1无效',
  PRIMARY KEY (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_token` */

/*Table structure for table `tbl_user` */

CREATE TABLE `tbl_user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account_id` varchar(20) DEFAULT NULL COMMENT '用户账号',
  `nick_name` varchar(128) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别 1男 2女',
  `status` tinyint(2) DEFAULT NULL COMMENT '用户状态  0正常 1停用',
  `current_profit` bigint(20) DEFAULT NULL COMMENT '当前收益',
  `deliver_count` bigint(20) DEFAULT NULL COMMENT '投递次数',
  `total_profit` varchar(20) DEFAULT NULL COMMENT '累计收益',
  `wxs_open_id` varchar(128) DEFAULT NULL COMMENT '微信OPEN_ID',
  `face_token` varchar(50) DEFAULT NULL COMMENT '人脸唯一标识',
  `ic_card` varchar(50) DEFAULT NULL COMMENT 'IC卡识别码',
  `create_time` varchar(20) DEFAULT NULL COMMENT '用户创建时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`uid`,`account_id`,`nick_name`,`sex`,`status`,`current_profit`,`deliver_count`,`total_profit`,`wxs_open_id`,`face_token`,`ic_card`,`create_time`) values (8,'18366129150','',0,0,0,0,'0','',NULL,'','2019-07-03 23:01:36');
insert  into `tbl_user`(`uid`,`account_id`,`nick_name`,`sex`,`status`,`current_profit`,`deliver_count`,`total_profit`,`wxs_open_id`,`face_token`,`ic_card`,`create_time`) values (9,'18615539629','',0,0,0,0,'0','',NULL,'','2019-07-03 23:04:02');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
