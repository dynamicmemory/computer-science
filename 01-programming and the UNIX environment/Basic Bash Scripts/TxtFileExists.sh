#!/bin/bash

if ls *.txt &> /dev/null
then
  echo "Text files exist"
else
  echo "No text files exist - Creating one"
  echo "Automatically generated text file" > test.txt
 fi

