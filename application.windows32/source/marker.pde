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
  void display() {
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
  void update() {
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
