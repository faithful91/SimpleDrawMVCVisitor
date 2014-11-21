package simpledraw.controller;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import simpledraw.model.ModelDraw;
import simpledraw.model.Line;

/**
 * The tool to create Lines
 * @author Rémi Bastide
 * @version 1.0
 * @see simpledraw.Line
 */

public class LineTool extends DrawingTool {
	private boolean iAmActive = false;
	private Point myInitialPoint;
	private Point myFinalPoint;

	public LineTool(DrawingPanel panel,ModelDraw drawing) {
		super(panel,drawing);
	}

	public void mouseClicked(MouseEvent e) {
		if (!iAmActive) {
                        iAmActive = true;
			myInitialPoint = e.getPoint();
			myFinalPoint = myInitialPoint;
			myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.
				MOVE_CURSOR));
			myPanel.repaint();
		} 
                
                else 
                
                {	iAmActive = false;
			myDrawModel.addShape(new Line(myInitialPoint, myFinalPoint));
			myPanel.setCursor(Cursor.getDefaultCursor());
			myPanel.repaint();
		}
	}

	public void mouseMoved(MouseEvent e) 
        {
		if (iAmActive) 
                {
			myFinalPoint = e.getPoint();
			myDrawModel.testSelectedForShape(new Line(myInitialPoint, myFinalPoint));
		}
	}

    @Override
    void draw(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
