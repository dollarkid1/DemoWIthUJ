 create database if not exists demo_db;

 create user if not exists 'user'@'localhost' identified by 'password';
 grant all privileges on demo_db.* to 'user'@'localhost';
 flush privileges;
