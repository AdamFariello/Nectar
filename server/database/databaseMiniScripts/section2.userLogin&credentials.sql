-- -----------------------------------------------------
-- User and Login information
-- -----------------------------------------------------
-- Credentials; Create UserName and Privliges
#Username: "user"
#Password: "1234"
#Host: Localhost
DROP USER IF EXISTS "user"; 
CREATE USER IF NOT EXISTS "user" IDENTIFIED BY "1234"; FLUSH PRIVILEGES;
GRANT SELECT, INSERT, DELETE, UPDATE ON nectar.* TO "user";
#SELECT user FROM mysql.user;
#SHOW grants FOR "user";

-- -----------------------------------------------------
-- Table `nectar`.`administration`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nectar`.`administration` ;
CREATE TABLE IF NOT EXISTS `nectar`.`administration` (
  `admin_id` INT NOT NULL,
  `admin_username` VARCHAR(45) NULL,
  `admin_password` VARCHAR(45) NULL,
  PRIMARY KEY (`admin_id`))
ENGINE = InnoDB;