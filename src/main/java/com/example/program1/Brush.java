package com.example.program1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Brush implements Shape
{
    public Brush() {}

    public Brush(double width, Color color)
    {
        brushWidth = width;
        brushColor = color;
    }

    private double brushWidth = 1;
    private Color brushColor;
    private Ellipse body = new Ellipse();

    @Override
    public void draw(double x, double y)
    {
        body = new Ellipse();
        body.setCenterX(x);
        body.setCenterY(y);
        body.setRadiusX(brushWidth);
        body.setRadiusY(brushWidth);
        body.setFill(brushColor);

        body.onMouseClickedProperty().set(action ->
        {
            if (Program1Controller.pipetteColor != null && Program1Controller.pipetteColor.equals(Program1Controller.TransparentColorProperty))
            {
                Program1Controller.pipetteColor = (Color)body.getFill();
                Program1Controller.ptrColorPicker.promptTextProperty().set("go");
            }
        });
    }

    @Override
    public void locate(Pane location)
    {
        location.getChildren().add(body);
    }
}
