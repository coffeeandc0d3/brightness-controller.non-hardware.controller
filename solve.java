// java Program to create a horizontal brightness slider

//GUI Imports
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.io.*;  


// Could go for a gui with checkbo	xes for each identifier found 
// even disconnected ones 
public class solve extends JFrame implements ChangeListener 
{
	static JRadioButton[] buttonDisplays;
	static String chosenDisplay;
	static JFrame frame;

	// slider
	static JSlider sliderButton;

	// label
	static JLabel label;

	// main class
	public static void main(String[] args) throws IOException
	{	
			String linuxCommandOutput = args[0];
			float currentBrightness = Float.parseFloat(linuxCommandOutput);	

			System.out.println("\nCurrent brightness: " + currentBrightness);
	
			// creat e a new frame
			frame = new JFrame("Set Current Brightness");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
			// create a object 
			solve s = new solve();
			// create label  
			label = new JLabel();
	
			// create a panel
			JPanel p = new JPanel();

// 			All this does is allow only 1 radio-button selection
			ButtonGroup group = new ButtonGroup();
	 
			int count = 1;
			buttonDisplays = new JRadioButton[4];
			for (int i = 0; i < 4; i++)
			{
				buttonDisplays[i] = new JRadioButton();

//				This removes need for array of DP:(1-4)
				buttonDisplays[i].setText("DP-" + count); 
				buttonDisplays[i].setBounds(i+10,i+20,20,20);
				buttonDisplays[i].setVisible(true);
				
				p.add(buttonDisplays[i]);
				group.add(buttonDisplays[i]);
				++count;
			}	

			// create a slider
			sliderButton = new JSlider(0, 100, (int)(currentBrightness * 100));
			
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

			while (true)
			{
				JRadioButton test = getChosenDisplay(buttonDisplays);
				chosenDisplay = test.getText();
				System.out.println("Selected: " + chosenDisplay);
			}
	}

	// if JSlider value is changed
	public void stateChanged(ChangeEvent e)
	{
		float currentLevel = ((float)sliderButton.getValue() / 100);

		label.setText("Brightness: " + currentLevel + "% ");
        try
        { 
          Process p = Runtime.getRuntime().exec(new String[]{"xrandr", 
		  	"--output", chosenDisplay, "--brightness", Float.toString(currentLevel)});
        }
          catch (IOException e2) 
          {
            e2.printStackTrace();
          }
    }

    public static JRadioButton getChosenDisplay(JRadioButton[] buttonsOther) 
	{
		for (JRadioButton b : buttonsOther)
		{
			if (b.isSelected())
			{
				System.out.println("Selected: " + b.getText());
				return b;		
			}
		}

//		Returns the last display by default
		return buttonDisplays[3];
	}

}
