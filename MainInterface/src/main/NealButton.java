package main;

//####### 欢迎使用 NealUI 伪控件库
//####### Copyrights: Deskriptio Design Studio.

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class NealButton {
    StackPane nameButton;
    Text name;
    Thread animationThread;
    Rectangle button;
    public NealButton(Scene scene, double w, double h, double arc, Color buttonColor, Text buttonText, double x, double y){
        button=new Rectangle(w,h);
        nameButton=new StackPane(button,buttonText);
        name=buttonText;
        button.setArcHeight(arc);
        button.setArcWidth(arc);
        Color oldColor=buttonColor.brighter();

        button.setFill(oldColor);
        nameButton.setLayoutX(x);
        nameButton.setLayoutY(y);
        KeyValue oldValue=new KeyValue(button.fillProperty(),buttonColor);
        KeyFrame oldFrame=new KeyFrame(Duration.millis(500),oldValue);
        Timeline line0=new Timeline(oldFrame);
        KeyValue newValue=new KeyValue(button.fillProperty(),oldColor);
        KeyFrame newFrame=new KeyFrame(Duration.millis(500),newValue);
        Timeline line1=new Timeline(newFrame);
        animationThread=new Thread(() -> {
            nameButton.setOnMouseEntered(event -> {
                scene.setCursor(Cursor.HAND);
                line1.stop();
                line0.play();
            });
            nameButton.setOnMouseExited(event -> {
                scene.setCursor(Cursor.DEFAULT);
                line0.stop();
                line1.play();
            });
        });
        animationThread.start();
    }
    public void setBackColor(Color color){

    }
    public void overButton(){
        animationThread.interrupt();
    }
    public StackPane getButton(){
        return nameButton;
    }
    public Text getName(){
        return name;
    }
}

