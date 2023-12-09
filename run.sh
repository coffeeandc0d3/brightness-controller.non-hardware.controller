#!/bin/sh

path=`echo /home/$USER`
dir=$path/build/brightness-controller.non-hardware.controller

cd $dir
if [ -f $dir/solve.class ]; then
	echo 'Found object file.'
else
	javac solve.java
	echo '.class missing. Compiling now'
fi

java solve `xrandr --verbose | awk '/ connected /{}/Brightness:/{print $2}'` `xrandr | grep -w connected | awk -F'[ ]' '{print $1}'` 

echo Closed.
