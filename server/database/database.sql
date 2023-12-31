-- MySQL Script generated by MySQL Workbench
-- Wed 03 May 2023 09:27:12 PM EDT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema nectarDB_user
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `nectarDB_user` ;

-- -----------------------------------------------------
-- Schema nectarDB_user
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nectarDB_user` ;
-- -----------------------------------------------------
-- Schema nectarDB_products
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `nectarDB_products` ;

-- -----------------------------------------------------
-- Schema nectarDB_products
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nectarDB_products` ;
-- -----------------------------------------------------
-- Schema nectarDB_administration
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `nectarDB_administration` ;

-- -----------------------------------------------------
-- Schema nectarDB_administration
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nectarDB_administration` ;
USE `nectarDB_user` ;

-- -----------------------------------------------------
-- Table `nectarDB_user`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_user`.`user` ;

CREATE TABLE IF NOT EXISTS `nectarDB_user`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `user_telephone` VARCHAR(15) NULL DEFAULT 'null',
  UNIQUE INDEX `userID_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `userEmail_UNIQUE` (`user_email` ASC) VISIBLE,
  UNIQUE INDEX `userTelephone_UNIQUE` (`user_telephone` ASC) VISIBLE,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_user`.`userPurchasingProfileDetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_user`.`userPurchasingProfileDetails` ;

CREATE TABLE IF NOT EXISTS `nectarDB_user`.`userPurchasingProfileDetails` (
  `uppd_id` INT NOT NULL AUTO_INCREMENT,
  `uppd_ownerName` VARCHAR(45) NOT NULL,
  `uppd_expirationDate` DATE NOT NULL,
  `uppd_creditCardNumber` VARCHAR(20) NOT NULL,
  `uppd_securityCode` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`uppd_id`),
  UNIQUE INDEX `uppd_id_UNIQUE` (`uppd_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_user`.`wishListAutoBuyOperators`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_user`.`wishListAutoBuyOperators` ;

CREATE TABLE IF NOT EXISTS `nectarDB_user`.`wishListAutoBuyOperators` (
  `wlabo_operator` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`wlabo_operator`),
  UNIQUE INDEX `wlabo_condition_UNIQUE` (`wlabo_operator` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_user`.`wishListAutoBuyConditionType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_user`.`wishListAutoBuyConditionType` ;

CREATE TABLE IF NOT EXISTS `nectarDB_user`.`wishListAutoBuyConditionType` (
  `wlabct_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`wlabct_type`),
  UNIQUE INDEX `wlabct_type_UNIQUE` (`wlabct_type` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_user`.`userWishListAutoBuySettings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_user`.`userWishListAutoBuySettings` ;

CREATE TABLE IF NOT EXISTS `nectarDB_user`.`userWishListAutoBuySettings` (
  `uwlaubs_id` INT NOT NULL AUTO_INCREMENT,
  `wlabct_type` VARCHAR(45) NOT NULL,
  `wlabo_operator` VARCHAR(45) NOT NULL,
  `uwlaubs_conditionValue` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`uwlaubs_id`),
  UNIQUE INDEX `uwlaubs_id_UNIQUE` (`uwlaubs_id` ASC) VISIBLE,
  INDEX `fk_userWishListAutoBuySetting_wishListAutoBuyOperators1_idx` (`wlabo_operator` ASC) VISIBLE,
  INDEX `fk_userWishListAutoBuySetting_wishListAutoBuyConditionType1_idx` (`wlabct_type` ASC) VISIBLE,
  CONSTRAINT `fk_userWishListAutoBuySetting_wishListAutoBuyOperators1`
    FOREIGN KEY (`wlabo_operator`)
    REFERENCES `nectarDB_user`.`wishListAutoBuyOperators` (`wlabo_operator`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userWishListAutoBuySetting_wishListAutoBuyConditionType1`
    FOREIGN KEY (`wlabct_type`)
    REFERENCES `nectarDB_user`.`wishListAutoBuyConditionType` (`wlabct_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_products`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_products`.`product` ;

CREATE TABLE IF NOT EXISTS `nectarDB_products`.`product` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_siteName` VARCHAR(45) NOT NULL,
  `product_siteURL` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_user`.`userWishList`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_user`.`userWishList` ;

CREATE TABLE IF NOT EXISTS `nectarDB_user`.`userWishList` (
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `uwlaubs_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `product_id`),
  INDEX `fk_userWishList_userWishListAutoBuySettings1_idx` (`uwlaubs_id` ASC) VISIBLE,
  INDEX `fk_userWishList_product_id_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_userWishList_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `nectarDB_user`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userWishList_userWishListAutoBuySettings1`
    FOREIGN KEY (`uwlaubs_id`)
    REFERENCES `nectarDB_user`.`userWishListAutoBuySettings` (`uwlaubs_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userWishList_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectarDB_products`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_user`.`userPurchasingProfile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_user`.`userPurchasingProfile` ;

CREATE TABLE IF NOT EXISTS `nectarDB_user`.`userPurchasingProfile` (
  `user_id` INT NOT NULL,
  `uppd_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `uppd_id`),
  INDEX `fk_userPurchasingProfile_userPurchasingProfileDetails1_idx` (`uppd_id` ASC) VISIBLE,
  CONSTRAINT `fk_userPurchasingProfile_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `nectarDB_user`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userPurchasingProfile_userPurchasingProfileDetails1`
    FOREIGN KEY (`uppd_id`)
    REFERENCES `nectarDB_user`.`userPurchasingProfileDetails` (`uppd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_user`.`timestamps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_user`.`timestamps` ;

CREATE TABLE IF NOT EXISTS `nectarDB_user`.`timestamps` (
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL);

USE `nectarDB_products` ;

-- -----------------------------------------------------
-- Table `nectarDB_products`.`amazonTagsDetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_products`.`amazonTagsDetails` ;

CREATE TABLE IF NOT EXISTS `nectarDB_products`.`amazonTagsDetails` (
  `atd_id` INT NOT NULL AUTO_INCREMENT,
  `atd_tag` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`atd_id`),
  UNIQUE INDEX `atd_id_UNIQUE` (`atd_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_products`.`amazonCategoryEnum`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_products`.`amazonCategoryEnum` ;

CREATE TABLE IF NOT EXISTS `nectarDB_products`.`amazonCategoryEnum` (
  `ace_category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ace_category`),
  UNIQUE INDEX `ace_category_UNIQUE` (`ace_category` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_products`.`amazon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_products`.`amazon` ;

CREATE TABLE IF NOT EXISTS `nectarDB_products`.`amazon` (
  `product_id` INT NOT NULL,
  `amazon_siteID` VARCHAR(15) NOT NULL,
  `amazon_seller` VARCHAR(45) NULL DEFAULT 'null',
  `ace_category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE,
  UNIQUE INDEX `amazon_siteID_UNIQUE` (`amazon_siteID` ASC) VISIBLE,
  INDEX `fk_amazon_2_idx` (`ace_category` ASC) VISIBLE,
  CONSTRAINT `fk_amazon_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectarDB_products`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_amazon_2`
    FOREIGN KEY (`ace_category`)
    REFERENCES `nectarDB_products`.`amazonCategoryEnum` (`ace_category`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_products`.`amazonTags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_products`.`amazonTags` ;

CREATE TABLE IF NOT EXISTS `nectarDB_products`.`amazonTags` (
  `product_id` INT NOT NULL,
  `atd_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `atd_id`),
  INDEX `fk_amazonTags_2_idx` (`atd_id` ASC) VISIBLE,
  CONSTRAINT `fk_amazonTags_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectarDB_products`.`amazon` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_amazonTags_2`
    FOREIGN KEY (`atd_id`)
    REFERENCES `nectarDB_products`.`amazonTagsDetails` (`atd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_products`.`amazonProductPriceHistoryDetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_products`.`amazonProductPriceHistoryDetails` ;

CREATE TABLE IF NOT EXISTS `nectarDB_products`.`amazonProductPriceHistoryDetails` (
  `apphd_id` INT NOT NULL AUTO_INCREMENT,
  `apphd_price` DOUBLE NOT NULL,
  `apphd_dateAndTime` DATETIME NOT NULL,
  PRIMARY KEY (`apphd_id`),
  UNIQUE INDEX `apphd_id_UNIQUE` (`apphd_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_products`.`amazonProductPriceHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_products`.`amazonProductPriceHistory` ;

CREATE TABLE IF NOT EXISTS `nectarDB_products`.`amazonProductPriceHistory` (
  `product_id` INT NOT NULL,
  `apphd_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `apphd_id`),
  INDEX `fk_amazonProductPriceHistory_2_idx` (`apphd_id` ASC) VISIBLE,
  CONSTRAINT `fk_amazonProductPriceHistory_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectarDB_products`.`amazon` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_amazonProductPriceHistory_2`
    FOREIGN KEY (`apphd_id`)
    REFERENCES `nectarDB_products`.`amazonProductPriceHistoryDetails` (`apphd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `nectarDB_administration` ;

-- -----------------------------------------------------
-- Table `nectarDB_administration`.`administration`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_administration`.`administration` ;

CREATE TABLE IF NOT EXISTS `nectarDB_administration`.`administration` (
  `admin_id` INT NOT NULL AUTO_INCREMENT,
  `admin_username` VARCHAR(45) NOT NULL,
  `admin_password` VARCHAR(45) NOT NULL,
  `admin_host` VARCHAR(45) NOT NULL,
  `admin_port` INT NOT NULL DEFAULT 3306,
  PRIMARY KEY (`admin_id`),
  UNIQUE INDEX `admin_id_UNIQUE` (`admin_id` ASC) VISIBLE,
  UNIQUE INDEX `admin_username_UNIQUE` (`admin_username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = armscii8;


-- -----------------------------------------------------
-- Table `nectarDB_administration`.`amazonProductAvailabilityHistoryDetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_administration`.`amazonProductAvailabilityHistoryDetails` ;

CREATE TABLE IF NOT EXISTS `nectarDB_administration`.`amazonProductAvailabilityHistoryDetails` (
  `apahd_id` INT NOT NULL AUTO_INCREMENT,
  `apahd_productsAvailable` INT NULL,
  `apahd_dateAndTime` VARCHAR(45) NULL,
  PRIMARY KEY (`apahd_id`),
  UNIQUE INDEX `apad_id_UNIQUE` (`apahd_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectarDB_administration`.`amazonProductAvailabilityHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectarDB_administration`.`amazonProductAvailabilityHistory` ;

CREATE TABLE IF NOT EXISTS `nectarDB_administration`.`amazonProductAvailabilityHistory` (
  `product_id` INT NOT NULL,
  `apahd_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `apahd_id`),
  INDEX `fk_amazonProductAvailabilityHistory_amazonProductAvailabili_idx` (`apahd_id` ASC) VISIBLE,
  CONSTRAINT `fk_amazonProductAvailabilityHistory_amazon`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectarDB_products`.`amazon` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_amazonProductAvailabilityHistory_amazonProductAvailability1`
    FOREIGN KEY (`apahd_id`)
    REFERENCES `nectarDB_administration`.`amazonProductAvailabilityHistoryDetails` (`apahd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `nectarDB_user`.`wishListAutoBuyOperators`
-- -----------------------------------------------------
START TRANSACTION;
USE `nectarDB_user`;
INSERT INTO `nectarDB_user`.`wishListAutoBuyOperators` (`wlabo_operator`) VALUES ('>=');
INSERT INTO `nectarDB_user`.`wishListAutoBuyOperators` (`wlabo_operator`) VALUES ('>');
INSERT INTO `nectarDB_user`.`wishListAutoBuyOperators` (`wlabo_operator`) VALUES ('<=');
INSERT INTO `nectarDB_user`.`wishListAutoBuyOperators` (`wlabo_operator`) VALUES ('<');
INSERT INTO `nectarDB_user`.`wishListAutoBuyOperators` (`wlabo_operator`) VALUES ('==');
INSERT INTO `nectarDB_user`.`wishListAutoBuyOperators` (`wlabo_operator`) VALUES ('!=');

COMMIT;


-- -----------------------------------------------------
-- Data for table `nectarDB_user`.`wishListAutoBuyConditionType`
-- -----------------------------------------------------
START TRANSACTION;
USE `nectarDB_user`;
INSERT INTO `nectarDB_user`.`wishListAutoBuyConditionType` (`wlabct_type`) VALUES ('Availability');
INSERT INTO `nectarDB_user`.`wishListAutoBuyConditionType` (`wlabct_type`) VALUES ('Price');
INSERT INTO `nectarDB_user`.`wishListAutoBuyConditionType` (`wlabct_type`) VALUES ('PercentageOfSale');
INSERT INTO `nectarDB_user`.`wishListAutoBuyConditionType` (`wlabct_type`) VALUES ('Date');
INSERT INTO `nectarDB_user`.`wishListAutoBuyConditionType` (`wlabct_type`) VALUES ('Time');
INSERT INTO `nectarDB_user`.`wishListAutoBuyConditionType` (`wlabct_type`) VALUES ('DateAndTime');
INSERT INTO `nectarDB_user`.`wishListAutoBuyConditionType` (`wlabct_type`) VALUES ('Rating');

COMMIT;


-- -----------------------------------------------------
-- Data for table `nectarDB_products`.`amazonCategoryEnum`
-- -----------------------------------------------------
START TRANSACTION;
USE `nectarDB_products`;
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Appliances');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Apps & Games');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Arts, Crafts, & Sewing');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Automotive Parts & Accessories');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Baby');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Beauty & Personal Care');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Books');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('CDs & Vinyl');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Cell Phones & Accessories');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Clothing, Shoes and Jewelry');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Collectibles & Fine Art');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Computers');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Electronics');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Garden & Outdoor');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Grocery & Gourmet Food');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Handmade');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Health, Household & Baby Care');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Home & Kitchen');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Industrial & Scientific');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Kindle');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Luggage & Travel Gear');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Movies & TV');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Musical Instruments');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Office Products');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Pet Supplies');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Sports & Outdoors');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Tools & Home Improvement');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Toys & Games');
INSERT INTO `nectarDB_products`.`amazonCategoryEnum` (`ace_category`) VALUES ('Video Games');

COMMIT;


-- -----------------------------------------------------
-- Data for table `nectarDB_administration`.`administration`
-- -----------------------------------------------------
START TRANSACTION;
USE `nectarDB_administration`;
INSERT INTO `nectarDB_administration`.`administration` (`admin_id`, `admin_username`, `admin_password`, `admin_host`, `admin_port`) VALUES (1, 'admin', 'password', 'localhost', 3306);

COMMIT;

