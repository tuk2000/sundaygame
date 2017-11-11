package com.sunday.tool;

import com.badlogic.gdx.Gdx;
import com.sunday.tool.Logger.GameLogger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ToolApplication extends Application implements Runnable {
    public static GameLogger gameLogger = new GameLogger();
    public static ScreenLoader screenLoader = new ScreenLoader();
    public static ObjectMonitor objectMonitor = new ObjectMonitor();
    private static Stage wnd;
    private Scene scene;
    private static ToolController toolController;
    private float duration = 0.0f;
    private float time = 0.0f;

    @Override
    public void start(Stage primaryStage) throws Exception {
        wnd = primaryStage;
        Platform.setImplicitExit(false);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Tool.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("TestTool");
        primaryStage.setScene(scene);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();
        toolController = loader.getController();
        toolController.initial();

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

    public void updateView() {
        duration += Gdx.graphics.getDeltaTime();
        if (duration > 0.5) {
            time += duration;
            duration -= 0.5;
            UpdateFPSChart(time);
            UpdateMemoryChart(time);
        }
    }

    private void UpdateMemoryChart(float time) {
        if (toolController != null) {
            toolController.newMemoryUsage(time, Gdx.app.getJavaHeap() / (1024));
        }
    }

    private void UpdateFPSChart(float time) {
        if (toolController != null) {
            toolController.newFPS(time, Gdx.graphics.getFramesPerSecond());
        }
    }
}
