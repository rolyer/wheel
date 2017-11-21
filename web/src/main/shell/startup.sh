#!/bin/sh
export JAVA_HOME=/apps/product/java
export CLASSPATH=.:$JAVA_HOME/lib:$JAVA_HOME/jre/lib
export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH

JAVA_OPTS="-Xms512m -Xmx4096m -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector"

CLASSPATH=.
CLASSPATH=$CLASSPATH:../conf

nohup /apps/product/java/bin/java ${JAVA_OPTS} -cp $CLASSPATH -jar ../stockmarket-demo-*.jar &>> ../log/system.out &

mkdir -p ../log

jps -l | grep demo

cd ../log
more ./system.out