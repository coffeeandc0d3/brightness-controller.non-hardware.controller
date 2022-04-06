#!/bin/sh

javac solve.java

path=`echo /home/$USER`
dir=$path/build/brightness-controller.non-hardware.controller

cd $dir
java solve `xrandr --verbose | awk '/ connected /{}/Brightness:/{print $2}'` `xrandr | grep -w connected | awk -F'[ ]' '{print $1}'` 

echo Copy this run.sh file to your home directory for easier use :)
