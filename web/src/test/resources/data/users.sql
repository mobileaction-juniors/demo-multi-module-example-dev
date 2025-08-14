INSERT INTO users(id, name, username, email) VALUES (1, 'sezer1', 'sezer1', 'sezer1@sezer.com');
INSERT INTO users(id, name, username, email) VALUES (2, 'sezer2', 'sezer2', 'sezer2@sezer.com');
INSERT INTO users(id, name, username, email) VALUES (3, 'sezer3', 'sezer3', 'sezer3@sezer.com');
INSERT INTO users(id, name, username, email) VALUES (4, 'sezer4', 'sezer4', 'sezer4@sezer.com');

ALTER TABLE users ALTER COLUMN id RESTART WITH 5;