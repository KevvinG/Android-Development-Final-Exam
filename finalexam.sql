DROP DATABASE IF EXISTS games_data;
CREATE DATABASE games_data;
USE games_data;
DELIMITER //

CREATE PROCEDURE drop_user_if_exists(IN user_name VARCHAR(20))
BEGIN
    DECLARE userCount BIGINT DEFAULT 0 ;
    SELECT COUNT(*) INTO userCount FROM mysql.user
    WHERE User = user_name and  Host = 'localhost';
    IF userCount > 0 THEN
		SET @SQL_STATEMENT = CONCAT('DROP USER ',user_name,'@localhost');
		PREPARE PREP_S FROM @SQL_STATEMENT;
        EXECUTE PREP_S;
        DEALLOCATE PREPARE PREP_S;
    END IF;
END; //
DELIMITER ;

CALL drop_user_if_exists('myGames_user') ;        
CREATE USER myGames_user@localhost IDENTIFIED BY 'sesame';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP
ON games_data.*
TO myGames_user@localhost;