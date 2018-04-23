package com.example.bb.weather14.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class GlobalStuff {
  private static final AtomicInteger seed = new AtomicInteger();

  public static int getFreshInt() {
    return seed.incrementAndGet();
  }
}
