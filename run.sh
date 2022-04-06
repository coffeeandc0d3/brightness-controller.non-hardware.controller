#!/bin/sh

java solve `xrandr --verbose | awk '/ connected /{}/Brightness:/{print $2}'` `xrandr | grep -w connected | awk -F'[ ]' '{print $1}'`
