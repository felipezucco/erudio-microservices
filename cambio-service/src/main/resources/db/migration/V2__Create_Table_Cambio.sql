CREATE TABLE cambio (
  id integer PRIMARY KEY default nextval('id_sequence') ,
  from_currency varchar(3) NOT NULL,
  to_currency varchar(3) NOT NULL,
  conversion_factor numeric(65,2) NOT NULL
);
