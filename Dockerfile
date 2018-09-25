FROM mysql/mysql-server:5.6


ENV MYSQL_ROOT_PASSWORD=toor
ENV MYSQL_DATABASE=employees

COPY ./employees_employee.sql /docker-entrypoint-initdb.d/init.sql

EXPOSE 3306
