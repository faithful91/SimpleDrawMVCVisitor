package simpledraw;

import simpledraw.controller.MainFrame;

/**
 * Main program of SimpleDraw
 * @author Rémi Bastide
 * @version 1.0
 */

public class Main {
		public static void main(String[] args) 
                {
		MainFrame frame = new MainFrame();
		frame.validate();
		frame.setVisible(true);
                }
}
