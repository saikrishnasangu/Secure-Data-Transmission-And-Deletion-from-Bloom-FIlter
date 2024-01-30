/*
SQLyog Community v8.71 
MySQL - 5.5.30 : Database - mjcc07_2022
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mjcc07_2022` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mjcc07_2022`;

/*Table structure for table `clouda` */

DROP TABLE IF EXISTS `clouda`;

CREATE TABLE `clouda` (
  `fid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `file` longblob,
  `status` varchar(100) DEFAULT NULL,
  `pkey` varchar(255) DEFAULT NULL,
  `cipertext` longtext,
  `skey` varchar(255) DEFAULT NULL,
  `hashfunction` longtext,
  PRIMARY KEY (`fid`),
  KEY `uid` (`uid`),
  CONSTRAINT `clouda_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `userdetails` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `clouda` */

/*Table structure for table `cloudb` */

DROP TABLE IF EXISTS `cloudb`;

CREATE TABLE `cloudb` (
  `fid` int(10) NOT NULL,
  `uid` int(10) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `file` longblob,
  `status` varchar(100) DEFAULT NULL,
  `pkey` varchar(255) DEFAULT NULL,
  `cipertext` longtext,
  `skey` varchar(255) DEFAULT NULL,
  `hashfunction` longtext,
  KEY `uid` (`uid`),
  CONSTRAINT `cloudb_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `userdetails` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cloudb` */

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `fid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `feedback` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

/*Table structure for table `userdetails` */

DROP TABLE IF EXISTS `userdetails`;

CREATE TABLE `userdetails` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `utype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `userdetails` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
