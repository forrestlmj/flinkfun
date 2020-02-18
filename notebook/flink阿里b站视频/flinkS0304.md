# NFA

    NFA

# 如何写FlinkCEP

几个关键类：
Pattern Quantifiers next/notNext followedBy/notFollowedBy

```
parttern.next("start").where(
    new SimpleCondition<Event>(){
        @Overider
        public boolean filter(Event event){
            return event.getId() == 42;
        }
    }
)
```

模式的属性：
times/oneOrMore/timesOrMore
greedy/optional

有效期
within

模式间的联系
next/notNext:严格连续性
followedBy/notFollowedBy:宽松连续性
followedByAny:非确定宽松连续性

# 连续性

Parttern(a,b)
Streaming("a","c","b1","b2")
严格连续性：两个match事件必须连续发生
宽松连续性：两个match的事件之间允许忽略不match的事件和match的事件。
非确定宽松连续性：两个match的事件之间不运行match的事件和match的事件

# 模式组

模式组发生多次

# 扩展FlinkCep

超时触发扩展
within + output

下单.followdBy(接单).within(Times)

下单
超时触发机制——WAITING

```
Pattern pattern = Pattern
        .<String>begin("start").where((SimpleCondition) (event) -> {
            return event.contains("begin");
        })
        .notFollowedBy("middle").where((SimpleCondition) (event)->{
            return event.contains("middle)
        })
        .wait("end").waiting(Time.seconds(10));
```

# 动态注入/变更

flinkCEP的规则一般会单独写在数据库中
1、injectionPatternFunction函数，inject()方法监听Rules的方法，如果需要更新，则通过Groovy加载Pattern类进行动态导入。
2、使用DB轮询方式用来做Rule的管理，设置轮询时间。
3、使用ZK+DB来做Rules的管理，ZK监听rules的变化，从db中获取加密的rules
FlinkCEP <----> Rules
