package com.sunday.builder;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Proton-Builder");
        setVisible(true);
        setSize(1980, 1020);
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });
        addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {

            }
        });
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                JOptionPane.showMessageDialog(e.getWindow(), "Welcome!");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(e.getWindow(), "Close?");
                //YES_OPTION = 0;NO_OPTION = 1;CANCEL_OPTION = 2;OK_OPTION = 0;CLOSED_OPTION = -1;
                switch (result) {
                    case 0:
                        System.exit(0);
                        break;
                    default:
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
