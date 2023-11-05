#!/bin/bash

HOSTNAME="127.0.0.1"
PORT="33060"
USERNAME="root"
PASSWORD="123456"
DBNAME="datawarehouse"

CREATE_DATABASE="create database if not exists ${DBNAME};"
USE_DATABASE="use $DBNAME;"

cd ../../sql/
pwd
SQL_FILE="create_table.sql"

echo "${SQL_FILE}"
# shellcheck disable=SC2037
mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} -e "${CREATE_DATABASE}"
mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} "$DBNAME" <"${SQL_FILE}"


if [ $? -eq 0 ]; then
  echo "create table success"
else
    echo "create table failed"
fi