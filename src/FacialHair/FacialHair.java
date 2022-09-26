package FacialHair;
import java.awt.*;


public class FacialHair {

    private static MoustacheArea moustache;
    private static ChinArea chin;

    public FacialHair(){

        moustache = new MoustacheArea();
        chin=new ChinArea();
    }

    public void drawFacialHair(Graphics2D g2d){

            moustache.drawMoustache(g2d);
            chin.drawChinArea(g2d);
     
    }

    

    public MoustacheArea moustache() {
        return moustache;
    }

    public void moustache(MoustacheArea moustache) {
        FacialHair.moustache = moustache;
    }

    public ChinArea chin() {
        return chin;
    }

    public void chin(ChinArea chin) {
        FacialHair.chin = chin;
    }


    public void setFacialHair(int moustacheSize,int curled,int soulpatchHeight,int soulpatchWidth){

        this.moustache().moustacheSize(moustacheSize);
        this.moustache().curled(curled);
        this.chin().soulpatchHeight(soulpatchHeight);
        this.chin().soulpatchWidth(soulpatchWidth);

        
    }

}


