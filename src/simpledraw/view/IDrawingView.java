package simpledraw.view;

import simpledraw.model.ModelDraw;


/**
 * L'interface Vue du pattern MVC
 */
public interface IDrawingView {

    public void notify(ModelDraw model);
}
