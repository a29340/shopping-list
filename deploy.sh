#!/usr/bin/env bash
cd ../shopping-list-fe
ng build --prod
cd ../shopping-list
mvn clean install
scp target/shopping-list-0.0.1-SNAPSHOT.jar orangepiwin@192.168.1.126:/home/orangepiwin/application/
echo "sudo systemctl restart shoppinglist" | ssh orangepiwin@192.168.1.126
