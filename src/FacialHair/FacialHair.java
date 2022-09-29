package FacialHair;
import java.awt.*;
import java.io.Serializable;


public class FacialHair implements Serializable {

    private   MoustacheArea moustache;
    private   ChinArea chin;

    public FacialHair(){

        moustache = new MoustacheArea();
        chin=new ChinArea();
    }

    public void drawFacialHair(Graphics2D g2d,Color hairColor){

            moustache.drawMoustache(g2d,hairColor);
            chin.drawChinArea(g2d,hairColor);
     
    }

    

    public MoustacheArea moustache() {
        return moustache;
    }

    public void moustache(MoustacheArea moustache) {
        this.moustache = moustache;
    }

    public ChinArea chin() {
        return chin;
    }

    public void chin(ChinArea chin) {
        this.chin = chin;
    }


    public void setFacialHair(int moustacheSize,int curled,int soulpatchHeight,int soulpatchWidth){

        this.moustache().moustacheSize(moustacheSize);
        this.moustache().curled(curled);
        this.chin().soulpatchHeight(soulpatchHeight);
        this.chin().soulpatchWidth(soulpatchWidth);

        
    }

}


