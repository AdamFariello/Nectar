-- -----------------------------------------------------
-- Table `nectar`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`product` ;
CREATE TABLE IF NOT EXISTS `nectar`.`product` (
  `product_id` INT NOT NULL,
  `product_siteName` VARCHAR(45) NULL,
  `product_siteURL` VARCHAR(45) NULL,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `nectar`.`amazon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`amazon` ;
CREATE TABLE IF NOT EXISTS `nectar`.`amazon` (
  `product_id` INT NOT NULL,
  `amazon_siteID` INT NOT NULL,
  `amazon_categoryEnum` VARCHAR(45) NULL,
  `amazon_seller` VARCHAR(45) NULL,
  PRIMARY KEY (`product_id`, `amazon_siteID`),
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
  `amazon_siteID` VARCHAR(45) NOT NULL,
  `at_tag` VARCHAR(45) NULL,
  PRIMARY KEY (`amazon_siteID`),
  CONSTRAINT `fk_amazonTags_amazon1`
	FOREIGN KEY (`amazon_siteID`)
	REFERENCES `nectar`.`amazon` (`amazon_siteID`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `nectar`.`amazonProductPriceHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`amazonProductPriceHistory` ;
CREATE TABLE IF NOT EXISTS `nectar`.`amazonProductPriceHistory` (
  `amazon_siteID` VARCHAR(45) NOT NULL,
  `apph_price` DOUBLE NOT NULL,
  `apph_date` DATETIME NOT NULL,
  PRIMARY KEY (`amazon_siteID`),
  CONSTRAINT `fk_amazonProductPriceHistory_amazon1`
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