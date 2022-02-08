create database snakeladder_db;
use snakeladder_db;

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20),(21,21),(22,22),(23,23),(24,24),(25,25),(26,26),(27,27),(28,28),(29,29),(30,30),(31,31),(32,32),(33,33),(34,34),(35,35),(36,36),(37,37),(38,38),(39,39),(40,40),(41,41),(42,42),(43,43),(44,44),(45,45),(46,46),(47,47),(48,48),(49,49),(50,50),(51,51),(52,52),(53,53),(54,54),(55,55),(56,56),(57,57),(58,58),(59,59),(60,60),(61,61),(62,62),(63,63),(64,64),(65,65),(66,66),(67,67),(68,68),(69,69),(70,70),(71,71),(72,72),(73,73),(74,74),(75,75),(76,76),(77,77),(78,78),(79,79),(80,80),(81,81),(82,82),(83,83),(84,84),(85,85),(86,86),(87,87),(88,88),(89,89),(90,90),(91,91),(92,92),(93,93),(94,94),(95,95),(96,96),(97,97),(98,98),(99,99),(100,100);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,'ajay@abc.ai','Ajay'),(2,'kishan@abc.ai','Kishan');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'First Board',100,1);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `acc_or_deaccelerator` WRITE;
/*!40000 ALTER TABLE `acc_or_deaccelerator` DISABLE KEYS */;
INSERT INTO `acc_or_deaccelerator` VALUES (1,1,1,41,99),(2,0,1,92,74),(3,1,1,53,89),(4,0,1,81,62),(5,1,1,58,76),(6,0,1,63,42),(7,0,1,69,50),(8,1,1,31,54),(9,0,1,49,33),(10,0,1,46,13),(11,1,1,18,43),(12,1,1,3,40),(13,0,1,25,4),(14,1,1,5,27),(15,1,1,45,66);
/*!40000 ALTER TABLE `acc_or_deaccelerator` ENABLE KEYS */;
UNLOCK TABLES;





