package main;

import javafx.animation.Timeline;

public class AnimationThreadBody implements Runnable{
    private Timeline animationLine;//final对象才可以被作为synchronized互斥资源
    public AnimationThreadBody(Timeline animationLine) {
        this.animationLine=animationLine;
    }
    public void run(){
        synchronized (this) {//为什么这里填this
            animationLine.play();
        }
    }
}
