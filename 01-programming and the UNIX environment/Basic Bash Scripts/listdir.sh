#!/bin/bash

for file in *
do 
    if [[ -d $file ]]
    then
        echo "$file is a directory"
#    else 
#        echo "$file is not a directory"
    fi
done

for file in *.sh
do
    echo "$file is a script"
done
