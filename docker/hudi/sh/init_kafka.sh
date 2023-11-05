#!/bin/bash
# Kafka容器名称
KAFKA_CONTAINER_NAME="hudi-kafka-1"
# 在Docker容器内部执行创建Kafka主题的命令
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_base_employee_base" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_base_post" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_base_department" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_entrant_base_employee" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_entrant_employee_onboarding" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_entrant_employee_contract" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_entrant_employee_account" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_entrant_employee_org" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_probation_base_employee" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_probation_stage_evaluate" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_probation_defense_arrange" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_attend_oa_attend" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_attend_emp_daily" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it "$KAFKA_CONTAINER_NAME" kafka-topics.sh --create --topic "ods_promote_config_user" --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092

# 检查命令的退出状态
if [ $? -eq 0 ]; then
  echo "Kafka topic 在容器 $KAFKA_CONTAINER_NAME 内创建成功"
else
  echo "Kafka topic 在容器 $KAFKA_CONTAINER_NAME 内创建失败"
fi