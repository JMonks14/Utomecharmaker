-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: utome
-- ------------------------------------------------------
-- Server version	5.7.25-google-log

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
-- Current Database: `utome`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `utome` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `utome`;

--
-- Table structure for table `char_skills`
--

DROP TABLE IF EXISTS `char_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `char_skills` (
  `fk_char_id` int(11) DEFAULT NULL,
  `fk_skill_id` int(11) DEFAULT NULL,
  KEY `fk_char_id` (`fk_char_id`),
  KEY `fk_skill_id` (`fk_skill_id`),
  CONSTRAINT `char_skills_ibfk_1` FOREIGN KEY (`fk_char_id`) REFERENCES `characters` (`char_id`),
  CONSTRAINT `char_skills_ibfk_2` FOREIGN KEY (`fk_skill_id`) REFERENCES `skills` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `char_skills`
--

LOCK TABLES `char_skills` WRITE;
/*!40000 ALTER TABLE `char_skills` DISABLE KEYS */;
INSERT INTO `char_skills` VALUES (251,41),(252,25);
/*!40000 ALTER TABLE `char_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `char_spells`
--

DROP TABLE IF EXISTS `char_spells`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `char_spells` (
  `fk_char_id` int(11) NOT NULL,
  `fk_spell_id` int(11) NOT NULL,
  KEY `fk_char_id` (`fk_char_id`),
  KEY `fk_spell_id` (`fk_spell_id`),
  CONSTRAINT `char_spells_ibfk_1` FOREIGN KEY (`fk_char_id`) REFERENCES `characters` (`char_id`),
  CONSTRAINT `char_spells_ibfk_2` FOREIGN KEY (`fk_spell_id`) REFERENCES `spells` (`spell_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `char_spells`
--

LOCK TABLES `char_spells` WRITE;
/*!40000 ALTER TABLE `char_spells` DISABLE KEYS */;
/*!40000 ALTER TABLE `char_spells` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characters`
--

DROP TABLE IF EXISTS `characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `characters` (
  `char_id` int(11) NOT NULL AUTO_INCREMENT,
  `char_name` varchar(40) NOT NULL,
  `char_background` varchar(300) DEFAULT NULL,
  `fk_player_id` int(11) NOT NULL,
  `HP` tinyint(4) DEFAULT '3',
  `MP` tinyint(4) DEFAULT '3',
  `AP_basic` tinyint(4) DEFAULT '1',
  `AP_light` tinyint(4) DEFAULT '4',
  `AP_heavy` tinyint(4) DEFAULT '8',
  `AP_magic` tinyint(4) DEFAULT '2',
  `XP_spent` tinyint(4) DEFAULT '0',
  `alive` bit(1) DEFAULT b'1',
  `race` varchar(15) DEFAULT NULL,
  `origin` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`char_id`),
  KEY `fk_player_id` (`fk_player_id`),
  CONSTRAINT `characters_ibfk_1` FOREIGN KEY (`fk_player_id`) REFERENCES `players` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters`
--

LOCK TABLES `characters` WRITE;
/*!40000 ALTER TABLE `characters` DISABLE KEYS */;
INSERT INTO `characters` VALUES (251,'Berren','butts',250,3,7,1,0,0,0,1,0x00,'Human','Ardglass'),(252,'Steve','A dude',250,3,3,1,0,0,0,1,0x01,'Human','Ardglass');
/*!40000 ALTER TABLE `characters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `headrefs`
--

DROP TABLE IF EXISTS `headrefs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `headrefs` (
  `parameter` varchar(30) DEFAULT NULL,
  `value` tinyint(4) DEFAULT NULL,
  `param_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `headrefs`
--

LOCK TABLES `headrefs` WRITE;
/*!40000 ALTER TABLE `headrefs` DISABLE KEYS */;
INSERT INTO `headrefs` VALUES ('max_XP',7,0);
/*!40000 ALTER TABLE `headrefs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (253),(253),(253),(253);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `players` (
  `player_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `activechar_id` int(11) NOT NULL,
  PRIMARY KEY (`player_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (250,'James','Monks','Jmonks','newtest',252);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skill_trees`
--

DROP TABLE IF EXISTS `skill_trees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skill_trees` (
  `tree_id` int(11) NOT NULL AUTO_INCREMENT,
  `tree_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`tree_id`),
  UNIQUE KEY `tree_name` (`tree_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill_trees`
--

LOCK TABLES `skill_trees` WRITE;
/*!40000 ALTER TABLE `skill_trees` DISABLE KEYS */;
INSERT INTO `skill_trees` VALUES (1,'Armour'),(2,'Body'),(4,'Magic'),(0,'null tree'),(3,'Resistance'),(5,'Weapons');
/*!40000 ALTER TABLE `skill_trees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skills` (
  `skill_id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(50) NOT NULL,
  `description` varchar(400) NOT NULL,
  `fk_tree_id` int(11) NOT NULL,
  `prerequisite_1` int(11) DEFAULT NULL,
  `prerequisite_2` int(11) DEFAULT NULL,
  `is_multibuy` bit(1) DEFAULT b'0',
  `prerequisite_3` int(11) DEFAULT NULL,
  `prerequisite_4` int(11) DEFAULT NULL,
  `prerequisite_5` int(11) DEFAULT NULL,
  PRIMARY KEY (`skill_id`),
  UNIQUE KEY `skill_name` (`skill_name`),
  KEY `prerequisite_1` (`prerequisite_1`),
  KEY `prerequisite_2` (`prerequisite_2`),
  KEY `fk_tree_id` (`fk_tree_id`),
  KEY `prerequisite_3` (`prerequisite_3`),
  KEY `prerequisite_4` (`prerequisite_4`),
  KEY `prerequisite_5` (`prerequisite_5`),
  CONSTRAINT `skills_ibfk_1` FOREIGN KEY (`prerequisite_1`) REFERENCES `skills` (`skill_id`),
  CONSTRAINT `skills_ibfk_2` FOREIGN KEY (`prerequisite_2`) REFERENCES `skills` (`skill_id`),
  CONSTRAINT `skills_ibfk_3` FOREIGN KEY (`fk_tree_id`) REFERENCES `skill_trees` (`tree_id`),
  CONSTRAINT `skills_ibfk_4` FOREIGN KEY (`prerequisite_3`) REFERENCES `skills` (`skill_id`),
  CONSTRAINT `skills_ibfk_5` FOREIGN KEY (`prerequisite_4`) REFERENCES `skills` (`skill_id`),
  CONSTRAINT `skills_ibfk_6` FOREIGN KEY (`prerequisite_5`) REFERENCES `skills` (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (0,'null skill','this represents a skill with no prerequisites',0,0,0,0x00,0,0,0),(1,'Professional Armourer','You can repair all varieties of non-magical armour. When you repair armour, you may fully repair the armour after 60 seconds of uninterrupted role-play (if you are interrupted part way through you still repair the armour points as if you did not have the skill). Multiple people repairing armour does not speed this time up.',1,0,0,0x00,0,0,0),(2,'Buckler Training','You may wield a buckler / small shield.',1,0,0,0x00,0,0,0),(3,'Fortified Shield','You may resist Impale that strikes your shield or buckler for 1 MP.',1,2,0,0x00,0,0,0),(4,'Shield Training','You may wield a shield.',1,2,0,0x00,0,0,0),(5,'Take Cover','Whilst wielding a shield you may resist any non Sunder Blast for 1 MP. Once per Renewal you may resist any non Sunder Blast for 0MP.',1,4,0,0x00,0,0,0),(6,'Light Armour Use','You may wear and repair light armour. This gives you a total of 4 AP.',1,0,0,0x00,0,0,0),(7,'Heavy Armour Use','You may wear and repair heavy armour. This gives you a total of 8 AP.',1,6,0,0x00,0,0,0),(8,'Armoured Casting','You gain the ability to cast in armour as per the armoured casting rules.',1,6,7,0x00,0,0,0),(9,'Light Armour Expert','Increases the maximum armour value of light armour to 6 AP.',1,6,0,0x00,0,0,0),(10,'Heavy Armour Expert','Increases the maximum armour value of heavy armour to 10 AP.',1,7,0,0x00,0,0,0),(11,'Unbreakable Defence','You may resist Shatter or Through for 1 MP.',1,9,10,0x00,0,0,0),(12,'Light Armour Mastery','Increases the maximum armour value of light armour to 8 AP.',1,9,0,0x00,0,0,0),(13,'Heavy Armour Mastery','Increases the maximum armour value of heavy armour to 12 AP.',1,10,0,0x00,0,0,0),(14,'Spiritual Armour','You may have two copies of the Resistance spell cast on you at one time.',1,12,13,0x00,0,0,0),(15,'Intense Training','You gain 1 max HP.',2,0,0,0x00,0,0,0),(16,'Matter Over Mind','While above 0 HP you may spend HP instead of MP when resisting effects.',2,15,0,0x00,0,0,0),(17,'Extreme Training','You gain 1 max HP.',2,15,0,0x00,0,0,0),(18,'Second Wind','When you complete a Renewal you regain 6 HP.',2,17,0,0x00,0,0,0),(19,'Exceptional Training','You gain 1 max HP.',2,17,0,0x00,0,0,0),(20,'Never Say Die','When you have no armour hits remaining you may strike for +1 Damage Grade to a maximum of Triple, Triple, Triple. eg. Single, Single, Single would upgrade to Single, Single, Double.',2,19,0,0x00,0,0,0),(21,'Excessive Training','You gain 1 max HP.',2,19,0,0x00,0,0,0),(22,'Surgeon','You gain the abilities Discern Scourge, Physician, and Discern Wounds and Effects.',3,0,0,0x00,0,0,0),(23,'Sure Footed','You may resist Fling or Bind for 1 MP.',3,0,0,0x00,0,0,0),(24,'Swift Escape','You gain the ability to Flee once per Renewal.',3,23,0,0x00,0,0,0),(25,'Exceptional Balance','You may resist Strikedown for 1 MP.',3,0,0,0x00,0,0,0),(26,'Roll With the Blows','You may resist Daze for 1 MP.',3,0,0,0x00,0,0,0),(27,'Strong Mind','You may resist Enthral for 1 MP.',3,0,0,0x00,0,0,0),(28,'Strong Voice','You may resist Mute for 1 MP. You may shout whilst performing the Rite of Renewal',3,0,0,0x00,0,0,0),(29,'Willful','Twice per Renewal you may use an ability to resist an effect without paying the MP cost.',3,23,25,0x00,26,27,28),(30,'Flow with the Magic','When you resist a single-target “non effect” effect you may cast that ranged effect once for 0 MP. You must do this within ten seconds of resisting the effect and are not required to target the source of the original effect. This follows all the normal rules for spellcasting (mute, daze, armour etc).',3,23,25,0x00,26,27,28),(31,'Cantrips','You may buy a single spell that you have the prerequisites for and gain the ability to cast it subject to armour. You can purchase this skill multiple times.',4,0,0,0x01,0,0,0),(32,'Broadened Spirit','You gain 1 max MP.',4,0,0,0x00,0,0,0),(33,'Invigorated by Magic','When you are affected by a non-damaging, non-effect, non-sensation ranged effect that you do not resist you regain 1 MP. This effect cannot occur more than once every 10 seconds.',4,32,0,0x00,0,0,0),(34,'Expanded Spirit','You gain 1 max MP.',4,32,0,0x00,0,0,0),(35,'Enriched Spirit','You gain 1 max MP. You may buy this skill multiple times.',4,34,0,0x01,0,0,0),(36,'Spiritual Well','You gain 3 max MP.',4,35,0,0x00,0,0,0),(37,'Magical Armour','You gain 2 AP of magical armour. You may repair this by completing a ten second chant then restoring a number of points of AP equal to the amount of Mana expended. Magical armour is also fully repaired by completing a Renewal.',4,0,0,0x00,0,0,0),(38,'Improved Magical Armour','Increases the maximum value of magical armour to 4 AP.',4,37,0,0x00,0,0,0),(39,'Ambidexterity','You may wield a small weapon in each hand.',5,0,0,0x00,0,0,0),(40,'Surprise Attack','You may strike for Through, Bind, Mute or Strikedown against an Unresisting target or someone that you strike directly from behind. You cannot use this against the same target again for 30 seconds or until after moving at least 30 ft from them.',5,0,0,0x00,0,0,0),(41,'Skilled Blows','You may use the rotation Single, Single, Double.',5,0,0,0x00,0,0,0),(42,'Stunning Strike','You may strike for Daze for 1 MP.',5,41,0,0x00,0,0,0),(43,'Adept Blows','You may use the rotation Single, Double, Double.',5,42,0,0x00,0,0,0),(44,'Binding Strike','You may strike for Bind for 1 MP.',5,41,0,0x00,0,0,0),(45,'Throat Punch','You may strike for Mute 30 for 1 MP.',5,44,0,0x00,0,0,0),(46,'Crippling Assault','You may convert the third blow in your damage rotation to Mute 30 or Bind.',5,45,0,0x00,0,0,0),(47,'Large Weapon Training','You may wield large weapons and use the rotation Single, Single, Double.',5,0,0,0x00,0,0,0),(48,'Powerful Blows','You may use the rotation Single, Double, Double.',5,47,0,0x00,0,0,0),(49,'Spiritual Weapon','You may strike for Spirit, Spirit, Spirit for 1 MP. If you are the target of the Imbue Weapon spell you gain double the normal number of strikes (6 strikes of Spirit or 2 strikes of the chosen effect).',5,41,48,0x00,0,0,0),(50,'Lacerating Strike','You may strike for Impale for 1 MP.',5,48,0,0x00,0,0,0),(51,'Splintering Strike','You may strike for Shatter for 1MP.',5,48,0,0x00,0,0,0),(52,'Mighty Blows','You may use the damage rotation Double, Double, Double.',5,50,51,0x00,0,0,0),(53,'Forceful Strike','You may strike for Fling, Fling, Fling for 1 MP.',5,47,0,0x00,0,0,0),(54,'Felling Strike','You may strike for Strikedown for 1MP.',5,53,0,0x00,0,0,0),(55,'Relentless Assault','You may convert the third blow in your damage rotation to Fling or Strikedown.',5,54,0,0x00,0,0,0);
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spells`
--

DROP TABLE IF EXISTS `spells`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spells` (
  `spell_id` int(11) NOT NULL AUTO_INCREMENT,
  `spell_name` varchar(30) DEFAULT NULL,
  `prerequisite` int(11) DEFAULT NULL,
  `mana_cost` tinyint(4) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  `description` varchar(700) DEFAULT NULL,
  PRIMARY KEY (`spell_id`),
  KEY `prerequisite` (`prerequisite`),
  CONSTRAINT `spells_ibfk_1` FOREIGN KEY (`prerequisite`) REFERENCES `spells` (`spell_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spells`
--

LOCK TABLES `spells` WRITE;
/*!40000 ALTER TABLE `spells` DISABLE KEYS */;
INSERT INTO `spells` VALUES (0,'null spell',0,0,NULL,NULL),(3,'Blast',0,1,'Ranged','Target takes a Blast:Spirit or Blast: Single, as called by the caster.'),(4,'Bind',0,1,'Ranged','Target takes the Bind effect'),(5,'Enthral',4,1,'Ranged','Target takes the Enthral effect'),(6,'Fling',0,1,'Ranged','The target takes the Fling effect. You may cast this spell twice for 1 MP so long as you cast one directly after the other.'),(7,'Mass Fling',6,3,'Mass','You may call for Mass Fling for 3 MP.'),(8,'Strikedown',6,1,'Ranged','Target takes the Strikedown effect'),(9,'Mute 30',0,1,'Ranged','Target takes the Mute 30 effect'),(10,'Mass mute 30',9,2,'Mass','You may call for Mass Mute 30 for 2 MP.'),(11,'Daze',9,1,'Ranged','Target takes the Daze effect'),(12,'Chant of Cure Wounds',0,1,'Touch','Whilst chanting, once every 10 secs you may make the call \"Heal 1\". After 1 minute of chanting you may then instead call Heal 10 once and the chant ends.'),(13,'Healing Word',12,1,'Touch','You instantly cure the target’s wounds. You may call Heal X where X is the amount of MP you spend.'),(14,'Cure Ailments',0,1,'Touch','Removes the Scourge effect from the target unless otherwise stated by a referee.'),(15,'Resistance',0,1,'Touch','When casting this spell choose an effect other than Sunder or Curse. The rules for resisting Curse are clarified in its effect. Target may resist the next effect of that type. You may only have one resistance in effect at any time unless you have a skill or lammie that says otherwise. If you have a resistance in effect and you complete a Renewal, you lose that resistance. \nIf you already have a resistance and are targeted with a second one, you choose which\none takes effect.'),(16,'Chant of Sanctuary',0,1,'Self','While chanting, if you are hit by a weapon blow you may reduce any damage dealt to 0 for 1 MP. This does not apply to Shatter, Through, Impale, Sunder  or Spirit. You must still take the effect of the call even if you nullify the damage. For example if you are hit for Strikedown you must still take the Strikedown but can stop the damage.'),(17,'Counterspell',0,1,'Ranged','This may only be cast in reaction to a ranged effect. Casting this spell nullifies a non-Mass, non-Sensation, non-Sunder ranged effect. You must begin casting within 3 seconds of the spell you wish to counter being cast. You cannot counterspell another counterspell or an effect preceded by the Effect keyword.'),(18,'Consume the Spirit’s Heart',0,2,'Touch','You may chant for 30 seconds over an Unresisting target. At the end of that time the target is killed. After doing so you count as having completed the Rite of Renewal.'),(19,'Imbue Weapon',0,1,'Touch','You change a willing target’s next damage rotation to Spirit, Spirit, Spirit.\nAlternatively you may imbue the target’s next strike to call for your choice of the following abilities if you have the ability to cast a single target ranged version of that call: Fling, Bind, Strikedown, Mute 30, Daze. You should always state clearly to your target what call you are imbuing their weapon with.\n\nIf you only have limited uses of that spell then this consumes a use and you must have at least one use remaining to imbue a weapon with that effect.\nThis effect is lost if not used within 1 minute or if the target completes a Renewal.');
/*!40000 ALTER TABLE `spells` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-15 15:25:42
