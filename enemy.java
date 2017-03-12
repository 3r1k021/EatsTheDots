import objectdraw.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class enemy extends ActiveObject
{
    
    static FilledOval you;
    FilledOval enemies;
    static int count=50;
    Random r=new Random();
    boolean moving=true;
    DrawingCanvas can;
    int motion;
    boolean pause=false;
    enemy en;
    static int cre=1;
    static boolean ready=false;
    int size;
    static int youSize=12;
    static boolean good=true;
    public enemy(DrawingCanvas c)
    {
        can=c;
        int xCoor=r.nextInt(384);  
        int yCoor=0;
        size=r.nextInt(youSize+10)+5;
        enemies=new FilledOval(xCoor,yCoor,size,size,c);
        enemies.setColor(Color.white);
        cre++;
    }

    public void firstclick(double x,double y){
        you.moveTo(x,y);
        ready=true;
    }

    public enemy(DrawingCanvas c, int q){
        if (you==null){
            you=new FilledOval(c.getWidth()/2,c.getHeight()/2,12,12,c);
            you.setColor(Color.red);
        }
    }

    public void run() {
        motion=r.nextInt(4);
        int speed=r.nextInt(50)+50;
        int up=1+r.nextInt(5);
        int over=1+r.nextInt(5);
        while (moving) {

            move(up,over);
            pause(speed); 
        }
    }

    public void move(int Up,int Over){

        this.generate();
        int j=can.getHeight()-20;

        int turn=r.nextInt(2);
        if (enemies.getX()<=0 || enemies.getX()>=can.getWidth() || enemies.getY()>=can.getHeight()-15 || enemies.getY()<0){
            count--;
            enemies.hide();
            moving=false;
        }

        if (motion==0){
            enemies.move(Over,Up);
        }else if (motion==1){
            enemies.move(-1*Over,Up);
        }else if (motion==2){
            enemies.move(Over,-1*Up);
        }else{
            enemies.move(-1*Over,-1*Up);

        }
        this.checkOverlap();
    }

    public void generate(){

        while (count<=30){
            en=new enemy(can);
            en.start();
            count++;
        }
    }

    public void moveYou(double x,double y,DrawingCanvas c){

        if (you==null){
            you=new FilledOval(x,y,youSize,youSize,c);
            you.setColor(Color.red);
        }else{

            if (good && ready){
                you.moveTo(x,y);

            }
        }

    }

    public void checkOverlap(){
        if (ready){
            if (you.overlaps(enemies)){
                if (youSize>size){
                    youSize+=(size/10);
                    you.setWidth(youSize+(size/10));
                    you.setHeight(youSize+(size/10));
                    enemies.hide();
                    count--;
                    moving=false;
                }else {

                    good=false;
                    you.setColor(Color.black);

                }
            }else{

            }
        }
    }
}