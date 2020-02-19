# [Flink cep官方文档阅读](https://ci.apache.org/projects/flink/flink-docs-release-1.10/zh/dev/libs/cep.html#examples)

## 前言

    flink cep主要基于NFA（不确定有限自动机）原理来侦测一个流中的连续事件。
    flink cep的业务开发需要完成：模式（Pattern）编写、模式组合、预警方法实现这三部分。
    文档中的示例与讲解以单流为主，没有涉及到join的方面。

## 文档正文

    文档主要分为模式API、

### [The Pattern API](https://ci.apache.org/projects/flink/flink-docs-release-1.10/zh/dev/libs/cep.html#the-pattern-api)

    主要介绍了where、or的模式api以及begin、next、end等组合api。

### [Detecting Patterns](https://ci.apache.org/projects/flink/flink-docs-release-1.10/zh/dev/libs/cep.html#detecting-patterns)

    在模式匹配成功后，针对匹配到的数据进行计算，获得报警结果

## 感想

### Flink Cep的核心

    0、链式匹配，将一系列事件的发生看为单向链表。
    1、针对单向莲表，使用NFA（不确定有限自动机），进行判断。
    2、模式主要是pattern
