#!/usr/bin/env bash
mvn clean install
scp target/shopping-list-0.0.1-SNAPSHOT.jar root@192.168.1.123:/home/orangepi/application
echo "systemctl restart shoppinglist" | ssh root@192.168.1.123