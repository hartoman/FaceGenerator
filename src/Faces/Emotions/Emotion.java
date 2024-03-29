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

package  Faces.Emotions;

public enum Emotion {

    POKERFACE("Poker Face", 0, 45, 0, 0),
    BORED("Boredom", -0, 25, -5, 5),
    SHOCK("Shock", -50, 75, 0, 40),
    AWE("Awe", 0, 75, 0, 40),
    BAD_SURPRISE("Bad Surprise", 50, 75, -15, 40),
    HAPPY("Happy", 10, 60, 15, 20),
    GLAD("Glad", 10, 50, 15, 0),
    APOLOGETIC("Apologetic",-50,45,15,0),
    VICIOUS("Vicious", 50, 45, 15, 20),
    CYNICAL("Cynical", 50, 35, 3, 0),
    DETERMINED("Determined", 50, 50, 0, 0),
    SAD("Sad", -50, 45, -15, 0),
    HORROR("Horror", -50, 75, -15, 40),
    RAGE("Rage", 50, 55, -15, 0),
    TICKED_OFF("Annoyed", 50, 35, -5, 0),
    INTOX_HAPPY("Intoxicated", 0, 35, 15, 20),
    INTOX_STONED(" 'Meditating' ", 0, 25, 15, 0),

    ;

    String name;
    int anger, // curvature: [-50,50]
            eyeOpenness, // eye openness: [0,75]
            smile, // smile: [-15,15]
            mouthOpenness; // mouth openness: [0,40]

    Emotion(String name, int anger, int eyeOpenness, int smile, int mouthOpenness) {
        this.name = name;
        this.anger = anger;
        this.eyeOpenness = eyeOpenness;
        this.smile = smile;
        this.mouthOpenness = mouthOpenness;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getAnger() {
        return anger;
    }

    public int getEyeOpenness() {
        return eyeOpenness;
    }

    public int getSmile() {
        return smile;
    }

    public int getMouthOpenness() {
        return mouthOpenness;
    }

    
}
