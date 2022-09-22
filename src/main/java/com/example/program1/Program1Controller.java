package com.example.program1;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Program1Controller
{
    public Button pencil;
    public Button brush;
    public Button rectangle;
    public Button fill;
    public Pane locationField;
    public ColorPicker colorPicker;

    public static ColorPicker ptrColorPicker = new ColorPicker();

    public static final Color TransparentColorProperty = Color.TRANSPARENT;
    public static Color fillColor = TransparentColorProperty;
    public static Color pipetteColor = null;

    public Button save;
    public Button pipette;

    private Shape currentShape;

    public void initialize()
    {
        initializeToolBarSelection();
        initializeLocationBehavior();
        initializeSaveButton();
    }

    public void initializeLocationBehavior()
    {
        locationField.onMousePressedProperty().set(action ->
        {
            drawAction(action.getX(), action.getY());
        });
        locationField.onMouseReleasedProperty().set(action ->
        {
            if (currentShape != null
                    && currentShape.getClass().equals(Rectangle.class))
                currentShape = null;
        });
        locationField.onMouseDraggedProperty().set(action ->
        {
            drawAction(action.getX(), action.getY());
        });
        locationField.onMouseClickedProperty().set(action ->
        {
            if (!fillColor.equals(TransparentColorProperty))
            {
                locationField.setStyle("-fx-background-color: " + String.format("#%02X%02X%02X", (int)
                        (fillColor.getRed() * 255), (int) (fillColor.getGreen() * 255), (int) (fillColor.getBlue() * 255)) + ";");

                fillColor = TransparentColorProperty;
            }
        });
    }

    public void drawAction(double x, double y)
    {
        if (currentShape != null)
        {
            currentShape.draw(x, y);
            currentShape.locate(locationField);
        }
    }

    public void initializeToolBarSelection()
    {
        pencil.onMouseClickedProperty().set(action ->
        {
            currentShape = new Pencil();

        });
        brush.onMouseClickedProperty().set(action ->
        {
            currentShape = new Brush(5, colorPicker.getValue());
        });
        rectangle.onMouseClickedProperty().set(action ->
        {
            currentShape = new Rectangle();
        });
        fill.onMouseClickedProperty().set(action ->
        {
            currentShape = null;
            fillColor = colorPicker.getValue();
        });
        pipette.onMouseClickedProperty().set(action ->
        {
            currentShape = null;
            pipetteColor = TransparentColorProperty;
        });

        ptrColorPicker.promptTextProperty().addListener(action ->
        {
            if (pipetteColor != null)
                colorPicker.setValue(pipetteColor);

            pipetteColor = null;

            Program1Controller.ptrColorPicker.promptTextProperty().set("");
        });

        colorPicker.onActionProperty().set(action ->
        {
            if (currentShape != null)
            {
                if (currentShape.getClass().equals(Brush.class))
                {
                    currentShape = new Brush(5, colorPicker.getValue());
                }
            }
        });
    }

    public void initializeSaveButton()
    {
        save.onMouseClickedProperty().set(action ->
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files", "*.png"));

            File file = fileChooser.showSaveDialog(null);

            try
            {
                if (file != null)
                {
                    WritableImage writableImage = new WritableImage((int) locationField.getWidth() + 20, (int) locationField.getHeight() + 20);
                    locationField.snapshot(null, writableImage);
                    ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        });
    }

}