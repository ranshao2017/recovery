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

insert  into `tbl_captcha`(`phone_no`,`captcha_code`,`send_time`) values ('18366129150','349883','2019-06-02 17:46:06');
insert  into `tbl_captcha`(`phone_no`,`captcha_code`,`send_time`) values ('18615539629','845917','2019-06-02 19:57:57');

/*Table structure for table `tbl_device` */

CREATE TABLE `tbl_device` (
  `eid` varchar(20) NOT NULL,
  `create_time` varchar(20) DEFAULT NULL COMMENT 'yyyy-mm-dd hh:mi:ss',
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_device` */

/*Table structure for table `tbl_know_dtl` */

CREATE TABLE `tbl_know_dtl` (
  `hid` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_path` varchar(200) DEFAULT NULL COMMENT '图像路径',
  `device_id` varchar(20) DEFAULT NULL COMMENT '发送图片设备',
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `know_name` varchar(20) DEFAULT NULL COMMENT '识别物品名称',
  `know_time` varchar(20) DEFAULT NULL COMMENT '识别时间',
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_know_dtl` */

insert  into `tbl_know_dtl`(`hid`,`image_path`,`device_id`,`uid`,`know_name`,`know_time`) values (1,'D:\\recovery\\image/9751caf3f3414a6eb586b1e283808c20.jpg','111111',1,'拖鞋','2019-05-21 22:23:50');

/*Table structure for table `tbl_know_obj` */

CREATE TABLE `tbl_know_obj` (
  `kid` bigint(20) NOT NULL AUTO_INCREMENT,
  `know_name` varchar(20) DEFAULT NULL COMMENT '识别物品名称',
  `obj_type` bigint(20) DEFAULT NULL COMMENT '物品分类ID',
  PRIMARY KEY (`kid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_know_obj` */

insert  into `tbl_know_obj`(`kid`,`know_name`,`obj_type`) values (3,'拖鞋',0);

/*Table structure for table `tbl_obj_type` */

CREATE TABLE `tbl_obj_type` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物品ID',
  `object_name` varchar(20) DEFAULT NULL COMMENT '物品名称',
  `unit_price` bigint(20) DEFAULT NULL COMMENT '单价',
  `remark` varchar(200) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_obj_type` */

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`uid`,`account_id`,`nick_name`,`sex`,`status`,`current_profit`,`deliver_count`,`total_profit`,`wxs_open_id`,`face_token`,`ic_card`,`create_time`) values (1,'18615539629','',0,0,5,40,'20','','942ab0fc01429b08a50796790e8927ce','abcdefg','2019-05-21 20:22:20');
insert  into `tbl_user`(`uid`,`account_id`,`nick_name`,`sex`,`status`,`current_profit`,`deliver_count`,`total_profit`,`wxs_open_id`,`face_token`,`ic_card`,`create_time`) values (2,'18366129150','',0,0,0,0,'0','','','','2019-05-30 18:08:55');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
