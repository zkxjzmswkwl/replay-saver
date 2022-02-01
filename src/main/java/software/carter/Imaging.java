package software.carter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.Rectangle;

public class Imaging {
    public static Image image = new Image("test.png");
    public static ImageView imageView = new ImageView(image);

    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }

    public static Mat convertToMat(BufferedImage image) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            byteArrayOutputStream.flush();
            return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage convertToBufferedImage(Mat matrix) {
        try {
            MatOfByte mob = new MatOfByte();
            Imgcodecs.imencode(".jpg", matrix, mob);
            return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Mat preprocessOtsu(Mat mat) {
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(mat, mat, 0, 255, Imgproc.THRESH_OTSU);
        return mat;
    }

    public static BufferedImage capture(Structures.Capture capture) {
        Rectangle rect = new Rectangle(
                capture.sector().x1(),
                capture.sector().y1(),
                capture.sector().x2() - capture.sector().x1(),
                capture.sector().y2() - capture.sector().y1());

        BufferedImage out = SRobot.getRobot().createScreenCapture(rect);
        Mat flippedBuffer = convertToMat(out);
        Size size = new Size(flippedBuffer.width(), flippedBuffer.height());
        Imgproc.resize(flippedBuffer, flippedBuffer, size, 2, 2, Imgproc.INTER_CUBIC);
        Imgproc.cvtColor(flippedBuffer, flippedBuffer, Imgproc.COLOR_BGR2RGB);
        Imgproc.medianBlur(flippedBuffer, flippedBuffer, 3);
        // flippedBuffer = preprocessOtsu(flippedBuffer);


        out = convertToBufferedImage(flippedBuffer);

        image = convertToFxImage(out);
        imageView.setImage(image);

        return out;
    }
}
