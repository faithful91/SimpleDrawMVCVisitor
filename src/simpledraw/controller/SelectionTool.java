package simpledraw.controller;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import simpledraw.model.ModelDraw;
import simpledraw.model.IShape;

/**
 * The tool to select, move and delete Shapes in the Drawing
 * @author Rémi Bastide
 * @version 1.0
 */

public class SelectionTool
	extends DrawingTool {
	private IShape mySelectedShape = null;
	private Point myLastPoint;

	public SelectionTool(DrawingPanel panel,ModelDraw drawing) {
		super(panel,drawing);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_DELETE) {
			if (mySelectedShape != null) {
				myDrawModel.deleteShape(mySelectedShape);
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		IShape pickedShape = myDrawModel.pickShapeAt(e.getPoint());
		myLastPoint = e.getPoint();
		if (mySelectedShape != null) {
			myDrawModel.setSelected(mySelectedShape,false);
		}
		mySelectedShape = pickedShape;
		if (mySelectedShape != null) {
			myDrawModel.setSelected(mySelectedShape,true);
			myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.
				MOVE_CURSOR));
		}
		myPanel.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		mouseMoved(e);
	}

	public void mouseMoved(MouseEvent e) {
		IShape pickedShape = myDrawModel.pickShapeAt(e.getPoint());
		if (pickedShape != null) {
			myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.
				HAND_CURSOR));
		} else {
			myPanel.setCursor(Cursor.getDefaultCursor());
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (mySelectedShape != null) {
			myDrawModel.translate(mySelectedShape,
				e.getX() - myLastPoint.x,
				e.getY() - myLastPoint.y
				);
			myLastPoint = e.getPoint();
        		myPanel.repaint();
		}
	}

	void draw(Graphics2D g) {
	}

}
