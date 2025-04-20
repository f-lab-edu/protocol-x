CREATE DATABASE IF NOT EXISTS protocolx_users;
CREATE DATABASE IF NOT EXISTS protocolx_orders;

GRANT ALL PRIVILEGES ON protocolx_users.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON protocolx_orders.* TO 'root'@'%';

FLUSH PRIVILEGES; 