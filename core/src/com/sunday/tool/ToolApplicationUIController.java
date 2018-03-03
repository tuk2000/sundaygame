package com.sunday.tool;

import com.sunday.game.framework.GameFramework;
import com.sunday.tool.datamonitor.DataMonitorUIController;
import com.sunday.tool.datamonitor.MonitoredData;
import com.sunday.tool.drivermonitor.KeyBoardMonitorUIController;
import com.sunday.tool.drivermonitor.MouseMonitorUIController;
import com.sunday.tool.logger.GameLoggerUIController;
import com.sunday.tool.logger.LogRecord;
import com.sunday.tool.perfermancemonitor.PerformanceMonitorUIController;
import com.sunday.tool.perfermancemonitor.PerformanceRecord;
import com.sunday.tool.screenloader.ScreenMonitorUIController;
import com.sunday.tool.screenloader.ScreenRecord;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ToolApplicationUIController implements PerformanceMonitorUIController, GameLoggerUIController, DataMonitorUIController, ScreenMonitorUIController, KeyBoardMonitorUIController, MouseMonitorUIController {
    private XYChart.Series memoryUsages = new XYChart.Series();
    private XYChart.Series fpsSeries = new XYChart.Series();
    @FXML
    private StackedAreaChart<Number, Number> fpsChart;
    @FXML
    private StackedAreaChart<Number, Number> memoryChart;

    @FXML
    private ListView<String> screens;

    @FXML
    private TableView<ScreenRecord> screenTable;

    @FXML
    private TableView<LogRecord> logTable;

    @FXML
    private Button btLoad;

    @FXML
    private TextArea keyBoardHistory;

    @FXML
    private Label keyboardSignal;

    @FXML
    private Label keyboardKey;

    @FXML
    private Label keyBoardStatus;

    @FXML
    private Label mouseSignal;

    @FXML
    private Label mouseKey;

    @FXML
    private Label mouseStatus;

    @FXML
    private Label gamePadSignal;

    @FXML
    private Label gamePadKey;

    @FXML
    private Label gamePadStatus;

    @FXML
    private TreeTableView<?> treeTableView;

    @FXML
    private RadioButton radixOptSystem;

    @FXML
    private ToggleGroup dataOptionGroup;

    @FXML
    private RadioButton radixOptType;

    @FXML
    void loadScreen(ActionEvent event) {
        if (event.getSource().equals(btLoad)) {
            String screen = screens.getSelectionModel().getSelectedItem();
            if (screen != null) {
                GameFramework.Screen.setCurrentScreen(screen);
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


        TableColumn screenClassCol = screenTable.getColumns().get(0);
        screenClassCol.setMinWidth(400);
        screenClassCol.setCellValueFactory(new PropertyValueFactory<ScreenRecord, String>("ClassName"));
        TableColumn screenInstanceCol = screenTable.getColumns().get(1);
        screenInstanceCol.setMinWidth(400);
        screenInstanceCol.setCellValueFactory(new PropertyValueFactory<ScreenRecord, String>("InstanceName"));


        TableColumn typeCol = logTable.getColumns().get(0);
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(new PropertyValueFactory<LogRecord, String>("Type"));

        TableColumn tagCol = logTable.getColumns().get(1);
        tagCol.setMinWidth(200);
        tagCol.setCellValueFactory(new PropertyValueFactory<LogRecord, String>("Tag"));

        TableColumn contentCol = logTable.getColumns().get(2);
        contentCol.setMinWidth(800);
        contentCol.setCellValueFactory(new PropertyValueFactory<LogRecord, String>("Content"));

        dataOptionGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (dataOptionGroup.getSelectedToggle() == radixOptSystem) {

                } else if (dataOptionGroup.getSelectedToggle() == radixOptType) {

                }
            }
        });

    }

    @Override
    public void newPerformanceRecord(PerformanceRecord performanceRecord) {
        Platform.runLater(() -> {
            memoryChart.getData().get(0).getData().add(new XYChart.Data(performanceRecord.time, performanceRecord.memoryUsage));
            fpsChart.getData().get(0).getData().add(new XYChart.Data(performanceRecord.time, performanceRecord.fps));
        });
    }

    @Override
    public void addMonitoredObject(MonitoredData monitoredObject) {
        Platform.runLater(() -> {
            // screenTable.getItems().add(monitoredObject);
        });
    }

    @Override
    public void removeMonitoredObject(MonitoredData monitoredObject) {
        Platform.runLater(() -> {
            //screenTable.getItems().remove(monitoredObject);
        });
    }

    @Override
    public void loadScreenClass(String screenClassName) {
        Platform.runLater(() -> {
            screens.getItems().add(screenClassName);
        });
    }

    @Override
    public void addScreenRecord(ScreenRecord screenRecord) {
        Platform.runLater(() -> {
            screenTable.getItems().add(screenRecord);
        });
    }

    @Override
    public void removeScreenRecord(ScreenRecord screenRecord) {
        Platform.runLater(() -> {
            screenTable.getItems().remove(screenRecord);
        });
    }

    @Override
    public void newLogRecord(LogRecord logRecord) {
        Platform.runLater(() -> {
            logTable.getItems().add(logRecord);
        });
    }

    @Override
    public void setKeyBoardSignal(String signal) {
        Platform.runLater(() -> {
            keyboardSignal.setText(signal);
        });
    }

    @Override
    public void setKeyBoardKey(String key) {
        Platform.runLater(() -> {
            keyboardKey.setText(key);
        });
    }

    @Override
    public void setKeyBoardStatus(String status) {
        Platform.runLater(() -> {
            keyBoardStatus.setText(status);
            keyBoardHistory.appendText(keyboardKey.getText() + "-" + keyboardSignal.getText() + "\n");
        });
    }

    @Override
    public void setMouseSignal(String signal) {
        Platform.runLater(() -> {
            mouseSignal.setText(signal);
        });
    }

    @Override
    public void setMouseLocation(int screenX, int screenY) {
        Platform.runLater(() -> {
            mouseStatus.setText("[" + screenX + "," + screenY + "]");
        });
    }

    @Override
    public void setMouseKey(String key) {
        Platform.runLater(() -> {
            mouseKey.setText(key);
        });
    }
}
