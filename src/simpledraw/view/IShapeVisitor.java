package simpledraw.view;

import simpledraw.model.Circle;
import simpledraw.model.Line;



/**
 * Interface du pattern Visitor implementee par toutes les fonctionnalitees
 * mais aussi par toutes les Vues
 *
 * @author GROLEAU Clément, OLIER Ghyslain
 */
public interface IShapeVisitor {

    public void visit(Line l);

    public void visit(Circle c);
}
