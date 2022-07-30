CREATE TABLE book (
  id integer PRIMARY KEY default nextval('id_sequence') ,
  author varchar(100),
  launch_date timestamp(6) NOT NULL,
  price decimal(65,2) NOT NULL,
  title varchar(100)
);
