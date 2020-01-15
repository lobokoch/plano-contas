#!/bin/bash
echo "Starting build generation..."
cd modules

echo "Packing modules..."
mvn clean install -DskipTests

echo "DONE"

read -p "Press any key to exit..."