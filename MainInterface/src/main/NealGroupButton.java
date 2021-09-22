package main;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NealGroupButton {
    public Rectangle back;
    public Text front;
    public StackPane button;
    public Color backColor,actionColor;
    public NealGroupButton(Scene buttonScene,double w, double h, double arc, String text, Color backColor, Color actionColor, Color frontColor, Font textFont){
        this.backColor=backColor;
        this.actionColor=actionColor;
        back=new Rectangle(w,h,backColor);
        back.setArcWidth(arc);
        back.setArcHeight(arc);
        front=new Text(text);
        front.setFont(textFont);
        front.setFill(frontColor);
        button=new StackPane(back,front);
        button.setOnMouseEntered(event -> buttonScene.setCursor(Cursor.HAND));
        button.setOnMouseExited(event -> buttonScene.setCursor(Cursor.DEFAULT));
        button.setOnMouseClicked(event -> back.setFill(actionColor));
    }
    public StackPane getButton(){
        return button;
    }
    public void avoid(NealGroupButton...otherButton){
        button.setOnMouseClicked(event -> {
            for(NealGroupButton w:otherButton)w.back.setFill(w.backColor);
        });
    }
}
