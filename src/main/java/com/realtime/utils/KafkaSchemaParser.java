package com.realtime.utils;

import com.realtime.domain.CDCJson;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class KafkaSchemaParser {
  /**
   * {"op":"c","before":{},"source":{"database":"datawarehouse","table":"ods_yx_base_department"},"after":{"bu_name":"广告部","second_department_name":"开发部","third_department_name":"开发部","code":52,"first_department_code":70,"level":"5","second_department_code":64,"bg_code":51358,"bu_code":50046,"bg_name":"销售部","all_parent_department_name":"事业部","is_valid":0,"name":"产品部","first_department_name":"广告部","id":44,"third_department_code":959}}
   *
   * @return
   */
  public CDCJson parseSchema(String str){
    ObjectMapper objMapper = new ObjectMapper();
    try {
      CDCJson json = objMapper.readValue(str, CDCJson.class);
      //TODO op equals update delete

      //TODO how to deal with before

      //get source

      //get after
      return json;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
