DROP TABLE steamtest IF EXISTS;
CREATE TABLE steamtest (collection VARCHAR(40) NOT NULL, tipus VARCHAR(10) NOT NULL, weapon VARCHAR(20) NOT NULL, skin VARCHAR(20) NOT NULL, condition VARCHAR(15) NOT NULL, price DOUBLE, PRIMARY KEY(tipus, weapon, skin, condition));