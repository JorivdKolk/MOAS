import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.svg.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class MOAS_Sticker_designer extends PApplet {

/*
==== MOAS Sticker Designer ====
 
 MOAS is a set of software tools that can be used to practice Exquisite Fabrication.
 Exquisite Fabrication allows samples to travel between machines that use different 
 manufacturing techniques, like a 3D printer and a digital embroidery machine.
 
 With the MOAS sticker designer it is possible generate sticker that can be used for alignment. 
 For more information go to https://github.com/JorivdKolk/MOAS.
 
 The MOAS sticker designer was created by Jori van der Kolk.
 */



//declare shapes, these are for the markers and the loaded SVG.
PShape input;
PShape marker;
PShape snowMan;

boolean record = false; //boolean to check if the sticker should be recorded
String exportPath;
float sF; //scaling factor to make sure the loaded shape fits withing the screen.

//declare buttons
Button openButton;
Button expButton;
Button process;
Button rotate;

public void setup() {
  

  //initialize markers and explenation SVG that is shown on start up
  input = loadShape("explenation.svg");
  marker = loadShape("marker.svg");
  snowMan = loadShape("snowman marker.svg");

  //create all the markers
  for (int i = 0; i <= 8; i++) {
    markers.add(new Marker(i));
  }

  //calculate the scaling factor
  sF = height/max(input.height, input.width)-0.2f;

  //initialize all the buttons
  openButton = new Button(width - 100, height - 50, 100, 50, "Open SVG");
  expButton = new Button(width - 200, height - 50, 100, 50, "Export");
  process = new Button(0, height - 50, 100, 50, "Embroidery");
  rotate = new Button(100, height - 50, 100, 50, "Rotate");
} 

public void draw() {
  background(102);

  //check if sticker should be recorded.
  if (record) {
    beginRecord(SVG, exportPath + ".svg"); //start new SVG shape
    drawInput(); //draw imported SVG
    for (Marker marker : markers) { //draw all the markers
      marker.display();
    }
    endRecord(); //end recording
    record = false;
  } else {
    pushMatrix(); 
    translate(width/2, height/2); //translate everything to the center of the screen
    scale(sF); //scale by the scaling factor
    drawInput();  //draw imported SVG
    for (Marker marker : markers) { //draw all the markers
      marker.display();
    }
    popMatrix();
  }


  //display all the buttons
  openButton.display();
  expButton.display();
  process.display();
  rotate.display();
}

//drawe the loaded SVG
public void drawInput() {
  shapeMode(CENTER);
  input.disableStyle();   //ignore the colors in the loaded SVG
  stroke(0);              //set the stike to black
  rectMode(CENTER);
  noFill();
  shape(input, 0, 0);     //draw the loaded SVG
}

//rotate the SVG by 90 degrees
public void rotShape() {
  shapeMode(CENTER);
  input.translate(input.width/2 + input.height/2, input.height/2 - input.width/2); //translate the SVG to rotate around the center
  input.rotate(radians(90));  //rotate by 90 degrees
  //update the positions of the markers.
  for (Marker marker : markers) {
    float tempW = marker.w;
    marker.w = marker.h;
    marker.h = tempW;
    marker.update();
  }
}

//check if one of the buttons is pressed
public void mouseReleased() {

  if (openButton.over()) { 
    selectInput("Select a file to process:", "fileSelectedforInput");
  } else if (expButton.over()) {
    selectOutput("Select a file to process:", "fileSelectedforOutput");
  } 
  if (rotate.over()) {
    rotShape();
    for (Marker marker : markers) {
      marker.update();
    }
  }
  if (process.over()) {
    //change from embroidery to 3D print markers and back
    switch(process.message) {
    case "3D print":
      process.message = "Embroidery";
      for (Marker marker : markers) {
        marker.on = true;
      }
      break;

    case "Embroidery":
      process.message = "3D print";
      for (Marker marker : markers) {
        marker.on = false;
      }
      markers.get(1).on = true;
      markers.get(2).on = true;
      break;

    default:
    }
  }
}

//open SVG
public void fileSelectedforInput(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    println("User selected " + selection.getAbsolutePath());
    input = loadShape(selection.getAbsolutePath());
    shape(input, 0, 0);
    for (Marker marker : markers) {
      marker.w = input.width;
      marker.h = input.height;
      marker.update();
    }
    sF = height/max(input.height, input.width)-0.2f;
  }
}

//export marker
public void fileSelectedforOutput(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    println("User selected " + selection.getAbsolutePath());
    exportPath = selection.getAbsolutePath();
    record = true;
  }
}

//scroll wheel for zooming
public void mouseWheel(MouseEvent event) {
  float e = event.getCount();
  sF += e*0.1f;
}
class Button {
  int x, y;
  int sizex, sizey;
  String message;

  //initialize the button
  Button(int ix, int iy, int isx, int isy, String im) {
    x = ix;
    y = iy;
    sizex = isx;
    sizey = isy;
    message = im;
  }

  //check if the mouse is over the button
  public boolean over() {
    if (mouseX >= x && mouseX <= x+sizex && 
      mouseY >= y && mouseY <= y+sizey) {
      return true;
    } else {
      return false;
    }
  }

  //display the button
  public void display() {
    rectMode(CORNER);
    if (over()) {
      fill(200);
    } else {
      fill(255);
    }
    stroke(0);
    rect(x, y, sizex, sizey);
    textAlign(CENTER, CENTER);
    fill(0);
    text(message, x + sizex/2, y + sizey/2);
  }
}
ArrayList<Marker> markers = new ArrayList<Marker>(); //make an arraylist for all the markers

class Marker {
  float x, y, w, h;
  int num;
  boolean on;

  //initialize makers
  Marker(int num) {
    x = y = 0;
    w = input.width;
    h = input.height;
    this.num = num;
    on = true;

    //decide where the maker should go based on its number
    switch(num) {
    case 0: //Center
      break;

    case 1: //Center Left
      x = -w/2;
      y = 0;
      break;

    case 2: //Center Right
      x = w/2;
      y = 0;
      break;

    case 3: //Top Center
      x = 0;
      y = -h/2;
      break;

    case 4: //Bottom Center
      x = 0;
      y = h/2;
      break;

    case 5: //Top Left
      x = -w/2;
      y = -h/2;
      break;

    case 6: //Top Right
      x = w/2;
      y = -h/2;
      break;

    case 7: //Bottom Left 
      x = -w/2;
      y = h/2;
      break;

    case 8: //Bottom Right
      x = w/2;
      y = h/2;
      break;

    default:
    }
  }

  //display marker
  public void display() {
    if (on) {

      fill(0);
      if (num == 0 && process.message.equals("Embroidery")) {
        rectMode(CENTER);
        fill(255);
        rect(0, 0, snowMan.width, snowMan.height);
        shape(snowMan, x, y);
      } else if (process.message.equals("Embroidery")) {
        shape(marker, x, y);
      } else {
        shape(marker, x, y);
      }


      textMode(SHAPE);
      if (process.message.equals("3D print")) {
        text(num, x + 5, y + 14);
      }
    }
  }

  //update marker locastion, used when rotating
  public void update() {
    switch(num) {
    case 0: //Center
      break;

    case 1: //Center Left
      x = -w/2;
      y = 0;
      break;

    case 2: //Center Right
      x = w/2;
      y = 0;
      break;

    case 3: //Top Center
      x = 0;
      y = -h/2;
      break;

    case 4: //Bottom Center
      x = 0;
      y = h/2;
      break;

    case 5: //Top Left
      x = -w/2;
      y = -h/2;
      break;

    case 6: //Top Right
      x = w/2;
      y = -h/2;
      break;

    case 7: //Bottom Left 
      x = -w/2;
      y = h/2;
      break;

    case 8: //Bottom Right
      x = w/2;
      y = h/2;
      break;

    default:
    }
  }
}
  public void settings() {  size(640, 640); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "MOAS_Sticker_designer" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
