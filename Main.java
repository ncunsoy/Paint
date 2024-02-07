import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

//Oval çizilmesi
//Oval taşınması

public class Main {

    public static void main(String[] args) {

        Frame frame=new Frame();
    }
}

class Rectangle extends Component{
    public Rectangle(Graphics g,int x, int y, int width, int heigth)
    {
        g.fillRect(x,y,width,heigth);
    }
}

class Oval extends Component{
    public Oval(Graphics g,int x, int y, int width, int heigth)
    {
        int newWidth=(int)(width*1.4);
        int newHeigth=(int)(heigth*1.4);
        if(Frame.x>=Frame.firstX){
            if(Frame.y>=Frame.firstY){
                if((y-heigth/5)>110){
                    g.fillOval((x-width/5),(y-heigth/5),newWidth,newHeigth);
                }

            }

            else{
                if((Frame.y-heigth/5)>110){
                    g.fillOval((x-width/5),(y+heigth/5),newWidth,newHeigth);
                }
            }

        }
        else {
            if(Frame.y>=Frame.firstY){
                if((y-heigth/5)>110){
                    g.fillOval((x+width/5),(y-heigth/5),newWidth,newHeigth);
                }

            }

            else{
                if(Frame.y-heigth/5>110){
                    g.fillOval((x+width/5),(y+heigth/5),newWidth,newHeigth);
                }

            }

        }
    }
}

class DrawingArea extends JPanel{
    int [] startPoint={0,0};
    static int [] endPoint={0,0};
    boolean add=false;
    int width=0;
    int heigth=0;
    static boolean isDraw=false;
    int h1=0;
    int w1=0;

    public void clear(Graphics g){
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(0,0,2000,10);
    }
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(0,0,2000,10);

        if(Frame.method.equalsIgnoreCase("Draw")){
            g.setColor(Frame.color);
            if (isDraw){
                g.fillOval(Frame.oldX,Frame.oldY,3,3);
                Frame.lines.add(new int[]{Frame.oldX,Frame.oldY});
                Frame.lineColors.add(Frame.color);
            }
            //System.out.println(Frame.lines);

        }

        else if(Frame.method.equalsIgnoreCase("Rectangle")){
            g.setColor(Frame.color);
            if(Frame.x>=Frame.firstX){
                if(Frame.y>=Frame.firstY){
                    new Rectangle(g,Frame.firstX,Frame.firstY,Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY));
                    startPoint= new int[]{Frame.firstX, Frame.firstY};
                    add=true;
                }

                else{
                    new Rectangle(g,Frame.firstX,Frame.y,Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY));
                    startPoint= new int[]{Frame.firstX, Frame.y};
                    add=true;
                }

            }
            else {
                if(Frame.y>=Frame.firstY){
                    new Rectangle(g,Frame.x,Frame.firstY,Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY));
                    startPoint= new int[]{Frame.x, Frame.firstY};
                    add=true;
                }

                else{
                    new Rectangle(g,Frame.x,Frame.y,Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY));
                    startPoint= new int[]{Frame.x, Frame.y};
                    add=true;
                }

            }
        }

        else if(Frame.method.equalsIgnoreCase("Oval")){
            g.setColor(Frame.color);
            int width=Math.abs(Frame.x-Frame.firstX);
            int heigth=Math.abs(Frame.y-Frame.firstY);
            add=true;

            if(Frame.x>=Frame.firstX){
                if(Frame.y>=Frame.firstY){
                    startPoint= new int[]{Frame.firstX-width/5, Frame.firstY-heigth/5};
                    new Oval(g,Frame.firstX,Frame.firstY,Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY));

                }

                else{
                    new Oval(g,Frame.firstX,Frame.y,Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY));
                    startPoint= new int[]{Frame.firstX-width/5, Frame.y+heigth/5};
                    new Oval(g,Frame.firstX,Frame.y,Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY));
                }

            }
            else {
                if(Frame.y>=Frame.firstY){
                    new Oval(g,Frame.x,Frame.firstY,Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY));
                    startPoint= new int[]{Frame.x+width/5, Frame.firstY-heigth/5};
                    new Oval(g,Frame.x,Frame.firstY,Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY));
                }

                else{
                    new Oval(g,Frame.x,Frame.y,Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY));
                    startPoint= new int[]{Frame.x+width/5, Frame.y+heigth/5};
                    new Oval(g,Frame.x,Frame.y,Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY));
                }

            }
        }

        else{
            int i=0;
            Color color=Color.white;
            String sType="";


            for (;i<Frame.shapes.size();i++){
                int [] locations=Frame.shapes.get(i);
                int x1=locations[0];
                int y1=locations[1];
                int x2 =locations[2]+x1;
                int y2=locations[3]+y1;
                width=locations[2];
                heigth=locations[3];


                if(Frame.firstX>=x1 && Frame.firstX<=x2 && Frame.firstY>=y1 && Frame.firstY<=y2){
                    w1=Frame.x-x1;
                    h1=Frame.y-y1;
                    break;
                }
            }

            if(i<Frame.shapes.size()){
                int [] points={Frame.x,Frame.y,width,heigth};
                sType=Frame.shapeNames.get(i);
                color=Frame.colors.get(i);
                add=true;

                Frame.colors.add(color);
                Frame.shapeNames.add(sType);
                Frame.shapes.add(points);

                Frame.shapes.remove(i);
                Frame.colors.remove(i);
                Frame.shapeNames.remove(i);

            }
            clear(this.getGraphics());

            int j=0;
            for(;j<Frame.shapes.size()-1;j++){
                g.setColor(Frame.colors.get(j));
                int [] p=Frame.shapes.get(j);
                String type=Frame.shapeNames.get(j);
                if(p[1]>=112){
                    if(type.equalsIgnoreCase("Rect"))
                        g.fillRect(p[0],p[1],p[2], p[3]);
                    else
                        g.fillOval(p[0],p[1],p[2], p[3]);
                }


            }

            if(j<Frame.colors.size())
                color=(Frame.colors.get(j));

            if(j<Frame.shapeNames.size())
                sType=(Frame.shapeNames.get(j));


            g.setColor(color);
            if(Frame.y-h1>112){
                if(sType.equalsIgnoreCase("Rect"))
                    g.fillRect(Frame.x-w1,Frame.y-h1,width, heigth);
                else
                    g.fillOval(Frame.x-w1,Frame.y-h1,width, heigth);
            }
            else {
                if(sType.equalsIgnoreCase("Rect"))
                    g.fillRect(Frame.x-w1,112,width, heigth);
                else
                    g.fillOval(Frame.x-w1,112,width, heigth);
            }


            for(int k=0;k<Frame.lines.size();k++){
                int [] coordinate=Frame.lines.get(k);
                if(k<Frame.lineColors.size())
                    g.setColor(Frame.lineColors.get(k));
                g.fillOval(coordinate[0],coordinate[1],3, 3);
            }



        }
    }

    public void addList() {
        if(add){
            if(Frame.method.equalsIgnoreCase("Rectangle")){
                int [] points={startPoint[0],startPoint[1],Math.abs(Frame.x-Frame.firstX),Math.abs(Frame.y-Frame.firstY)};
                Color shape=Frame.color;
                Frame.shapes.add(points);
                Frame.shapeNames.add("Rect");
                Frame.colors.add(shape);
            }
            else if(Frame.method.equalsIgnoreCase("Oval")){
                int [] points={startPoint[0],startPoint[1],(int)(Math.abs(Frame.x-Frame.firstX)*1.4),(int)(Math.abs(Frame.y-Frame.firstY)*1.4)};
                Color shape=Frame.color;
                Frame.shapes.add(points);
                Frame.shapeNames.add("Oval");
                Frame.colors.add(shape);
            }
            else if(Frame.method.equalsIgnoreCase("Move")){
                if(Frame.shapes.size()-1>=0){
                    int [] old=Frame.shapes.get(Frame.shapes.size()-1);
                    int [] points=new int[]{0,0,0,0};

                    if(Frame.y-h1<110){
                        System.out.println("aaaaaaaaaa");
                        points= new int[]{Frame.x - w1, 112, old[2], old[3]};

                    }
                    else{
                        points= new int[]{Frame.x - w1, Frame.y - h1, old[2], old[3]};
                    }

                    Frame.shapes.remove(Frame.shapes.size()-1);
                    Frame.shapes.add(points);
                    System.out.println(Frame.shapes.size());
                    for(int i=0;i<points.length;i++)
                        System.out.println(points[i]);
                }

            }

        }
        add=false;
    }
}



class Frame extends JFrame implements MouseInputListener {
    static Color color=Color.BLACK;
    static int x=0;
    static int y=0;
    static int oldX=0;
    static int oldY=0;
    static String method="";
    static boolean isArea=false;
    static int firstX=0;
    static int firstY=0;
    static Color shapeColor=Color.BLACK;
    static ArrayList<int[]> shapes=new ArrayList<>();
    static ArrayList <String> shapeNames =new ArrayList<>();
    static ArrayList <Color> colors=new ArrayList<>();
    static ArrayList <Color> lineColors=new ArrayList<>();
    static ArrayList <int[]> lines=new ArrayList<>();
    DrawingArea drawingArea=new DrawingArea();

    public Frame(){
        setSize(1500,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        ColorPanel colorPanel=new ColorPanel();
        ButtonPanel buttonPanel=new ButtonPanel();


        JPanel topPanel=new JPanel();
        topPanel.setLayout(new GridLayout(2,1));
        topPanel.add(colorPanel);
        topPanel.add(buttonPanel);

        add(topPanel,BorderLayout.NORTH);
        add(drawingArea,BorderLayout.CENTER);

        addMouseListener(this);
        addMouseMotionListener(this);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if((e.getX()>=330 && e.getX()<=430) && (e.getY()>=45 && e.getY()<=65)){
            color=Color.BLUE;
        }
        else if((e.getX()>=455 && e.getX()<=555) && (e.getY()>=45 && e.getY()<=65))
            color=Color.RED;
        else if((e.getX()>=580 && e.getX()<=680) && (e.getY()>=45 && e.getY()<=65))
            color=Color.GREEN;
        else if((e.getX()>=705 && e.getX()<=805) && (e.getY()>=45 && e.getY()<=65))
            color=Color.YELLOW;
        else if((e.getX()>=830 && e.getX()<=930) && (e.getY()>=45 && e.getY()<=65))
            color=Color.ORANGE;
        else if((e.getX()>=955 && e.getX()<=1055) && (e.getY()>=45 && e.getY()<=65))
            color=Color.MAGENTA;
        else if((e.getX()>=1080 && e.getX()<=1180) && (e.getY()>=45 && e.getY()<=65))
            color=Color.BLACK;
        System.out.println(e.getX()+"  "+e.getY());

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getY()>115){
            isArea=true;
            firstX=e.getX();
            firstY=e.getY();
        }
        oldX=e.getX();
        oldY=e.getY();
        if(method.equalsIgnoreCase("Draw") && isArea){
            drawingArea.isDraw=true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(method.equalsIgnoreCase("Rectangle")  || method.equalsIgnoreCase("Oval") ||  method.equalsIgnoreCase("Move")){
            DrawingArea.endPoint[0]=e.getX();
            DrawingArea.endPoint[1]=e.getY();
            x=e.getX();
            y=e.getY();
            drawingArea.addList();
        }
        isArea=false;
        DrawingArea.isDraw=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("enter");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("exit");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(isArea) {
            x = e.getX();
            y = e.getY();
            oldX=x;
            oldY=y;

            shapeColor=e.getComponent().getGraphics().getColor();
            drawingArea.paint(getGraphics());


        }
        if(e.getY()<115)
            isArea=false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("move");
    }


}
class ButtonPanel extends JPanel{
    public ButtonPanel(){
        setLayout(new FlowLayout());

        JButton button1=new JButton("Dikdörtgen Çiz");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Frame.method="Rectangle";}
        });
        add(button1);

        JButton button2=new JButton("Oval Çiz");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.method="Oval";
            }
        });
        add(button2);

        JButton button3=new JButton("Kalemle Çiz");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.method="Draw";
            }
        });
        add(button3);

        JButton button4=new JButton("Taşı");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.method="Move";
            }
        });
        add(button4);

    }

}


class ColorPanel extends JPanel{
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(325, 20, 100, 15);
        g.setColor(Color.RED);
        g.fillRect(450, 20, 100, 15);
        g.setColor(Color.GREEN);
        g.fillRect(575, 20, 100, 15);
        g.setColor(Color.YELLOW);
        g.fillRect(700, 20, 100, 15);
        g.setColor(Color.ORANGE);
        g.fillRect(825, 20, 100, 15);
        g.setColor(Color.MAGENTA);
        g.fillRect(950, 20, 100, 15);
        g.setColor(Color.BLACK);
        g.fillRect(1075, 20, 100, 15);

    }
}




