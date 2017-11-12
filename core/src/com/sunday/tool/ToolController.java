package com.sunday.tool;

import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.tool.GameMonitor.GameMonitorController;
import com.sunday.tool.Logger.GameLoggerController;
import com.sunday.tool.Logger.LogMessage;
import com.sunday.tool.ObjectMonitor.ObjectMonitorController;
import com.sunday.tool.ScreenLoader.ScreenLoaderController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;

public class ToolController implements GameMonitorController, GameLoggerController, ObjectMonitorController, ScreenLoaderController {
    XYChart.Series memoryUsages = new XYChart.Series();
    XYChart.Series fpsSeries = new XYChart.Series();
    @FXML
    private StackedAreaChart<Number, Number> fpsChart;
    @FXML
    private StackedAreaChart<Number, Number> memoryChart;

    @FXML
    private TableView<Object> screenTable;

    @FXML
    private TableView<LogMessage> logTable;

    @FXML
    private Button btLoad;


    @FXML
    void loadScreen(ActionEvent event) {
        if (event.getSource().equals(btLoad)) {
            System.out.println("LoadScreen");
        }
    }

    public void initial() {
        memoryUsages.getData().add(new XYChart.Data(0, 0));
        memoryChart.setLegendVisible(false);
        memoryChart.getData().add(memoryUsages);

        fpsSeries.getData().add(new XYChart.Data(0, 0));
        fpsChart.setLegendVisible(false);
        fpsChart.getData().add(fpsSeries);

        TableColumn typeCol = logTable.getColumns().get(0);
        typeCol.setMinWidth(200);
        typeCol.setCellValueFactory(new PropertyValueFactory<LogMessage, String>("Type"));

        TableColumn tagCol = logTable.getColumns().get(1);
        tagCol.setMinWidth(200);
        tagCol.setCellValueFactory(new PropertyValueFactory<LogMessage, String>("Tag"));

        TableColumn contentCol = logTable.getColumns().get(2);
        contentCol.setMinWidth(800);
        contentCol.setCellValueFactory(new PropertyValueFactory<LogMessage, String>("Content"));

    }

    @Override
    public void newMemoryUsage(float time, long memoryUsage) {
        Platform.runLater(() -> {
            memoryChart.getData().get(0).getData().add(new XYChart.Data(time, memoryUsage));
        });
    }

    @Override
    public void newFPS(float time, int fps) {
        Platform.runLater(() -> {
            fpsChart.getData().get(0).getData().add(new XYChart.Data(time, fps));
        });
    }

    @Override
    public void addMonitoredObject(Object object) {

    }

    @Override
    public void removeMonitoredObject(Object object) {

    }

    @Override
    public void loadGameStatusEnum(ArrayList<GameStatus> arrayList) {

    }

    @Override
    public void newLogMessage(LogMessage logMessage) {
        Platform.runLater(() -> {
            logTable.getItems().add(logMessage);
        });
    }
}
