package simpledraw.model;

/**
 * Drawing, a collection of Shapes
 * @author Rémi Bastide
 * @version 1.0
 * @see simpledraw.Shape
 */

import java.awt.Point;
import java.util.*;
import simpledraw.view.IDrawingView;
import simpledraw.view.IShapeVisitor;

public class ModelDraw {

    /**
     * A drawing is a collection of shapes
     */
    private List<IShape> form = new LinkedList<IShape>();
    private Set<IDrawingView> myViews = new java.util.HashSet<IDrawingView>();
    IShape TempShape;

    public ModelDraw() {
    }

    /**
     * Add a shape to the Drawing
     * @param s     The Shape to add
     **/
    public void addShape(IShape s) {
        form.add(s);
        notifyViews();
    }

    /**
     * Delete a shape from the Drawing
     * @param s     The Shape to delete
     **/
    public void deleteShape(IShape s) {
        form.remove(s);
        notifyViews();
    }

    /**
     * add a temporary Shape in the model
     * that is to say a shape that we are currently defining
     * @param testSelected
     */
    public void testSelectedForShape(IShape testSelected) {
        this.TempShape = testSelected;
        if (testSelected != null) {
            this.TempShape.setSelected(true);
            notifyViews();
        }
    }

    /**
     * Determines whether the given Point lies whithin a Shape
     * @param p     The Point to test
     * @return      A Shape selected by this Point or null if no Shape is there
     **/
    public IShape pickShapeAt(Point p) {
        IShape result = null;
        for (IShape s : form) {
            if (s.isPickedBy(p)) {
                result = s;
                notifyViews();
                break;
            }
        }
        return result;
    }

    /**
     * Ensures that no Shape is currently selected
     */
    public void clearSelection() {
        for (IShape s : form) {
            s.setSelected(false);
        }
        notifyViews();
    }

    /**
     * Add a new Listener to this model
     * @param l     the new listener
     **/
    public void addView(IDrawingView l) {
        myViews.add(l);
        // Bring the Listener up to date
        l.notify(this);
    }

    /**
     * Remove a Listener from this model
     * @param l     the  listener to remove
     **/
    public void removeView(IDrawingView l) {
        myViews.remove(l);
    }

    /**
     * Iterates on all listeners and
     * send the StateChanged method to each
     * @param e the event to dispatch
     */
    protected void notifyViews() {
        if (null != myViews) {
            for (IDrawingView view : myViews) {
                view.notify(this);
            }
        }
    }

    /**
     * Itere sur toutes les shapes en leur
     * demandant d'accepter le visiteur
     * @param v
     */
    public void acceptAll(IShapeVisitor v) {
        for (IShape s : form) {
            s.accept(v);
        }
        if (TempShape != null) {
            TempShape.accept(v);
        }
    }

    /*
     * Fonction translate redefinit dans le model
     * en effet tout doit passer par celui ci pour 
     * qu'il soit au courant
     */
    public void translate(IShape s, int dx, int dy) {
        s.translateBy(dx, dy);
        notifyViews();
    }

    /**
     * renseigne le statut selecté ou non de la shape
     * de meme on doit passer par le model
     * @param selected is the shape selected or not ?
     */
    public void setSelected(IShape s, boolean selected) {
        s.setSelected(selected);
        notifyViews();
    }
}
