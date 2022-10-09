/*
 * Made by Christos Chartomatsidis, 2022
 * This application is free to use, but it comes as-is:
 * I hold no responsibility for any damage or loss of that may arise from it's use.
 * Attribution is not required, but would be greatly appreciated.
 * For any comments, bug-reports, and ideas do not hesitate to contact me at:
 * hartoman@gmail.com
 */
package FunctionalClasses;

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