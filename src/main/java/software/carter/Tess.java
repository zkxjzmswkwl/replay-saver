package software.carter;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.awt.image.BufferedImage;

// Singleton
public class Tess {
    private static Tess tess;
    private static final Tesseract tesseract;

    static {
        tess = new Tess();
        tesseract = new Tesseract();
        tesseract.setDatapath(Config.getConfig().read("tesseract_path"));
        // tesseract.setTessVariable("user_defined_dpi", "70");
    }

    public static Tess getInstance() {
        return tess;
    }

    public void setTesseractVar(String key, String value) {
        tesseract.setTessVariable(key, value);
    }

    public String readSingleLine(BufferedImage input) {
        String ret = this.readBufferedImage(input);
        return ret.toLowerCase();
    }

    public String readBufferedImage(BufferedImage input) {
        try {
            String a = tesseract.doOCR(input);
            // System.out.println(a);
            return a;
        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }
}