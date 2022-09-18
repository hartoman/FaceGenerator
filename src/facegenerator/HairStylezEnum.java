package facegenerator;

public enum HairStylezEnum {

    BALD {
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
    },      //
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
    PARTMIDDLE {
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
    MOHAWK {
        @Override
        public HairCut makeHair() {
            return new Mohawk();
        }
    },  //
    MIAWALLACE {
        @Override
        public HairCut makeHair() {
            return new MiaWallace();
        }
    }
    ;

    // returns the hairstyle of each enum
    public abstract HairCut makeHair();
}