package com.sunday.builder;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.Timer;
import java.util.TimerTask;

public class BuilderWindow extends JFrame {

    public BuilderWindow() {
        setTitle("Proton-Builder");
        setVisible(true);
        setSize(1980, 1020);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
                JFrame splashFrame = new JFrame();
                splashFrame.setUndecorated(true);
                JLabel jLabel = new JLabel("Welcome");
                splashFrame.getContentPane().add(jLabel);
                splashFrame.setLocationRelativeTo(BuilderWindow.this);
                splashFrame.setSize(100, 50);
                splashFrame.setVisible(true);
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        splashFrame.dispose();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(timerTask, 2000);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(e.getWindow(), "Close?", "Exit", JOptionPane.OK_CANCEL_OPTION);
                //YES_OPTION = 0;NO_OPTION = 1;CANCEL_OPTION = 2;OK_OPTION = 0;CLOSED_OPTION = -1;
                switch (result) {
                    case JOptionPane.OK_OPTION:
                        System.exit(0);
                        break;
                    case JOptionPane.CANCEL_OPTION:
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
