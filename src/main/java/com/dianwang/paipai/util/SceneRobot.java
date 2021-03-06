package com.dianwang.paipai.util;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public interface SceneRobot {

    void keyPress(KeyCode key);
    void keyRelease(KeyCode key);
    void keyType(KeyCode key, String character);

    Point2D getMouseLocation();
    void mouseMove(Point2D location);
    void mousePress(MouseButton button, int clickCount);
    void mouseRelease(MouseButton button, int clickCount);
    void mouseClick(MouseButton button, int clickCount);
    void mousePress(MouseButton button);
    void mouseRelease(MouseButton button);
    void mouseClick(MouseButton button);
    void mouseDrag(MouseButton button);
    void mouseWheel(int wheelAmount);

    Color getCapturePixelColor(Point2D location);
    Image getCaptureRegion(Rectangle2D region);

}
