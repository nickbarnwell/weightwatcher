#!/bin/bash

#This script will help track your weight over time by writing out to an easily parsed flat file.
if [ $1 ]; then
  weight=$1
else
  echo "What is your current weight?"
  read weight
fi 
echo "`date +%Y-%m-%d`: $weight" >> ~/Dropbox/weightwatcher/weight.txt
echo "Weight appended to log"
