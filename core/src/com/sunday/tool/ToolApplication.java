package com.sunday.tool;

import com.sunday.tool.datamonitor.DataMonitor;
import com.sunday.tool.drivermonitor.KeyBoardMonitor;
import com.sunday.tool.drivermonitor.MouseMonitor;
import com.sunday.tool.logger.Logger;
import com.sunday.tool.perfermancemonitor.PerformanceMonitor;
import com.sunday.tool.screenmonitor.ScreenMonitor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ToolApplication extends Application implements Runnable {
    public static Logger logger = new Logger();
    public static ScreenMonitor screenMonitor = new ScreenMonitor();
    public static DataMonitor dataMonitor = new DataMonitor();
    public static PerformanceMonitor performanceMonitor = new PerformanceMonitor();
    public static KeyBoardMonitor keyBoardMonitor = new KeyBoardMonitor();
    public static MouseMonitor mouseMonitor = new MouseMonitor();

    private static Stage wnd;
    private static ToolApplicationUIController toolApplicationUIController;
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
        primaryStage.setTitle("Tool");
        primaryStage.setScene(scene);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();

        toolApplicationUIController = loader.getController();
        toolApplicationUIController.initial();
        logger.setUIController(toolApplicationUIController);
        screenMonitor.setUIController(toolApplicationUIController);
        dataMonitor.setUIController(toolApplicationUIController);
        performanceMonitor.setUIController(toolApplicationUIController);
        keyBoardMonitor.setUIController(toolApplicationUIController);
        mouseMonitor.setUIController(toolApplicationUIController);

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
