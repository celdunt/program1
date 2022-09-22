package com.example.program1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Pencil implements Shape
{

    public Pencil()
    {
    }

    private Ellipse body = new Ellipse();

    @Override
    public void draw(double x, double y)
    {
        body = new Ellipse();
        body.setCenterX(x);
        body.setCenterY(y);
        body.setRadiusX(1);
        body.setRadiusY(1);

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
