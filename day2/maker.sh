#!/bin/bash

gcc -o day2 day2.cpp -lstdc++
if [ $? -eq 0 ]; then
	./day2
fi
