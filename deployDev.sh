#!/usr/bin/env bash
mvn clean install
scp target/shopping-list-0.0.1-SNAPSHOT.jar root@192.168.1.123:/home/orangepi/application/dev
echo "cd /home/orangepi/application/dev
java -jar -Dserver.port=8081 -DdevMode=true -DenableCORS=false shopping-list-0.0.1-SNAPSHOT.jar" | ssh root@192.168.1.123