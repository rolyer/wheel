#!/bin/sh
export JAVA_HOME=/apps/product/java
export CLASSPATH=.:$JAVA_HOME/lib:$JAVA_HOME/jre/lib
export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH

APP_MAIN=stockmarket-demo

tradePortalPID=0

getTradeProtalPID(){
    javaps=`jps -l | grep $APP_MAIN`
    if [ -n "$javaps" ]; then
        tradePortalPID=`echo $javaps | awk '{print $1}'`
    else
        tradePortalPID=0
    fi
}

shutdown(){
    getTradeProtalPID
    if [ $tradePortalPID -ne 0 ]; then
        echo -n "Stopping $APP_MAIN(PID=$tradePortalPID)..."
        kill -9 $tradePortalPID
        if [ $? -eq 0 ]; then
            echo "[Success]"
        else
            echo "[Failed]"
        fi
        getTradeProtalPID
        if [ $tradePortalPID -ne 0 ]; then
            shutdown
        fi
    else
        echo "$APP_MAIN is not running"
    fi
}

shutdown