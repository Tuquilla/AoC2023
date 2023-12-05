#!/bin/bash

gcc -o day3 day3.cpp -lstdc++
if [ $? -eq 0 ]; then
	./day3
fi
