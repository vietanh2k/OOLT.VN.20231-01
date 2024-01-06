package gui;

import javax.swing.*;
import java.awt.*;

public class MenuGui extends JPanel {
    private int sizeW = 0;
    private int sizeH = 0;
    public JButton startBtn;
    public JButton helpBtn;
    public JButton quitBtn;
    public JComboBox<String> sortList;

    public MenuGui(int sizeW, int sizeH){
        this.sizeW = sizeW;
        this.sizeH = sizeH;
        setLayout(null);
        JLabel title = new JLabel("Sorting Demo");
        title.setFont(new Font("Arial", Font.BOLD, 50));
        add(title);
        title.setBounds(sizeW / 2 - 170, 100, 600, 80);

        sortList = new JComboBox<>(new String[]{"Bubble Sort", "Heap Sort", "Shell Sort"});
        sortList.setFont(new Font("Arial", Font.PLAIN, 40));
        add(sortList);
        sortList.setBounds(sizeW / 2 - 200, 300, 400, 50);

        startBtn = new JButton("Start Demo");
        startBtn.setFont(new Font("Arial", Font.PLAIN, 40));
        add(startBtn);
        startBtn.setBounds(sizeW / 2 - 150, 400, 300, 50);

        helpBtn = new JButton("Help");
        helpBtn.setFont(new Font("Arial", Font.PLAIN, 40)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
        add(helpBtn);
        helpBtn.setBounds(sizeW / 2 - 150, 480, 300, 50);

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Arial", Font.PLAIN, 40)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
        add(quitBtn);
        quitBtn.setBounds(sizeW / 2 - 150, 560, 300, 50);
    }
}
