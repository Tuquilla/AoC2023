#!/bin/bash

gcc -o day4 day4.cpp -lstdc++
if [ $? -eq 0 ]; then
	./day4
fi
