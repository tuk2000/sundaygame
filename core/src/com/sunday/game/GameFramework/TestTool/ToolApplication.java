package com.sunday.game.GameFramework.TestTool;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ToolApplication extends Application implements Runnable {
    private static Stage wnd;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        wnd = primaryStage;
        Platform.setImplicitExit(false);
        Parent root = FXMLLoader.load(getClass().getResource("Tool.fxml"));
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
