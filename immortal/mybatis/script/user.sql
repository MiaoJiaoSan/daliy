create user 'user'@'%' IDENTIFIED BY 'user';
grant select, insert, update, delete ON user.* TO 'user'@'%';