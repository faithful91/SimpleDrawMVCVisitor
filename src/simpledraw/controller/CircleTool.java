package simpledraw.controller;

/**
 * The tool to create circles
 **/

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import simpledraw.model.Circle;
import simpledraw.model.ModelDraw;

public class CircleTool
        extends DrawingTool {
	private boolean iAmActive = false;
	private Point myCenter;
	private int myRadius;

	public CircleTool(DrawingPanel panel,ModelDraw drawing) {
		super(panel,drawing);
	}

	public void mouseClicked(MouseEvent e) {
		if (!iAmActive) {
			// Center
			iAmActive = true;
			myCenter = e.getPoint();
			myRadius = 0;
			myPanel.setCursor(
				Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)
				);
                                } else 
                                {
			// Radius
			iAmActive = false;
			myDrawModel.testSelectedForShape(null);
                        myDrawModel.addShape(new Circle(myCenter, myRadius));
                        myPanel.setCursor(Cursor.getDefaultCursor());

		}
	}

	public void mouseMoved(MouseEvent e) {
		if (iAmActive) 
                {
			myRadius = (int) (myCenter.distance(e.getPoint()));
                        myDrawModel.testSelectedForShape(new Circle(myCenter, myRadius));
		}
	}

    @Override
    void draw(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	
}