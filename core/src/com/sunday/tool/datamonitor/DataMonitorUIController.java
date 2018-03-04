package com.sunday.tool.datamonitor;

import com.sunday.tool.ToolExtenderUIController;

public interface DataMonitorUIController extends ToolExtenderUIController {
    void addDataRecord(DataRecord dataRecord);

    void removeDataRecord(DataRecord dataRecord);
}
