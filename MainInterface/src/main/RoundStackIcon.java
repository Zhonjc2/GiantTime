package main;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class RoundStackIcon {
    Text text;
    StackPane icon;//一定要把这个放在成员
    public RoundStackIcon(double r, Text text, Color baseColor,Color textColor){
        Circle base=new Circle(r);
        this.text=text;
        base.setFill(baseColor);
        this.text.setFill(textColor);
        icon=new StackPane(base,text);
    }
    public StackPane getStackIcon(){
        return icon;
    }
}
