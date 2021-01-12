# Exquisite Fabrication: Exploring Turn-taking between Designers and Digital Fabrication Machines
Digital fabrication and craftsmanship is entering into a new phase with increasing levels of complexity and a renewed desire for composites and cross-material experimentation. However, allowing work to travel from machine to machine, remains a challenge in terms of workflow, communication, orientation and material. Based on an exploration to combine embroidery and 3D printing in the pursuit of inflatable solutions, we propose the metaphor of the drawing game Exquisite Corpse to outline the three emerging concerns: turn taking, orientation and trade-offs. We propose a set of guidelines that suggest ways in which, we may allow different digital fabrication machines to be used in sequence, as a method for adding complexity to the things we make and the ways our machines may talk to one another.

DOI: https://doi.org/10.1145/3411764.3445236

# MOAS software
Turn-taking between machines is achieved through the MOAS software. MOAS stands for Machine Operation Alignment System. This software can be used to align operations within a machine, e.g. position a digital model precisely within the build area of a 3D printer or align a digital embroidery machine to a previously made sample. MOAS was created as part of the aforementioned research.

MOAS consists of two pieces of software, MOAS SD and MOAS TT both can be found within the repository. Their functionality and use are explained below. As well as how to use MOAS with a digital Embroidery machine and a 3D printer.

The goal of this software is to allow samples to travel between machines and production techniques. We demonstrate this in the paper Exquisite Fabrication: Exploring Turn-taking between Designers and Digital Fabrication Machines through the fabrication of a soft wearable inflatable.
## MOAS SD (Sticker Designer)
MOAS SD forms the basis for aligning a sample within a machine. For some machines, like 3D printers, the addition of MOAS TT is needed. In MOAS SD, the outline of the operation can be converted to a sticker/placeholder. This sticker includes markers that can be used for aligning the machine.
### Using MOAS SD
The steps of using MOAS SD are as follows.
* *Step 1: Create the outline.*

  From your design file (Illustrator, SolidWorks, etc.) export the outline of the operation you want to perform as an SVG file (as of now, the software only supports the use of  SVG’s).
* *Step 2: Create the sticker.* 

![MOAS SD](https://github.com/JorivdKolk/MOAS/blob/main/Images/MOAS_SD.jpg?raw=true)

  * a.	*Import the SVG.* 
  
    (When creating the SVG it is important to use the ‘save as’ function in Illustrator. SVG’s created by exporting won’t import)
  * b.	*Select your production technology.* 

   (Currently there is the choice of Embroidery and 3D printing).
  * c.	*Position the sticker, the sticker can be rotated by 90° increments.* 

    This can be important for 3D printing and when using the snowman marker with professional Brother embroidery machines.
  * d.	*Export your sticker.*
## MOAS TT (Translation tool)
MOAS TT needs to be used in combination with 3D printers. The makers for 3D printing are numbered one and two. With the controls of the 3D printer, the X and Y coordinates for both points need to be found. These coordinates can be filled in in MOAS TT and the center point of the operation and the rotation relative to the printer are given.

![MOAS TT](https://github.com/JorivdKolk/MOAS/blob/main/Images/MOAS_TT.jpg?raw=true)

### Using MOAS TT
* *Step 1: Record the coordinates of maker one and two on the sticker.*

  Always home before recording the coordinates. Use the interface of your printer to position the nozzle above the marker and record the X and Y position. Do this for both markers.
* *Step 2: Fill in the coordinates in MOAS TT*

  Click on one of the coordinates to start filling it in. By clicking on a coordinate, the current value will be erased.
* *Step 3: Use the center point and rotation to position your 3D model in the slicer of the printer.*

  (Always double check whether the position of the model in the slicer corresponds to the position of the sticker. In some slicers the model is not positioned truly to the origin of the bed.)
## Positioning with an Embroidery machine
![Embroidering with MOAS](https://github.com/JorivdKolk/MOAS/blob/main/Images/MOAS_Embroidery.png)
Using MOAS with an embroidery machine works as follows. For embroidery machines there are two options to align the machine to the sticker. Option one is two manually align the embroidery file using the controls on the machine. Option two is using the build in camera to recognize the snowman marker (this only works with professional Brother embroidery machines).
### Preparation
* *Step 1: Create an embroidery sticker using MOAS SD.*
* *Step 2: Prepare the embroidery hoop.*

  You will have to tension something in the hoop. This can be a piece of fabric you want to embroider on or a dissolvable material that can be washed away. On top of the base layer you can place a sample you want to add something to.
* *Step 3: Place the sticker in the embroidery hoop.* 
### Manual alignment
* *Step 4: Align the embroidery file to the sticker.*
  
  Using the controls on the machine, you can position the embroidery file so its position corresponds with the sticker. To check the alignment you can drop the needle on a couple of the markers.
* *Step 5: Remove the sticker.*
* *Step 6: Embroider away!*
### Automatic alignment
* *Step 4: Align the embroidery file to the sticker.*
  
  Run the alignment procedure on the embroidery machine. After the procedure, double check the alignment, the rotation can be a little bit off. 
* *Step 5: Remove the sticker.*
* *Step 6: Embroider away!*
## Positioning with a 3D printer
![3D printing with MOAS](https://github.com/JorivdKolk/MOAS/blob/main/Images/MOAS_3DP.jpg)
* *Step 1: Create an embroidery sticker using MOAS SD.*
* *Step 2: Attach a sample to the print bed (optional) and place the sticker where you want to print.*
* *Step 3: Use MOAS TT to translate your 3D model in the slicer.*
  
  To print on top of a sample, the z-height also needs to be adjusted. This can be done by measuring the thickness of the sample in the printer with calipers and adjusting the z position of your model. Be very careful when doing this, using the wrong z-height might damage your printer or the sample! It is safer to take a little bit of extra space and adjust afterwards.
* *Step 4: Start your print!*
  
  You can now start printing! Pay extra attention when starting your print, you want to be able to stop the machine if anything goes wrong. 
# Enjoy MOAS!
We hope you have all the information necessary to start using MOAS for yourself to start exploring the possibilities of Exquisite fabrication. If you have any questions regarding the software, feature suggestions or additions feel free to contact us. You can reach us via email (j.m.l.v.d.kolk@student.tue.nl &  b.goveia.da.rocha@tue.nl).

