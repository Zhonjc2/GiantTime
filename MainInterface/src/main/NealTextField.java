package main;

import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class NealTextField {
    TextField trueField;
    StackPane nealTextField;
    public NealTextField(String promptText, double w, double h, int column,double arc,Color fieldColor, Font textFont){
        trueField=new TextField();
        trueField.setPrefSize(w+5,h);
        trueField.setPromptText(promptText);
        trueField.setFont(textFont);
        trueField.setPrefColumnCount(column);
        trueField.lengthProperty().addListener(observable -> {
            String old=trueField.getText();
            if(trueField.getText().length()>column)trueField.setText(old.substring(0,column));
        });
//        trueField.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        trueField.setStyle("-fx-background-color: rgba(1,1,1,0);/*-fx-display-caret: false*/");
        Rectangle fakeField=new Rectangle(w,h,fieldColor);
        fakeField.setArcHeight(arc);
        fakeField.setArcWidth(arc);
        nealTextField=new StackPane(fakeField,trueField);
    }
    public StackPane getField(){
        return nealTextField;
    }
}
