package Hair;

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