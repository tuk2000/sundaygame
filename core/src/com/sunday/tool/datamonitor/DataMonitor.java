package com.sunday.tool.datamonitor;

import com.sunday.engine.common.Data;
import com.sunday.tool.ToolExtender;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class DataMonitor extends ToolExtender<DataMonitorUIController> {
    private List<DataRecord> dataRecords = new ArrayList<>();

    public DataMonitor() {
        uiControllerBuffer.addBuffer(DataRecord.class, true,
                (BiConsumer<DataMonitorUIController, DataRecord>) (dataMonitorUIController, dataRecord) -> dataMonitorUIController.addDataRecord(dataRecord));
        uiControllerBuffer.addBuffer(DataRecord.class, false,
                (BiConsumer<DataMonitorUIController, DataRecord>) (dataMonitorUIController, dataRecord) -> dataMonitorUIController.removeDataRecord(dataRecord));
    }

    public void newData(Data data, String systemName) {
        DataRecord dataRecord = new DataRecord(data, systemName);
        dataRecords.add(dataRecord);
        uiControllerBuffer.addInstance(dataRecord);
        flushBuffer();
    }

    public void deleteData(Data data) {
        for (DataRecord record : dataRecords) {
            if (record.data.equals(data)) {
                dataRecords.remove(record);
                uiControllerBuffer.removeInstance(record);
                break;
            }
        }
    }
}
