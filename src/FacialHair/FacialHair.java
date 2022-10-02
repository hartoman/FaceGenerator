package FacialHair;

import java.awt.*;
import java.io.Serializable;

public class FacialHair implements Serializable {

    private MoustacheArea moustache;
    private ChinArea chin;
    private Cheeks cheeks;

    public FacialHair() {

        moustache = new MoustacheArea();
        chin = new ChinArea();
        cheeks= new Cheeks();
    }

    public void drawFacialHair(Graphics2D g2d, Color hairColor) {

        moustache.drawMoustache(g2d, hairColor);
        chin.drawChinArea(g2d, hairColor);
        cheeks.drawBeardArea(g2d, hairColor);

    }

    public void setFacialHair(int moustacheSize, int curled,
            int soulpatchHeight, int soulpatchWidth,
            int beardLength, int beardWidth) {

        this.getMoustache().setMoustacheSize(moustacheSize);
        this.getMoustache().setCurled(curled);
        this.getChinArea().setSoulpatchHeight(soulpatchHeight);
        this.getChinArea().setSoulpatchWidth(soulpatchWidth);
        this.getCheeks().beardLength(beardLength);
        this.getCheeks().beardWidth(beardWidth);

    }

    public Cheeks getCheeks() {
        return cheeks;
    }

    public void setCheeks(Cheeks cheeks) {
        this.cheeks = cheeks;
    }

    public MoustacheArea getMoustache() {
        return moustache;
    }

    public void setMoustache(MoustacheArea moustache) {
        this.moustache = moustache;
    }

    public ChinArea getChinArea() {
        return chin;
    }

    public void setChinArea(ChinArea chin) {
        this.chin = chin;
    }

}
