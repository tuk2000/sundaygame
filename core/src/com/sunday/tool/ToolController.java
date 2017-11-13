package com.sunday.tool;

import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.game.GameFramework.GameFramework;
import com.sunday.tool.GameMonitor.GameMonitorController;
import com.sunday.tool.Logger.GameLoggerController;
import com.sunday.tool.Logger.LogMessage;
import com.sunday.tool.ObjectMonitor.MonitoredObject;
import com.sunday.tool.ObjectMonitor.ObjectMonitorController;
import com.sunday.tool.ScreenLoader.ScreenLoaderController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ToolController implements GameMonitorController, GameLoggerController, ObjectMonitorController, ScreenLoaderController {
    XYChart.Series memoryUsages = new XYChart.Series();
    XYChart.Series fpsSeries = new XYChart.Series();
    @FXML
    private StackedAreaChart<Number, Number> fpsChart;
    @FXML
    private StackedAreaChart<Number, Number> memoryChart;

    @FXML
    private ListView<GameStatus> gameStatusList;

    @FXML
    private TableView<MonitoredObject> screenTable;

    @FXML
    private TableView<LogMessage> logTable;

    @FXML
    private Button btLoad;


    @FXML
    void loadScreen(ActionEvent event) {
        if (event.getSource().equals(btLoad)) {
            GameStatus gameStatus = gameStatusList.getSelectionModel().getSelectedItem();
            if (gameStatus != null) {
                GameFramework.GameFlow.setGameStatus(gameStatus);
            }
        }
    }

    public void initial() {
        memoryUsages.getData().add(new XYChart.Data(0, 0));
        memoryChart.setLegendVisible(false);
        memoryChart.getData().add(memoryUsages);

        fpsSeries.getData().add(new XYChart.Data(0, 0));
        fpsChart.setLegendVisible(false);
        fpsChart.getData().add(fpsSeries);


        TableColumn classCol = screenTable.getColumns().get(0);
        classCol.setMinWidth(400);
        classCol.setCellValueFactory(new PropertyValueFactory<MonitoredObject, String>("ClassName"));
        TableColumn objectCol = screenTable.getColumns().get(1);
        objectCol.setMinWidth(400);
        objectCol.setCellValueFactory(new PropertyValueFactory<MonitoredObject, String>("ObjectName"));


        TableColumn typeCol = logTable.getColumns().get(0);
        typeCol.setMinWidth(100);
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
    public void addMonitoredObject(MonitoredObject monitoredObject) {
        Platform.runLater(() -> {
            screenTable.getItems().add(monitoredObject);
        });
    }

    @Override
    public void removeMonitoredObject(MonitoredObject monitoredObject) {
        Platform.runLater(() -> {
            screenTable.getItems().remove(monitoredObject);
        });
    }

    @Override
    public void loadGameStatusEnum(ArrayList<GameStatus> arrayList) {
        Platform.runLater(() -> {
            arrayList.forEach(e -> {
                gameStatusList.getItems().add(e);
            });
        });
    }

    @Override
    public void newLogMessage(LogMessage logMessage) {
        Platform.runLater(() -> {
            logTable.getItems().add(logMessage);
        });
    }
}