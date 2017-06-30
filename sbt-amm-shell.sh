#!/bin/bash

_bsd_="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

export JAVA_OPTS="-Xmx20G -Xms16M"

exec java ${JAVA_OPTS} \
     -cp "$(cat "${_bsd_}/.sbt.paths/SBT_RUNTIME_CLASSPATH")" \
     -Djava.library.path="${_bsd_}/lib/native" \
     com.databricks.phi9t.repl.ReplMain \
     $@
