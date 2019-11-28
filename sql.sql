/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.19-log : Database - ordered_online_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ordered_online_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ordered_online_system`;

/*Table structure for table `appraise` */

DROP TABLE IF EXISTS `appraise`;

CREATE TABLE `appraise` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) DEFAULT NULL,
  `goodsid` int(10) DEFAULT NULL,
  `grade` int(10) DEFAULT '1' COMMENT '用户对商品的评分，从1到5分',
  `comment` varchar(255) DEFAULT NULL COMMENT '文字评论',
  `comment_photo` varchar(255) DEFAULT NULL COMMENT '图片评论',
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `goodsid` (`goodsid`),
  CONSTRAINT `appraise_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `appraise_ibfk_2` FOREIGN KEY (`goodsid`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `appraise` */

insert  into `appraise`(`id`,`userid`,`goodsid`,`grade`,`comment`,`comment_photo`) values (1,1,1,5,'饭很好吃，老板很nice','upload/comment_photoImg/gif.gif'),(4,14,1,5,'好吃','upload/comment_photoImg/1527082991412.jpg'),(5,1,2,2,'一般般吧','upload/comment_photoImg/1527499003638.png'),(6,3,3,5,'hjkkjhjkhk','upload/comment_photoImg/1527910507305.jpg'),(7,18,6,5,'啊啊啊啊啊','upload/comment_photoImg/1527910534241.png');

/*Table structure for table `check_business` */

DROP TABLE IF EXISTS `check_business`;

CREATE TABLE `check_business` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `seller` varchar(255) DEFAULT NULL,
  `shopname` varchar(255) DEFAULT NULL,
  `shopphoto` varchar(255) DEFAULT NULL COMMENT '店铺图片',
  `checked` varchar(255) DEFAULT '未审核' COMMENT '审核店家和店铺',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `check_business` */

insert  into `check_business`(`id`,`seller`,`shopname`,`shopphoto`,`checked`) values (1,'鬼谷子','多滋味店','upload/shopImg/duoziwei.jpg','审核通过'),(2,'王五','隆江饭店','upload/shopImg/lonjianfandian.jpg','未审核'),(4,'张三','麦当劳','upload/shopImg/1527562452780.jpg','未审核'),(5,'张三','王子拉茶','upload/shopImg/1527901434126.png','未审核'),(6,'GarsonChan','嘿嘿额','upload/shopImg/1527910459330.png','审核通过'),(7,'张三','复合物f','upload/shopImg/1527911427140.png','未审核');

/*Table structure for table `check_goods` */

DROP TABLE IF EXISTS `check_goods`;

CREATE TABLE `check_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsname` varchar(255) DEFAULT NULL,
  `price` int(255) DEFAULT NULL,
  `goodsphoto` varchar(255) DEFAULT NULL,
  `goodskind` varchar(255) DEFAULT NULL,
  `shop` varchar(255) DEFAULT NULL,
  `checked` varchar(255) DEFAULT '未审核' COMMENT '审核商品',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `check_goods` */

insert  into `check_goods`(`id`,`goodsname`,`price`,`goodsphoto`,`goodskind`,`shop`,`checked`) values (1,'牛肉炒面加强版',15,'upload/goodsImg/niurouchaomian.jpg','面','叫个炒饭','未审核'),(2,'奶茶',5,'upload/goodsImg/1527912266708.png','饮料','叫个炒饭','未审核');

/*Table structure for table `check_login` */

DROP TABLE IF EXISTS `check_login`;

CREATE TABLE `check_login` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `personphoto` varchar(255) DEFAULT NULL,
  `paypassword` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `checked` varchar(255) DEFAULT '未审核' COMMENT '审核状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `check_login` */

insert  into `check_login`(`id`,`username`,`password`,`sex`,`personphoto`,`paypassword`,`address`,`checked`) values (1,'王五','888','男','upload/personImg/1526750969230.png','888','广工','审核通过'),(2,'花木兰','777','女','upload/personImg/huamulan.jpg','777','清华','审核通过'),(3,'李华','999','男','upload/personImg/1527909528153.png','666','广工','审核通过'),(4,'haha','aaa','女','upload/personImg/1527909567032.jpg','aaa','adaa','审核通过'),(5,'lueluelue','123123','男','upload/personImg/1527909617961.jpg','123123','12123','未审核'),(6,'GarsonChan','123123','女','upload/personImg/1527909697571.jpg','123123','DIZHI','审核通过'),(7,'李三','555','男','upload/personImg/1527909701931.png','888','广工','审核通过'),(8,'haha','aaa','女','upload/personImg/1527909728274.jpg','aaa','adaa','审核通过'),(9,'lalala','123123','男','upload/personImg/1527913321937.jpg','666','666','未审核'),(10,'lalala','123123','男','upload/personImg/1527913331948.jpg','666','666','未审核');

/*Table structure for table `foodkind` */

DROP TABLE IF EXISTS `foodkind`;

CREATE TABLE `foodkind` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `goodskind` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `goodskind` (`goodskind`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `foodkind` */

insert  into `foodkind`(`id`,`goodskind`) values (5,'粉'),(1,'粥'),(2,'面'),(3,'饭'),(4,'饮料');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `goodsname` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `goodsphoto` varchar(255) DEFAULT NULL COMMENT '商品图片路径',
  `well_reputed` int(10) DEFAULT '0' COMMENT '好评度',
  `sales` int(10) DEFAULT '0' COMMENT '销量',
  `goodskind` varchar(255) DEFAULT NULL COMMENT '商品种类',
  `shop` varchar(255) DEFAULT NULL COMMENT '所属的店铺',
  PRIMARY KEY (`id`),
  KEY `shop` (`shop`),
  KEY `goodskind` (`goodskind`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`shop`) REFERENCES `shop` (`shopname`),
  CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`goodskind`) REFERENCES `foodkind` (`goodskind`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`goodsname`,`price`,`goodsphoto`,`well_reputed`,`sales`,`goodskind`,`shop`) values (1,'扬州炒饭',10,'upload/goodsImg/1526963053893.jpg',10,3,'饭','叫个炒饭'),(2,'蛋炒饭',9,'upload/goodsImg/danchaofan.jpg',0,6,'饭','叫个炒饭'),(3,'蛋炒米粉',11,'upload/goodsImg/danchaomifen.jpg',0,10,'粉','叫个炒饭'),(4,'可乐',2,'upload/goodsImg/kele.jpg',0,20,'饮料','叫个炒饭'),(5,'蛋炒面',12,'upload/goodsImg/danchaomian.jpg',0,30,'面','叫个炒饭'),(6,'牛肉炒面',13,'upload/goodsImg/niurouchaomian.jpg',0,40,'面','叫个炒饭'),(7,'牛肉炒饭',12,'upload/goodsImg/niurouchaofan.jpg',0,50,'饭','叫个炒饭');

/*Table structure for table `person_order` */

DROP TABLE IF EXISTS `person_order`;

CREATE TABLE `person_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) DEFAULT NULL,
  `goodsid` int(10) DEFAULT NULL,
  `ensure_order` varchar(255) DEFAULT '未确认订单' COMMENT '商家确认订单',
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `goodsid` (`goodsid`),
  CONSTRAINT `person_order_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `person_order_ibfk_2` FOREIGN KEY (`goodsid`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `person_order` */

insert  into `person_order`(`id`,`userid`,`goodsid`,`ensure_order`) values (1,1,1,'确认订单'),(2,14,1,'确认订单'),(3,1,2,'确认订单'),(4,1,5,'未确认订单'),(5,1,1,'未确认订单'),(6,1,2,'未确认订单'),(7,3,3,'未确认订单'),(8,3,4,'未确认订单'),(9,1,4,'未确认订单'),(10,1,4,'未确认订单'),(11,1,1,'未确认订单'),(12,1,3,'未确认订单'),(13,1,6,'未确认订单'),(14,18,6,'未确认订单'),(15,2,2,'未确认订单'),(23,1,2,'未确认订单');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) DEFAULT NULL COMMENT '用户类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`rolename`) values (1,'普通用户'),(2,'商家'),(3,'网站管理员');

/*Table structure for table `shop` */

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `shopname` varchar(255) DEFAULT NULL COMMENT '店铺名字',
  `shopphoto` varchar(255) DEFAULT NULL COMMENT '店铺图片',
  `well_reputed` int(10) DEFAULT '0' COMMENT '好评度',
  `sales` int(10) DEFAULT '0' COMMENT '销量',
  `sellerid` int(10) DEFAULT NULL COMMENT '卖家id',
  PRIMARY KEY (`id`),
  KEY `shopname` (`shopname`),
  KEY `sellerid` (`sellerid`),
  CONSTRAINT `shop_ibfk_1` FOREIGN KEY (`sellerid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `shop` */

insert  into `shop`(`id`,`shopname`,`shopphoto`,`well_reputed`,`sales`,`sellerid`) values (1,'叫个炒饭',NULL,22,3,2),(15,'多滋味店','upload/shopImg/duoziwei.jpg',0,0,4),(17,'嘿嘿额','upload/shopImg/1527910459330.png',0,0,18);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `sex` varchar(255) DEFAULT NULL,
  `personphoto` varchar(255) DEFAULT NULL COMMENT '图片的路径',
  `paypassword` varchar(255) DEFAULT NULL COMMENT '支付密码',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`sex`,`personphoto`,`paypassword`,`address`) values (1,'张三','123','男',NULL,'000','广工'),(2,'李四','456','男',NULL,'111','华工'),(3,'王九','789','女',NULL,'222','中大'),(4,'鬼谷子','111','男',NULL,'111','广大'),(14,'王五','888','男','upload/personImg/1526750969230.png','888','广工'),(15,'李三','555','男','upload/personImg/C:\\Users\\l\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\LiangZhenJi_OrderedOnlineSystem\\upload\\personImg\\\\1527909701931.png','888','广工'),(16,'花木兰','777','女','upload/personImg/huamulan.jpg','777','清华'),(17,'李华','999','男','upload/personImg/1527909528153.png','666','广工'),(18,'GarsonChan','123123','女','upload/personImg/1527909697571.jpg','123123','DIZHI'),(19,'haha','aaa','女','upload/personImg/1527909728274.jpg','aaa','adaa');

/*Table structure for table `userrole` */

DROP TABLE IF EXISTS `userrole`;

CREATE TABLE `userrole` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) DEFAULT NULL,
  `roleid` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `userrole_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `userrole_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `userrole` */

insert  into `userrole`(`id`,`userid`,`roleid`) values (1,1,1),(2,2,1),(3,2,2),(4,3,1),(5,3,3),(6,4,1),(8,14,1),(9,4,2),(10,4,2),(11,15,1),(12,16,1),(13,17,1),(14,18,1),(15,19,1),(16,18,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
