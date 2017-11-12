package com.sunday.tool;

import com.sunday.tool.GameMonitor.GameMonitor;
import com.sunday.tool.Logger.GameLogger;
import com.sunday.tool.ObjectMonitor.ObjectMonitor;
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
    public static ObjectMonitor objectMonitor = new ObjectMonitor();
    public static GameMonitor gameMonitor = new GameMonitor();

    private static Stage wnd;
    private static ToolController toolController;
    private static Runnable afterInitialRunnable;

    public void runAfterInitial(Runnable runnable) {
        afterInitialRunnable = runnable;
    }

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
        gameLogger.setToolExtenderController(toolController);
        screenLoader.setToolExtenderController(toolController);
        objectMonitor.setToolExtenderController(toolController);
        gameMonitor.setToolExtenderController(toolController);

        Platform.runLater(afterInitialRunnable);
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
