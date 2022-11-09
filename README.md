# FaceGenerator v1.0

## Introduction

This simple application was written in Java 8.


Copyright © 2022 Christos Chartomatsidis

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU    General Public License along with this program. If not, see http://www.gnu.org/licenses/. 

Attribution is not required, but it would be really nice if given.



## Purpose 

FaceGenerator is a tool that generates cartoony sketches of faces.

A Game-Master (GM) can use these to create custom NPCs for his pen-and-paper RPG campaign / boardgame on the spot .
The stand-alone classes can also be implemented in a computer game, to procedurally create content (i.e. for roguelike games).



## How to use

### Defining Parameters



#### General 

Things are pretty straight-forward. This single-screen application has two sections: 
The canvas, where the face is being drawn, and the options panel, from where the user can fiddle with the parameters.

#### Custom-made

The sub-panels are color-coded for convenience. Through these, the user may experiment and adjust the drawn face, and create his own sketch without needing to do anything hand-drawn.
There is also the option to add any image as visual background.
 
#### Random

There are also options to randomly create sketches of faces, both male, female, as well as a totally random combination of features,

### Menu: Ι'm Serial

#### to-and-from .ser

Saves the sketch as a serialized (.ser) file, which can be loaded at a later time, and be further processed. This feature can be useful for computer games, to assign a portrait to each NPC, and have them change emotions during in-game events and conversations.
Only the sketch is saved, not the background image.

### Menu: Import/Export

There is a checkbox named 'Show Gridlines'. While it doesn't have any effect on what the user sees on-screen, it affects the exported image of the map.

#### as .JPG

This saves the map to a simple jpg image, located in the folder from where the application is running.

#### as .PNG

Same as with jpg, but the background tiles are transparent.




## Technical Support
