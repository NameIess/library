-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `author` varchar(150) NOT NULL,
  `year_of_publishing` smallint(5) unsigned NOT NULL,
  `number_of_pages` smallint(5) unsigned NOT NULL,
  `description` varchar(1500) NOT NULL,
  `amount` smallint(5) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `title` (`title`),
  KEY `author` (`author`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Past Perfect','Danielle Steel',2015,352,'Past and present collide at their elegant mansion, when they meet the large and lively family who lived there a century ago.',17),(2,'The Whispering Room','Mr Dean Koonz',2010,240,'These are the words that ring in the mind of mild-mannered, beloved schoolteacher Cora Gundersun—just before she takes her own life, and many others’, in a shocking act of carnage.',136),(3,'Two Kinds of Truth','Michael Connelly',2000,210,'Harry Bosch searches for the truth in the new thriller from #1 NYT bestselling author Michael Connelly',0),(4,'Collusion','Luke Harding',1952,700,'Collusion is invaluable in collating the overwhelming evidence of a web of relationships between the Kremlin, Trump and members of Trumpâ??s circle.',8),(5,'Sinful Empire','Meghan March',2005,198,'The Mount Trilogy concludes with Sinful Empire!',2),(6,'Oathbringer','Brandon Sanderson',2017,365,'The #1 New York Times bestselling sequel to Words of Radiance, from epic fantasy author Brandon Sanderson at the top of his gamef',12),(7,'A Duke in Shining Armor','Loretta Chase',1952,2,'Not all dukes are created equal. Most are upstanding members of Society. And then thereÃ¢??s the trio known as Their Dis-Graces.',2),(8,'End Game','David Baldacci',1999,400,'#1 New York Times bestselling author David Baldacci returns with his most breathtaking thriller yet...',11),(9,'Raven Strike: A Dreamland Thriller','Dale Brown & Jim DeFelice',2014,650,'New York Times bestselling author Dale Brown (“The best military adventure writer in the country” —Clive Cussler) joins forces once again with Jim DeFelice for another electrifying Dreamland novel, Raven Strike. In this breathtaking international thriller, Dreamland’s elite Whiplash unit is called to action when an illegal CIA black ops mission goes frighteningly wrong in Africa and a powerful weapon of mass destruction falls into terrorist hands.',6),(10,'The Rooster Bar','John Grisham',1920,267,'Mark, Todd, and Zola came to law school to change the world, to make it a better place. But now, as third-year students, these close friends realize they have been duped. ',4),(11,'Defiant Queen','Defiant Queen',2005,654,'The Mount Trilogy continues with Defiant Queen…',11),(12,'Rainbow Six','Tom Clancy',2010,321,'Trouble comes in bunches for Stephanie Plum. First, professional grave robber and semi-professional loon, Simon Diggery, won’t let her take him in until she agrees to care for his boa constrictor, Ethel.',15),(13,'Hardcore Twenty-Four','Janet Evanovich',2016,555,'Tom Clancy is a fantastic writer, he totally keeps you interested in his books, but this book became very confusing at times. You\'d be reading a paragraph, finish it, go to the next one and he was writing about something else. I\'d go back to the previous paragraph to see if I was missing something, maybe he was stoned when he wrote this book?',6),(14,'Ready Player One','Ernest Cline',1986,678,'The year is 1928. On the outskirts of a large German city, three young men are earning a thin and precarious living. Fully armed young storm troopers swagger in the streets. ',122),(15,'Three Comrades ','Erich Maria Remarque',1988,555,'Restlessness, poverty, and violence are everywhere. For these three, friendship is the only refuge from the chaos around them.',606);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status_id` tinyint(4) NOT NULL DEFAULT '1',
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `quantity` tinyint(3) unsigned NOT NULL,
  `is_subscribtion` tinyint(1) NOT NULL,
  `term` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `status_id` (`status_id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `receipt_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `receipt_ibfk_3` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Administrator'),(2,'Librarian'),(3,'Reader');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(35) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Waiting'),(2,'Transferred'),(3,'Returned'),(4,'Rejected');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '3',
  `employee_number` varchar(50) DEFAULT NULL,
  `name` varchar(35) NOT NULL,
  `second_name` varchar(35) NOT NULL,
  `surname` varchar(35) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(35) DEFAULT NULL,
  `passport_series` varchar(15) NOT NULL,
  `passport_number` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `passport_number_series` (`passport_number`,`passport_series`),
  KEY `us_ibfk_2` (`role_id`),
  CONSTRAINT `us_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'ADM-1-01','Alex','Wladimirowich','Nosko','alex.nosko.jr@gmail.com','+375298371333','KH','2320118','18be72b8424e86d1e8ca810500bf689f'),(2,1,'ADM-2-02','Manu','Erich','Taggart','manu_taggart@gmail.com','+375291861104','KB','9770505','8f7560d57072e074266e5e78d9c11bc7'),(3,2,'LBRN-1-01488','Clemens','Emanuel','Taqqu','clemens_taqqu@gmail.com','+375294780637','KH','5521126','cac8cc1343ca33a01e7fee3ad1b17224'),(4,2,'LBRN-1-022','Simon','Menno','Kleid','simon_kleid@gmail.com','+375291088158','KX','3276156','2a4fedc440be93ca3d3ba91687659481'),(7,3,'','Corina','Hilda','Faerber','corina_faerar@gmail.com','+375292725229','KE','4490975','2f1c1bf0113937889d9e0e7b6012a081'),(8,3,'','Krista','Lene','Eckstein','kristina_eckstein@gmail.com','+375294227225','KX','9677959','5b09cc29425c5311d9d5fdf33078fee5'),(9,3,'','Leeann','Stewart','Phillips','leeann_stewart_phillips@gmail.com','+375297317590','KH','3681826','425b592ddcee90d88f81fb9c78e406b8'),(10,3,NULL,'Nora','Jerry','Forester','nora_forester@gmail.com','+375298899615','KH','3981327','bc83c767a9b6da8b7b9e37ca8d38c201'),(11,3,NULL,'Pearle','Janelle','Harlan','pearle_harlan@gmail.com','+375294269482','KH','7549371','90960b075a3c372cb9d8970211de6da8'),(12,3,NULL,'Franklin','Aileen','Hunt','franklin_hunt@gmail.com','+375290643772','KB','5505869','7fd19bc21174e1c68441ab1f4cfdda43'),(13,3,NULL,'Chelle','Jamie','Linwood','chelle_linwood@gmail.com','+375294783267','KB','9492667','7afec1f2b0713cd1ce525baff7de1235'),(14,3,NULL,'Lusine','Spartak','Nazaryan','lusine_nazaryan@gmail.com','+375293354408','KX','5053153','20ac9df20830919a310f78e25f23530b'),(18,2,'','Dmitry','Wolfhang','Nosko','dmitry@gmail.com','+375292800252','QR','23216988','bf55b4d9d47927851f4a61aa58991df7'),(23,3,'','Ernst','Erich','Greber','ernst_greber@gmail.com','+3753359638','WT','5126375','1ff12de63926f646c1e4092648b710b4'),(27,3,NULL,'fasdf','asdf','sadf','dennyMarko@gmaisfl.com','+375298371488','sadfg','124234','bb3283c0db996d11c7678e9ea138bd48'),(29,2,'LBRN-241','Alex','Wolfhang','Nosko','sample_mail@gmail.com','+375291488','SA','12312','6dc19cee35e7fd33006ca64a75a90056');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'library'
--

--
-- Dumping routines for database 'library'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-10 17:25:00
