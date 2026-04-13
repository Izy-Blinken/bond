package bond.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBSetup {

    //  Change these if your MySQL credentials are different 
    private static final String HOST     = "localhost";
    private static final String PORT     = "3306";
    private static final String DB_NAME  = "bonddb";
    private static final String USER     = "root";
    private static final String PASSWORD = "root";

    private static final String ROOT_URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/?useSSL=false&allowPublicKeyRetrieval=true";

    private static final String DB_URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME
            + "?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf8";

    public static void main(String[] args) {

        try (Connection con  = DriverManager.getConnection(ROOT_URL, USER, PASSWORD);
             Statement  stmt = con.createStatement()) {

            stmt.executeUpdate(
                "CREATE DATABASE IF NOT EXISTS `" + DB_NAME + "` " +
                "CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci"
            );
            System.out.println("[OK] Database '" + DB_NAME + "' ready.");

        } catch (SQLException e) {
            System.err.println("[ERROR] Could not create database: " + e.getMessage());
            return;
        }

        try (Connection con  = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement  stmt = con.createStatement()) {

            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");

            //  1. academic_year 
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `academic_year` (" +
                "  `academic_year_id` int NOT NULL AUTO_INCREMENT," +
                "  `year_label`       varchar(20)  COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `start_date`       date NOT NULL," +
                "  `end_date`         date NOT NULL," +
                "  `is_current`       tinyint(1)   NOT NULL DEFAULT '0'," +
                "  PRIMARY KEY (`academic_year_id`)," +
                "  UNIQUE KEY `uq_year_label` (`year_label`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci"
            );
            stmt.executeUpdate(
                "INSERT IGNORE INTO `academic_year` (academic_year_id, year_label, start_date, end_date, is_current) VALUES " +
                "(1, '2024-2025', '2024-06-01', '2025-05-31', 1)"
            );
            
            //  2. oso_admin
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `oso_admin` (" +
                "  `oso_admin_id`  int NOT NULL AUTO_INCREMENT," +
                "  `username`      varchar(50)  COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `password_hash` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `email`         varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  PRIMARY KEY (`oso_admin_id`)," +
                "  UNIQUE KEY `uq_oso_username` (`username`)," +
                "  UNIQUE KEY `uq_oso_email`    (`email`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci"
            );
            stmt.executeUpdate(
                "INSERT IGNORE INTO `oso_admin` VALUES" +
                "  (1,'oso','4919f53a959de9fbdea48ce1fcbb2e0fd0a6de0c20334df14c3431ec2373156d','admin@bond.com')"
            );

            //  3. registration_form 
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `registration_form` (" +
                "  `form_id`                 int NOT NULL AUTO_INCREMENT," +
                "  `proposed_org_name`       varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `proposed_classification` enum('Academic','Civic & Cultural','Religious'," +
                "       'Media & Publications','Sports & Recreation') COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `description`       text    COLLATE utf8mb4_unicode_ci," +
                "  `contact_email`     varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `review_status`     enum('Pending','Contacted','Approved','Rejected') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Pending'," +
                "  `submitted_at`      datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "  `reviewed_by`       int DEFAULT NULL," +
                "  `reviewed_at`       datetime DEFAULT NULL," +
                "  `mission`           text COLLATE utf8mb4_unicode_ci," +
                "  `vision`            text COLLATE utf8mb4_unicode_ci," +
                "  `objectives`        text COLLATE utf8mb4_unicode_ci," +
                "  `target_members`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  `proposed_officers` text COLLATE utf8mb4_unicode_ci," +
                "  `adviser`           varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  `appointed_by`      varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  PRIMARY KEY (`form_id`)," +
                "  KEY `fk_regform_oso` (`reviewed_by`)," +
                "  CONSTRAINT `fk_regform_oso` FOREIGN KEY (`reviewed_by`) " +
                "    REFERENCES `oso_admin` (`oso_admin_id`) ON DELETE SET NULL ON UPDATE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci"
            );

            //  4. organization
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `organization` (" +
                "  `org_id`           int NOT NULL AUTO_INCREMENT," +
                "  `form_id`          int DEFAULT NULL," +
                "  `org_name`         varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `description`      text COLLATE utf8mb4_unicode_ci," +
                "  `vision`           text COLLATE utf8mb4_unicode_ci," +
                "  `mission`          text COLLATE utf8mb4_unicode_ci," +
                "  `classification`   enum('Academic','Civic & Cultural','Religious'," +
                "       'Media & Publications','Sports & Recreation') COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `established_date` date DEFAULT NULL," +
                "  `logo_path`        varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  `status`           enum('Active','Inactive') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Active'," +
                "  `created_by`       int NOT NULL," +
                "  `created_at`       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "  `updated_at`       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                "  `is_featured`      tinyint(1) DEFAULT '0'," +
                "  PRIMARY KEY (`org_id`)," +
                "  UNIQUE KEY `uq_org_name` (`org_name`)," +
                "  KEY `fk_org_form` (`form_id`)," +
                "  KEY `fk_org_oso`  (`created_by`)," +
                "  CONSTRAINT `fk_org_form` FOREIGN KEY (`form_id`) " +
                "    REFERENCES `registration_form` (`form_id`) ON DELETE SET NULL ON UPDATE CASCADE," +
                "  CONSTRAINT `fk_org_oso`  FOREIGN KEY (`created_by`) " +
                "    REFERENCES `oso_admin` (`oso_admin_id`) ON DELETE RESTRICT ON UPDATE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci"
            );

            //  5. adviser 
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `adviser` (" +
                "  `adviser_id`       int NOT NULL AUTO_INCREMENT," +
                "  `org_id`           int NOT NULL," +
                "  `academic_year_id` int NOT NULL," +
                "  `full_name`        varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `email`            varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  `contact_number`   varchar(20)  COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  PRIMARY KEY (`adviser_id`)," +
                "  KEY `fk_adviser_org`  (`org_id`)," +
                "  KEY `fk_adviser_acad` (`academic_year_id`)," +
                "  CONSTRAINT `fk_adviser_acad` FOREIGN KEY (`academic_year_id`) " +
                "    REFERENCES `academic_year` (`academic_year_id`) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "  CONSTRAINT `fk_adviser_org`  FOREIGN KEY (`org_id`) " +
                "    REFERENCES `organization` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci"
            );

            //  6. officer 
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `officer` (" +
                "  `officer_id`       int NOT NULL AUTO_INCREMENT," +
                "  `org_id`           int NOT NULL," +
                "  `academic_year_id` int NOT NULL," +
                "  `full_name`        varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `position`         varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `email`            varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  `contact_number`   varchar(20)  COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  `photo_path`       varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  PRIMARY KEY (`officer_id`)," +
                "  KEY `fk_officer_org`  (`org_id`)," +
                "  KEY `fk_officer_acad` (`academic_year_id`)," +
                "  CONSTRAINT `fk_officer_acad` FOREIGN KEY (`academic_year_id`) " +
                "    REFERENCES `academic_year` (`academic_year_id`) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "  CONSTRAINT `fk_officer_org`  FOREIGN KEY (`org_id`) " +
                "    REFERENCES `organization` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci"
            );

            //  7. org_admin 
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `org_admin` (" +
                "  `org_admin_id`  int NOT NULL AUTO_INCREMENT," +
                "  `org_id`        int NOT NULL," +
                "  `officer_id`    int NOT NULL," +
                "  `created_by`    int NOT NULL," +
                "  `username`      varchar(50)  COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `display_name`  varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  `password_hash` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `email`         varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `is_active`     tinyint(1) NOT NULL DEFAULT '1'," +
                "  `created_at`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "  PRIMARY KEY (`org_admin_id`)," +
                "  UNIQUE KEY `uq_org_admin_org`     (`org_id`)," +
                "  UNIQUE KEY `uq_org_admin_user`    (`username`)," +
                "  UNIQUE KEY `uq_org_admin_email`   (`email`)," +
                "  UNIQUE KEY `uq_org_admin_officer` (`officer_id`)," +
                "  KEY `fk_orgadmin_oso` (`created_by`)," +
                "  CONSTRAINT `fk_orgadmin_officer` FOREIGN KEY (`officer_id`) " +
                "    REFERENCES `officer` (`officer_id`) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "  CONSTRAINT `fk_orgadmin_org`     FOREIGN KEY (`org_id`) " +
                "    REFERENCES `organization` (`org_id`) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "  CONSTRAINT `fk_orgadmin_oso`     FOREIGN KEY (`created_by`) " +
                "    REFERENCES `oso_admin` (`oso_admin_id`) ON DELETE RESTRICT ON UPDATE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci"
            );

            //  8. announcement 
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `announcement` (" +
                "  `announcement_id`  int NOT NULL AUTO_INCREMENT," +
                "  `org_id`           int NOT NULL," +
                "  `academic_year_id` int NOT NULL," +
                "  `posted_by`        int NOT NULL," +
                "  `title`            varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `content`          text COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `status`           enum('Active','Archived') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Active'," +
                "  `created_at`       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "  `updated_at`       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                "  PRIMARY KEY (`announcement_id`)," +
                "  KEY `fk_ann_org`   (`org_id`)," +
                "  KEY `fk_ann_acad`  (`academic_year_id`)," +
                "  KEY `fk_ann_admin` (`posted_by`)," +
                "  CONSTRAINT `fk_ann_acad`  FOREIGN KEY (`academic_year_id`) " +
                "    REFERENCES `academic_year` (`academic_year_id`) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "  CONSTRAINT `fk_ann_admin` FOREIGN KEY (`posted_by`) " +
                "    REFERENCES `org_admin` (`org_admin_id`) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "  CONSTRAINT `fk_ann_org`   FOREIGN KEY (`org_id`) " +
                "    REFERENCES `organization` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci"
            );

            

            //  9. event 
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `event` (" +
                "  `event_id`         int NOT NULL AUTO_INCREMENT," +
                "  `org_id`           int NOT NULL," +
                "  `academic_year_id` int NOT NULL," +
                "  `posted_by`        int NOT NULL," +
                "  `title`            varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `description`      text COLLATE utf8mb4_unicode_ci," +
                "  `event_date`       date NOT NULL," +
                "  `venue`            varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  `status`           enum('Upcoming','Ongoing','Completed','Cancelled') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Upcoming'," +
                "  `created_at`       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "  `updated_at`       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                "  PRIMARY KEY (`event_id`)," +
                "  KEY `fk_event_org`   (`org_id`)," +
                "  KEY `fk_event_acad`  (`academic_year_id`)," +
                "  KEY `fk_event_admin` (`posted_by`)," +
                "  CONSTRAINT `fk_event_acad`  FOREIGN KEY (`academic_year_id`) " +
                "    REFERENCES `academic_year` (`academic_year_id`) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "  CONSTRAINT `fk_event_admin` FOREIGN KEY (`posted_by`) " +
                "    REFERENCES `org_admin` (`org_admin_id`) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "  CONSTRAINT `fk_event_org`   FOREIGN KEY (`org_id`) " +
                "    REFERENCES `organization` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci"
            );

            //  10. members 
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `members` (" +
                "  `member_id`   int NOT NULL AUTO_INCREMENT," +
                "  `org_id`      int NOT NULL," +
                "  `student_id`  varchar(50)  COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `full_name`   varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL," +
                "  `role`        varchar(50)  COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  `course`      varchar(50)  COLLATE utf8mb4_unicode_ci DEFAULT NULL," +
                "  `joined_date` date DEFAULT NULL," +
                "  PRIMARY KEY (`member_id`)," +
                "  KEY `org_id` (`org_id`)," +
                "  CONSTRAINT `members_ibfk_1` FOREIGN KEY (`org_id`) " +
                "    REFERENCES `organization` (`org_id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci"
            );

            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");

            System.out.println("\n Setup complete! \n");
            System.out.println("    Default OSO login → username: oso | password: oso");

        } catch (SQLException e) {
            System.err.println("[ERROR] " + e.getMessage());
            e.printStackTrace();
        }
    }
}