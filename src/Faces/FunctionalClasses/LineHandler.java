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

import java.awt.Point;
import java.lang.Exception;

public class LineHandler {

    // calculates distance between two points
    public double distance(Point ptA, Point ptB) {
        double dist;
        double distX = Math.pow((ptA.x - ptB.x), 2);
        double distY = Math.pow((ptA.y - ptB.y), 2);
        dist = Math.sqrt(distX + distY);
        return dist;
    }

    // converts two points to a Linear Function
    public Linear pointsToLinear(Point ptA,Point ptB){

        int x1=ptA.x;
        int y1=ptA.y;
        int x2=ptB.x;
        int y2=ptB.y;

        try{
            if(x1==x2){
                if(y1!=y2){
                    throw new Exception("Points A and B cannot belong to a linear function");
                }else{
                    return new Linear(0,y1,null);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new Linear(0,0,x1);  // returns a line parallel to y-axis
        }

        int a = (y2-y1)/(x2-x1);
        int b = y1 - a*x1;

        return new Linear(a,b,null);
    }

    // calculates the line (in equation form) that comes perpendicular to the one designated by points a and b from external point of origin
    public Linear calcPerpendicularLine(Point external,Point ptA,Point ptB){

        Linear line = pointsToLinear(ptA,ptB);
      
        if(line.lineX!=null){                                   //if line is parallel to y axis
            return new Linear(0,external.x,null);       // return line y=b
            }
       
        int perpA = -1/line.a;                              // for high-school math
        int perpB = (-perpA*external.x) + external.y;

        return new Linear(perpA,perpB,null);
    }

    // calculates the orthogonal projection of an external point on a line designated by points A and B
    public Point calcOrthogonalProjection(Point external,Point ptA,Point ptB){

        Linear line = pointsToLinear(ptA,ptB);
        if(line.lineX!=null){                                   // if line is parallel to y axis
            return new Point(line.lineX,external.y);            
            }

        Linear perp = calcPerpendicularLine(external,ptA,ptB);    // else

        int orthX = (perp.b-line.b)/(line.a-perp.a);
        int orthY = line.a*orthX+line.b;

        return new Point(orthX,orthY);

    }

    // checks if t is on line (ptA,ptB)
    public boolean isOnLine(Point t, Point ptA, Point ptB) {

        double distAt = distance(t, ptA);
        double distBt = distance(t, ptB);
        double distAB = distance(ptA, ptB);
        return distAt + distBt == distAB;
    }

    // checks what how far is t from ptA as percentage of the length of line
    // (ptA,ptB)
    public double percentOfLine(Point t, Point ptA, Point ptB) {

        double distAt = distance(t, ptA);
        double distAB = distance(ptA, ptB);
        return distAt / distAB;
    }

    // Implementation of Casteljau algorithm for single point:
    // returns the Y point on a bezier curve for given point x
    // ptA,ptB = start and end-points of the line
    // cp1,cp2 = bezier handle control points
        public Point bezierPointAt(Point x, Point ptA, Point ptB, Point cp0, Point cp1) {

        try {
            if (!isOnLine(x, ptA, ptB)) {
                throw new PointNotOnLineException("Point (" + x.x + ", " + x.y + ") is not on the line (A,B).\nWill use orthogonal projection instead");
                }

        } catch (PointNotOnLineException e) {
            System.out.println(e.getMessage());

            return bezierPointAt(calcOrthogonalProjection(x,ptA,ptB), ptA, ptB, cp0, cp1);
        }

        double t = percentOfLine(x,ptA,ptB);

        double aX=casteljauLine(t, ptA.getX(),cp0.getX());
        double aY=casteljauLine(t, ptA.getY(),cp0.getY());
        double bX=casteljauLine(t, cp0.getX(), cp1.getX());
        double bY=casteljauLine(t, cp0.getY(), cp1.getY());
        double cX=casteljauLine(t, cp1.getX(), ptB.getX());
        double cY=casteljauLine(t, cp1.getY(), ptB.getY());

        double dX=casteljauLine(t, aX, bX);
        double dY=casteljauLine(t, aY, bY);
        double eX=casteljauLine(t, bX, cX);
        double eY=casteljauLine(t, bY, cY);

        double pX=casteljauLine(t, dX, eX);
        double pY=casteljauLine(t, dY, eY);

        Point bez = new Point((int)pX,(int)pY);
        return bez;
    }

    // executed for x and y between all points during bezierPointAt (Casteljau algorithm)
    double casteljauLine(double t,double pt0, double pt1){
        return ((1-t)* pt0)+(t*pt1);
    }

    // occurs if we try to run a method that requires a point that is on a certain
    // line
    class PointNotOnLineException extends Exception {
        public PointNotOnLineException(String str) {
            super(str);
        }
    }

}
