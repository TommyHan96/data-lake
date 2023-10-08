import random
import time

import requests
import pymysql
import json
import threading

# 发送HTTP请求
import http.client

import requests

base_url = "http://127.0.0.1:4523/m1/3342378-0-default/"
payload = {}
headers = {
    'User-Agent': 'Apifox/1.0.0 (https://apifox.com)'
}

mysql_host = "localhost"
mysql_user = "root"
mysql_password = "123456"
mysql_database = "datawarehouse"

url_list = ["employee/base", "base/post", "base/department", "entrant/base/employee", "entrant/employee/onboarding",
            "entrant/employee/contract"]
url_table_map = {"employee/base": "ods_yx_base_employee_base", "base/post": "ods_yx_base_post",
                 "base/department": "ods_yx_base_department", "entrant/base/employee": "ods_yx_entrant_base_employee",
                 "entrant/employee/onboarding": "ods_yx_entrant_employee_onboarding",
                 "entrant/employee/contract": "ods_yx_entrant_employee_contract"}


def random_table():
    secondary_url = random.choice(url_list)
    table = url_table_map.get(secondary_url)
    return secondary_url, table


def operate_sql(body, table):
    keys = ', '.join(body.keys())
    values = ', '.join(['%s'] * len(body))

    sql = 'INSERT INTO {table} ({keys}) VALUES ({values})'.format(table=table, keys=keys, values=values)
    return sql


def connection_and_commit(body, table):
    db = pymysql.connect(host=mysql_host, user=mysql_user, password=mysql_password, db=mysql_database)
    cursor = db.cursor()
    sql = operate_sql(body, table)
    cursor.execute(sql, tuple(body.values()))

    # 提交事务
    db.commit()
    # 关闭数据库连接
    cursor.close()
    db.close()


def post_create():
    secondary_url, table = random_table()
    concat_url = base_url + secondary_url

    response = requests.request("GET", concat_url, headers=headers, data=payload)

    # # 获取HTTP响应的body并解析为JSON
    body = json.loads(response.text)
    print(body)
    #

    # 连接MySQL数据库
    connection_and_commit(body, table)


def call():
    post_create()
    time.sleep(1)


if __name__ == '__main__':
    while 1:
        call()
