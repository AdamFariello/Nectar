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
-- Table `nectar`.`userWishList`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`userWishList` ;
CREATE TABLE IF NOT EXISTS `nectar`.`userWishList` (
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `uwl_AutoPurchaseID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `uwl_AutoPurchaseID`),
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