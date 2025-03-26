#!/bin/bash

stop=0

while ((stop=0))
do
  echo "Options menu"
  echo "1: Print Menu"
  echo "2:Print menu"
  read choice
  if (($choice=2))
  then 
    stop=1
  fi
done

echo "Menu Complete"
