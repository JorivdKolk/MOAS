/*MOAS Translation Tool
MOAS is a set of software tools that can be used to practice Exquisite Fabrication. 
Exquisite Fabrication allows samples to travel between machines that use different 
manufacturing techniques, like a 3D printer and a digital embroidery machine.

With the MOAS translation tool it is possible to calculate the linear translation 
and rotation of a sticker withing the work area of a 3D printer. 
For more information go to https://github.com/JorivdKolk/MOAS.

The MOAS translation tool was created by Jori van der Kolk
*/

char [] inputCheck = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'}; //Check input for numbers

String result = "";

//Variables for output
float centerX;
float centerY;
float rotAngle;

//Declare coordinates for input
Coordinate X1;
Coordinate Y1;
Coordinate X2;
Coordinate Y2;

void setup() {
  size(400, 200);
  background(255);
  
  //Initialize coordinates
  X1 = new Coordinate("X1", width/4, 20);
  Y1 = new Coordinate("Y1", 3*width/4, 20);
  X2 = new Coordinate("X2", width/4, 60);
  Y2 = new Coordinate("Y2", 3*width/4, 60);
}

void draw() {
  background(255);
  
  //Show coordinates
  X1.update();
  X2.update();
  Y1.update();
  Y2.update();
  
  //call translate function
  translatePoint();
  
  //display results
  text("Center X: " + centerX, width/2, 120);
  text("Center Y: " + centerY, width/2, 150);
  text("Rotation: " + degrees(rotAngle), width/2, 180);
}

//Class for coordinates
class Coordinate {
  float value = 0;
  String name;
  boolean active = false;
  int x;
  int y;

  Coordinate(String name, int x, int y) {
    this.name = name;
    this.x = x;
    this.y = y;
  }
  
  //shows coordinates on the screen
  void update() {
    int r = (!active) ? 0 : 255;
    int g = (over()) ? 200 : 255;
    fill(g);
    if (active) {
      value = float(result);
    }
    rectMode(CENTER);
    rect(x, y, width/2, 40);
    fill(0);
    textAlign(CENTER, CENTER);
    textSize(25);
    text(name + ":", x-50, y);
    fill(r, 0, 0);
    text(value, x + 25, y);
  }
  
  //checks if mouse is over the coordinate
  boolean over() {
    if (mouseX >= x-width/4 && mouseX <= x+width/4 && 
      mouseY >= y-20 && mouseY <= y+20) {
      return true;
    } else {
      return false;
    }
  }
}

void translatePoint() {
  centerX = (X1.value + X2.value)/2; //calculate center in x axis
  centerY = (Y1.value + Y2.value)/2; //calculate center in y axis
  rotAngle = atan((Y2.value-Y1.value)/(X2.value-X1.value)); //calculate rotation
}

//activate coordinates to input values
void mouseReleased() {
  if (X1.over()) {
    X1.active = (X1.active) ? false : true;
    X2.active = false;
    Y1.active = false;
    Y2.active = false;
  }
  if (X2.over()) {
    X2.active = (X2.active) ? false : true;
    X1.active = false;
    Y1.active = false;
    Y2.active = false;
  }
  if (Y1.over()) {
    Y1.active = (Y1.active) ? false : true;
    X2.active = false;
    X1.active = false;
    Y2.active = false;
  }
  if (Y2.over()) {
    Y2.active = (Y2.active) ? false : true;
    X2.active = false;
    X1.active = false;
    Y1.active = false;
  }
  result = "0";
}
//check keys for input
void keyReleased() {
  if (key == BACKSPACE) {
    result = result.substring(0, result.length()-1);
  } else {
    for (int i = 0; i < inputCheck.length; i++) { //check if input is a number or a point
      if (key == inputCheck[i]) {
        result = result + key;
        break;
      }
    }
  }
} 
