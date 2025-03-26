#!/bin/bash

read -p "Are you happy or sad? " feelings

if [[ $feelings == "sad" ]]
then 
  read -p "How many days since you last went for a walk? " walk
  if [[ $walk > 1 ]]
  then 
    echo "You should go for a walk"
  else
    echo "Sorry I dont know how to help"  
  fi
else 
  echo "Sorry I dont know how to help"
fi
