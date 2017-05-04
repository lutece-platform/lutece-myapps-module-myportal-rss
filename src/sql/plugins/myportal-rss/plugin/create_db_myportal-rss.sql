
--
-- Structure for table myportal_rss_advanced_conf
--

DROP TABLE IF EXISTS myportal_rss_advanced_conf;
CREATE TABLE myportal_rss_advanced_conf (
id_rss_conf int(6) NOT NULL,
attributeuser varchar(50) default '' NOT NULL,
attributevalue varchar(50) default '' NOT NULL,
url varchar(255) default '' NOT NULL,
idcategory int(11) default '0' NOT NULL,
PRIMARY KEY (id_rss_conf)
);

--
-- Structure for table myportal_rss_category
--

DROP TABLE IF EXISTS myportal_rss_category;
CREATE TABLE myportal_rss_category (
id_category int(6) NOT NULL,
title varchar(50) default '' NOT NULL,
PRIMARY KEY (id_category)
);
