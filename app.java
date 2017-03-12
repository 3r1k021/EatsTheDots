import objectdraw.*;
import java.awt.*;

import java.util.*;

public class app extends FrameWindowController 
{
    enemy en;
    boolean ready=false;

    public void begin(){

        ((JDrawingCanvas)canvas).setBackground(Color.black);
        canvas.clear();
        enemy E=new enemy(canvas,90);
        for (int x=1;x<=50;x++){
            en=new enemy(canvas);
            en.start();
        }
        ready=true;

    }
    public void onMouseMove(Location p){
     en.moveYou(p.getX(),p.getY(),canvas);   
    }
    public void onMouseClick(Location p){
        en.firstclick(p.getX(),p.getY());
    }

    

    public void generate(){
        enemy en=new enemy(canvas);
        en.start();
    }

}
