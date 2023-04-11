-- -----------------------------------------------------
-- Table `nectar`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`user` ;

CREATE TABLE IF NOT EXISTS `nectar`.`user` (
  `user_id` INT NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `user_telephone` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `userID_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `userEmail_UNIQUE` (`user_email` ASC) VISIBLE,
  UNIQUE INDEX `userTelephone_UNIQUE` (`user_telephone` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`userPurchasingProfile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`userPurchasingProfile` ;

CREATE TABLE IF NOT EXISTS `nectar`.`userPurchasingProfile` (
  `user_id` INT NOT NULL,
  `upp_typeOfCard` VARCHAR(45) NOT NULL,
  `upp_nameOnCard` VARCHAR(45) NOT NULL,
  `upp_dateOnCard` DATE NOT NULL,
  `upp_numberOnCard` VARCHAR(45) NOT NULL,
  `upp_securityCodeOnCard` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_userPurchasingProfile_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `nectar`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`product` ;

CREATE TABLE IF NOT EXISTS `nectar`.`product` (
  `product_id` INT NOT NULL,
  `product_siteName` VARCHAR(45) NULL,
  `product_siteURL` VARCHAR(45) NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`userWishList`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`userWishList` ;

CREATE TABLE IF NOT EXISTS `nectar`.`userWishList` (
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `uwl_AutoPurchaseID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_userWishList_product1_idx` (`product_id` ASC) VISIBLE,
  UNIQUE INDEX `uwl_AutoPurchaseID_UNIQUE` (`uwl_AutoPurchaseID` ASC) VISIBLE,
  CONSTRAINT `fk_userWishList_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `nectar`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userWishList_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectar`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`amazon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`amazon` ;

CREATE TABLE IF NOT EXISTS `nectar`.`amazon` (
  `product_id` INT NOT NULL,
  `amazon_siteID` VARCHAR(15) NOT NULL,
  `amazon_categoryEnum` VARCHAR(45) NULL,
  `amazon_seller` VARCHAR(45) NULL,
  PRIMARY KEY (`product_id`, `amazon_siteID`),
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE,
  UNIQUE INDEX `amazon_siteID_UNIQUE` (`amazon_siteID` ASC) VISIBLE,
  CONSTRAINT `fk_productAmazon_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectar`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`amazonTags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`amazonTags` ;

CREATE TABLE IF NOT EXISTS `nectar`.`amazonTags` (
  `amazon_siteID` VARCHAR(15) NOT NULL,
  `at_tag` VARCHAR(45) NULL,
  PRIMARY KEY (`amazon_siteID`),
  CONSTRAINT `fk_amazonTags_amazon1`
    FOREIGN KEY (`amazon_siteID`)
    REFERENCES `nectar`.`amazon` (`amazon_siteID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`ebay`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`ebay` ;

CREATE TABLE IF NOT EXISTS `nectar`.`ebay` (
  `product_id` INT NOT NULL,
  `ebay_siteID` VARCHAR(45) NOT NULL,
  `ebay_categoryEnum` VARCHAR(45) NULL,
  `ebay_seller` VARCHAR(45) NULL,
  PRIMARY KEY (`product_id`, `ebay_siteID`),
  INDEX `fk_ebay_product1_idx` (`product_id` ASC) VISIBLE,
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE,
  UNIQUE INDEX `ebay_siteID_UNIQUE` (`ebay_siteID` ASC) VISIBLE,
  CONSTRAINT `fk_ebay_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `nectar`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`ebayTags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`ebayTags` ;

CREATE TABLE IF NOT EXISTS `nectar`.`ebayTags` (
  `ebay_siteID` VARCHAR(45) NOT NULL,
  `et_tag` VARCHAR(45) NULL,
  PRIMARY KEY (`ebay_siteID`),
  CONSTRAINT `fk_ebayTags_ebay1`
    FOREIGN KEY (`ebay_siteID`)
    REFERENCES `nectar`.`ebay` (`ebay_siteID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`amazonProductPriceHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`amazonProductPriceHistory` ;

CREATE TABLE IF NOT EXISTS `nectar`.`amazonProductPriceHistory` (
  `amazon_siteID` VARCHAR(15) NOT NULL,
  `apph_price` DOUBLE NOT NULL,
  `amazon_amazon_siteID` DATETIME NOT NULL,
  PRIMARY KEY (`amazon_siteID`),
  CONSTRAINT `fk_amazonProductPriceHistory_amazon1`
    FOREIGN KEY (`amazon_siteID`)
    REFERENCES `nectar`.`amazon` (`amazon_siteID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`ebayProductPriceHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`ebayProductPriceHistory` ;

CREATE TABLE IF NOT EXISTS `nectar`.`ebayProductPriceHistory` (
  `ebay_siteID` VARCHAR(45) NOT NULL,
  `epph_price` DOUBLE NULL,
  `epph_date` DATETIME NULL,
  PRIMARY KEY (`ebay_siteID`),
  CONSTRAINT `fk_ebayProductPriceHistory_ebay1`
    FOREIGN KEY (`ebay_siteID`)
    REFERENCES `nectar`.`ebay` (`ebay_siteID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nectar`.`userWishListAutoBuySetting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`userWishListAutoBuySetting` ;

CREATE TABLE IF NOT EXISTS `nectar`.`userWishListAutoBuySetting` (
  `uwl_AutoPurchaseID` INT NOT NULL,
  `uwlaubs_conditionTypeEnum` VARCHAR(45) NULL,
  `uwlaubs_conditionOperatorEnum` VARCHAR(45) NULL COMMENT 'connditionOperatorEnum = {equals, less than, greater than, .... }',
  `uwlaubs_conditionValue` VARCHAR(45) NULL,
  PRIMARY KEY (`uwl_AutoPurchaseID`),
  CONSTRAINT `fk_userWishListAutoBuySetting_userWishList1`
    FOREIGN KEY (`uwl_AutoPurchaseID`)
    REFERENCES `nectar`.`userWishList` (`uwl_AutoPurchaseID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;