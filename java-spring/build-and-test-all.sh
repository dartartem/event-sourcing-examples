#! /bin/bash

set -e

if [ -z "$DOCKER_HOST_IP" ] ; then
  if [ -z "$DOCKER_HOST" ] ; then
    export DOCKER_HOST_IP=`hostname`
  else
    echo using ${DOCKER_HOST?}
    XX=${DOCKER_HOST%\:*}
    export DOCKER_HOST_IP=${XX#tcp\:\/\/}
  fi
  echo set DOCKER_HOST_IP $DOCKER_HOST_IP
fi

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

dockerall="./gradlew mysqlbinlogCompose"
dockerinfrastructure="./gradlew mysqlbinloginfrastructureCompose"

if [ "$1" = "--use-existing" ] ; then
  shift;
else
  ${dockerall}Down
fi

NO_RM=false

if [ "$1" = "--no-rm" ] ; then
  NO_RM=true
  shift
fi

${dockerinfrastructure}Up


if [ -z "$SPRING_DATA_MONGODB_URI" ] ; then
  export SPRING_DATA_MONGODB_URI=mongodb://${DOCKER_HOST_IP?}/mydb
  echo Set SPRING_DATA_MONGODB_URI $SPRING_DATA_MONGODB_URI
fi

export SERVICE_HOST=$DOCKER_HOST_IP

./gradlew $BUILD_AND_TEST_ALL_EXTRA_GRADLE_ARGS $* build -x :e2e-test:test

${dockerall}Up

set -e

./gradlew $BUILD_AND_TEST_ALL_EXTRA_GRADLE_ARGS -a $* :e2e-test:cleanTest :e2e-test:test -P ignoreE2EFailures=false

if [ $NO_RM = false ] ; then
  ${dockerall}Down
fi
