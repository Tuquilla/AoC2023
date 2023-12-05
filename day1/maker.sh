#!/bin/bash

gcc -o day1 day1.cpp -lstdc++
if [ $? -eq 0 ]; then
	./day1
fi
