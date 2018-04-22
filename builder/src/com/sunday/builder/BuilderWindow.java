package com.sunday.builder;

import com.sunday.builder.simulation.SimulationExecutor;
import com.sunday.builder.simulation.SimulationExecutorImpl;
import com.sunday.builder.simulation.SimulationTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class BuilderWindow extends JFrame {
    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    protected JMenuBar menuBar;
    protected JPanel mainPane;
    protected JList guideList;
    protected JToolBar workToolBar;
    protected JToolBar viewToolBar;
    protected JTabbedPane workTablePane;
    protected JTextArea consoleArea;
    JPanel workPane;
    private JMenu fileMenu, editorMenu, viewMenu, demoMenu, aboutMenu;
    private SimulationExecutor simulationExecutor;
    private SimulationTask simulationTask;

    public BuilderWindow() {
//        setJMenuBar(menuToolBar1);
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
                splashFrame.getContentPane().setBackground(Color.WHITE);
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        guideList = new JList();
        workPane = new JPanel();
        workTablePane = new JTabbedPane();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editorMenu = new JMenu("Editor");
        viewMenu = new JMenu("View");
        demoMenu = new JMenu("Demo");
        aboutMenu = new JMenu("About");
        JMenuItem runDemo = new JMenuItem("execute in builder");
        JMenuItem runDemoDistracted = new JMenuItem("execute outside builder");
        JMenuItem terminateDemo = new JMenuItem("terminate");
        JFrame jFrame = new JFrame();
        ActionListener menuActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(runDemo)) {
                    workTablePane.add(workPane, simulationTask.getConfig().title);
                    simulationExecutor.setup(simulationTask, workPane);
                    simulationExecutor.execute();
                    consoleOutput("executing : " + simulationTask.getConfig().title);
                } else if (e.getSource().equals(runDemoDistracted)) {
                    jFrame.setLocationRelativeTo(workTablePane);
                    jFrame.setSize(simulationTask.getConfig().width, simulationTask.getConfig().height);
                    jFrame.setVisible(true);
                    jFrame.add(workPane);
                    simulationExecutor.setup(simulationTask, workPane);
                    simulationExecutor.execute();
                    consoleOutput("executing : " + simulationTask.getConfig().title);
                } else if (e.getSource().equals(terminateDemo)) {
                    boolean wasRunning = simulationExecutor.isRunning();
                    simulationExecutor.terminate();
                    workTablePane.remove(workPane);
                    jFrame.remove(workPane);
                    jFrame.setVisible(false);
                    jFrame.dispose();
                    boolean isRunning = simulationExecutor.isRunning();
                    if (wasRunning && !isRunning)
                        consoleOutput("terminated : " + simulationTask.getConfig().title);
                    else if (!wasRunning)
                        consoleOutput("noting to terminate ");
                }
            }
        };
        runDemo.addActionListener(menuActionListener);
        terminateDemo.addActionListener(menuActionListener);
        runDemoDistracted.addActionListener(menuActionListener);
        demoMenu.add(runDemo);
        demoMenu.add(runDemoDistracted);
        demoMenu.add(terminateDemo);
        menuBar.add(fileMenu);
        menuBar.add(editorMenu);
        menuBar.add(viewMenu);
        menuBar.add(demoMenu);
        menuBar.add(aboutMenu);
        this.setJMenuBar(menuBar);
        mainPane = new JPanel();
        this.setContentPane(mainPane);
        workPane = new JPanel();
        workPane.setLayout(new BorderLayout());
        consoleArea = new JTextArea();
    }

    private void consoleOutput(String msg) {
        LocalTime now = LocalTime.now();
        consoleArea.append(now.toString() + " " + msg + "\n");
    }

    public void setDemoSimulationTask(SimulationTask simulationTask) {
        if (simulationExecutor == null) {
            simulationExecutor = new SimulationExecutorImpl();
        } else {
            if (simulationExecutor.isRunning()) {
                simulationExecutor.terminate();
            }
        }
        this.simulationTask = simulationTask;
    }
}
