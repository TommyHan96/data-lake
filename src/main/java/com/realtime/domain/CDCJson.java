package com.realtime.domain;

import lombok.Data;

@Data
public class CDCJson {
  private Operation op;
  private CDCSource source;
  private Object before;
  private Object after;
}
