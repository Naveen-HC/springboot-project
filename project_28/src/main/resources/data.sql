INSERT INTO `holidays` (`day`, `reason`, `type`, `created_by`, `created_at`)
VALUES ('Jan 1', 'New Years Day', 'FESTIVAL', 'DBA', CURDATE());

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_by`, `created_at`)
VALUES ('Oct 31', 'Halloween', 'FESTIVAL', 'DBA', CURDATE());

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_by`, `created_at`)
VALUES ('Nov 24', 'Thanksgiving Day', 'FESTIVAL', 'DBA', CURDATE());

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_by`, `created_at`)
VALUES ('Dec 25', 'Christmas', 'FESTIVAL', 'DBA', CURDATE());

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_by`, `created_at`)
VALUES ('Jan 17','Martin Luther King Jr. Day', 'FEDERAL', 'DBA', CURDATE());

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_by`, `created_at`)
VALUES ('Jul 4', 'Independence Day', 'FEDERAL', 'DBA', CURDATE());

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_by`, `created_at`)
VALUES ('sep 5', 'Labour Day', 'FEDERAL', 'DBA', CURDATE());

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_by`, `created_at`)
VALUES ('Nov 11', 'Veterans Day', 'FEDERAL', 'DBA', CURDATE());


INSERT INTO `roles`(`role_name`,`created_by`, `created_at`)
VALUES('admin', 'DBA', CURDATE());

INSERT INTO `roles`(`role_name`,`created_by`, `created_at`)
VALUES('school', 'DBA', CURDATE());