# gui-brightness-controller.non-hardware.controller
sub-optimal GUI to adjust brightness through software-rendering rather than the hardware controller (as best i understand). can only confirm it works to change brightness levels for my Legion Slim 7 on Pop OS and Linux Mint. Brightness did not work prior on fresh installs of both Pop and Mint. Resorted to using xrandr in the terminal to change it but figured a simple GUI with a slider would be convenient until/if we get full hardware compatbility. 
Haven't tested further to confirm support for other Lenovo Legions unfortunately.
Other than the small GUI display for the brightness controller, the only thing this script does is invoke:
     xrandr --output DP-4 --brightness *value* 
where value is grabbed from the current value of the slider. 

*Note* The above command uses a specific display identifier (DP-4), but this likely won't be yours. To find your identifier launch a terminal and type: 

xrandr | grep -w connected  | awk -F'[ ]' '{print $1}'

Example Output:
DP-4

Program looks for 2 arguments provided below. *Note* You'll need both parameters with the backtick (`) character, not the apostrophe.

-1st argument gives program your system's current brightness, 

Exanple Input of 1st Argument (enclosed with backticks, but they might not be visible) 
` xrandr --verbose | awk '/ connected /{}/Brightness:/{print $2} `

2nd Argument gives program your system's connected display identifier
` xrandr | grep -w connected | awk -F'[ ]' '{print $1}' ` 


<img src="https://user-images.githubusercontent.com/31811490/151293547-6f05e007-24ff-4ee3-949c-47ce46615efb.png">

[To Run]

Clone the repo into a ~/build directory.

Execute the ./run.sh script to run the GUI. 

`sudo chmod u+x run.sh`
(optional) `cp run.sh /home/$USER`
`./run.sh`

if you want to run manually:

javac solve.java

java solve `xrandr --verbose | awk '/ connected /{}/Brightness:/{print $2}'` `xrandr | grep -w connected | awk -F'[ ]' '{print $1}'` 

*Note* Again the above 2 parameters need to be enclosed with backticks (`), they won't show on Github. 

*Tips: This can be useful as a panel plugin that could run the above command when the plugin is clicked on. 
See https://github.com/levimake/xfce4-genmon-panel-scripts 
An example (xfce-only) would be adding one of the Genmon Monitor plugins to your XFCE panel and having the plugin run bash /home/$your-name/your-script.sh and inside this script.sh you would have an on-click command: java solve `arg0` `arg1` (with back-ticks (`) enclosing arg0-1) 
