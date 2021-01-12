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
  boolean over() {
    if (mouseX >= x && mouseX <= x+sizex && 
      mouseY >= y && mouseY <= y+sizey) {
      return true;
    } else {
      return false;
    }
  }

  //display the button
  void display() {
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
