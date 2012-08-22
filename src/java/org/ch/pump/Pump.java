package org.ch.pump;

import cascading.operation.Aggregator;
import cascading.operation.Filter;
import cascading.operation.Function;
import cascading.pipe.Each;
import cascading.pipe.Every;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.pipe.assembly.Coerce;
import cascading.pipe.assembly.Discard;
import cascading.pipe.assembly.Retain;
import cascading.pipe.joiner.Joiner;
import cascading.tuple.Fields;

/** Author: duxbury */
public class Pump {
  private final Pipe prev;

  private Pump(String name) {
    this.prev = new Pipe(name);
  }

  private Pump(Pipe prev) {
    this.prev = prev;
  }

  public static Pump prime() {
    return new Pump("input");
  }

  public static Pump prime(Pipe pipe) {
    return null;
  }

  public static Pump cogroup(Pump left, Pump right) {
    return null;
  }

  public static Pump cogroup(Pump left, Pump right, String... cogroupFields) {
    return null;
  }

  public static Pump cogroup(Pump left, Pump right, Joiner joiner, String... cogroupFields) {
    return null;
  }

  private static Fields getArgSelector(String... args) {
    Fields f = Fields.ALL;
    if (args.length > 0) {
      f = new Fields(args);
    }
    return f;
  }

  public Pump each(Function function, String... args) {
    return new Pump(new Each(prev, getArgSelector(args), function, Fields.ALL));
  }

  public Pump each(Filter filter, String... args) {
    return new Pump(new Each(prev, getArgSelector(args), filter));
  }

  public Pump groupby(String... fields) {
    return new Pump(new GroupBy(prev, getArgSelector(fields)));
  }

  public Pump every(Aggregator agg, String... args) {
    return new Pump(new Every(prev, getArgSelector(args), agg));
  }

  public Pipe toPipe() {
    return prev;
  }

  public Pump retain(String ... fieldsToKeep) {
    return new Pump(new Retain(prev, getArgSelector(fieldsToKeep)));
  }

  public Pump discard(String ... fieldsToDiscard) {
    return new Pump(new Discard(prev, getArgSelector(fieldsToDiscard)));
  }

  public Pump coerce(String field, Class toClass) {
    return new Pump(new Coerce(prev, new Fields(field), toClass));
  }
}
