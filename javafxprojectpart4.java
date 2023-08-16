package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;



enum MyColor{
    TRANSPARENT(0, 0, 0, 0),
    ALICEBLUE(240, 248, 255, 255),
    ANTIQUEWHITE(250, 235, 215, 255),
    AQUA(0, 255, 255, 255),
    AQUAMARINE(127, 255, 212, 255),
    AZURE(240, 255, 255, 255),
    BEIGE(245, 245, 220, 255),
    BISQUE(255, 228, 196, 255),
    BLACK(0, 0, 0, 255),
    BLANCHEDALMOND(255, 235, 205, 255),
    BLUE(0, 0, 255, 255),
    BLUEVIOLET(138, 43, 226, 255),
    BROWN(165, 42, 42, 255),
    BURLYWOOD(222, 184, 135, 255),
    CADETBLUE(95, 158, 160, 255),
    CHARTREUSE(127, 255, 0, 255),
    CHOCOLATE(210, 105, 30, 255),
    CORAL(255, 127, 80, 255),
    CORNFLOWERBLUE(100, 149, 237, 255),
    CORNSILK(255, 248, 220, 255),
    CRIMSON(220, 20, 60, 255),
    CYAN(0, 255, 255, 255),
    DARKBLUE(0, 0, 139, 255),
    DARKCYAN(0, 139, 139, 255),
    DARKGOLDENROD(184, 134, 11, 255),
    DARKGRAY(169, 169, 169, 255),
    DARKGREEN(0, 100, 0, 255),
    DARKGREY(169, 169, 169, 255),
    DARKKHAKI(189, 183, 107, 255),
    DARKMAGENTA(139, 0, 139, 255),
    DARKOLIVEGREEN(85, 107, 47, 255),
    DARKORANGE(255, 140, 0, 255),
    DARKORCHID(153, 50, 204, 255),
    DARKRED(139, 0, 0, 255),
    DARKSALMON(233, 150, 122, 255),
    DARKSEAGREEN(143, 188, 143, 255),
    DARKSLATEBLUE(72, 61, 139, 255),
    DARKSLATEGRAY(47, 79, 79, 255),
    DARKSLATEGREY(47, 79, 79, 255),
    DARKTURQUOISE(0, 206, 209, 255),
    DARKVIOLET(148, 0, 211, 255),
    DEEPPINK(255, 20, 147, 255),
    DEEPSKYBLUE(0, 191, 255, 255),
    DIMGRAY(105, 105, 105, 255),
    DIMGREY(105, 105, 105, 255),
    DODGERBLUE(30, 144, 255, 255),
    FIREBRICK(178, 34, 34, 255),
    FLORALWHITE(255, 250, 240, 255),
    FORESTGREEN(34, 139, 34, 255),
    FUCHSIA(255, 0, 255, 255),
    GAINSBORO(220, 220, 220, 255),
    GHOSTWHITE(248, 248, 255, 255),
    GOLD(255, 215, 0, 255),
    GOLDENROD(218, 165, 32, 255),
    GRAY(128, 128, 128, 255),
    GREY(128, 128, 128, 255),
    GREEN(0, 128, 0, 255),
    GREENYELLOW(173, 255, 47, 255),
    HONEYDEW(240, 255, 240, 255),
    HOTPINK(255, 105, 180, 255),
    INDIANRED(205, 92, 92, 255),
    INDIGO(75, 0, 130, 255),
    IVORY(255, 255, 240, 255),
    KHAKI(240, 230, 140, 255),
    LAVENDER(230, 230, 250, 255),
    LAVENDERBLUSH(255, 240, 245, 255),
    LAWNGREEN(124, 252, 0, 255),
    LEMONCHIFFON(255, 250, 205, 255),
    LIGHTBLUE(173, 216, 230, 255),
    LIGHTCORAL(240, 128, 128, 255),
    LIGHTCYAN(224, 255, 255, 255),
    LIGHTGOLDENRODYELLOW(250, 250, 210, 255),
    LIGHTGRAY(211, 211, 211, 255),
    LIGHTGREEN(144, 238, 144, 255),
    LIGHTGREY(211, 211, 211, 255),
    LIGHTPINK(255, 182, 193, 255),
    LIGHTSALMON(255, 160, 122, 255),
    LIGHTSEAGREEN(32, 178, 170, 255),
    LIGHTSKYBLUE(135, 206, 250, 255),
    LIGHTSLATEGRAY(119, 136, 153, 255),
    LIGHTSLATEGREY(119, 136, 153, 255),
    LIGHTSTEELBLUE(176, 196, 222, 255),
    LIGHTYELLOW(255, 255, 224, 255),
    LIME(0, 255, 0, 255),
    LIMEGREEN(50, 205, 50, 255),
    LINEN(250, 240, 230, 255),
    MAGENTA(255, 0, 255, 255),
    MAROON(128, 0, 0, 255),
    MEDIUMAQUAMARINE(102, 205, 170, 255),
    MEDIUMBLUE(0, 0, 205, 255),
    MEDIUMORCHID(186, 85, 211, 255),
    MEDIUMPURPLE(147, 112, 219, 255),
    MEDIUMSEAGREEN(60, 179, 113, 255),
    MEDIUMSLATEBLUE(123, 104, 238, 255),
    MEDIUMSPRINGGREEN(0, 250, 154, 255),
    MEDIUMTURQUOISE(72, 209, 204, 255),
    MEDIUMVIOLETRED(199, 21, 133, 255),
    MIDNIGHTBLUE(25, 25, 112, 255),
    MINTCREAM(245, 255, 250, 255),
    MISTYROSE(255, 228, 225, 255),
    MOCCASIN(255, 228, 181, 255),
    NAVAJOWHITE(255, 222, 173, 255),
    NAVY(0, 0, 128, 255),
    OLDLACE(253, 245, 230, 255),
    OLIVE(128, 128, 0, 255),
    OLIVEDRAB(107, 142, 35, 255),
    ORANGE(255, 165, 0, 255),
    ORANGERED(255, 69, 0, 255),
    ORCHID(218, 112, 214, 255),
    PALEGOLDENROD(238, 232, 170, 255),
    PALEGREEN(152, 251, 152, 255),
    PALETURQUOISE(175, 238, 238, 255),
    PALEVIOLETRED(219, 112, 147, 255),
    PAPAYAWHIP(255, 239, 213, 255),
    PEACHPUFF(255, 218, 185, 255),
    PERU(205, 133, 63, 255),
    PINK(255, 192, 203, 255),
    PLUM(221, 160, 221, 255),
    POWDERBLUE(176, 224, 230, 255),
    PURPLE(128, 0, 128, 255),
    REBECCAPURPLE(102, 51, 153, 255),
    RED(255, 0, 0, 255),
    ROSYBROWN(188, 143, 143, 255),
    ROYALBLUE(65, 105, 225, 255),
    SADDLEBROWN(139, 69, 19, 255),
    SALMON(250, 128, 114, 255),
    SANDYBROWN(244, 164, 96, 255),
    SEAGREEN(46, 139, 87, 255),
    SEASHELL(255, 245, 238, 255),
    SIENNA(160, 82, 45, 255),
    SILVER(192, 192, 192, 255),
    SKYBLUE(135, 206, 235, 255),
    SLATEBLUE(106, 90, 205, 255),
    SLATEGRAY(112, 128, 144, 255),
    SLATEGREY(112, 128, 144, 255),
    SNOW(255, 250, 250, 255),
    SPRINGGREEN(0, 255, 127, 255),
    STEELBLUE(70, 130, 180, 255),
    TAN(210, 180, 140, 255),
    TEAL(0, 128, 128, 255),
    THISTLE(216, 191, 216, 255),
    TOMATO(255, 99, 71, 255),
    TURQUOISE(64, 224, 208, 255),
    VIOLET(238, 130, 238, 255),
    WHEAT(245, 222, 179, 255),
    WHITE(255, 255, 255, 255),
    WHITESMOKE(245, 245, 245, 255),
    YELLOW(255, 255, 0, 255),
    YELLOWGREEN(154, 205, 50, 255);

    private int r;
    private int g;
    private int b;
    private int a;
    private String rgba;


    MyColor(int red, int grn, int blu, int opa){
        setR(red);
        setG(grn);
        setB(blu);
        setA(opa);
        setRGBA();
    }

    public void setR(int red){
        if (red >= 0 && red <= 255){
            this.r = red;
        } else {
            this.r = 0;
        }
    }
    
    public void setG(int grn){
        if (grn >= 0 && grn <= 255){
            this.g = grn;
        } else {
            this.g = 0;
        }
    }

    public void setB(int blu){
        if (blu >= 0 && blu <= 255){
            this.b = blu;
        } else {
            this.b = 0;
        }
    }

    public void setA(int opa){
        if (opa >= 0 && opa <= 255){
            this.a = opa;
        } else {
            this.a = 0;
        }
    }

    public void setRGBA(){
        int o = GetA();
        int r = GetR();
        int g = GetG();
        int b = GetB();
        String ohex = String.format("%02X", (0xFF & o));
        String rhex = String.format("%02X", (0xFF & r));
        String ghex = String.format("%02X", (0xFF & g));
        String bhex = String.format("%02X", (0xFF & b));
        String rgbalower = rhex + ghex + bhex + ohex;
        String newrgba = "0x" + rgbalower.toUpperCase();
        this.rgba = newrgba;
    }

    public static MyColor generateRandomMyColor() {
        MyColor[] colors = MyColor.values();
        int numofMyColors = colors.length;
        int index = new Random().nextInt(numofMyColors);
        return colors[index];
    }

    public int GetR(){
        return r;
    }

    public int GetG(){
        return g;
    }

    public int GetB(){
        return b;
    }

    public int GetA(){
        return a;
    }

    public String GetRGBA(){ //returns STRING of the rgba hex
        return this.rgba;

    }
    public Color GetJavaFXColor() { 
        return Color.valueOf(this.GetRGBA());
    }
}

class MyPoint{
    private double x;
    private double y;
    private MyColor color;

    public MyPoint(){//default constructor
        Set_x(0); 
        Set_y(0);
        Set_MyColor(MyColor.BLACK);
    }
    public MyPoint(double x_input, double y_input, MyColor newcolor){//parametrized constructor
        Set_x(x_input);
        Set_y(y_input);
        Set_MyColor(Optional.ofNullable(newcolor).orElse(MyColor.BLACK));
    }
    public MyPoint(double x_input, double y_input){//parametrized constructor
        Set_x(x_input);
        Set_y(y_input);
        Set_MyColor(MyColor.BLACK);
    }
    public MyPoint(MyPoint tocopy){
        Set_x(tocopy.Get_x());
        Set_y(tocopy.Get_y());
        Set_MyColor(tocopy.Get_MyColor());
    }

    public double Get_x(){
        return this.x;
    }
    public double Get_y(){
        return this.y;
    }
    public MyColor Get_MyColor(){
        return this.color;
    }
    public void Set_x(double xnew){
        this.x = xnew;
    }
    public void Set_y(double ynew){
        this.y = ynew;
    }
    public void Set_MyColor(MyColor newcolor){
        this.color = newcolor;
    }
    public static MyPoint Add(MyPoint A, MyPoint B){ //adding: point A + point B
        MyPoint sum = new MyPoint(A.Get_x() + B.Get_x(), A.Get_y() + B.Get_y());
        return sum;
    }
    public static MyPoint Sub(MyPoint A, MyPoint B){ //subtracting: point A - point B
        MyPoint dif = new MyPoint(A.Get_x() - B.Get_x(), A.Get_y() - B.Get_y());
        return dif;
    }
    public static String StringPt(MyPoint print){
        return "(" + print.Get_x() + ", " + print.Get_y() + ")";
    }
    public void draw(GraphicsContext GC){
        GC.setFill(this.Get_MyColor().GetJavaFXColor()); 
        GC.fillRect(this.Get_x(), this.Get_y(), 1, 1);
    }
}

interface MyShapeInterface {
    public abstract MyRectangle getMyBoundingRectangle();

    public abstract boolean pointInMyShape(MyPoint point);

    public static ArrayList<MyPoint> intersectMyShapes(MyShape shape1, MyShape shape2, GraphicsContext GC, MyColor color){
        ArrayList<MyPoint> intersectingPoints = new ArrayList<MyPoint>(0);
        for (int x = 0; x <= GC.getCanvas().getWidth(); x++){
            for (int y = 0; y <= GC.getCanvas().getHeight(); y++){
                if(shape1.pointInMyShape(new MyPoint(x, y, color)) && shape2.pointInMyShape(new MyPoint(x, y, color))){
                    intersectingPoints.add(new MyPoint(x, y, color));
                }
            }
        }
        return intersectingPoints; 
    }

    public static Canvas drawIntersectMyShapes(MyShape shape1, MyShape shape2, GraphicsContext GC, MyColor color){
        Canvas newcanvas = new Canvas(GC.getCanvas().getWidth(), GC.getCanvas().getHeight());
        GraphicsContext newGC = newcanvas.getGraphicsContext2D();
        shape1.draw(newGC);
        shape2.draw(newGC);
        for (int i = 0; i < intersectMyShapes(shape1, shape2, GC, color).size(); i++){
            intersectMyShapes(shape1, shape2, GC, color).get(i).draw(newGC);
        }
        return newGC.getCanvas();
    } 
}

abstract class MyShape extends Object implements MyShapeInterface{
    private MyPoint p;
    private MyColor color;

    //MyShape Constructors
    public MyShape(){
        Set_Point(new MyPoint());
        Set_MyColor(MyColor.BLACK); 
    }

    public MyShape(MyPoint t, MyColor newcolor){
        Set_Point(t);
        Set_MyColor(Optional.ofNullable(newcolor).orElse(MyColor.BLACK));
    }

    public MyShape(MyPoint t){
        Set_Point(t);
        Set_MyColor(MyColor.BLACK);
    }

    public MyShape(int x, int y, MyColor newcolor){
        this.p.Set_x(x);
        this.p.Set_y(y);
        Set_MyColor(Optional.ofNullable(newcolor).orElse(MyColor.BLACK));
    }

    public MyShape(int x, int y){
        Set_Point(new MyPoint());
        Get_Point().Set_x(x);
        Get_Point().Set_y(y);
        Set_MyColor(MyColor.BLACK);
    }

    public MyShape(MyShape tocopy){
        Set_Point(tocopy.Get_Point());
        Set_MyColor(tocopy.Get_MyColor());
    }

    public void Set_Point(MyPoint newp){
        this.p = newp;
    }
    public void Set_MyColor(MyColor newc){
        this.color = newc;
    }

    public MyPoint Get_Point() {
        return this.p;
    }

    public MyColor Get_MyColor(){
        return this.color;
    }

    public abstract double perimeter();

    public abstract double area();

    public abstract void draw(GraphicsContext GC);

    public abstract String toString();
}

class MyLine extends MyShape{
    private MyPoint p1;
    private MyPoint p2;
    private MyColor color;

    public MyLine(){
        super(new MyPoint(), null);
        Set_Point1(new MyPoint());
        Set_Point2(new MyPoint());
        Set_MyColor(MyColor.BLACK); 
    }

    public MyLine(MyPoint point1, MyPoint point2, MyColor newcolor){
        super(new MyPoint(), null);
        Set_Point1(point1);
        Set_Point2(point2);
        Set_MyColor(Optional.ofNullable(newcolor).orElse(MyColor.BLACK));
    }

    public MyLine(MyPoint point1, MyPoint point2){
        super(new MyPoint(), null);
        Set_Point1(point1);
        Set_Point2(point2);
        Set_MyColor(MyColor.BLACK);
    }

    public MyLine(MyLine tocopy) {
        super(new MyPoint(), null);
        Set_Point1(tocopy.Get_Point1());
        Set_Point2(tocopy.Get_Point2());
        Set_MyColor(tocopy.Get_MyColor());
    }

    public double xAngle(){
        double angle = Math.toDegrees(Math.atan2(this.Get_Point1().Get_y() - this.Get_Point2().Get_y(), this.Get_Point1().Get_x() - this.Get_Point2().Get_x())) - 180; //-180 because angle is measure from negative x axis to line in JavaFX canvas, which I dont want, I want from pos x axis
        if (angle < 0){
            return angle + 360.0;
        } else {
            return angle;
        }
    }
    
    @Override
    public MyColor Get_MyColor(){
        return this.color;
    }

    public MyLine Get_Line(){
        return this;
    }
    public MyPoint Get_Point1(){
        return this.p1;
    }
    public MyPoint Get_Point2(){
        return this.p2;
    }
    @Override
    public double perimeter(){
        return 0; //line doesn't have perimeter
    }

    @Override
    public double area(){
        return 0;
    }

    public double length(){
        return Math.sqrt(Math.pow(this.p2.Get_x() - this.p1.Get_x(), 2) + Math.pow(this.p2.Get_y() - this.p1.Get_y(), 2));
    }
    
    public void Set_Point1(MyPoint newp){
        this.p1 = newp;
    }
    public void Set_Point2(MyPoint newp){
        this.p2 = newp;
    }
    public void Set_MyColor(MyColor c){
        this.color = c;
    }
    @Override
    public String toString(){
        Double x1 = this.p1.Get_x();
        Double x2 = this.p2.Get_x();
        Double y1 = this.p1.Get_y();
        Double y2 = this.p2.Get_y();
        Double len = this.length();
        Double angl = this.xAngle(); 
        return "Line info: Endpoint 1: (" + x1.toString() + ", " + y1.toString() + ") Endpoint 2: (" + x2.toString() + ", " + y2.toString() + ")" + " Length: " + len.toString() + " Angle from line to x-axis: " + angl.toString() + ".";
    }

    @Override
    public void draw(GraphicsContext GC){
        GC.setStroke(this.Get_MyColor().GetJavaFXColor());
        GC.setLineWidth(1);
        GC.strokeLine(Get_Point1().Get_x(), Get_Point1().Get_y(), Get_Point2().Get_x(), Get_Point2().Get_y());
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        MyRectangle bounding = new MyRectangle();
        MyPoint newtlcp = new MyPoint();
        double newheight;
        double newwidth;
        if(this.Get_Point1().Get_x() < this.Get_Point2().Get_x()){
            newtlcp.Set_x(this.Get_Point1().Get_x() - 1);
            newwidth = this.Get_Point2().Get_x() - this.Get_Point1().Get_x() + 2;
        } else {
            newtlcp.Set_x(this.Get_Point2().Get_x() - 1);
            newwidth = this.Get_Point1().Get_x() - this.Get_Point2().Get_x() + 2;
        }

        if(this.Get_Point1().Get_y() < this.Get_Point2().Get_y()){
            newtlcp.Set_y(this.Get_Point1().Get_y() - 1);
            newheight = this.Get_Point2().Get_y() - this.Get_Point1().Get_y() + 2;
        } else {
            newtlcp.Set_y(this.Get_Point2().Get_y() - 1);
            newheight = this.Get_Point1().Get_y() - this.Get_Point2().Get_y() + 2;
        }

        
        bounding.Set_TLCP(newtlcp);
        bounding.Set_Height(newheight);
        bounding.Set_Width(newwidth);

        return bounding;
    }

    @Override
    public boolean pointInMyShape(MyPoint point) {
        MyLine first = new MyLine(this.Get_Point1(), point);
        MyLine second = new MyLine(point, this.Get_Point2()); 
        if (first.length() + second.length() == this.length()){
            return true;
        } else {
            return false;
        }
    }

}

class MyRectangle extends MyShape{
    private double h; 
    private double w;
    private MyPoint TLCP;
    private MyColor color;
    
    public MyRectangle(){
        super(new MyPoint(), null);
        Set_Height(0);
        Set_Width(0);
        Set_TLCP(new MyPoint());
        Set_MyColor(MyColor.BLACK);
    }
    public MyRectangle(double height, double width, MyPoint newTLCP, MyColor newcolor){
        super(new MyPoint(), null);
        Set_Height(height);
        Set_Width(width);
        Set_TLCP(newTLCP);
        Set_MyColor(Optional.ofNullable(newcolor).orElse(MyColor.BLACK));
    }

    public MyRectangle(double height, double width, MyPoint newTLCP){
        super(new MyPoint(), null);
        Set_Height(height);
        Set_Width(width);
        Set_TLCP(newTLCP);
        Set_MyColor(MyColor.BLACK);
    }

    public MyRectangle(MyRectangle tocopy){
        super(new MyPoint(), null);
        Set_Height(tocopy.Get_Height());
        Set_Width(tocopy.Get_Width());
        Set_TLCP(tocopy.Get_TLCP());
        Set_MyColor(MyColor.BLACK);
    }

    public double Get_Height(){
        return this.h;
    }
    public double Get_Width(){
        return this.w;
    }
    public MyPoint Get_TLCP(){
        return this.TLCP;
    }
    @Override
    public MyColor Get_MyColor(){
        return this.color;
    }
    public void Set_Height(double height){
        this.h = height;
    }
    public void Set_Width(double width){
        this.w = width;
    }
    public void Set_TLCP(MyPoint newTLCP){
        this.TLCP = newTLCP;
    }
    @Override
    public void Set_MyColor(MyColor newColor){
        this.color = newColor;
    }
    @Override
    public double perimeter(){
        return (2 * this.Get_Height()) + (2 * this.Get_Width());
    }
    @Override
    public double area(){
        return (this.Get_Height() * this.Get_Width());
    }

    @Override
    public String toString(){
        Double x1 = this.Get_TLCP().Get_x();
        Double y1 = this.Get_TLCP().Get_y();
        Double width = this.Get_Width();
        Double height = this.Get_Height();
        Double perim = this.perimeter();
        Double area = this.area();
        return "Rectangle info: TLCP: (" + x1.toString() + ", " + y1.toString() + ") Width: " + width.toString() + "Height: " + height.toString() + "Perimeter: " + perim.toString() + "Area: " + area.toString() + ".";
    }

    @Override
    public void draw(GraphicsContext GC){
        GC.setFill(this.Get_MyColor().GetJavaFXColor()); 
        GC.fillRect(Get_TLCP().Get_x(), Get_TLCP().Get_y(), Get_Width(), Get_Height());
    }
    @Override
    public MyRectangle getMyBoundingRectangle() {
        MyPoint newtlcp = new MyPoint(this.Get_TLCP().Get_x() - 1, this.Get_TLCP().Get_y() - 1);
        MyRectangle bounding = new MyRectangle(this.Get_Height() + 2, this.Get_Width() + 2, newtlcp);

        return bounding;
    }
    @Override
    public boolean pointInMyShape(MyPoint point) {
        if (point.Get_x() >= this.Get_TLCP().Get_x() && point.Get_x() < this.Get_TLCP().Get_x() + Get_Width() && point.Get_y() >= this.Get_TLCP().Get_y() && point.Get_y() < this.Get_TLCP().Get_y() + Get_Height()){
            return true;
        }
        return false;
    }
}

class MyOval extends MyShape{
    private double h; 
    private double w;
    private MyPoint cntr;
    private MyColor color;

    public MyOval(){
        super(new MyPoint(), null);
        Set_Height(0);
        Set_Width(0);
        Set_Cntr(new MyPoint());
        Set_MyColor(MyColor.BLACK);
    }
    public MyOval(double height, double width, MyPoint center, MyColor newcolor){
        super(new MyPoint(), null);
        Set_Height(height);
        Set_Width(width);
        Set_Cntr(center);
        Set_MyColor(Optional.ofNullable(newcolor).orElse(MyColor.BLACK));
    }
    public MyOval(double height, double width, MyPoint center){
        super(new MyPoint(), null);
        Set_Height(height);
        Set_Width(width);
        Set_Cntr(center);
        Set_MyColor(MyColor.BLACK);
    }
    public MyOval(MyOval tocopy){
        super(new MyPoint(), null);
        Set_Height(tocopy.Get_Height());
        Set_Width(tocopy.Get_Width());
        Set_Cntr(tocopy.Get_Center());
        Set_MyColor(tocopy.Get_MyColor());
    }
    public MyPoint Get_Center(){
        return this.cntr;
    }
    public double Get_Height(){
        return this.h;
    }
    public double Get_Width(){
        return this.w;
    }
    public double Get_SemiMinorAxis(){
        if (this.Get_Height() < this.Get_Width()){
            return this.Get_Height();
        } else {
            return this.Get_Width();
        }
    }
    public double Get_SemiMajorAxis(){
        if (this.Get_Height() > this.Get_Width()){
            return this.Get_Height();
        } else {
            return this.Get_Width();
        }
    }
    @Override
    public MyColor Get_MyColor(){
        return this.color;
    }

    public void Set_Height(double height){
        this.h = height;
    }
    public void Set_Width(double width){
        this.w = width;
    }
    @Override
    public void Set_MyColor(MyColor newcolor){
        this.color = newcolor;
    }
    public void Set_Cntr(MyPoint newcntr){
        this.cntr = newcntr;
    }

    @Override
    public double perimeter(){ //using approx formula for elipse perimeter 
        return Math.PI * (((3/2)*((this.Get_SemiMinorAxis()) + (this.Get_SemiMajorAxis()))) - Math.sqrt(((this.Get_SemiMinorAxis()) * (this.Get_SemiMajorAxis()))));
    }
    @Override
    public double area(){
        return Math.PI * (this.Get_SemiMinorAxis()) * (this.Get_SemiMajorAxis());
    }

    @Override
    public String toString(){
        Double semiminoraxis = this.Get_SemiMinorAxis();
        Double semimajoraxis = this.Get_SemiMajorAxis();
        Double minoraxis = this.Get_SemiMinorAxis() * 2;
        Double majoraxis = this.Get_SemiMajorAxis() * 2;
        Double perimeter = this.perimeter();
        Double area = this.area();
        Double x = this.Get_Center().Get_x();
        Double y = this.Get_Center().Get_y();
        return "Oval info: Semi Minor Axis Length: " + semiminoraxis.toString() + " Semi Major Axis Length: " + semimajoraxis.toString() + " Minor Axis Length: " + minoraxis.toString() + " Major Axis Length: " + majoraxis.toString() + " Perimeter: " + perimeter.toString() + " Area: " + area.toString() + " Center: (" + x.toString() + ", " + y.toString() + ").";
    }

    @Override
    public void draw(GraphicsContext GC){
        GC.setFill(this.Get_MyColor().GetJavaFXColor());
        GC.fillOval(this.Get_Center().Get_x() - (this.Get_Width()/2), this.Get_Center().Get_y() - (this.Get_Height()/2),this.Get_Width(), this.Get_Height());
    }
    @Override
    public MyRectangle getMyBoundingRectangle() {
        MyPoint newTLCP = new MyPoint(this.Get_Center().Get_x() - this.Get_Width()/2, this.Get_Center().Get_y() - this.Get_Height()/2);
        MyRectangle bounding = new MyRectangle(this.Get_Height(), this.Get_Width(), newTLCP);
        return bounding;
    }
    @Override
    public boolean pointInMyShape(MyPoint point) {
        if ( ((Math.pow(point.Get_x() - this.Get_Center().Get_x(), 2)) / Math.pow(this.Get_SemiMajorAxis(), 2)) + ((Math.pow(point.Get_y() - this.Get_Center().Get_y(), 2)) / Math.pow(this.Get_SemiMinorAxis(), 2)) <= 1){
            return true;
        } else {
            return false;
        }
    }
}

class MyCircle extends MyOval{
    private MyPoint cntr;
    private MyColor color;
    double r;

    public MyCircle(){
        super();
        Set_Cntr(new MyPoint());
        Set_Radius(5);
        Set_MyColor(MyColor.BLACK);
    }
    public MyCircle(MyPoint center, MyColor newcolor, double newr){
        super();
        Set_Cntr(center);
        Set_Radius(newr);
        Set_MyColor(newcolor);
    }
    public MyCircle(MyPoint center, double newr){
        super();
        Set_Cntr(center);
        Set_Radius(newr);
        Set_MyColor(MyColor.BLACK);
    }
    public MyCircle(MyCircle tocopy){
        super();
        Set_Cntr(tocopy.Get_Center());
        Set_Radius(tocopy.Get_Radius());
        Set_MyColor(tocopy.Get_MyColor());
    }

    @Override
    public MyPoint Get_Center(){
        return this.cntr;
    }

    @Override
    public MyColor Get_MyColor(){
        return this.color;
    }

    public double Get_Radius(){
        return this.r;
    }

    public void Set_Radius(double newr){
        this.r = newr;
    }

    @Override
    public void Set_MyColor(MyColor newcolor){
        this.color = newcolor;
    }

    @Override
    public void Set_Cntr(MyPoint newcntr){
        this.cntr = newcntr;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * this.Get_Radius();
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(this.Get_Radius(), 2);
    }

    @Override
    public String toString(){
        Double perimeter = this.perimeter();
        Double area = this.area();
        Double radius = this.Get_Radius();
        Double x = this.Get_Center().Get_x();
        Double y = this.Get_Center().Get_y();
        return "Circle info: Center: (" +  x + ", " + y + ") Area: " + area.toString() + " Perimeter: " + perimeter.toString() + "Radius: " + radius.toString() + ".";
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        MyRectangle bounding = new MyRectangle();
        bounding.Set_Height(this.Get_Radius() * 2);
        bounding.Set_Width(this.Get_Radius() * 2);
        bounding.Set_TLCP(new MyPoint(this.Get_Center().Get_x() - this.Get_Radius(), this.Get_Center().Get_y() - this.Get_Radius()));
        return bounding;
    }
    
    @Override
    public boolean pointInMyShape(MyPoint point) {
        double px = point.Get_x();
        double py = point.Get_y();
        double cx = this.Get_Center().Get_x();
        double cy = this.Get_Center().Get_y();

        if((Math.pow(px - cx, 2)) + (Math.pow(py - cy, 2)) <= Math.pow(this.Get_Radius(), 2)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(GraphicsContext GC) {
        GC.setFill(this.Get_MyColor().GetJavaFXColor());
        GC.fillOval(this.Get_Center().Get_x() - (this.Get_Radius()), this.Get_Center().Get_y() - (this.Get_Radius()), this.Get_Radius() * 2, this.Get_Radius() * 2);
    }
}

class MyArc extends MyShape{
    private MyPoint p1;
    private MyPoint p2;
    private MyColor color;
    private double xanglep1;
    private double xanglep2;
    private MyOval main;

    public MyArc(){
        super(new MyPoint(), null);
        Set_MyOval(new MyOval());
        Set_P1(new MyPoint());
        Set_P2(new MyPoint());
        Set_MyColor(MyColor.BLACK);
        Set_Angle1(0);
        Set_Angle2(0);
    }
    public MyArc(MyOval newmain, MyPoint newp1, MyPoint newp2, MyColor newcolor){
        super(new MyPoint(), null);
        Set_MyOval(newmain);
        Set_P1(newp1);
        Set_P2(newp2);
        Set_MyColor(newcolor);
        Set_Angle1(360 - new MyLine(main.Get_Center(), newp1).xAngle()); //360 minus bc the draw method of arc goes by a regular counter-clock wise angle measurement, while my xAngle method for MyLine goes in a clockwise angle measurement
        Set_Angle2(360 - new MyLine(main.Get_Center(), newp2).xAngle());
    }
    public MyArc(MyOval newmain, double newangle1, double newangle2, MyColor newcolor){
        super(new MyPoint(), null);
        Set_MyOval(newmain);
        Set_MyColor(newcolor);
        Set_P1(new MyPoint(Get_MyOval().Get_Center().Get_x() + newmain.Get_SemiMinorAxis()/2 * Math.cos(Math.toRadians(newangle1)), Get_MyOval().Get_Center().Get_y() - newmain.Get_SemiMajorAxis()/2 * Math.sin(Math.toRadians(newangle1))));
        Set_P2(new MyPoint(Get_MyOval().Get_Center().Get_x() + newmain.Get_SemiMinorAxis()/2 * Math.cos(Math.toRadians(newangle2)), Get_MyOval().Get_Center().Get_y() - newmain.Get_SemiMajorAxis()/2 * Math.sin(Math.toRadians(newangle2)))); 
        Set_Angle1(newangle1);
        Set_Angle2(newangle2);
    }
    public MyArc(MyArc tocopy){
        super(new MyPoint(), null);
        Set_MyOval(tocopy.Get_MyOval());
        Set_P1(tocopy.Get_P1());
        Set_P2(tocopy.Get_P2());
        Set_MyColor(tocopy.Get_MyColor());
        Set_Angle1(tocopy.Get_Angle1());
        Set_Angle2(tocopy.Get_Angle2());
    }

    public void Set_MyOval(MyOval newoval){
        this.main = newoval;
    }

    public void Set_P1(MyPoint newp1){
        this.p1 = newp1;
    }

    public void Set_P2(MyPoint newp2){
        this.p2 = newp2;
    }
    
    @Override
    public void Set_MyColor(MyColor newcolor){
        this.color = newcolor;
    }
    
    public void Set_Angle1(double newangle1){
        this.xanglep1 = newangle1;
    }

    public void Set_Angle2(double newangle2){
        this.xanglep2 = newangle2;
    }

    public MyOval Get_MyOval(){
        return this.main;
    }

    public MyPoint Get_P1(){
        return Optional.ofNullable(this.p1).orElse(new MyPoint());
    }

    public MyPoint Get_P2(){
        return Optional.ofNullable(this.p2).orElse(new MyPoint());
    }

    @Override
    public MyColor Get_MyColor(){
        return this.color;
    }

    public double Get_Angle1(){
        return this.xanglep1;
    }

    public double Get_Angle2(){
        return this.xanglep2;
    }
    
    public double length(){
        double sum = 0;
        if (this.Get_Angle1() > this.Get_Angle2()){
            for (double a = this.Get_Angle2(); a < this.Get_Angle1(); a++){
                MyArc temp = new MyArc(this.Get_MyOval(), a, a+1, MyColor.BLACK);
                MyLine smallLine = new MyLine(temp.Get_P1(), temp.Get_P2());
                sum = sum + smallLine.length();
            }
            return sum;
        } else {
            for (double a = this.Get_Angle1(); a < this.Get_Angle2(); a++){
                MyArc temp = new MyArc(this.Get_MyOval(), a, a+1, MyColor.BLACK);
                MyLine smallLine = new MyLine(temp.Get_P1(), temp.Get_P2());
                sum = sum + smallLine.length();
            }
            return sum;
        }
    }

    @Override
    public double perimeter() {
        double perimeter = 0;
        MyLine first = new MyLine(this.Get_MyOval().Get_Center(), this.Get_P1());
        MyLine second = new MyLine(this.Get_MyOval().Get_Center(), this.Get_P2());
        double arclen = this.length();
        perimeter = first.length() + second.length() + arclen;
        return perimeter;
    }

    @Override
    public double area() {
        double biggerangle;
        double smallerangle;
        if(this.Get_Angle1() < this.Get_Angle2()){
            biggerangle = this.Get_Angle2();
            smallerangle = this.Get_Angle1();
        } else {
            biggerangle = this.Get_Angle1();
            smallerangle = this.Get_Angle2();
        }
        return ((0.5 * this.Get_MyOval().Get_SemiMajorAxis() * this.Get_MyOval().Get_SemiMinorAxis()) * (Math.atan((this.Get_MyOval().Get_SemiMajorAxis() * Math.tan(biggerangle))/this.Get_MyOval().Get_SemiMinorAxis()) - Math.atan((this.Get_MyOval().Get_SemiMajorAxis() * Math.tan(smallerangle))/this.Get_MyOval().Get_SemiMinorAxis())));
    }

    @Override
    public String toString(){
        Double x1 = this.Get_P1().Get_x();
        Double x2 = this.Get_P2().Get_x();
        Double y1 = this.Get_P1().Get_y();
        Double y2 = this.Get_P2().Get_y();
        Double angle1 = this.Get_Angle1();
        Double angle2 = this.Get_Angle2();
        Double area = this.area();
        Double arclength = this.length();
        Double perimeter = this.perimeter();
        return "Arc info: Point1 :(" + x1 + ", " + y1 + ") Point 2: (" + x2 + ", " + y2 + ") Angle 1: " + angle1 + "Angle 2:" + angle2 + "Main oval of arc: " + this.Get_MyOval().toString() + "Arc Area: " + area.toString() + "Arc Perimeter" + perimeter.toString() + "Arc length: " + arclength.toString();
    }


    @Override
    public void draw(GraphicsContext GC) {
        GC.setFill(this.Get_MyColor().GetJavaFXColor());
        GC.fillArc(this.Get_MyOval().Get_Center().Get_x() - this.Get_MyOval().Get_Width()/2, this.Get_MyOval().Get_Center().Get_y()- this.Get_MyOval().Get_Height()/2, this.Get_MyOval().Get_Width(), this.Get_MyOval().Get_Height(), Get_Angle1(), Get_Angle2() - Get_Angle1(), ArcType.ROUND);
    }
    @Override
    public MyRectangle getMyBoundingRectangle() {
        return this.Get_MyOval().getMyBoundingRectangle();
    }
    @Override
    public boolean pointInMyShape(MyPoint point) {
        double biggerangle, smallerangle;
        if(this.Get_Angle1() < this.Get_Angle2()){
            biggerangle = this.Get_Angle2();
            smallerangle = this.Get_Angle1();
        } else {
            biggerangle = this.Get_Angle1();
            smallerangle = this.Get_Angle2();
        }

        if (this.Get_MyOval().pointInMyShape(point)){
            MyLine linetopoint = new MyLine(this.Get_MyOval().Get_Center(), point);
            if ((360 - linetopoint.xAngle() <=  biggerangle) && (360 - linetopoint.xAngle() >= smallerangle)){
                return true;
            }
        }
        return false;
    }
}

class Slice extends MyArc{
    MyArc arc;

    public Slice(){
        setArc(new MyArc());
    }
    public Slice(MyOval newmain, MyPoint newp1, MyPoint newp2, MyColor newcolor){
        setArc(new MyArc(newmain, newp1, newp2, newcolor));
    }
    public Slice(MyOval newmain, double newangle1, double newangle2, MyColor newcolor){
        setArc(new MyArc(newmain, newangle1, newangle2, newcolor));
    }
    public Slice(Slice tocopy){
        setArc(new MyArc(tocopy.getArc()));
    }

    public void setArc(MyArc newarc){
        this.arc = newarc;
    }

    public MyArc getArc() {
        return this.arc;
    }

    @Override
    public String toString(){
        return "Slice (Arc) information (originates from MyArc instance):" + getArc().toString();
    }

    @Override
    public void draw(GraphicsContext GC){
        getArc().draw(GC);
    }
}

class MyPieChart extends Object{
    Map<Character, Slice> tograph = new HashMap<Character, Slice>();
    MyOval base;
    int n;

    public MyPieChart(){ //default constructor just places everything out, but will have to be set up by the user
        setBase(new MyOval());
        setN(1);
    }

    public MyPieChart(MyOval newbase, Map<Character, Double> stats, int n) {
        MyColor[] colors = MyColor.values(); //used to determine all of the colors for each letter
        setBase(newbase);
        setN(n);
        Map<Character, Double> tempstats = new HashMap<Character, Double>();
        for (Map.Entry<Character, Double> entry : stats.entrySet()){ //this is done so that I can pop the 'n' keys that have the highest probabilities
            tempstats.put(entry.getKey(), entry.getValue());
        }

        double largestprob = 0.0;
        char key = ' ';
        double angle = 0.0;
        double angletoadd = 0.0;
        for (int i = 0; i < n; i++){
            for (Map.Entry<Character, Double> entry : tempstats.entrySet()){
                 if (entry.getValue() >= largestprob){
                    largestprob = entry.getValue();
                    key = entry.getKey();
                 }
            }
            tempstats.remove(key);
            angletoadd = (largestprob/1.0) * 360.0; 
            getTograph().put(key, new Slice(getBase(), angle, angle + angletoadd, colors[(int)key])); 
            angle = angle + angletoadd;
            angletoadd = 0.0;
            key = ' ';
            largestprob = 0.0;
        }
        //getTograph().put('~', new Slice(getBase(), angle, 360, MyColor.GREY)); //the "other" slice of the pie (all other characters)
    }

    public MyPieChart(GraphicsContext GC, Map<Character, Double> stats, int n, MyColor basecolor){
        MyColor[] colors = MyColor.values();
        setBase(new MyOval(GC.getCanvas().getHeight()/2.5, GC.getCanvas().getWidth()/2.5, new MyPoint(GC.getCanvas().getWidth()/2, GC.getCanvas().getHeight()/2), basecolor));  
        setN(n);
        Map<Character, Double> tempstats = new HashMap<Character, Double>();
        for (Map.Entry<Character, Double> entry : stats.entrySet()){ //this is done so that I can pop the 'n' keys that have the highest probabilities
            tempstats.put(entry.getKey(), entry.getValue());
        }

        double largestprob = 0.0;
        char key = ' ';
        double angle = 0.0;
        double angletoadd = 0.0;
        for (int i = 0; i < n; i++){
            for (Map.Entry<Character, Double> entry : tempstats.entrySet()){
                 if (entry.getValue() >= largestprob){
                    largestprob = entry.getValue();
                    key = entry.getKey();
                 }
            }
            tempstats.remove(key);
            angletoadd = (largestprob/1.0) * 360.0; 
            if (i + 1 == n) {
            	getTograph().put(key, new Slice(getBase(), angle, 360.0, colors[(int)key])); //see how to choose color (makes it randomized in the first place)
            } else {
            	getTograph().put(key, new Slice(getBase(), angle, angle + angletoadd, colors[(int)key]));
            }
            angle = angle + angletoadd;
            angletoadd = 0.0;
            key = ' ';
            largestprob = 0.0;
        }

        //getTograph().put('~', new Slice(getBase(), angle, 360.0, MyColor.GREY));
    }

    public MyPieChart(MyPieChart tocopy){
        setBase(tocopy.getBase());
        for (Map.Entry<Character, Slice> entry : tocopy.getTograph().entrySet()){
            getTograph().put(entry.getKey(), entry.getValue()); 
        }
        setN(tocopy.getN());
    }

    public int getN() {
        return n;
    }

    public MyOval getBase() {
        return base;
    }

    public Map<Character, Slice> getTograph() {
        return this.tograph;
    }

    public void setTograph(HashMap <Character, Slice> tocopy){
        for (Map.Entry<Character, Slice> entry : tocopy.entrySet()){
            getTograph().put(entry.getKey(), entry.getValue()); 
        }
    }

    public void setBase(MyOval base) {
        this.base = base;
    }

    public void setN(int n) {
        if (n > 26 || n < 0){
            this.n = 26; //ensures that the values are just enough for the number of letters in the English alphabet
        } else {
            this.n = n;
        }
    }

    public void draw(GraphicsContext GC){
        getBase().draw(GC);
        for (Map.Entry<Character, Slice> entry : getTograph().entrySet()){
//            if (entry.getKey() == '~'){
//                entry.getValue().draw(GC);
//                GC.setLineWidth(0.75);
//                GC.setFill(MyColor.BLACK.GetJavaFXColor());
//                GC.strokeText("All other letters, " + ((360 - entry.getValue().getArc().Get_Angle1())/360.0), entry.getValue().getArc().Get_P2().Get_x() + (20 * (GC.getCanvas().getWidth()/1000)), entry.getValue().getArc().Get_P2().Get_y() + (30 * (GC.getCanvas().getHeight()/1000)));
//                
//            } else {
                entry.getValue().draw(GC);
                GC.setLineWidth(0.75);
                GC.setFill(MyColor.BLACK.GetJavaFXColor());
                if (entry.getValue().getArc().Get_Angle2() > 0 && entry.getValue().getArc().Get_Angle2() <= 90){
                    GC.strokeText(entry.getKey() + ", " + ((entry.getValue().getArc().Get_Angle2() - entry.getValue().getArc().Get_Angle1())/360.0), entry.getValue().getArc().Get_P2().Get_x() + (20 * (GC.getCanvas().getWidth()/1000)), entry.getValue().getArc().Get_P2().Get_y() + (20 * (GC.getCanvas().getHeight()/1000)));
                } else if (entry.getValue().getArc().Get_Angle2() > 90 && entry.getValue().getArc().Get_Angle2() <= 120){
                    GC.strokeText(entry.getKey() + ", " + ((entry.getValue().getArc().Get_Angle2() - entry.getValue().getArc().Get_Angle1())/360.0), entry.getValue().getArc().Get_P2().Get_x() - (30 * (GC.getCanvas().getWidth()/1000)), entry.getValue().getArc().Get_P2().Get_y() - (40 * (GC.getCanvas().getHeight()/1000))); 
                } else if (entry.getValue().getArc().Get_Angle2() > 120 && entry.getValue().getArc().Get_Angle2() <= 215){
                    GC.strokeText(entry.getKey() + ", " + ((entry.getValue().getArc().Get_Angle2() - entry.getValue().getArc().Get_Angle1())/360.0), entry.getValue().getArc().Get_P2().Get_x() - (120 * (GC.getCanvas().getWidth()/1000)), entry.getValue().getArc().Get_P2().Get_y() - (40 * (GC.getCanvas().getHeight()/1000))); 
                } else if (entry.getValue().getArc().Get_Angle2() > 215 && entry.getValue().getArc().Get_Angle2() <= 240){
                    GC.strokeText(entry.getKey() + ", " + ((entry.getValue().getArc().Get_Angle2() - entry.getValue().getArc().Get_Angle1())/360.0), entry.getValue().getArc().Get_P2().Get_x() - (170 * (GC.getCanvas().getWidth()/1000)), entry.getValue().getArc().Get_P2().Get_y() - (30 * (GC.getCanvas().getHeight()/1000)));
                } else if (entry.getValue().getArc().Get_Angle2() > 240 && entry.getValue().getArc().Get_Angle2() <= 260){
                    GC.strokeText(entry.getKey() + ", " + ((entry.getValue().getArc().Get_Angle2() - entry.getValue().getArc().Get_Angle1())/360.0), entry.getValue().getArc().Get_P2().Get_x() - (140 * (GC.getCanvas().getWidth()/1000)), entry.getValue().getArc().Get_P2().Get_y() - (10 * (GC.getCanvas().getHeight()/1000)));
                } else if (entry.getValue().getArc().Get_Angle2() > 260 && entry.getValue().getArc().Get_Angle2() <= 280){
                    GC.strokeText(entry.getKey() + ", " + ((entry.getValue().getArc().Get_Angle2() - entry.getValue().getArc().Get_Angle1())/360.0), entry.getValue().getArc().Get_P2().Get_x() - (160 * (GC.getCanvas().getWidth()/1000)), entry.getValue().getArc().Get_P2().Get_y() + (10 * (GC.getCanvas().getHeight()/1000)));
                } else {
                    GC.strokeText(entry.getKey() + ", " + ((entry.getValue().getArc().Get_Angle2() - entry.getValue().getArc().Get_Angle1())/360.0), entry.getValue().getArc().Get_P2().Get_x() - (5 * (GC.getCanvas().getWidth()/1000)), entry.getValue().getArc().Get_P2().Get_y() + (20 * (GC.getCanvas().getHeight()/1000))); 
                }
           // }
        }
    }
}

class HistogramAlphaBet extends Object{
    Map <Character, Integer> count = new HashMap<Character, Integer>();
    Map <Character, Double> stats = new HashMap<Character, Double>();
    MyPieChart pie;
    FileReader read;
    int n;

    HistogramAlphaBet(String text, int n, GraphicsContext GC) throws IOException{
        this.read = new FileReader(text);
        int i;
        int tracker;
        int totalchars = 0; //will be used for stats map
        while ((i = read.read()) != -1){
            if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)){ //unicode values for latin alphabet letters
                totalchars++; //increments total number of chars for the stat map
                tracker = i; //keeps purpose of i as the variable iterating thru .txt file
                if(tracker >= 97 && tracker <= 122){
                    tracker -= 32; //ensures letters are lowercase
                }
                this.count.putIfAbsent((char)tracker, 0);
                this.count.put((char)tracker, this.count.get((char)tracker) + 1);
                tracker = 0;
            }
        }
        double prob;
        this.n = n;
        for (Map.Entry<Character, Integer> entry : count.entrySet()){
            prob = ((double)entry.getValue()) / totalchars;
            this.stats.put(entry.getKey(), prob);
        }
        this.pie = new MyPieChart(GC, this.getStats(), this.n, MyColor.BLANCHEDALMOND);
    }

    public Map<Character, Integer> getCount() {
        return this.count;
    }
    public Map<Character, Double> getStats() {
        return this.stats;
    }
    public MyPieChart getPie() {
        return this.pie;
    }
    public int getN() {
        return this.n;
    }
}
interface ClassScheduleInterface {

}

class Database extends Object implements ClassScheduleInterface{
	Schedule sched;
	Students stud;
	Courses cour;
	Classes cls;
	AggregateGrades aggr;
	Connection con;
	
	
	Database(String sched, String schedcsv, String students, int n, String courses, String classes, String aggregate, String courseId1, String courseId2, String courseId3, String courseId4, String courseId5, String url, String name, String password) throws SQLException{
		this.con = DriverManager.getConnection(url, name, password);
		this.sched = new Schedule(sched, schedcsv, this.con);
		this.stud = new Students(students, this.con, n);
		this.cour = new Courses(courses, this.con);
		this.cls = new Classes(classes, sched, students, this.con);
		this.aggr = new AggregateGrades(aggregate, courseId1, courseId2, courseId3, courseId4, courseId5, this.con);
	}
	public void closeConnection() throws SQLException {
		this.con.close();
	}
}

class Schedule{
	
	String ddlTableSchedule; //name of table
	String ddlLoadDataInFile; //location of csv file
	
	Schedule(String ddlTable, String ddlInsert, Connection con) throws SQLException{
		this.ddlTableSchedule = ddlTable;
		
		try {
			String createquery = "CREATE TABLE " + ddlTable + "(courseId CHAR(12) NOT NULL UNIQUE, "
					+ "    sectionNumber VARCHAR(8) NOT NULL UNIQUE,"
					+ "    title VARCHAR(64),"
					+ "    year INT,"
					+ "    semester VARCHAR(6),"
					+ "    instructor VARCHAR(24),"
					+ "    department VARCHAR(16),"
					+ "    program VARCHAR(100),"
					+ "    PRIMARY KEY(courseId, sectionNumber)"
					+ ") ";
			PreparedStatement psTableSchedule = con.prepareStatement(createquery);
			
			//just create table here
			
			
			psTableSchedule.executeUpdate();
		} catch (SQLException e){
			
		}
		
		this.ddlLoadDataInFile = ddlInsert;
		writeCsvScheduleUsingLoad(con, this.ddlLoadDataInFile);
	}
	
	void writeCsvScheduleUsingLoad(Connection con, String ddlInsert) throws SQLException{
		
		String loadQuery = "LOAD DATA INFILE '" + ddlInsert + "'"
				+ " INTO TABLE student." + this.ddlTableSchedule
				+ " FIELDS TERMINATED BY ',' "
				+ " LINES TERMINATED BY '\n' "
				+ " IGNORE 1 ROWS "
				+ " (courseID, sectionNumber, title, year, semester, instructor, department, program) ";
		PreparedStatement psLoadDataInFile = con.prepareStatement(loadQuery);
		try {
			psLoadDataInFile.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void insertRow(Connection con, String courseId, String sectionNumber, String title, String year, String semester, String instructor, String department, String program) throws SQLException {
		String Query = "INSERT INTO schedule (courseId, sectionNumber, title, year, semester, instructor, department, program)\r\n"
				+ "VALUES(\"" + courseId + "\",\r\n"
				+ "	\"" + sectionNumber + "\",\r\n"
				+ "	\"" + title + "\",\r\n"
				+ "	" + year + ",\r\n"
				+ "	\"" + semester + "\",\r\n"
				+ "	\"" + instructor + "\",\r\n"
				+ "	\"" + department + "\",\r\n"
				+ "	\"" + program + "\")";
		
		PreparedStatement psinsertRow = con.prepareStatement(Query);
		psinsertRow.executeUpdate();
	}
	
}

class Students{
	int numberofstudents;
	String ddlTableStudents;
	
	Students(String ddlTable, Connection con, int n) throws SQLException{
		this.numberofstudents = n;
		this.ddlTableStudents = ddlTable;
		try {
			String createquery = "CREATE TABLE Students("
					+ "	empID INT NOT NULL UNIQUE,"
					+ "    firstName VARCHAR(50),"
					+ "    lastName VARCHAR(50),"
					+ "    email VARCHAR(50),"
					+ "    gender VARCHAR(1) NOT NULL,"
					+ "    CONSTRAINT chk_gender CHECK (gender in ('M', 'F', 'U')),"
					+ "    PRIMARY KEY(empID)"
					+ ")";
			PreparedStatement psTableStudents = con.prepareStatement(createquery);
			psTableStudents.executeUpdate();
		} catch (SQLException e) {
			
		}
		for (int i = 0; i < numberofstudents; i++) {
			String queryinsert = "INSERT INTO " + this.ddlTableStudents + " (empID, firstName, lastName, email, gender)"
					+ " VALUES (" + i + ", \"" + Students.randomName() + "\", \"" + Students.randomName() + "\", \"" + Students.randomEmail() + "\", \"" + Students.randomGender().toString() + "\")";
			PreparedStatement psLoadData = con.prepareStatement(queryinsert);
			//System.out.println(queryinsert); used for testing purposes
			psLoadData.executeUpdate();
		}
		
	}
	
	public static void insertRow(Connection con, String empID, String firstName, String lastName, String email, String gender) throws SQLException {
		String Query = "INSERT INTO students (empID, firstName, lastName, email, gender)\r\n"
				+ "VALUES(" + empID + ",\r\n"
				+ "	\"" + firstName + "\",\r\n"
				+ "	\"" + lastName + "\",\r\n"
				+ "	\"" + email + "\",\r\n"
				+ "	\"" + gender + "\")";
		
		PreparedStatement psinsertRow = con.prepareStatement(Query);
		psinsertRow.executeUpdate();
	}
	
	public static Character randomGender() { //belongs to class
		char[] grades = new char[]{'M', 'F', 'U'};
		Random rand = new Random();
		int gradeindx = rand.nextInt(3); //generates random numbers in [0,2]
		
		return grades[gradeindx];
	}
	
	public static String randomName() { //https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
		Random rand = new Random();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int sizelimit = rand.nextInt(20) + 1;
		StringBuilder sb = new StringBuilder(sizelimit);
		for (int i = 0; i < sizelimit; i++) {
			int index = (int)(alphabet.length() * Math.random());
			sb.append(alphabet.charAt(index));
		}
		return sb.toString();
	}
	
	public static Integer randomID() {
		Random rand = new Random();
		String alphabet = "1234567890";
		int sizelimit = rand.nextInt(8) + 1;
		StringBuilder sb = new StringBuilder(sizelimit);
		for (int i = 0; i < sizelimit; i++) {
			int index = (int)(alphabet.length() * Math.random());
			sb.append(alphabet.charAt(index));
		}
		return Integer.parseInt(sb.toString());
	}
	
	public static String randomEmail() {
		Random rand = new Random();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		int sizelimit = rand.nextInt(18) + 1;
		StringBuilder sb = new StringBuilder(sizelimit);
		for (int i = 0; i < sizelimit; i++) {
			int index = (int)(alphabet.length() * Math.random());
			sb.append(alphabet.charAt(index));
		}
		sb.append("@citymail.cuny.edu");
		return sb.toString();
	}
}

class Courses{
	String ddlTableCourses; //name of table
	
	Courses(String ddlTable, Connection con) throws SQLException{
		this.ddlTableCourses = ddlTable;
		
		try {
			String createquery = "CREATE TABLE " + ddlTable + "(courseId CHAR(12) NOT NULL UNIQUE, "
					+ "    title VARCHAR(64),"
					+ "    department VARCHAR(16),"
					+ "    PRIMARY KEY(courseId)"
					+ ") ";
			PreparedStatement psTableSchedule = con.prepareStatement(createquery);
			
			//just create table here
			
			
			psTableSchedule.executeUpdate();
		} catch (SQLException e){
			
		}
		
		writeUsingSchedule(con);
	}
	
	public void writeUsingSchedule(Connection con) throws SQLException {
		String createquery = "INSERT INTO " + this.ddlTableCourses + " (courseId, title, department)"
				+ " SELECT courseId, title, department FROM schedule";
		PreparedStatement psTableCourses = con.prepareStatement(createquery);
		psTableCourses.executeUpdate();
	}
	public static void insertRow(Connection con, String courseId, String title, String department) throws SQLException {
		String Query = "INSERT INTO courses (courseId, title, department)\r\n"
				+ "VALUES(\"" + courseId + "\",\r\n"
				+ "	\"" + title + "\",\r\n"
				+ "	\"" + department + "\")";
		
		PreparedStatement psinsertRow = con.prepareStatement(Query);
		psinsertRow.executeUpdate();
	}
}

class Classes{
	String ddlTableClasses;
	
	Classes(String ddlTable, String scheduleTable, String studentsTable, Connection con) throws SQLException{
		this.ddlTableClasses = ddlTable;
		try {
			String createquery = "CREATE TABLE " + ddlTable + " AS (SELECT\r\n"
					+ "	schedule.courseId, students.empID, schedule.sectionNumber,\r\n"
					+ "            schedule.year,\r\n"
					+ "            schedule.semester\r\n"
					+ "FROM \r\n"
					+ "	schedule, students)";
			PreparedStatement psTableClasses = con.prepareStatement(createquery);
			psTableClasses.executeUpdate();
			String createquery1 = "ALTER TABLE Classes ADD COLUMN grade VARCHAR(1) AFTER semester";
			String createquery2 = "ALTER TABLE classes RENAME COLUMN empID TO studentId";
			String createquery3 = "ALTER TABLE classes ADD PRIMARY KEY(courseId, studentId, sectionNumber)";
			String createquery4 = "UPDATE classes\r\n"
					+ "SET grade = concat(\r\n"
					+ "  substring('ABCDFW', rand()*(6-1)+1, 1))\r\n"
					+ "WHERE grade IS NULL";
			psTableClasses = con.prepareStatement(createquery1);
			psTableClasses.executeUpdate();
			psTableClasses = con.prepareStatement(createquery2);
			psTableClasses.executeUpdate();
			psTableClasses = con.prepareStatement(createquery3);
			psTableClasses.executeUpdate();
			psTableClasses = con.prepareStatement(createquery4);
			psTableClasses.executeUpdate();
		} catch (SQLException e) {
			
		}
		
	}
	
	public static void setGrade(Connection con, Character grade, Integer studentId, String courseId) throws SQLException {
		String gradeupdate = "UPDATE classes SET grade = '" + grade + "' WHERE studentId = \"" + studentId.toString() + "\" AND courseId = \"" + courseId + "\"";
		PreparedStatement update = con.prepareStatement(gradeupdate);
		update.execute();
	}
	
	public static void partD(Connection con) throws SQLException {
		String query = "SELECT "
				+ "grade, COUNT(*) "
				+ "FROM"
				+ " classes"
				+ " GROUP BY grade";
		PreparedStatement partD = con.prepareStatement(query);
		partD.executeUpdate();
	}
	public void gradesToFile(Connection con) throws SQLException, IOException {
		String copy = "SELECT grade FROM classes";
		Statement copystt = con.createStatement();
		
		
		ResultSet results = copystt.executeQuery(copy);
		
		StringBuilder colgrades = new StringBuilder("");
		
		while(results.next()) {
			String grade = results.getString("grade");
			colgrades.append(grade);
		}
		
		String finalgrades = colgrades.toString();
		FileWriter write = new FileWriter("grades.txt");
        write.write(finalgrades);
        write.close();
	}
}

class AggregateGrades{
	String ddlTableAggregateGrades;
	
	AggregateGrades(String ddlAggregate, String courseId1, String courseId2, String courseId3, String courseId4, String courseId5, Connection con) throws SQLException{ //add strings of the classes that you want the aggregate grades for
		this.ddlTableAggregateGrades = ddlAggregate;
		String query = "CREATE TABLE " + this.ddlTableAggregateGrades + " AS( "
				+ "SELECT grade, COUNT(*) "
				+ "FROM classes "
				+ "WHERE courseId in (\"" + courseId1 + "\", \"" + courseId2 + "\", \"" + courseId3 + "\", \"" + courseId4 + "\", \"" + courseId5 + "\") "
				+ "GROUP BY grade "
				+ "ORDER BY COUNT(*) DESC)";
		PreparedStatement createtbl = con.prepareStatement(query);
		createtbl.executeUpdate();
	}
	
	public static void partD(Connection con) throws SQLException {
		
		String partD = "SELECT grade, COUNT(*) "
				+ "FROM classes "
				+ "WHERE courseId in (\"33600 H\", \"33600 M\") "
				+ "GROUP BY grade "
				+ "ORDER BY COUNT(*) DESC";
		Statement statm = con.createStatement();
		ResultSet result = statm.executeQuery(partD);
		
		System.out.println("PART D TABLE");
		while(result.next()) {
			String r = result.getString("grade");
			int num = result.getInt("COUNT(*)");
			
			System.out.println(r + " " + num);
		}
	}
}

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/***", "***", "****");
        	
        	//Schedule schedule = new Schedule("schedule", "ScheduleSpring2021new.csv", con);
        	//Courses courses = new Courses("courses", con);
        	//Students students = new Students("students", con, 10);
        	//Classes classes = new Classes("classes", "schedule", "students", con);
//        	//AggregateGrades aggr = new AggregateGrades("aggregategrades", "33600 H", "33600 M", "NULL", "NULL", "NULL", con);
        	//^ used for testing
        
    		Database students = new Database("schedule", "ScheduleSpring2021new.csv", "students", 100, "courses", "classes", "aggregategrades", "33600 H", "33600 M", "NULL", "NULL", "NULL", "jdbc:mysql://localhost:3306/student", "cristian", "Mihaela2010!!!");
    		AggregateGrades.partD(con);
    		//^^ creating tables and running piechart for randomly generated tables
        	
    		//students.cls.gradesToFile(students.con);
    		//students.closeConnection();
    		//Schedule.insertRow(con, "12123 D", "123212", "CompSci Database MYSQL", "2021", "Spring", "WES", "Computer Science", "Undergraduate");
    		//Students.insertRow(con, "100", "Bob", "Saget", "bsaget@citymail.cuny.edu", "M");
    		//Courses.insertRow(con, "65432 E", "Computation with Toasters", "Computer Science");
    		//Classes.setGrade(con, 'A', 4, "10000 PP");
    		//^used to test inserting rows/affecting data in appropriate tables
    		
    	} catch (SQLException e){
    		System.out.println("Error with database");
    	}
    	
    	VBox root = new VBox();
        
        
        Button button = new Button("Show Pie Chart");
        
        root.getChildren().addAll(button);
        Scene scene = new Scene(root, 1000, 1000, MyColor.WHITE.GetJavaFXColor()); 
        Stage stage = new Stage();
        stage.setTitle("Grades and how many students have them");
        Canvas canvas = new Canvas(1000, 1000);
        GraphicsContext GC = canvas.getGraphicsContext2D();
        button.setOnAction(e -> {
            int n = 6;
            try {
                GC.clearRect(0, 0, GC.getCanvas().getWidth(), GC.getCanvas().getHeight());
                HistogramAlphaBet buttonAlphaBet = new HistogramAlphaBet("***\\grades.txt", n, GC);
                buttonAlphaBet.getPie().draw(GC);
                stage.setScene(scene);
                root.getChildren().add(canvas);
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        stage.setScene(scene);
        root.getChildren().add(canvas);
        stage.show();
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	

    }
}
