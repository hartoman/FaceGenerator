// Copyright © 2022 Christos Chartomatsidis

/*
 This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version. This program is distributed in the hope that it will be
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
    General Public License for more details. You should have received a copy of the GNU 
    General Public License along with this program. If not, see http://www.gnu.org/licenses/. 

 */

package  Faces.FunctionalClasses;

/**
 *
 * @author chris
 */
    // defines a line in the format y = a*x + b     (linear)
    // lineX is for the cases that the line is parallel to y axis-- Integer can be null
    public class Linear{
        
        int a,b;
        Integer lineX;

        public Linear(int a,int b,Integer lineX){
            this.a=a;
            this.b=b;
            this.lineX=lineX;
        }

        public String toString(){
            return new String("Line: Y = "+ this.a + " *X + "+this.b);
        }
    }