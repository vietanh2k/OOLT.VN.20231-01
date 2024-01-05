package gui;

import javax.swing.*;
import java.awt.*;

public class MenuGui extends JPanel {
    public JButton startBtn;
    public JButton helpBtn;
    public JButton quitBtn;
    public JComboBox<String> sortList;

    public MenuGui(){
        setLayout(new GridBagLayout());
        JLabel title = new JLabel("Sorting Demo");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        GridBagConstraints titleConstraint = new GridBagConstraints();
        titleConstraint.gridx = 0;
        titleConstraint.gridy = 0;
        titleConstraint.insets = new Insets(10, 10, 20, 10);
        add(title, titleConstraint);

        sortList = new JComboBox<>(new String[]{"Bubble Sort", "Insertion Sort", "Selection Sort"});
        sortList.setFont(new Font("Arial", Font.PLAIN, 16));
        GridBagConstraints sortListConstraints = new GridBagConstraints();
        sortListConstraints.gridx = 0;
        sortListConstraints.gridy = 1;
        sortListConstraints.insets = new Insets(0, 10, 10, 10);
        add(sortList, sortListConstraints);

        startBtn = new JButton("Start Demo");
        startBtn.setFont(new Font("Arial", Font.PLAIN, 18));
//        startBtn.addActionListener(e -> showDemo());
        GridBagConstraints startBtnConstraints = new GridBagConstraints();
        startBtnConstraints.gridx = 0;
        startBtnConstraints.gridy = 2;
        startBtnConstraints.insets = new Insets(10, 10, 10, 10);
        add(startBtn, startBtnConstraints);

        helpBtn = new JButton("Help");
        helpBtn.setFont(new Font("Arial", Font.PLAIN, 16)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
//        helpBtn.addActionListener(e -> showHelp());
        GridBagConstraints helpBtnConstraints = new GridBagConstraints();
        helpBtnConstraints.gridx = 0;
        helpBtnConstraints.gridy = 3;
        helpBtnConstraints.insets = new Insets(10, 10, 10, 10);
        add(helpBtn, helpBtnConstraints);

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Arial", Font.PLAIN, 16)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
//        quitBtn.addActionListener(e -> confirmQuit());
        GridBagConstraints quitBtnConstraints = new GridBagConstraints();
        quitBtnConstraints.gridx = 0;
        quitBtnConstraints.gridy = 4;
        quitBtnConstraints.insets = new Insets(10, 10, 10, 10);
        add(quitBtn, quitBtnConstraints);
    }
}
