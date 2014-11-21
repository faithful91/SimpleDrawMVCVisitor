package simpledraw.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import simpledraw.view.IVisitorShape;

/**
 * A circle
 **/

public class Circle implements IShape {
        private Point myCenter;
	private int myRadius;
        private boolean isSelected = false;


	/**
	 * Construct a Circle
	 * @param center        The center of the circle
	 * @param radius        The radius of the circle
	 **/
	public Circle(Point center, int radius) {
		myCenter = center;
		myRadius = radius;
	}

	public void draw(Graphics2D g) {
		g.setColor(
			isSelected() ?
			Color.red :
			Color.black
			);
		g.drawOval(myCenter.x - myRadius,
			   myCenter.y - myRadius,
			   myRadius * 2,
			   myRadius * 2
			);
	}

	public void translateBy(int dx, int dy) {
            myCenter.translate(dx, dy);
	}

	public boolean isPickedBy(Point p) {
            return (Math.abs(myCenter.distance(p) - myRadius) <= 2);
	}

        public boolean isSelected() {
            return isSelected;    
        }

        public void setSelected(boolean selected) {
            isSelected = selected;

        }

        public void accept(IVisitorShape v) {
                    v.visit(this);
        }
        public Point getMyCenter() {
            return myCenter;
        }
        
        public int getMyRadius() {
            return myRadius;
        }   
}