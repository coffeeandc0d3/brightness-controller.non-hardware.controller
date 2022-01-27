# gui-brightness-controller.non-hardware.controller
[Description]
sub-optimal GUI to adjust brightness through software-rendering rather than the hardware controller (as best i understand). can only confirm it works to change brightness levels for my Legion Slim 7 on Pop OS and Linux Mint. Brightness did not work prior on fresh installs of both Pop and Mint. Resorted to using xrandr in the terminal to change it but figured a simple GUI with a slider would be convenient until/if we get full hardware compatbility. 
Haven't tested further to confirm support for other Lenovo Legions unfortunately.
Other than the small GUI display for the brightness controller, the only thing this script does is invoke:
     xrandr --output 0x1c1 --brightness *value* 
where value is grabbed from the current value of the slider. 


<img src="https://user-images.githubusercontent.com/31811490/151270330-03cec57b-3452-4fd3-b78e-9cc9cab0656f.png">

[To Run]

mkdir build
cd build
git clone https://github.com/coffeeandc0d3/brightness-controller.non-hardware.controller

java slider.java

*Tips: This can be useful as a panel plugin that could run 'java slider.java' when the plugin is clicked on. 
