package com.sunday.tool;

import com.sunday.tool.Logger.GameLogger;
import com.sunday.tool.ScreenLoader.ScreenLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ToolApplication extends Application implements Runnable {
    public static GameLogger gameLogger = new GameLogger();
    public static ScreenLoader screenLoader = new ScreenLoader();
    private static Stage wnd;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        wnd = primaryStage;
        Platform.setImplicitExit(false);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Tool.fxml"));
        Parent root = loader.load();
        Tool tool = new Tool();
        loader.setController(tool);

        Scene scene = new Scene(root);
        primaryStage.setTitle("TestTool");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void switchOnOrOff() {
        Platform.runLater(() -> {
            if (wnd.isShowing())
                wnd.hide();
            else {
                wnd.show();
            }
        });
    }

    @Override
    public void run() {
        launch();
    }
}
