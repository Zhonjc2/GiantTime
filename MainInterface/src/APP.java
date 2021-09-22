public class APP {

    public static void main(String[] args) {

        Tickets t=new Tickets(5);

        new Producer(t).start();

        new Consumer(t).start();

    }
}

class Tickets {

    protected static int size ;//总票数

    protected static int sum;//每次随机获得的票数

    static boolean available=false ;

    protected int sizeM;//总次数

    int number=0;//次数

    public Tickets(int sizeM) {

        this.sizeM=sizeM;

    }

    public synchronized void put() { //平台存票

// try {Thread.sleep(5000);}

// catch(Exception e) {}
//        System.out.println("putThread");//又执行了一次put，然后wait()释放互斥锁。
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(available)

            try {wait();}

            catch(Exception e){ }

        Random k=new Random();

        k.run();

        size=k.size;

        sum=k.sum;
//        System.out.println(sum);


        System.out.println("第"+(++number)+"次存入"+k.sum+"张票");

//System.out.println();


        available=true;


        notify();

    }

    public synchronized void sell() { //平台售票
//        System.out.println(sum);

//        System.out.println("sellThread");
        int i=1;

        if(!available) {
            System.out.println(5);

            try {wait();}

            catch(Exception e){}

        }

         /*else */if(sum<30) {

            System.out.println("第"+number+"次第"+1+"波卖了"+sum+"张");
            available=false;
        }

        else if(sum>=30) {

            for( i=1;sum>30;i++) {

                System.out.println("第"+number+"次第"+i+"波卖了30张");

                sum=sum-30;

                if(sum<30) {

                    i=i+1;

                    System.out.println("第"+number+"次第"+i+"波卖了"+sum+"张");
                    available=false;

                }

            }

        }

        notify();

        if(number==sizeM)

            number=sizeM+1;

    }

}

class Producer extends Thread{    //存票类线程

    Tickets t=null;

    public Producer(Tickets t) {

        this.t=t;

    }

    public void run() {

        while(t.number<t.sizeM) {

// System.out.println();

            t.put();

// System.out.println();

        }

    }

}

class Consumer extends Thread{   //售票类线程

    Tickets t=null;

    public Consumer( Tickets t) {

        this.t=t;

    }

    public void run() {

        while(t.number<=t.sizeM) {

            System.out.println();

            t.sell();

        }

    }

}

class Random {

    protected int size ;//总票数

    protected int sum;//每次随机获得的票数

    boolean available =false;

    public void run() {
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){}
        sum=(int)(Math.random()*100);

        size=size+sum;

        try { //1s执行一次随机数；

            Thread.sleep(1000);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

    public int getsum() {

        return sum;

    }

    public int getsize() {

        return size;

    }

    public boolean getavailable() {

        return available;

    }

}