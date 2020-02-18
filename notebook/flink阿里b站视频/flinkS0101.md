# Flink Application

## 基础处理语义

    Streams
        bounded stream
            有终 1tb
        unbounded stream
            无界 
    State
        有状态计算stateful，带状态，最近一个小时的缓存数据。
        stateless 无状态，不带数据的java应用。
    Time
        Eveng Time 事件真正发生的时间。业务时间。
        Ingestion Time 进入flink的时间。
        Processing Time 每个算子计算时候的时间。

        时间主要用于进度评估：滞后性：Ptime-ETime

## 多层API

    三层：
        高层次API:SQL/Table API
        Stream & Batch Data Processing 
        ProcessFunction

# Flink Architecture

    有界无界数据流
        state内部算子保存数据状态，不像storm无状态。
    部署灵活
    极高可伸缩性
    流式处理性能

# 场景分类

    ETL:
    DataPipeline:实时数仓

    Data分析

    DataDriven
    风控，event-driven 负责

# 高效学习

    jdk 8.x
    flink 本地环境搭。
    学习经验：
        1、先实践后理论。把应用做好，再研究内部。
        2、横向扩展比较同类工具，比如Storm,Spark,DataFlow。
        3、Apache Flink&FlinkChina社区。
