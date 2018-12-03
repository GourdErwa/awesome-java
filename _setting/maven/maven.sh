#!/usr/bin/env bash


## maven-checkstyle-plugin 插件相关命令
#查看帮助
mvn checkstyle:help
#查看工程是否满足检查。如果不满足，检查失败，可以通过target/checkstyle-result.xml来查看
mvn checkstyle:check
#查看工程是否满足检查。如果不满足，不会失败，可以通过target/site/checkstyle.html查看检查信息
mvn checkstyle:checkstyle
#检查工程是否满足检查。如果不满足，不会失败，可以通过target/site/checkstyle.html查看
mvn checkstyle:checkstyle-aggregate
