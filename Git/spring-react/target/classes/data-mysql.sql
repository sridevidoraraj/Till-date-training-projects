DELETE FROM client;
INSERT INTO client (id, `name`)
VALUES (1, 'Docspera');
INSERT INTO client (id, `name`)
VALUES (2, 'MRN');
INSERT INTO client (id, `name`)
VALUES (3, 'Redox');

DELETE FROM client_config;
INSERT INTO `client_config` VALUES
                          (1, 'DOCSPERA_ID_PATTERN', '(?i)entry[*]/resource/identifier[*]/type/text$'),
                          (2, 'DOCSPERA_ID_VALUE', '(?i)entry[*]/resource/identifier[*]/value$'),
                          (3, 'DOCSPERA_MR_ID_STRING', 'DocSpera MR ID'),
                          (4, 'DOCSPERA_USER_ID_STRING', 'DocSpera User ID'),
                          (5, 'MRN_PATTERN_STRING', 'Medical Record Number'),
                          (6, 'MRN_PATTERN_STRING', 'Medical Record Number'),
                          (7, 'REDOX_MR_ID_STRING', '(?i)entry[*]/resource/identifier[*]/system$'),
                          (8, 'REDOX_USER_ID_STRING', '(?i)entry[*]/resource/identifier[*]/value$');;