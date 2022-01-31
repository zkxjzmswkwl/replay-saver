package software.carter;

import java.awt.image.BufferedImage;

public class Structures {
    public static record Sector(int x1, int y1, int x2, int y2) {} 
    public static record PreProcessing(boolean useGray, boolean useThresh) {}
    public static record Pass(BufferedImage sample, int deltaR, int deltaG){}
}
