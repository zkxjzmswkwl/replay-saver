package software.carter.overwatch;

import software.carter.Imaging;
import software.carter.Structures;
import software.carter.Tess;

enum PlayerDisplay {
}

public class Overwatch {

    // 653:168
    // 772:190
    private static Structures.Sector testSector = new Structures.Sector(2560 + 655, 155, 2560 + 785, 175);
    private static Structures.Capture testCapture = new Structures.Capture(testSector, 600, 200);

    public static int getSpecPosition(String playerName) {
        int x1 = 2560 + 80;
        int x2;
        int y1 = 155;
        int y2 = 175;

        for (int i = 1; i <= 7; i++) {
            x2 = x1 + 130;
            Structures.Sector testing = new Structures.Sector(x1, x2, y1, y2);
            Structures.Capture testing1 = new Structures.Capture(testing, 600, 200);
            String name = Tess.getInstance().readSingleLine(Imaging.capture(testing1));
            System.out.println(name);
            x1 += 130;
        }
        // int position;
        // String out = Tess.getInstance().readSingleLine(Imaging.capture(testCapture));
        // System.out.println(out);
        return 5;
    }
}
