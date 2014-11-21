package simpledraw.view;

import simpledraw.model.Circle;
import simpledraw.model.Line;



public interface IVisitorShape 
{
    public void visit(Line l);
    public void visit(Circle c);
}
