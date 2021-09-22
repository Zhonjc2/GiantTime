package main;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/*class SecondPageThread extends Thread{
    Pane mainPane;

    public SecondPageThread(Pane mainPane){
        this.mainPane=mainPane;
    }
    public void run(){

    }
}*/

public class Main extends Application {
    final static String FONT_LIBRARY ="file://"+"/Users/zhonjc/IdeaProjects/GiantTime/MainInterface/src/OPPOSans-H.ttf";
    Pane mainPane=new Pane();
    volatile Scene mainScene=new Scene(mainPane);
    Rectangle mainRec=new Rectangle(506.16,229.85);

    //主题色
    Color mainColor0=Color.web("#ECECEC");
    Color mainColor1=Color.WHITE;
    Color buttonColor=Color.web("#C4C4C4");
    Color blankColor=Color.web("#E6E6E6");
    Color mainColorTransparent=new Color(mainColor0.getRed(), mainColor0.getGreen(), mainColor0.getBlue(), 0);
    //字体
    Font mainFont=Font.loadFont(FONT_LIBRARY,29);
    Font defaultFont=Font.loadFont(FONT_LIBRARY,12);
    Font nextFont=Font.loadFont(FONT_LIBRARY,11);
    Font stepFont =Font.loadFont(FONT_LIBRARY,19);
    Font counterFont =Font.loadFont(FONT_LIBRARY,70);
    //文字
    Text welcome=new Text("欢迎使用 GiantTime");
    Text description=new Text("这是一个由 JavaFX 制作的简单的倒计时应用\n" +
            "只需两步，即可进入倒计时状态\n" +
            "\n" +
            "点击下一步开始");
    Text next=new Text("下一步");
    Text step1Text=new Text("1");
    Text step2Text=new Text("2");
    Text stepText =new Text("选择目标日期、时间和名字");
    Text choose=new Text("选择");
    Text start=new Text("开始计时");
    Text logo=new Text("GiantTime");
    Text aimName=new Text("目标事件名称");
    Text aimDate=new Text("选择日期与时间");
    Text y=new Text("年");
    Text mo=new Text("月");
    Text d=new Text("日");
    Text h=new Text("时");
    Text mi=new Text("分");
    Text s=new Text("秒");
    Text beginButtonText=new Text("开始");
    Text prepare=new Text("正在准备");
    Text far;



    Rectangle timeChooser=new Rectangle(0,207.45,new Color(buttonColor.getRed(),buttonColor.getGreen(),buttonColor.getBlue(),0));

    NealButton nextButton=new NealButton(mainScene,88.27,22.3,5,buttonColor,next,48.06,236.05);
    NealButton chooseButton=new NealButton(mainScene,76.25,22.3,5,buttonColor,choose,122.07,107.26);
    NealButton beginButton=new NealButton(mainScene,55.89,18.64,5,blankColor,beginButtonText,345.65,212.17);
    NealTextField name=new NealTextField("请输入目标事件名称",162.12,19.44,100,5,blankColor,defaultFont);
    NealTextField yEnter=new NealTextField("",42.3,15.05,4,3,buttonColor,defaultFont);
    NealTextField moEnter=new NealTextField("",24.02,15.05,2,3,buttonColor,defaultFont);
    NealTextField dEnter=new NealTextField("",24.02,15.05,2,3,buttonColor,defaultFont);
    NealTextField hEnter=new NealTextField("",42.3,15.05,2,3,buttonColor,defaultFont);
    NealTextField miEnter=new NealTextField("",24.02,15.05,2,3,buttonColor,defaultFont);
    NealTextField sEnter=new NealTextField("",24.02,15.05,2,3,buttonColor,defaultFont);
    RoundStackIcon step1=new RoundStackIcon(23.735,step1Text,new Color(buttonColor.getRed(),buttonColor.getGreen(),buttonColor.getBlue(),0),Color.TRANSPARENT);
    RoundStackIcon step2=new RoundStackIcon(23.735,step2Text,new Color(buttonColor.getRed(),buttonColor.getGreen(),buttonColor.getBlue(),0),Color.TRANSPARENT);
    NealGroupButton daysCounter=new NealGroupButton(mainScene,42,23,24,"天",Color.WHITE,buttonColor,Color.BLACK,nextFont);
    NealGroupButton hoursCounter=new NealGroupButton(mainScene,42,23,24,"小时",Color.WHITE,buttonColor,Color.BLACK,nextFont);
    NealGroupButton minutesCounter=new NealGroupButton(mainScene,42,23,24,"分",Color.WHITE,buttonColor,Color.BLACK,nextFont);
    NealGroupButton secondsCounter=new NealGroupButton(mainScene,42,23,24,"秒",Color.WHITE,buttonColor,Color.BLACK,nextFont);
    NealGroupButton quit=new NealGroupButton(mainScene,42,23,24,"关闭",Color.web("#FF9E9E"),Color.RED,Color.WHITE,nextFont);
    HBox buttonCounterGroup;

    static String iName;
    static String iY;
    static String iMo;
    static String iD;
    static String iH;
    static String iMi;
    static String iS;

    FlowPane flow1=enter(y,mo,d,yEnter,moEnter,dEnter,345.65,140.51);
    FlowPane flow2=enter(h,mi,s,hEnter,miEnter,sEnter,345.65,170.25);

    SimpleTransitAnimationBar load=new SimpleTransitAnimationBar(438.77,23.68,buttonColor,mainColor0);

    static boolean done;

    Timer mainTimer;
    Text counterText;

    final static Object lock=new Object();

    static String current="days";

    static boolean legal=true;

    public double getCounterSize(String text){
        int x=text.length();
        return -0.00030039784768262233*x*x*x*x*x*x*x*x*x*x+0.01813128373740655*x*x*x*x*x*x*x*x*x-0.47547116653847243*x*x*x*x*x*x*x*x+7.105082857454049*x*x*x*x*x*x*x-66.690032428504*x*x*x*x*x*x+408.67004199620294*x*x*x*x*x-1645.6986918311616*x*x*x*x+4269.026116075196*x*x*x-6764.106419150634*x*x+5837.259409082208*x-1930.1078663201058;
    }
    public void warn(TimeOutException e){
        System.out.println(e.getMessage());
        legal=false;
    }
    public void counter(){
        new Thread(()-> {
            try{
                Thread.sleep((long) ((Math.random() + 2) * 2000));
            }catch (InterruptedException e1) {
            }
            far.setFont(Font.loadFont(FONT_LIBRARY,20));
            counterText=new Text();
            counterText.setFont(counterFont);
            counterText.setText(mainTimer.getGapDays() + "");
//            daysCounter.avoid(hoursCounter,minutesCounter,secondsCounter);
//            hoursCounter.avoid(daysCounter,minutesCounter,secondsCounter);
//            minutesCounter.avoid(daysCounter,hoursCounter,secondsCounter);
//            secondsCounter.avoid(daysCounter,hoursCounter,minutesCounter);
            buttonCounterGroup = new HBox(daysCounter.getButton(), hoursCounter.getButton(), minutesCounter.getButton(), secondsCounter.getButton(), quit.getButton());
            buttonCounterGroup.setSpacing(30);
            buttonCounterGroup.setAlignment(Pos.CENTER);
            Rectangle counterStackBack=new Rectangle(mainRec.getWidth(),130.63,Color.TRANSPARENT);
            Rectangle nameStackBack=new Rectangle(mainRec.getWidth(),56.35,Color.TRANSPARENT);
            Rectangle buttonStackBack=new Rectangle(mainRec.getWidth(),51.24,Color.TRANSPARENT);
            StackPane counterStack=new StackPane(counterStackBack,counterText);
            StackPane nameStack=new StackPane(nameStackBack,far);
            StackPane buttonStack=new StackPane(buttonStackBack,buttonCounterGroup);
            counterStack.setLayoutX(mainRec.getLayoutX());
            counterStack.setLayoutY(73.27);
            nameStack.setLayoutX(mainRec.getLayoutX());
            nameStack.setLayoutY(mainRec.getLayoutY()+10);
            buttonStack.setLayoutX(mainRec.getLayoutX());
            buttonStack.setLayoutY(204.02);
            done = true;
            synchronized (lock) {
                try {
                    lock.wait();
                }catch(InterruptedException e){}
                Platform.runLater(()->mainPane.getChildren().addAll(nameStack,counterStack,buttonStack));
            }
            daysCounter.getButton().setOnMouseClicked(event -> current="days");
            hoursCounter.getButton().setOnMouseClicked(event -> current="hours");
            minutesCounter.getButton().setOnMouseClicked(event -> current="minutes");
            secondsCounter.getButton().setOnMouseClicked(event -> current="seconds");
            quit.getButton().setOnMouseClicked(event -> {
                Platform.exit();
                System.exit(0);
            });
            //倒计时线程
            new Thread(()->{
                while(true){
                    try {
                        mainTimer = new Timer(iY + iMo + iD + iH + iMi + iS);
                        switch(current){
                            case "days":{
                                counterText.setFont(Font.loadFont(FONT_LIBRARY, getCounterSize(mainTimer.getGapDays()+"")));
                                counterText.setText(mainTimer.getGapDays()+"");
                            }break;
                            case "hours":{
                                counterText.setFont(Font.loadFont(FONT_LIBRARY, getCounterSize(mainTimer.getGapHours()+"")));
                                counterText.setText(mainTimer.getGapHours()+"");
                            }break;
                            case "minutes":{
                                counterText.setFont(Font.loadFont(FONT_LIBRARY, getCounterSize(mainTimer.getGapMinutes()+"")));
                                counterText.setText(mainTimer.getGapMinutes()+"");
                            }break;
                            case "seconds":{
                                counterText.setFont(Font.loadFont(FONT_LIBRARY, getCounterSize(mainTimer.getGapSeconds()+"")));
                                counterText.setText(mainTimer.getGapSeconds()+"");
                            }break;
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch(TimeOutException e1){
                        System.out.println("故障");
                        break;
                    }
                }
                Text over=new Text(iName+"计时结束！");
                over.setFont(counterFont);
                over.setFill(new Color(1,1,1,0));
                Rectangle temp=new Rectangle(mainRec.getWidth(),mainRec.getHeight(),mainColorTransparent);
                temp.setArcHeight(mainRec.getArcHeight());
                temp.setArcWidth(mainRec.getArcWidth());
                temp.setLayoutX(mainRec.getLayoutX());
                temp.setLayoutY(mainRec.getLayoutY());
                StackPane overTemp=new StackPane(temp,over);
                overTemp.setLayoutX(mainRec.getLayoutX());
                overTemp.setLayoutY(mainRec.getLayoutY());
                Platform.runLater(()->mainPane.getChildren().add(overTemp));
                KeyValue v=new KeyValue(temp.fillProperty(),mainColor0);
                KeyValue v1=new KeyValue(over.fillProperty(),Color.BLACK);
                KeyFrame f=new KeyFrame(Duration.millis(1000),event -> {
                    Platform.runLater(()->mainPane.getChildren().add(over));
                },v,v1);
                Timeline l=new Timeline(f);
                l.play();
            }).start();
        }).start();

    }
    public void beginCountDown(){
        prepare.setFont(stepFont);
        prepare.setLayoutX(59.61);
        prepare.setLayoutY(54.54+16.01);
        load.barPane.setLayoutX(58.74);
        load.barPane.setLayoutY(146.26);
        Rectangle temp=new Rectangle(mainRec.getWidth(),mainRec.getHeight(),mainColor0);
        temp.setArcWidth(mainRec.getArcWidth());
        temp.setArcHeight(mainRec.getArcHeight());
        temp.setLayoutX(mainRec.getLayoutX());
        temp.setLayoutY(mainRec.getLayoutY());
        mainPane.getChildren().addAll(prepare,load.barPane);
        mainPane.getChildren().add(temp);
        KeyValue v=new KeyValue(temp.fillProperty(),new Color(mainColor0.getRed(),mainColor0.getGreen(),mainColor0.getBlue(),0));
        KeyFrame f=new KeyFrame(Duration.millis(500),event -> mainPane.getChildren().remove(temp),v);
        Timeline l=new Timeline(f);
        l.play();
        new Thread(()->{
            counter();
            while (!done) {
                Platform.runLater(() -> prepare.setText("正在准备."));
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> prepare.setText("正在准备.."));
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> prepare.setText("正在准备..."));
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lock) {
                Platform.runLater(() -> mainPane.getChildren().removeAll(prepare, load.barPane));
                lock.notifyAll();
            }
        }).start();
    }

    public FlowPane enter(Text a,Text b,Text c,NealTextField aa,NealTextField bb,NealTextField cc,double layoutX,double layoutY){
        a.setFont(nextFont);
        b.setFont(nextFont);
        c.setFont(nextFont);
        FlowPane enterFlow=new FlowPane();
        enterFlow.setPrefSize(159.98,10.44);
        enterFlow.setPadding(new Insets(0,0,0,4));
        enterFlow.setHgap(2);
        enterFlow.setColumnHalignment(HPos.CENTER);
        enterFlow.setStyle("-fx-background-color: rgb(230,230,230);-fx-background-radius: 5");
        enterFlow.setLayoutX(layoutX);
        enterFlow.setLayoutY(layoutY);
        enterFlow.getChildren().addAll(aa.getField(),a,bb.getField(),b,cc.getField(),c);
        return enterFlow;
    }
    public void chooser(){
        beginButtonText.setFont(nextFont);

        name.getField().setLayoutX(345.65);
        name.getField().setLayoutY(68.89);

//        name.getField().getChildren().get(1).setDisable(true);
        aimName.setLayoutX(345.92);
        aimName.setLayoutY(52.35+9.52);
        aimDate.setLayoutX(345.92);
        aimDate.setLayoutY(120.24+9.52);
        aimName.setFont(nextFont);
        aimDate.setFont(nextFont);
        mainPane.getChildren().addAll(name.getField(),flow1,flow2,aimName,aimDate,beginButton.getButton());
        //Ani
        Rectangle temp=new Rectangle(timeChooser.getWidth(),timeChooser.getHeight(),timeChooser.getFill());
        temp.setLayoutX(timeChooser.getLayoutX());
        temp.setLayoutY(timeChooser.getLayoutY());
        mainPane.getChildren().add(temp);
        KeyValue value=new KeyValue(temp.fillProperty(),new Color(buttonColor.getRed(),buttonColor.getGreen(), buttonColor.getBlue(),0));
        KeyFrame frame=new KeyFrame(Duration.millis(200),event -> mainPane.getChildren().remove(temp),value);
        Timeline line=new Timeline(frame);
        AnimationThreadBody a=new AnimationThreadBody(line);
        Thread at=new Thread(a);
        at.start();
        beginButton.getButton().setOnMouseClicked(event -> {
            iName=name.trueField.getText();
            iY=yEnter.trueField.getText();
            iMo=moEnter.trueField.getText();
            iD=dEnter.trueField.getText();
            iH=hEnter.trueField.getText();
            iMi=miEnter.trueField.getText();
            iS=sEnter.trueField.getText();
            far=new Text("距离 "+iName+" 还有");
            try {
                mainTimer = new Timer(iY + iMo + iD + iH + iMi + iS);
                legal=true;
            }catch(TimeOutException e){
                warn(e);
            }
            if(legal) {
                Rectangle temp1 = new Rectangle(mainRec.getWidth(), mainRec.getHeight(), Color.color(mainColor0.getRed(), mainColor0.getGreen(), mainColor0.getBlue(), 0));
                temp1.setLayoutX(mainRec.getLayoutX());
                temp1.setLayoutY(mainRec.getLayoutY());
                temp1.setArcHeight(mainRec.getArcHeight());
                temp1.setArcWidth(mainRec.getArcWidth());
                mainPane.getChildren().add(temp1);
                KeyValue value1 = new KeyValue(temp1.fillProperty(), mainColor0);
                KeyFrame frame1 = new KeyFrame(Duration.millis(500), event1 -> {
                    mainPane.getChildren().removeAll(step1.getStackIcon(), step2.getStackIcon(), stepText, start, aimName, aimDate, name.getField(), flow1, flow2, beginButton.getButton(), timeChooser, chooseButton.getButton(), temp1);
                    beginCountDown();
                }, value1);
                Timeline line1 = new Timeline(frame1);
                new Thread(new AnimationThreadBody(line1)).start();
            }
        });
    }
    public void secondPage(){
        mainPane.getChildren().addAll(step1.getStackIcon(),step2.getStackIcon(), stepText,chooseButton.getButton(),start,logo);
        step1.getStackIcon().setLayoutX(57.92);
        step1.getStackIcon().setLayoutY(81.56);
        step2.getStackIcon().setLayoutX(57.92);
        step2.getStackIcon().setLayoutY(155.95);
        stepText.setFont(stepFont);
        stepText.setLayoutX(122.58);
        stepText.setLayoutY(82.54+15);
        choose.setFont(nextFont);
        start.setFont(stepFont);
        start.setLayoutX(123.06);
        start.setLayoutY(170.62+15);
        logo.setFont(stepFont);
        logo.setLayoutX(227.01);
        logo.setLayoutY(268.84+16.45);
        //全部设置为透明
        stepText.setFill(Color.TRANSPARENT);
        start.setFill(Color.TRANSPARENT);
        choose.setFill(Color.TRANSPARENT);
        logo.setFill(Color.TRANSPARENT);
        //渐变动画：
        KeyValue v1=new KeyValue(((Circle)(step1.getStackIcon().getChildren().get(0))).fillProperty(),buttonColor);
        KeyValue v2=new KeyValue(((Text)(step1.getStackIcon().getChildren().get(1))).fillProperty(),Color.BLACK);
        KeyValue v3=new KeyValue(((Circle)(step2.getStackIcon().getChildren().get(0))).fillProperty(),buttonColor);
        KeyValue v4=new KeyValue(((Text)(step2.getStackIcon().getChildren().get(1))).fillProperty(),Color.BLACK);
        KeyValue v5=new KeyValue(stepText.fillProperty(),Color.BLACK);
        KeyValue v6=new KeyValue(start.fillProperty(),Color.BLACK);
        KeyValue v7=new KeyValue(((Rectangle)(chooseButton.getButton().getChildren().get(0))).fillProperty(),buttonColor.brighter());
        KeyValue v8=new KeyValue(choose.fillProperty(),Color.BLACK);
        KeyValue v9=new KeyValue(logo.fillProperty(),Color.BLACK);
        KeyFrame frame=new KeyFrame(Duration.millis(500),v1,v2,v3,v4,v5,v6,v7,v8,v9);
        Timeline line=new Timeline(frame);
        AnimationThreadBody a0=new AnimationThreadBody(line);
        Thread t0=new Thread(a0);
        t0.start();
        chooseButton.getButton().setOnMouseClicked(event -> {
            if(mainPane.getChildren().get(mainPane.getChildren().size()-1)!=timeChooser) {
                mainPane.getChildren().add(timeChooser);
                timeChooser.setArcWidth(10);
                timeChooser.setArcHeight(10);
                timeChooser.setLayoutX(329.71 + 191.86);
                timeChooser.setLayoutY(37.27);
                timeChooser.setEffect(new DropShadow(41, new Color(0, 0, 0, 0.11)));
                KeyValue c1 = new KeyValue(timeChooser.widthProperty(), 191.86, Interpolator.EASE_OUT);
                KeyValue c2 = new KeyValue(timeChooser.layoutXProperty(), 329.71);
                KeyValue c3 = new KeyValue(timeChooser.fillProperty(), buttonColor);
//            KeyValue c4=new KeyValue(timeChooser.effectProperty(),new DropShadow(40,new Color(0,0,0,0.2)));
                KeyFrame frame1 = new KeyFrame(Duration.millis(300), event1 -> {
                    chooser();
                }, c1, c2, c3);
                Timeline line1 = new Timeline(frame1);
                line1.play();

            }
        });
    }

    public void start(Stage primaryStage){
        //主界面
        next.setFont(nextFont);
        step1Text.setFont(stepFont);
        step2Text.setFont(stepFont);
//        System.out.println("file://"+new File("OPPOSans-H.ttf").getAbsolutePath()+" "+mainFont);
        mainPane.setPrefSize(560,300);
        mainPane.getChildren().addAll(mainRec,welcome,description,nextButton.getButton());
        mainPane.setStyle("-fx-background-radius: 26;-fx-background-color: WHITE;-fx-border-width: 1;-fx-border-color: rgba(1,1,1,0.3);-fx-border-radius: 26");
        mainRec.setArcWidth(23);
        mainRec.setArcHeight(23);
        mainRec.setFill(mainColor0);
        mainRec.setLayoutX(26.92);
        mainRec.setLayoutY(46.07);
        welcome.setFont(mainFont);
        welcome.setLayoutX(48.06);
        welcome.setLayoutY(90);
        description.setFont(defaultFont);
        description.setLayoutX(48.06);
        description.setLayoutY(122);
        mainScene.setFill(Color.rgb(1,1,1,0));
        //监听区
        nextButton.getButton().setOnMouseClicked(event -> {
            KeyValue oldValue=new KeyValue(mainRec.layoutYProperty(),26.07);
            KeyFrame oldFrame=new KeyFrame(Duration.millis(300),oldValue);
            Timeline line0=new Timeline(oldFrame);
//            line0.play();
            KeyValue textValue0=new KeyValue(description.fillProperty(),Color.rgb(1,1,1,0));
            KeyValue textValue1=new KeyValue(welcome.fillProperty(),Color.rgb(1,1,1,0));
            KeyValue textValue2=new KeyValue(((Rectangle)(nextButton.getButton().getChildren().get(0))).fillProperty(),Color.rgb(1,1,1,0));
            KeyValue textValue3=new KeyValue(((Text)(nextButton.getButton().getChildren().get(1))).fillProperty(),Color.rgb(1,1,1,0));
            KeyFrame textFrame=new KeyFrame(Duration.millis(200),event1 -> {
                mainPane.getChildren().removeAll(welcome,description,nextButton.getButton());
                nextButton.overButton();
                secondPage();
            },textValue0,textValue1,textValue3,textValue2);
            Timeline line1=new Timeline(textFrame);
//            line1.play();
            AnimationThreadBody animation0=new AnimationThreadBody(line0);
            AnimationThreadBody animation1=new AnimationThreadBody(line1);
            Thread animationThread0=new Thread(animation0);
            Thread animationThread1=new Thread(animation1);
            animationThread0.start();
            animationThread1.start();
        });

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
