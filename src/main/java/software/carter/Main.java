package software.carter;

import javax.lang.model.util.Elements;

import org.apache.log4j.net.SyslogAppender;
import org.opencv.core.Core;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import software.carter.overwatch.Overwatch;

public class Main extends Application {

    Button btn = new Button("Github");
    Button settingsBtn = new Button("Settings");
    Button aboutBtn = new Button("About");
    TextField playerName = new TextField();
    RadioButton saveLog = new RadioButton("Output JSON log");
    RadioButton saveAllPovs = new RadioButton("Record all perspectives");
    Label headerLabel = new Label("Overwatch Replay Tool");
    Label testLabel = new Label("Player Name");


    // public static void sendNotification() {
    //     SystemTray tray = SystemTray.getSystemTray();
    //     Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
    //     PopupMenu popupMenu = new PopupMenu();
    //     MenuItem exitItem = new MenuItem("Exit");

    //     TrayIcon trayIcon = new TrayIcon(image, "Test", popupMenu);
    //     trayIcon.setImageAutoSize(true);
    //     trayIcon.setToolTip("Replay Saver");
    //     tray.add(trayIcon);
    //     trayIcon.displayMessage("Replay Saver", "Test toast", MessageType.INFO);
    // }

    public static void main(String[] args) { 
        launch(args);
    }

    public HBox addHBox(Node... children) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: linear-gradient(#2a4d79, #19304b)");
        hbox.getChildren().addAll(children);
        return hbox;
    }

    public VBox addVBox(Node... children) {
        for (Node child : children) {
            child.getStyleClass().set(0, "vbox-button");
        }
        VBox vbox = new VBox();
        // vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setStyle("-fx-background-color: #141417");
        vbox.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: #303336");
        vbox.getChildren().addAll(children);
        return vbox;
    }

    public GridPane addGridPane(Node... children) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        for (int i = 0; i < children.length; i++) {
            gridPane.add(children[i], i, 0);
        }
        return gridPane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                System.out.println(Overwatch.getSpecPosition("Oppenheimer"));
            }
        });
        BorderPane root = new BorderPane();
        HBox topBar = addHBox(headerLabel);
        GridPane grid = addGridPane(testLabel, playerName, saveLog, saveAllPovs, btn, Imaging.imageView);
        // VBox leftBar = addVBox(settingsBtn, aboutBtn);
        root.setTop(topBar);
        root.setCenter(grid);
        // root.setLeft(leftBar);
        Scene scene = new Scene(root, 720, 480);
        scene.getStylesheets().add("zkxjBase.css");
        stage.setScene(scene);
        stage.setTitle("Overwatch Replay Tool");
        stage.show();
    }

}