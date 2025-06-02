скрипты sql

CREATE TABLE stations (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    line_id INT NOT NULL,
    x INT NOT NULL,
    y INT NOT NULL
);

CREATE TABLE connections (
    station1_id INT NOT NULL REFERENCES stations(id),
    station2_id INT NOT NULL REFERENCES stations(id),
    travel_time INT NOT NULL,
    isTransfer INT NOT NULL  -- 0 = обычное соединение, 1 = пересадка
);

INSERT INTO stations (name, line_id, x, y) VALUES
('K1', 1, 200, 100),
('K2', 1, 250, 150),
('K3', 1, 250, 250),
('K4', 1, 200, 300),
('K5', 1, 100, 300),
('K6', 1,  50, 250),
('K7', 1,  50, 150),
('K8', 1, 100, 100);


INSERT INTO stations (name, line_id, x, y) VALUES
('L2_1', 2, 150,  20),
('L2_2', 2, 150,  60),
('L2_3', 2, 150, 100),
('L2_4', 2, 150, 150), -- Пересадка (K1)
('L2_5', 2, 150, 200),
('L2_6', 2, 150, 250), -- Пересадка (K3)
('L2_7', 2, 150, 290),
('L2_8', 2, 150, 330);


INSERT INTO stations (name, line_id, x, y) VALUES
('L3_1', 3,  20, 200),
('L3_2', 3,  60, 200),
('L3_3', 3, 100, 200), -- Пересадка (K8)
('L3_4', 3, 150, 200),
('L3_5', 3, 200, 200), -- Пересадка (K2)
('L3_6', 3, 250, 200),
('L3_7', 3, 290, 200),
('L3_8', 3, 330, 200);


INSERT INTO stations (name, line_id, x, y) VALUES
('L4_1', 4,  60,  60),
('L4_2', 4, 100, 100),
('L4_3', 4, 150, 150), -- Пересадка (K1, L2_4)
('L4_4', 4, 200, 200), -- Пересадка (K2, L3_5)
('L4_5', 4, 250, 250), -- Пересадка (K3, L2_6)
('L4_6', 4, 290, 290),
('L4_7', 4, 330, 330);


INSERT INTO stations (name, line_id, x, y) VALUES
('L5_1', 5, 330,  60),
('L5_2', 5, 290, 100),
('L5_3', 5, 250, 150), -- Пересадка (K2)
('L5_4', 5, 200, 200), -- Пересадка (K2, L3_5, L4_4)
('L5_5', 5, 150, 250), -- Пересадка (K3, L2_6, L4_5)
('L5_6', 5, 100, 290),
('L5_7', 5,  60, 330);

-- Кольцевая
INSERT INTO connections VALUES
(1,2,3,0),(2,3,3,0),(3,4,3,0),(4,5,3,0),
(5,6,3,0),(6,7,3,0),(7,8,3,0),(8,1,3,0);

-- Линия 2
INSERT INTO connections VALUES
(9,10,2,0),(10,11,2,0),(11,12,2,0),(12,13,2,0),
(13,14,2,0),(14,15,2,0),(15,16,2,0);

-- Линия 3
INSERT INTO connections VALUES
(17,18,2,0),(18,19,2,0),(19,20,2,0),(20,21,2,0),
(21,22,2,0),(22,23,2,0),(23,24,2,0);

-- Линия 4
INSERT INTO connections VALUES
(25,26,2,0),(26,27,2,0),(27,28,2,0),
(28,29,2,0),(29,30,2,0),(30,31,2,0);

-- Линия 5
INSERT INTO connections VALUES
(32,33,2,0),(33,34,2,0),(34,35,2,0),
(35,36,2,0),(36,37,2,0),(37,38,2,0);

INSERT INTO connections VALUES
(1,12,1,1),(1,27,1,1),(12,27,1,1);


INSERT INTO connections VALUES
(2,21,1,1),(2,28,1,1),(2,34,1,1),
(21,28,1,1),(21,34,1,1),(28,34,1,1);


INSERT INTO connections VALUES
(3,14,1,1),(3,29,1,1),(3,35,1,1),
(14,29,1,1),(14,35,1,1),(29,35,1,1);


INSERT INTO connections VALUES
(8,19,1,1);


INSERT INTO connections VALUES
(6,36,1,1);

