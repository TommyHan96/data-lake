package com.realtime.domain;

import lombok.Data;

@Data
public class CDCJson {
  private Operation op;
  private CDCSource source;
  private CDCFieldValue before;
  private CDCFieldValue after;
}
