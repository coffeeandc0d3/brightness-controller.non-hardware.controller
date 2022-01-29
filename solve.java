// [Description] 
// Suboptimal java gui to change brightness levels through software rendering
//Useful for Linux devices that are too new for compatbility.
//This would not be a good long-term solution as this doesn't use the hardware controller
// to adjust the system brightness. 

import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.io.*;  

/*  This should determine your display identifier: 
	xrandr | grep -w connected  | awk -F'[ ]' '{print $1}'
	
Pass it as a parameter when you run 'java solve.java' `<Arguments-Here>`
This gives the progaram the ID of your connected display.
*Note* the above arguments are enclosed with back-ticks(`) - not the apostrophe. 

Alternatively there's radio buttons for all display ID types.
This includes types not on your system. 
Overall it's meant to provide a reliable trial & error approach... 
...at the cost of speed, optimization, & everything good. 

<todo> allow for entering in your display type, JTextField

*/

public class solve extends JFrame implements ChangeListener 
{
	static String ConnectedDisplay, SystemBrightness;
	static JRadioButton[] buttonDisplays;
	static JFrame frame;

//	Slider to adjust brightness
	static JSlider sliderButton;

//	Label to show % set to 
	static JLabel label;

	// main class
	public static void main(String[] args) throws IOException
	{	
//			Argument 0 is system command to retrieve current brightness level
			SystemBrightness = args[0];
//			Argument 1 is system command to retrieve connected display's ID
			ConnectedDisplay = args[1];

			float currentBrightness = Float.parseFloat(SystemBrightness);	
			System.out.println("\nCurrent brightness: " + currentBrightness);
	
//			Standard jframe being initialized 
			frame = new JFrame("Set Current Brightness");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
			// Serves as the reference  
			solve s = new solve();
			// create label  
			label = new JLabel();
	
			// create a panel
			JPanel p = new JPanel();

// 			All this does is allow 1 radio-button selected at a time
			ButtonGroup group = new ButtonGroup();
	 
			int count = 1;
//			By default supports: DP1-4, LVDS-1, and HDMI-1
			buttonDisplays = new JRadioButton[6];
			for (int i = 0; i < 4; i++)
			{
				buttonDisplays[i] = new JRadioButton();

//				This removes need for string[] of DP:(1-4)
				buttonDisplays[i].setText("DP-" + count); 
				buttonDisplays[i].setBounds(i+10,i+20,20,20);
				buttonDisplays[i].setVisible(true);
				
				p.add(buttonDisplays[i]);
				group.add(buttonDisplays[i]);
				++count;
			}	

// 			LVDS Support: 
			buttonDisplays[4] = new JRadioButton();
			buttonDisplays[4].setText("LVDS-1");
			buttonDisplays[4].setBounds(30, 200,10,10);
			buttonDisplays[4].setVisible(true);

			p.add(buttonDisplays[4]);
			group.add(buttonDisplays[4]);

//			HDMI-1 Support: 
			buttonDisplays[5] = new JRadioButton();
			buttonDisplays[5].setText("HDMI-1");
			buttonDisplays[5].setBounds(30, 200,10,10);
			buttonDisplays[5].setVisible(true);

			p.add(buttonDisplays[5]);
			group.add(buttonDisplays[5]);

//			Last param ensures slider starts at current system brightness
			sliderButton = new JSlider(0, 100, (int)(currentBrightness * 100	));
			
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
			sliderButton.setFont(new Font("Ubuntu Bold", Font.BOLD, 20));
	
			// add slider to panel
			p.add(sliderButton);
			p.add(label);
	
			frame.add(p);
	
			// Set the text of label on slider 
			label.setText("Brightness: " + sliderButton.getValue() + "% ");
	
			// set the size of slider menu
			frame.setSize(300, 200);
			frame.setVisible(true);	

// 			Main event loop to switch all the ID types
			while (true)
			{
				JRadioButton currentEnabledID = getChosenDisplay(buttonDisplays);
				ConnectedDisplay = currentEnabledID.getText();
				System.out.println("Using: " + ConnectedDisplay);
			}
	}

// Each time slider is changed, will execute a shell command 
// to adjust brightness with last parameter being the desired
// brightness. This parameter will be equal to value of the slider. 
	public void stateChanged(ChangeEvent e)
	{
		float currentLevel = ((float)sliderButton.getValue() / 100);

		label.setText("Brightness: " + currentLevel + "% ");
        try
        { 
          Process p = Runtime.getRuntime().exec(new String[]{"xrandr", 
		  	"--output", ConnectedDisplay, "--brightness", Float.toString(currentLevel)});
        }
           catch (IOException e2) 
           {
             e2.printStackTrace();
           }
    }

//  Returns current display type user selected
    public static JRadioButton getChosenDisplay(JRadioButton[] buttonsOther) 
	{
		for (JRadioButton b : buttonsOther)
		{
			if (b.isSelected())
			{
//				System.out.println("Selected: " + b.getText());
				return b;		
			}
		}

//		Returns the last display by default
		return buttonDisplays[3];
	}

}
