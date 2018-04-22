package com.sunday.builder.simulation;

import javax.swing.*;

public interface SimulationExecutor {

    boolean isRunning();

    void setup(SimulationTask simulationTask, JPanel container);

    void execute();

    void terminate();

}
