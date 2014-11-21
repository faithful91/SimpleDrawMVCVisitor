package simpledraw.model;

/**
 * L'interface Shape
 * @author GROLEAU Clément, OLIER Ghyslain
 */
import java.awt.Point;
import simpledraw.view.IShapeVisitor;

public interface IShape {

    /**
     * Is this shape selected ?
     * @return true if selected, false otherwise
     */
    public boolean isSelected();

    /**
     * Sets the selected status of this shape
     * @param selected is the shape selected or not ?
     */
    public void setSelected(boolean selected);

    /**
     * Translates this shape
     * @param dx delta x
     * @param dy delta y
     */
    public void translateBy(int dx, int dy);

    /**
     * Determines if the given point is inside this shape
     * @param p the point to test
     * @return true if <code>p</code> inside the shape, false otherwise
     */
    public boolean isPickedBy(Point p);

    /*
     * Implementation du pattern Visitor
     */
    public void accept(IShapeVisitor v);
}
