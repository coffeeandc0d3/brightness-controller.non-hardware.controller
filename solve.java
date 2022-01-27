// java Program to create a vertical slider with
// min and max value and major and minor ticks
// painted and set the font of the slider.

//GUI Imports
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.PrintStream;
import java.io.File;


//File I/O Imports
import java.io.IOException;


class solve extends JFrame implements ChangeListener 
{
	// frame
	static JFrame frame;

	// slider
	static JSlider sliderButton;

	// label
	static JLabel label;

	// main class
	public static void main(String[] args) throws IOException
	{
		// create a new frame
		frame = new JFrame("Set Current Brightness");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create a object
		solve s = new solve();
		// create label  
		label = new JLabel();

		// create a panel
		JPanel p = new JPanel();

		// create a slider
		sliderButton = new JSlider(0, 100, 100);

		// paint the ticks and tracks
		sliderButton.setPaintTrack(true);
		sliderButton.setPaintTicks(true);
		sliderButton.setPaintLabels(true);

		// set spacing
		sliderButton.setMajorTickSpacing(50);
		sliderButton.setMinorTickSpacing(5);

		// setChangeListener   
		sliderButton.addChangeListener(s);

		// set orientation of slider
		sliderButton.setOrientation(SwingConstants.HORIZONTAL);

		// set Font for the slider
		sliderButton.setFont(new Font("Monospace Bold", Font.BOLD, 20));

		// add slider to panel
		p.add(sliderButton);
		p.add(label);

		frame.add(p);

		// set the text of label
		label.setText("Brightness: " + sliderButton.getValue() + "% ");

		// set the size of frame
        	frame.show();
		frame.setSize(300, 150);

// 		In case of bugs/memory leaks 
//		System.exit();
	}

	// if JSlider value is changed
	public void stateChanged(ChangeEvent e)
	{
		float currentLevel = ((float)sliderButton.getValue() / 100);

		label.setText("Brightness: " + currentLevel + "% ");
        try
        { 
          Process p = Runtime.getRuntime().exec(new String[]{"xrandr", "--output", "0x1c1", "--brightness", Float.toString(currentLevel)});
        }
          catch (IOException e2) 
          {
            e2.printStackTrace();
          }
    }

}
