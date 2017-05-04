CREATE TABLE IF NOT EXISTS myportal_rss_advanced_conf (
id_rss_conf int(6) NOT NULL,
attributeuser varchar(50) default '' NOT NULL,
attributevalue varchar(50) default '' NOT NULL,
url varchar(255) default '' NOT NULL,
idcategory int(11) default '0' NOT NULL,
PRIMARY KEY (id_rss_conf)
);

CREATE TABLE IF NOT EXISTS myportal_rss_category (
id_category int(6) NOT NULL,
title varchar(50) default '' NOT NULL,
PRIMARY KEY (id_category)
);
--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'MYPORTAL_RSS_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('MYPORTAL_RSS_MANAGEMENT','module.myportal.rss.adminFeature.ManageRss.name',1,'jsp/admin/plugins/myportal/modules/rss/ManageCategorys.jsp','module.myportal.rss.adminFeature.ManageRss.description',0,'myportal-rss',NULL,NULL,NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'MYPORTAL_RSS_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('MYPORTAL_RSS_MANAGEMENT',1);