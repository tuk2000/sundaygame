package com.sunday.tool.ScreenLoader;

import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.game.GameFramework.GameFramework;
import com.sunday.tool.ToolPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScreenLoaderPanel extends ToolPanel {
    private JPanel panel;
    private JList list;
    private JButton btnLoad;

    public ScreenLoaderPanel() {
        btnLoad.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFramework.app.log("ScreenLoader", "GameStatus selected");
                GameFramework.GameFlow.setGameStatus((GameStatus) list.getSelectedValue());
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        list = new JList();
    }

    @Override
    public void updateView() {
        SwingUtilities.invokeLater(list::updateUI);
    }

    @Override
    public void useContentData(Object contentData) {
        if (contentData instanceof ArrayList) {
            ArrayList<GameStatus> arrayList = (ArrayList) contentData;
            list.setModel(new AbstractListModel() {
                @Override
                public int getSize() {
                    return arrayList.size();
                }

                @Override
                public Object getElementAt(int index) {
                    return arrayList.get(index);
                }
            });
        }
        updateView();
    }
}
