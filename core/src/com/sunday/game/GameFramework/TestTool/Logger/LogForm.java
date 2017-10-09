package com.sunday.game.GameFramework.TestTool.Logger;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.Vector;

public class LogForm extends JFrame {
    private JList logList;
    private JPanel panel1;

    public LogForm(){
        setSize(600,800);
        setContentPane(panel1);
        setVisible(true);
        pack();
    }

    public void UpdateList(ListModel listModel){
        SwingUtilities.invokeLater(() -> {
            logList.setModel(listModel);
            logList.validate();
        });
    }
}
