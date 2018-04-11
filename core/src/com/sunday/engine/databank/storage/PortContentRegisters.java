package com.sunday.engine.databank.storage;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.Data;
import com.sunday.engine.databank.Port;
import com.sunday.engine.common.register.GroupRegister;

public class PortContentRegisters implements Disposable {
    private GroupRegister<Port, Data> portDataRegister = new GroupRegister<>();
    private GroupRegister<Port, Connection<? extends Data>> portDataConnectionRegister = new GroupRegister<>();
    private GroupRegister<Port, ClassConnection<? extends Data>> portClassConnectionRegister = new GroupRegister<>();

    public GroupRegister<Port, Data> getPortDataRegister() {
        return portDataRegister;
    }

    public void registerData(Port port, Data data) {
        portDataRegister.register(port, data);
    }

    public void deregisterData(Port port, Data data) {
        portDataRegister.deregister(port, data);
        portDataConnectionRegister.getValues(port).stream().filter(dataSensor -> dataSensor.source.equals(data)).forEach(connection -> portDataConnectionRegister.deregister(port, connection));
    }

    public void registerConnection(Port port, Connection connection) {
        if (connection instanceof ClassConnection) {
            portClassConnectionRegister.register(port, (ClassConnection<? extends Data>) connection);
        } else {
            portDataConnectionRegister.register(port, connection);
        }
    }

    public void deregisterConnection(Port port, Connection connection) {
        if (connection instanceof ClassConnection) {
            portClassConnectionRegister.deregister(port, (ClassConnection<? extends Data>) connection);
        } else {
            portDataConnectionRegister.deregister(port, connection);
        }
    }

    public void destroy(Port port) {
        portDataRegister.getValues(port).forEach(data -> portDataRegister.deregister(port, data));
        portDataConnectionRegister.getValues(port).forEach(dataSensor -> portDataConnectionRegister.deregister(port, dataSensor));
        portClassConnectionRegister.getValues(port).forEach(classSensor -> portClassConnectionRegister.deregister(port, classSensor));
    }

    public void dispose() {
        portDataRegister.getKeys().forEach(key -> {
            portDataRegister.deregisterKey(key);
            portDataConnectionRegister.deregisterKey(key);
            portClassConnectionRegister.deregisterKey(key);
        });
    }
}
