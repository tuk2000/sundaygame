package com.sunday.tool;

import com.sunday.game.framework.GameFramework;
import com.sunday.tool.datamonitor.DataMonitorUIController;
import com.sunday.tool.datamonitor.DataRecord;
import com.sunday.tool.drivermonitor.KeyBoardMonitorUIController;
import com.sunday.tool.drivermonitor.MouseMonitorUIController;
import com.sunday.tool.logger.LogRecord;
import com.sunday.tool.logger.LoggerUIController;
import com.sunday.tool.perfermancemonitor.PerformanceMonitorUIController;
import com.sunday.tool.perfermancemonitor.PerformanceRecord;
import com.sunday.tool.screenmonitor.ScreenMonitorUIController;
import com.sunday.tool.screenmonitor.ScreenRecord;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class ToolApplicationUIController implements PerformanceMonitorUIController, LoggerUIController, DataMonitorUIController, ScreenMonitorUIController, KeyBoardMonitorUIController, MouseMonitorUIController {
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
    private TreeTableView<DataRecord> treeTableView;

    @FXML
    private TreeTableColumn<DataRecord, String> groupColumn;

    @FXML
    private TreeTableColumn<DataRecord, String> instanceColumn;

    @FXML
    private RadioButton radixOptSystem;

    @FXML
    private ToggleGroup dataOptionGroup;

    @FXML
    private RadioButton radixOptType;

    @FXML
    private Label description;

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
                if (treeTableView.getRoot() == null) return;
                List<DataRecord> list = getDataRecords();
                treeTableView.getRoot().getChildren().forEach(item -> item.getChildren().clear());
                treeTableView.getRoot().getChildren().clear();
                list.forEach(dataRecord -> addDataRecord(dataRecord));
                treeTableView.refresh();
            }
        });

        groupColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<DataRecord, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<DataRecord, String> param) {
                adjustItemName(param.getValue().getValue());
                return new ReadOnlyStringWrapper(param.getValue().getValue().itemName);
            }
        });

        instanceColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<DataRecord, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<DataRecord, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getValue().instanceName);
            }
        });

    }

    @Override
    public void newPerformanceRecord(PerformanceRecord performanceRecord) {
        Platform.runLater(() -> {
            memoryChart.getData().get(0).getData().add(new XYChart.Data(performanceRecord.time, performanceRecord.memoryUsage / 1024));
            fpsChart.getData().get(0).getData().add(new XYChart.Data(performanceRecord.time, performanceRecord.fps));
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

    private List<DataRecord> getDataRecords() {
        List<DataRecord> list = new ArrayList();
        treeTableView.getRoot().getChildren().forEach(item -> {
            item.getChildren().forEach(recordTreeItem -> list.add(recordTreeItem.getValue()));
        });
        return list;
    }

    private void adjustItemName(DataRecord dataRecord) {
        boolean isLabel = false;
        if (dataRecord.equals(treeTableView.getRoot().getValue())) {
            return;
        }
        for (TreeItem<DataRecord> item : treeTableView.getRoot().getChildren()) {
            if (item.getValue().equals(dataRecord)) {
                isLabel = true;
                break;
            }
        }
        if (!isLabel) {
            dataRecord.itemName = radixOptSystem.isSelected() ? dataRecord.className : dataRecord.systemName;
        }
    }

    private TreeItem findGroup(DataRecord dataRecord) {
        boolean hasGroup = false;
        TreeItem treeItem = null;
        for (TreeItem<DataRecord> item : treeTableView.getRoot().getChildren()) {
            if (item.getValue().itemName.equals(dataRecord.systemName) || item.getValue().itemName.equals(dataRecord.className)) {
                treeItem = item;
                hasGroup = true;
                break;
            }
        }
        if (!hasGroup) {
            DataRecord newDataRecord = new DataRecord();
            if (radixOptSystem.isSelected()) {
                newDataRecord.itemName = dataRecord.systemName;
            } else {
                newDataRecord.itemName = dataRecord.className;
            }
            treeItem = new TreeItem(newDataRecord);
            treeTableView.getRoot().getChildren().add(treeItem);
        }
        return treeItem;
    }

    @Override
    public void addDataRecord(DataRecord dataRecord) {
        Platform.runLater(() -> {
            if (treeTableView.getRoot() == null) {
                DataRecord root = new DataRecord();
                root.itemName = "DataBank";
                treeTableView.setRoot(new TreeItem<>(root));
            }
            treeTableView.getRoot().getChildren().forEach(item -> item.setExpanded(false));
            TreeItem treeItem = findGroup(dataRecord);
            treeItem.setExpanded(true);
            treeItem.getChildren().add(new TreeItem<>(dataRecord));
        });
    }

    @Override
    public void removeDataRecord(DataRecord dataRecord) {
        Platform.runLater(() -> {
            if (treeTableView.getRoot() == null) {
                return;
            }
            TreeItem<DataRecord> treeItem = findGroup(dataRecord);
            treeItem.setExpanded(true);
            TreeItem<DataRecord> theItem = null;
            for (TreeItem<DataRecord> item : treeItem.getChildren()) {
                if (item.getValue().equals(dataRecord)) {
                    theItem = item;
                    break;
                }
            }
            if (theItem != null) {
                treeItem.getChildren().remove(theItem);
            }
            if (treeItem.getChildren().size() == 0) {
                treeTableView.getRoot().getChildren().remove(treeItem);
            }
        });
    }
}
