CREATE TABLE IF NOT EXISTS OPERATION
(id INT NOT NULL AUTO_INCREMENT, id_usuario int, monto DECFLOAT, moneda_origen VARCHAR,
moneda_destino VARCHAR, monto_cambio DECFLOAT, tipo_cambio REAL, PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS EXCHANGE
(id INT NOT NULL AUTO_INCREMENT, moneda_origen VARCHAR, moneda_destino VARCHAR,tipo_cambio REAL);

INSERT INTO EXCHANGE VALUES (DEFAULT, 'PEN', 'USD', 0.31);
INSERT INTO EXCHANGE VALUES (DEFAULT, 'USD', 'PEN', 3.72);

SELECT * FROM EXCHANGE WHERE moneda_origen = 'PEN' and moneda_destino = 'USD';

CREATE TABLE IF NOT EXISTS AUDIT
(id INT NOT NULL AUTO_INCREMENT, nombre_usuario VARCHAR, tipo_cambio REAL, fecha TIMESTAMP,
 tipo VARCHAR, moneda_origen VARCHAR, moneda_destino VARCHAR, PRIMARY KEY (id));