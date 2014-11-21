package simpledraw.model;

import java.awt.Point;
import simpledraw.view.IVisitorShape;

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

   
    public boolean isPickedBy(Point p);

   
    public void accept(IVisitorShape v);
}
