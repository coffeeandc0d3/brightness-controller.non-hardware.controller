# brightness-controller.non-hardware.controller
sub-optimal quick fix that changed brightness levels for my Legion Slim 7 on Pop OS and Linux Mint. have not tested further to confirm support for other Lenovo Legions. Outside of the GUI menu for the brightness controller, the only 'implementation' is invoking: xrandr --output 0x1c1 --brightness &lt;VALUE>. Th
