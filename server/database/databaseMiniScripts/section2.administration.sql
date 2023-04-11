-- -----------------------------------------------------
-- Table `nectar`.`administration`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`administration` ;

CREATE TABLE IF NOT EXISTS `nectar`.`administration` (
  `admin_id` INT NOT NULL,
  `admin_username` VARCHAR(45) NULL,
  `admin_password` VARCHAR(45) NULL,
  `admin_host` VARCHAR(45) NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE INDEX `admin_id_UNIQUE` (`admin_id` ASC) VISIBLE,
  UNIQUE INDEX `admin_username_UNIQUE` (`admin_username` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `nectar`.`administration`
-- -----------------------------------------------------
START TRANSACTION;
USE `nectar`;
INSERT INTO `nectar`.`administration` (`admin_id`, `admin_username`, `admin_password`, `admin_host`) VALUES (0, 'admin', '', NULL);
COMMIT;