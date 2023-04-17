-- MySQL Script generated by MySQL Workbench
-- Mon 17 Apr 2023 08:23:37 AM EDT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema nectar
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `nectar` ;

-- -----------------------------------------------------
-- Schema nectar
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nectar` ;
USE `nectar` ;

-- -----------------------------------------------------
-- Table `nectar`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`user` ;

CREATE TABLE IF NOT EXISTS `nectar`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `user_telephone` VARCHAR(45) NULL DEFAULT 'null',
  UNIQUE INDEX `userID_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `userEmail_UNIQUE` (`user_email` ASC) VISIBLE,
  UNIQUE INDEX `userTelephone_UNIQUE` (`user_telephone` ASC) VISIBLE,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`userPurchasingProfileDetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`userPurchasingProfileDetails` ;

CREATE TABLE IF NOT EXISTS `nectar`.`userPurchasingProfileDetails` (
  `uppd_id` INT NOT NULL AUTO_INCREMENT,
  `uppd_nameOnCard` VARCHAR(45) NOT NULL,
  `uppd_dateOnCard` DATE NOT NULL,
  `uppd_numberOnCard` VARCHAR(45) NOT NULL,
  `uppd_securityCodeOnCard` VARCHAR(45) NOT NULL,
  `uppd_typeOfCard` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`uppd_id`),
  UNIQUE INDEX `uppd_id_UNIQUE` (`uppd_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`product` ;

CREATE TABLE IF NOT EXISTS `nectar`.`product` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_siteName` VARCHAR(45) NOT NULL,
  `product_siteURL` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`wishListAutoBuyOperators`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`wishListAutoBuyOperators` ;

CREATE TABLE IF NOT EXISTS `nectar`.`wishListAutoBuyOperators` (
  `wlabo_operator` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`wlabo_operator`),
  UNIQUE INDEX `wlabo_condition_UNIQUE` (`wlabo_operator` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`wishListAutoBuyConditionType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`wishListAutoBuyConditionType` ;

CREATE TABLE IF NOT EXISTS `nectar`.`wishListAutoBuyConditionType` (
  `wlabct_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`wlabct_type`),
  UNIQUE INDEX `wlabct_type_UNIQUE` (`wlabct_type` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`userWishListAutoBuySettings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`userWishListAutoBuySettings` ;

CREATE TABLE IF NOT EXISTS `nectar`.`userWishListAutoBuySettings` (
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
    REFERENCES `nectar`.`wishListAutoBuyOperators` (`wlabo_operator`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userWishListAutoBuySetting_wishListAutoBuyConditionType1`
    FOREIGN KEY (`wlabct_type`)
    REFERENCES `nectar`.`wishListAutoBuyConditionType` (`wlabct_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`userWishList`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`userWishList` ;

CREATE TABLE IF NOT EXISTS `nectar`.`userWishList` (
  `user_id` INT NOT NULL,
  `product_id` INT NULL DEFAULT NULL,
  `uwlaubs_id` INT NULL DEFAULT NULL,
  INDEX `fk_userWishList_product1_idx` (`product_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`),
  INDEX `fk_userWishList_userWishListAutoBuySettings1_idx` (`uwlaubs_id` ASC) VISIBLE,
  CONSTRAINT `fk_userWishList_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `nectar`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userWishList_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectar`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userWishList_userWishListAutoBuySettings1`
    FOREIGN KEY (`uwlaubs_id`)
    REFERENCES `nectar`.`userWishListAutoBuySettings` (`uwlaubs_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`amazonCategoryEnum`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`amazonCategoryEnum` ;

CREATE TABLE IF NOT EXISTS `nectar`.`amazonCategoryEnum` (
  `ace_category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ace_category`),
  UNIQUE INDEX `ace_category_UNIQUE` (`ace_category` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`amazon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`amazon` ;

CREATE TABLE IF NOT EXISTS `nectar`.`amazon` (
  `product_id` INT NOT NULL,
  `amazon_siteID` VARCHAR(15) NOT NULL,
  `amazon_seller` VARCHAR(45) NULL DEFAULT 'null',
  `ace_category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE,
  UNIQUE INDEX `amazon_siteID_UNIQUE` (`amazon_siteID` ASC) VISIBLE,
  INDEX `fk_amazon_amazonCategoryEnum1_idx` (`ace_category` ASC) VISIBLE,
  CONSTRAINT `fk_productAmazon_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectar`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_amazon_amazonCategoryEnum1`
    FOREIGN KEY (`ace_category`)
    REFERENCES `nectar`.`amazonCategoryEnum` (`ace_category`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`amazonTagsDetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`amazonTagsDetails` ;

CREATE TABLE IF NOT EXISTS `nectar`.`amazonTagsDetails` (
  `atd_id` INT NOT NULL AUTO_INCREMENT,
  `atd_tag` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`atd_id`),
  UNIQUE INDEX `atd_id_UNIQUE` (`atd_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`ebayCategoryEnum`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`ebayCategoryEnum` ;

CREATE TABLE IF NOT EXISTS `nectar`.`ebayCategoryEnum` (
  `ece_category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ece_category`),
  UNIQUE INDEX `ece_category_UNIQUE` (`ece_category` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`ebay`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`ebay` ;

CREATE TABLE IF NOT EXISTS `nectar`.`ebay` (
  `product_id` INT NOT NULL,
  `ebay_siteID` VARCHAR(45) NOT NULL,
  `ebay_seller` VARCHAR(45) NOT NULL,
  `ece_category` VARCHAR(45) NOT NULL,
  INDEX `fk_ebay_product1_idx` (`product_id` ASC) VISIBLE,
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE,
  UNIQUE INDEX `ebay_siteID_UNIQUE` (`ebay_siteID` ASC) VISIBLE,
  PRIMARY KEY (`product_id`),
  INDEX `fk_ebay_ebayCategoryEnum1_idx` (`ece_category` ASC) VISIBLE,
  CONSTRAINT `fk_ebay_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectar`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ebay_ebayCategoryEnum1`
    FOREIGN KEY (`ece_category`)
    REFERENCES `nectar`.`ebayCategoryEnum` (`ece_category`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`ebayTagsDescription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`ebayTagsDescription` ;

CREATE TABLE IF NOT EXISTS `nectar`.`ebayTagsDescription` (
  `etd_id` INT NOT NULL AUTO_INCREMENT,
  `et_tag` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`etd_id`),
  UNIQUE INDEX `etd_id_UNIQUE` (`etd_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`amazonProductPriceHistoryDetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`amazonProductPriceHistoryDetails` ;

CREATE TABLE IF NOT EXISTS `nectar`.`amazonProductPriceHistoryDetails` (
  `apphd_id` INT NOT NULL AUTO_INCREMENT,
  `apphd_price` DOUBLE NOT NULL,
  `apphd_date` DATETIME NOT NULL,
  PRIMARY KEY (`apphd_id`),
  UNIQUE INDEX `apphd_id_UNIQUE` (`apphd_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`ebayProductPriceHistoryDescription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`ebayProductPriceHistoryDescription` ;

CREATE TABLE IF NOT EXISTS `nectar`.`ebayProductPriceHistoryDescription` (
  `epphd_id` INT NOT NULL AUTO_INCREMENT,
  `epphd_price` DOUBLE NOT NULL,
  `epphd_date` DATETIME NOT NULL,
  PRIMARY KEY (`epphd_id`),
  UNIQUE INDEX `epphd_id_UNIQUE` (`epphd_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`administration`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`administration` ;

CREATE TABLE IF NOT EXISTS `nectar`.`administration` (
  `admin_id` INT NOT NULL AUTO_INCREMENT,
  `admin_username` VARCHAR(45) NOT NULL,
  `admin_password` VARCHAR(45) NOT NULL,
  `admin_host` VARCHAR(45) NOT NULL,
  `admin_port` INT NOT NULL DEFAULT 3306,
  `admin_url` VARCHAR(254) NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE INDEX `admin_id_UNIQUE` (`admin_id` ASC) VISIBLE,
  UNIQUE INDEX `admin_username_UNIQUE` (`admin_username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`userPurchasingProfile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`userPurchasingProfile` ;

CREATE TABLE IF NOT EXISTS `nectar`.`userPurchasingProfile` (
  `user_id` INT NOT NULL,
  `uppd_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `uppd_id`),
  INDEX `fk_userPurchasingProfile_userPurchasingProfileDetails1_idx` (`uppd_id` ASC) VISIBLE,
  CONSTRAINT `fk_userPurchasingProfile_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `nectar`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userPurchasingProfile_userPurchasingProfileDetails1`
    FOREIGN KEY (`uppd_id`)
    REFERENCES `nectar`.`userPurchasingProfileDetails` (`uppd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`amazonTags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`amazonTags` ;

CREATE TABLE IF NOT EXISTS `nectar`.`amazonTags` (
  `product_id` INT NOT NULL,
  `atd_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `atd_id`),
  INDEX `fk_amazonTags_amazonTagsDetails1_idx` (`atd_id` ASC) VISIBLE,
  INDEX `fk_amazonTags_amazon1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_amazonTags_amazonTagsDetails1`
    FOREIGN KEY (`atd_id`)
    REFERENCES `nectar`.`amazonTagsDetails` (`atd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_amazonTags_amazon1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectar`.`amazon` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`amazonProductPriceHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`amazonProductPriceHistory` ;

CREATE TABLE IF NOT EXISTS `nectar`.`amazonProductPriceHistory` (
  `product_id` INT NOT NULL,
  `apphd_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `apphd_id`),
  INDEX `fk_amazonProductPriceHistory_amazon1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_amazonProductPriceHistory_amazonProductPriceHistoryDetails1`
    FOREIGN KEY (`apphd_id`)
    REFERENCES `nectar`.`amazonProductPriceHistoryDetails` (`apphd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_amazonProductPriceHistory_amazon1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectar`.`amazon` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`ebayTags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`ebayTags` ;

CREATE TABLE IF NOT EXISTS `nectar`.`ebayTags` (
  `ebay_product_id` INT NOT NULL,
  `ebayTagsDescription_etd_id` INT NOT NULL,
  PRIMARY KEY (`ebay_product_id`, `ebayTagsDescription_etd_id`),
  INDEX `fk_ebayTags_ebayTagsDescription1_idx` (`ebayTagsDescription_etd_id` ASC) VISIBLE,
  CONSTRAINT `fk_ebayTags_ebay1`
    FOREIGN KEY (`ebay_product_id`)
    REFERENCES `nectar`.`ebay` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ebayTags_ebayTagsDescription1`
    FOREIGN KEY (`ebayTagsDescription_etd_id`)
    REFERENCES `nectar`.`ebayTagsDescription` (`etd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`ebayProductPriceHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`ebayProductPriceHistory` ;

CREATE TABLE IF NOT EXISTS `nectar`.`ebayProductPriceHistory` (
  `product_id` INT NOT NULL,
  `epphd_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `epphd_id`),
  INDEX `fk_ebayProductPriceHistory_ebayProductPriceHistoryDescripti_idx` (`epphd_id` ASC) VISIBLE,
  CONSTRAINT `fk_ebayProductPriceHistory_ebay1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectar`.`ebay` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ebayProductPriceHistory_ebayProductPriceHistoryDescription1`
    FOREIGN KEY (`epphd_id`)
    REFERENCES `nectar`.`ebayProductPriceHistoryDescription` (`epphd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `nectar`.`administration`
-- -----------------------------------------------------
START TRANSACTION;
USE `nectar`;
INSERT INTO `nectar`.`administration` (`admin_id`, `admin_username`, `admin_password`, `admin_host`, `admin_port`, `admin_url`) VALUES (0, 'admin', 'password', 'localhost', 3306, '\"jdbc:mysql://localhost:3306/nectar?verifyServerCertificate=false\"');

COMMIT;

