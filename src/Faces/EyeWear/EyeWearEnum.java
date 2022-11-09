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
package  Faces.EyeWear;

public enum EyeWearEnum {
    
    NO_EYEWEAR("No Eyewear") {      //0
        @Override
        public EyeWear makeEyeWear() {
            return new NoEyeWear();
        }
    },
    MONOCLE("Monocle"){
        @Override
        public EyeWear makeEyeWear() {
            return new Monocle();
        }
    },
    SPECTACLES("Spectacles"){
        @Override
        public EyeWear makeEyeWear() {
            return new Spectacles();
        }
    },
    EYEPATCH("Eye Patch"){
        @Override
        public EyeWear makeEyeWear() {
            return new EyePatch();
        }
    },
    GLASSES_SQUARE("King of the Hill"){
        @Override
        public EyeWear makeEyeWear() {
            return new GlassesSquare();
        }
    },
    GLASSES_ROUND("Round Glasses"){
        @Override
        public EyeWear makeEyeWear() {
            return new GlassesRound();
        }
    },
    TINY_BLACK("Tiny Black Glasses"){
        @Override
        public EyeWear makeEyeWear() {
            return new TinyBlack();
        }
    },
    PABLO("70's Yellow Tinted"){
        @Override
        public EyeWear makeEyeWear() {
            return new SunglassesPablo();
        }
    },
    TYLER("Fight Club"){
        @Override
        public EyeWear makeEyeWear() {
            return new SunglassesTyler();
        }
    },
    JACKIEO("Jackie-Oh!"){
        @Override
        public EyeWear makeEyeWear() {
            return new SunglassesJackie();
        }
    },
    JANICE("One.. Two.. Jango!!!"){
        @Override
        public EyeWear makeEyeWear() {
            return new SunglassesJanice();
        }
    },
    NEOMATRIX("Neo Matrix"){
        @Override
        public EyeWear makeEyeWear() {
            return new SunglassesNeo();
        }
    }
;



private String name;

EyeWearEnum(String name){
    this.name=name;
}

// returns the hairstyle of each enum
public abstract EyeWear makeEyeWear();


public String toString(){
    return this.name;
}

}
