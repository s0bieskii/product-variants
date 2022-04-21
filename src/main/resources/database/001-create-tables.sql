--liquibase formatted sql
--changeset intersport:1
create TABLE categories (
  id bigint NOT NULL auto_increment,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
--changeset intersport:2
create TABLE brands (
  id bigint NOT NULL auto_increment,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
--changeset intersport:3
create TABLE genders (
  id bigint NOT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
--changeset intersport:4
create TABLE types (
  id bigint NOT NULL auto_increment,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
--changeset intersport:5
create TABLE models (
  id bigint NOT NULL auto_increment,
  name varchar(255) DEFAULT NULL,
  gender_id bigint DEFAULT NULL,
  type_id bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk3e1ai0q34fvq0wu1m52cn8847` (`gender_id`),
  KEY `fkpeqw8pxrounx8ciwn2cee0u4s` (`type_id`),
  CONSTRAINT `fk3e1ai0q34fvq0wu1m52cn8847` FOREIGN KEY (`gender_id`) references `genders` (`id`),
  CONSTRAINT `fkpeqw8pxrounx8ciwn2cee0u4s` FOREIGN KEY (`type_id`) references `types` (`id`)
);
--changeset intersport:6
create TABLE size_categories (
  id bigint NOT NULL auto_increment,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
--changeset intersport:7
create TABLE sizes (
  id bigint NOT NULL,
  sizes varchar(255) DEFAULT NULL,
  size_category_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY `fkmtqre73tiari0qnemmcx2ip82` (size_category_id),
  CONSTRAINT `fkmtqre73tiari0qnemmcx2ip82` FOREIGN KEY (size_category_id) references size_categories (id)
);
--changeset intersport:8
create TABLE products (
  id bigint NOT NULL auto_increment,
  archived bit(1) NOT NULL,
  brand_id bigint DEFAULT NULL,
  category_id bigint DEFAULT NULL,
  model_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY `fka3a4mpsfdf4d2y6r8ra3sc8mv` (brand_id),
  KEY `fkog2rp4qthbtt2lfyhfo32lsw9` (category_id),
  KEY `fk29c4nbv58vgu9wg14fd8ac4xy` (model_id),
  CONSTRAINT `fk29c4nbv58vgu9wg14fd8ac4xy` FOREIGN KEY (model_id) references models (id),
  CONSTRAINT `fka3a4mpsfdf4d2y6r8ra3sc8mv` FOREIGN KEY (brand_id) references brands (id),
  CONSTRAINT `fkog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (category_id) references categories (id)
);
--changeset intersport:9
create TABLE variants (
  id bigint NOT NULL auto_increment,
  color varchar(255) DEFAULT NULL,
  price decimal(8,2) DEFAULT NULL,
  model_id bigint DEFAULT NULL,
  size_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY `fkmxurvhsy064u97v5aqs4a627x` (model_id),
  KEY `fkpj3hv14tks24dgi529b1t9k8f` (size_id),
  CONSTRAINT `fkmxurvhsy064u97v5aqs4a627x` FOREIGN KEY (model_id) references models (id),
  CONSTRAINT `fkpj3hv14tks24dgi529b1t9k8f` FOREIGN KEY (size_id) references sizes (id)
);