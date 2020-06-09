#!/usr/bin/env bash
mvn clean install
scp target/shopping-list-0.0.1-SNAPSHOT.jar root@192.168.1.126:/home/orangepi/application/dev
echo "systemctl restart shoppinglist-dev" | ssh root@192.168.1.126
