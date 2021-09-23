package main;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.beans.EventHandler;

public class NealGroupButton {
    public Rectangle back;
    public Text front;
    public StackPane button;
    public Color backColor,actionColor;
    public NealGroupButton(Scene buttonScene, double w, double h, double arc, String text, Color backColor, Color actionColor, Color frontColor, Font textFont){
        this.backColor=backColor;
        this.actionColor=actionColor;
        back=new Rectangle(w,h,backColor);
        back.setArcWidth(arc);
        back.setArcHeight(arc);
        front=new Text(text);
        front.setFont(textFont);
        front.setFill(frontColor);
        button=new StackPane(back,front);
        button.setOnMouseEntered(event -> {
            buttonScene.setCursor(Cursor.HAND);
            
        });
        button.setOnMouseExited(event -> buttonScene.setCursor(Cursor.DEFAULT));
    }
    public StackPane getButton(){
        return button;
    }

    public void setClicked(Runnable clickedThread,NealGroupButton...otherButtons){
        button.setOnMouseClicked(event ->{
            back.setFill(actionColor);
            for(NealGroupButton w:otherButtons)w.back.setFill(backColor);
            new Thread(clickedThread).start();
        });
    }

    public void isSelected(boolean i){
        if(i)back.setFill(actionColor);
        else back.setFill(backColor);
    }
    //注意：同种类型监听器声明第二次会覆盖原有监听器!!!!!
}
