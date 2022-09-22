package com.example.program1;

import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Rectangle implements Shape
{

    public Rectangle()
    {
        rectangle.setOnMouseClicked(action ->
        {
            if (!Program1Controller.fillColor.equals(Program1Controller.TransparentColorProperty))
            {
                rectangle.setFill(Program1Controller.fillColor);
                Program1Controller.fillColor = Program1Controller.TransparentColorProperty;
            }
            if (Program1Controller.pipetteColor != null && Program1Controller.pipetteColor.equals(Program1Controller.TransparentColorProperty))
            {
                Program1Controller.pipetteColor = (Color)rectangle.getFill();
                Program1Controller.ptrColorPicker.promptTextProperty().set("go");
            }
        });
    }


    private double X = 0;
    private double Y = 0;

    private double X1 = 0;
    private double Y1 = 0;

    private String PhaseProperty = "first phase";

    private Color FillColor = Color.TRANSPARENT;

    private final javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle();

    @Override
    public void draw(double x, double y)
    {
        String firstPhaseProperty = "first phase";
        String secondPhaseProperty = "second phase";

        if (PhaseProperty.equals(firstPhaseProperty))
        {
            PhaseProperty = secondPhaseProperty;
            X = x;
            Y = y;
        }
        if (PhaseProperty.equals(secondPhaseProperty))
        {
            X1 = x;
            Y1 = y;
            if (x < X)
            {
                X1 = X;
                X = x;
            }
            if (y < Y)
            {
                Y1 = Y;
                Y = y;
            }
            rectangle.setX(X);
            rectangle.setY(Y);
            rectangle.setWidth(X1 - X);
            rectangle.setHeight(Y1 - Y);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.TRANSPARENT);
        }
    }

    @Override
    public void locate(Pane location)
    {
        location.getChildren().remove(rectangle);
        location.getChildren().add(rectangle);
    }

    public void setXY(double x, double y)
    {
        X = x;
        Y = y;
    }

    public void setXY1(double x1, double y1)
    {
        X1 = x1;
        Y1 = y1;
    }

    public javafx.scene.shape.Rectangle getRectangle()
    {
        return rectangle;
    }

    public Color getFillColor()
    {
        return FillColor;
    }

    public void setFillColor(Color fillColor)
    {
        FillColor = fillColor;
    }
}
