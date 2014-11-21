package simpledraw.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import simpledraw.model.Circle;
import simpledraw.model.ModelDraw;
import simpledraw.model.Line;
import simpledraw.view.IView;
import simpledraw.view.IShapeVisitor;

/**
 * A Panel that displays a Drawing, and maintains a current DrawingTool<BR>
 * Uses the "State" design pattern
 * @author Rémi Bastide
 * @version 1.0
 * @see simpledraw.Drawing
 * @see simpledraw.DrawingTool
 */

public class DrawingPanel extends JPanel implements IShapeVisitor, IView {
    
    Graphics2D g;
    private DrawingTool myCurrentTool;
    ModelDraw myDrawModel;
    

    public DrawingPanel(ModelDraw modelDraw) {
        super();
        myDrawModel = modelDraw;
        setBackground(java.awt.Color.white);
        myCurrentTool = new SelectionTool(this, myDrawModel);
        activate(myCurrentTool);
    }

    void activateSelectionTool() {
        terminate(myCurrentTool);
        myCurrentTool = new SelectionTool(this, myDrawModel);
        activate(myCurrentTool);
    }

    void activateCircleTool() {
        terminate(myCurrentTool);
        myCurrentTool = new CircleTool(this, myDrawModel);
        activate(myCurrentTool);
        myDrawModel.clearSelection();
    }

    void activateLineTool() {
        terminate(myCurrentTool);
        myCurrentTool = new LineTool(this, myDrawModel);
        activate(myCurrentTool);
        myDrawModel.clearSelection();
    }

    public void paintComponent(Graphics g) {
        this.g = (Graphics2D) g;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(qualityHints);
        myDrawModel.acceptAll(this);
    }

    private void terminate(DrawingTool t) {
        removeKeyListener(t);
        removeMouseListener(t);
        removeMouseMotionListener(t);
    }

    private void activate(DrawingTool t) {
        addKeyListener(t);
        addMouseListener(t);
        addMouseMotionListener(t);
    }

    public void visit(Line line) {
        g.setColor(
                line.isSelected()
                ? Color.red
                : Color.black);
        g.drawLine(line.getMyStart().x, line.getMyStart().y, line.getMyEnd().x, line.getMyEnd().y);
    }

    public void visit(Circle cercle) {
        g.setColor(
                cercle.isSelected()
                ? Color.red
                : Color.black);
        g.drawOval(cercle.getMyCenter().x - cercle.getMyRadius(),
                cercle.getMyCenter().y - cercle.getMyRadius(),
                cercle.getMyRadius() * 2,
                cercle.getMyRadius() * 2);
    }
    
    public void notify(ModelDraw model) {
        repaint();
    }
}
