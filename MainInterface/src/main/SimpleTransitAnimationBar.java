package main;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class SimpleTransitAnimationBar {
    Pane barPane;
    Color backColor;
    Color frontColor;
    public SimpleTransitAnimationBar(double width, double height, Color backColor, Color frontColor){
        this.backColor=backColor;
        this.frontColor=frontColor;
        Circle shadowCircle=new Circle(height*4,frontColor);
        this.barPane=new Pane(shadowCircle);
        barPane.setStyle("-fx-background-color: #"+backColor.toString().substring(2,8));
        //首位为数字的十六进制格式颜色需要加#才能识别
        System.out.println(backColor.toString().substring(2,8));
        barPane.setPrefWidth(width);
        barPane.setPrefHeight(height);
        shadowCircle.setLayoutX(-300-shadowCircle.getRadius());
        shadowCircle.setLayoutY(height/2);
        shadowCircle.setEffect(new BoxBlur(shadowCircle.getRadius(),shadowCircle.getRadius(),1000));
        KeyValue shadowValue0=new KeyValue(shadowCircle.layoutXProperty(),width+300+shadowCircle.getRadius());
        KeyFrame shadowFrame=new KeyFrame(Duration.millis((width+600)*4),shadowValue0);
        Timeline shadowLine=new Timeline(shadowFrame);
//        shadowLine.setDelay(Duration.millis(100));
        shadowLine.setCycleCount(Animation.INDEFINITE);
        shadowLine.play();
    }
    public void setRoundBar(double set){
        barPane.setStyle("-fx-;-fx-background-radius: "+set+";-fx-background-color: #"+backColor.toString().substring(2,8));
    }
}
