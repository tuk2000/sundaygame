package com.sunday.tool;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

public class ToolController {
    XYChart.Series memoryUsages = new XYChart.Series();
    XYChart.Series fpsSeries = new XYChart.Series();
    @FXML
    private StackedAreaChart<Number, Number> fpsChart;
    @FXML
    private StackedAreaChart<Number, Number> memoryChart;

    @FXML
    private TableView<?> screenTable;

    @FXML
    private ListView<?> logList;

    @FXML
    private Button btLoad;


    @FXML
    void loadScreen(ActionEvent event) {
        if (event.getSource().equals(btLoad)) {
            System.out.println("LoadScreen");
        }
    }

    public void initial() {
        System.out.println("ToolController");
        memoryUsages.getData().add(new XYChart.Data(0, 0));
        memoryChart.setLegendVisible(false);
        memoryChart.getData().add(memoryUsages);

        fpsSeries.getData().add(new XYChart.Data(0, 0));
        fpsChart.setLegendVisible(false);
        fpsChart.getData().add(fpsSeries);
    }

    public void newMemoryUsage(float time, long memoryUsage) {
        Platform.runLater(() -> {
            memoryChart.getData().get(0).getData().add(new XYChart.Data(time, memoryUsage / 1024));
        });
    }

    public void newFPS(float time, int fps) {
        Platform.runLater(() -> {
            fpsChart.getData().get(0).getData().add(new XYChart.Data(time, fps));
        });
    }
}
