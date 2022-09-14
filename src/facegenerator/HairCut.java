package facegenerator;
import java.awt.*;


public abstract class HairCut {
    
    Shape templeHair;
    Shape topOfHeadHair;

 //   HairCut(){

 //   }

}


class SkinHead extends HairCut{
        
    SkinHead(){

        templeHair = null;
        topOfHeadHair = null;
    }

}

class Bald extends HairCut{
    
    Bald(){
        templeHair = null;
        topOfHeadHair = null;
    }
}

class Mohawk extends HairCut{
    
}

class PartMiddle extends HairCut{

}

class PartSide extends HairCut{

}