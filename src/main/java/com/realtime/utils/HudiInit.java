package com.realtime.utils;

import com.mysql.jdbc.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.flink.table.api.Schema;
import org.apache.hudi.util.HoodiePipeline;
import java.util.*;
import java.util.stream.Collectors;

public class HudiInit {
    private final String table;
    private final Map<String, String> fieldsWithType;
    private final List<String> primaryKeys;
    private final List<String> partitions;
    private final Map<String, String> options;
    private final Schema schema;

    public HudiInit(
            String database,
            String table,
            Map<String, String> fieldsWithType,
            Schema schema,
            List<String> primaryKeys,
            List<String> partitions,
            Map<String, String> options) {
        if (StringUtils.isNullOrEmpty(database)) {
            this.table = table;
        } else {
            this.table = database + "." + table;
        }
        this.schema = schema;
        this.fieldsWithType = fieldsWithType;
        this.primaryKeys = primaryKeys;
        this.partitions = partitions;
        this.options = options;
    }

    public HudiInit(
            String table,
            Schema schema,
            List<String> primaryKeys,
            List<String> partitions,
            Map<String, String> options) {
        this(null, table, Collections.emptyMap(), schema, primaryKeys, partitions, options);
    }

    public HudiInit(
            String table,
            Map<String, String> fieldsWithType,
            List<String> primaryKeys,
            Map<String, String> options) {
        this(null, table, fieldsWithType, null, primaryKeys, Collections.emptyList(), options);
    }


    public HoodiePipeline.Builder getBuilder() {
        HoodiePipeline.Builder builder = HoodiePipeline.builder(table);
        if (fieldsWithType.isEmpty()) {
            builder.schema(schema);
        } else {
            fieldsWithType.forEach((k, v) -> builder.column("`" + k + "` " + v));
        }
        builder.pk(primaryKeys.toArray(new String[0]));
        if (!CollectionUtils.isEmpty(partitions)) {
            builder.partition(partitions.toArray(new String[0]));
        }
        builder.options(options);
        return builder;
    }


    public String getCreateHoodieTableDDL() {
        StringBuilder builder = new StringBuilder();
        builder.append("create table ")
                .append(table)
                .append("(\n");

        if (!fieldsWithType.isEmpty()) {
            for (String field : map2List(fieldsWithType)) {
                builder.append("  ")
                        .append(field)
                        .append(",\n");
            }
        } else if (!schema.getColumns().isEmpty()) {
            for (Schema.UnresolvedColumn column : schema.getColumns()) {
                builder.append("  ")
                        .append(column.toString())
                        .append("(\n");
            }
        }

        builder.append("  PRIMARY KEY(")
                .append(primaryKeys)
                .append(") NOT ENFORCED\n")
                .append(")\n");
        if (!partitions.isEmpty()) {
            String partitons = partitions
                    .stream()
                    .map(partitionName -> "`" + partitionName + "`")
                    .collect(Collectors.joining(","));
            builder.append("PARTITIONED BY (")
                    .append(partitons)
                    .append(")\n");
        }
        builder.append("with ('connector' = 'hudi'");
        options.forEach((k, v) -> builder
                .append(",\n")
                .append("  '")
                .append(k)
                .append("' = '")
                .append(v)
                .append("'"));
        builder.append("\n)");
        return builder.toString();
    }

    private List<String> map2List(Map<String, String> map) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            list.add("`" + entry.getKey() + "` " + entry.getValue());
        }
        return list;
    }
}
