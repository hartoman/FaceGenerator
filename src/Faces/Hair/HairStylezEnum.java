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
package  Faces.Hair;

public enum HairStylezEnum {

    BALD {      //0
        @Override
        public HairCut makeHair() {
            return new Bald();
        }
    },
    BALDLONGHAIR {
        @Override
        public HairCut makeHair() {
            return new BaldLongHair();
        }
    },      
    SKINHEAD {
        @Override
        public HairCut makeHair() {
            return new SkinHead();
        }
    },
    LOWFRINGE {
        @Override
        public HairCut makeHair() {
            return new LowFringe();
        }
    },
    HIGHFRINGE {
        @Override
        public HairCut makeHair() {
            return new HighFringe();
        }
    },
    PARTMIDDLE {            //5
        @Override
        public HairCut makeHair() {
            return new PartMiddle();
        }
    },
    PARTSIDE {
        @Override
        public HairCut makeHair() {
            return new PartSide();
        }
    },
    HIGHTEMPLEHAIRCUT {
        @Override
        public HairCut makeHair() {
            return new HightTempleHairCut();
        }
    },  
    MOHAWK {
        @Override
        public HairCut makeHair() {
            return new Mohawk();
        }
    },   
    SPIKYHAIR {
        @Override
        public HairCut makeHair() {
            return new SpikyHair();
        }
    },   
    MIAWALLACE {
        @Override
        public HairCut makeHair() {
            return new MiaWallace();
        }
    },  
    HIGHFRINGELONGHAIR {                //10
        @Override
        public HairCut makeHair() {
            return new HighFringeLongHair();
        }
    },
    PARTSIDELONGHAIR {
        @Override
        public HairCut makeHair() {
            return new PartSideLongHair();
        }
    },  
    PARTMIDDLELONGHAIR {
        @Override
        public HairCut makeHair() {
            return new PartMiddleLongHair();
        }
    },  
    HIGHTEMPLELONGHAIR {
        @Override
        public HairCut makeHair() {
            return new HightTempleLongHair();
        }
    }
   
    ;

    // returns the hairstyle of each enum
    public abstract HairCut makeHair();
}