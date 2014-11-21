package simpledraw.model;

/**
 * Drawing, a collection of Shapes
 * @author Rémi Bastide
 * @version 1.0
 * @see simpledraw.Shape
 */

import java.awt.Point;
import java.util.*;
import simpledraw.view.IView;
import simpledraw.view.IShapeVisitor;

public class ModelDraw {
    
    private List<IShape> Form = new LinkedList<IShape>();
    private Set<IView> ViewsDraw = new java.util.HashSet<IView>();
    IShape testShape;

    public ModelDraw() {
    }

   
    public void addShape(IShape s) {
        Form.add(s);
        notifyViews();
    }

    public void deleteShape(IShape s) {
        Form.remove(s);
        notifyViews();
    }

    
    public void testSelectedForShape(IShape testSelected) {
        this.testShape = testSelected;
        if (testSelected != null) {
            this.testShape.setSelected(true);
            notifyViews();
        }
    }

   
    public IShape pickShapeAt(Point p) {
        IShape result = null;
        for (IShape s : Form) {
            if (s.isPickedBy(p)) {
                result = s;
                notifyViews();
                break;
            }
        }
        return result;
    }

   
    public void clearSelection() {
        for (IShape s : Form) {
            s.setSelected(false);
        }
        notifyViews();
    }

   
    public void addView(IView draw) {
        ViewsDraw.add(draw);
        draw.notify(this);
    }

   
    public void translate(IShape s, int dx, int dy) {
        s.translateBy(dx, dy);
        notifyViews();
    }

    
    protected void notifyViews() {
        if (null != ViewsDraw) {
            for (IView view : ViewsDraw) {
                view.notify(this);
            }
        }
    }

    
    public void removeView(IView draw) {
        ViewsDraw.remove(draw);
    }
    
   
    public void setSelected(IShape s, boolean selected) {
        s.setSelected(selected);
        notifyViews();
    }
    
     public void acceptAll(IShapeVisitor v) {
        for (IShape s : Form) {
            s.accept(v);
        }
        if (testShape != null) {
            testShape.accept(v);
        }
    }
}
