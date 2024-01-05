package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoGui extends JPanel {
    private int sizeW = 0;
    private int sizeH = 0;
    public JLabel[] itemList;
    public JLabel[] indexList;
    public Timer timer;
    public JButton backBtn;
    public JButton startBtn;
    public JLabel resultArray;
    public JLabel sortName;
    public int[] arr = null;


    public DemoGui(int sizeW, int sizeH) {
        this.sizeW = sizeW;
        this.sizeH = sizeH;
        setLayout(null);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        backBtn.addActionListener(e -> resetObject());
        add(backBtn);
        backBtn.setBounds(0, 0, 80, 40);

        startBtn = new JButton("Start");
        startBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        startBtn.addActionListener(e -> swapTwoItem(0, 5));
        add(startBtn);
        startBtn.setBounds(0, 50, 80, 40);

        resultArray = new JLabel();
        resultArray.setFont(new Font("Arial", Font.PLAIN, 24));
        add(resultArray);
        resultArray.setBounds(100, 0, sizeW, 40);

        sortName = new JLabel();
        sortName.setFont(new Font("Arial Bold", Font.PLAIN, 24));
        add(sortName);
        sortName.setBounds(100, 50, 500, 40);
    }

    public void swapTwoItem(int ind1, int ind2) {
        if(ind1 >= itemList.length || ind2 >= itemList.length){
            return;
        }

        JLabel item1 = itemList[ind1];
        JLabel item2 = itemList[ind2];
        item1.setForeground(Color.RED);
        item2.setForeground(Color.RED);

        int posX1 = item1.getX();
        int posX2 = item2.getX();

        int startY = item1.getY();
        int endY = item1.getHeight() * 2 +startY;

        int stepsDown = 10;
        int stepSizeDown = (endY - startY) / stepsDown;

        int sub = Math.abs(ind1 - ind2);
        if(sub <= 2) sub = 2;

        int stepsMove = 2*sub;
        int stepSizeMove = (int)Math.ceil((posX2- posX1) / stepsMove);

        int stepsUp = stepsDown;

        timer = new Timer(10, new ActionListener() {
            int dem0 = 1;
            int dem1 = 1;
            int dem2 = 1;
            int dem3 = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dem0 <= 20){
                    dem0++;
                }else if(dem1 <= stepsDown){
                    if(dem1 == stepsDown){
                        item1.setLocation(item1.getX(), endY);
                        item2.setLocation(item2.getX(), endY);
                    }else{
                        item1.setLocation(item1.getX(), item1.getY() + stepSizeDown);
                        item2.setLocation(item2.getX(), item1.getY() + stepSizeDown);
                    }

                    dem1++;
                }else if(dem2 <= stepsMove){
                    if(dem2 == stepsMove){
                        item1.setLocation(posX2, item1.getY());
                        item2.setLocation(posX1, item2.getY());
                    }else{
                        item1.setLocation(item1.getX() + stepSizeMove, item1.getY());
                        item2.setLocation(item2.getX() - stepSizeMove, item1.getY());
                    }

                    dem2++;
                }else if(dem3 <= stepsUp){
                    if(dem3 == stepsUp){
                        item1.setLocation(item1.getX(), startY);
                        item2.setLocation(item2.getX(), startY);
                    }else{
                        item1.setLocation(item1.getX(), item1.getY() - stepSizeDown);
                        item2.setLocation(item2.getX(), item1.getY() - stepSizeDown);
                    }

                    dem3++;
                }else{
                    item1.setForeground(Color.BLACK);
                    item2.setForeground(Color.BLACK);
                    timer.stop();
                }
            }
        });

        timer.start();
    }

    public void createObject() {
        if (this.arr == null || this.arr.length <= 1) return;
        itemList = new JLabel[arr.length];
        indexList = new JLabel[arr.length];

        int startX = this.sizeW/2 - (arr.length+1)/2 * 70;

        for (int i = 0; i < arr.length; i++) {
            JLabel label = new JLabel();
            label.setLayout(new BorderLayout());
            String pathName = "res/a.png";
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(pathName));
            Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);

            label.setIcon(scaledIcon);
            label.setText("<html><center>" + arr[i] + "</center></html>");
            label.setFont(new Font("Arial", Font.BOLD, 26));
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.CENTER);


            label.setBounds(startX + 70 * i, 120, 50, 50);
            setLayout(null);
            add(label);
            itemList[i] = label;

            JLabel label2 = new JLabel();
            label2.setLayout(new BorderLayout());
            label2.setText("<html><center>" + i + "</center></html>");
            label2.setFont(new Font("Arial", Font.BOLD, 18));
            label2.setHorizontalTextPosition(JLabel.CENTER);
            label2.setVerticalTextPosition(JLabel.CENTER);

            add(label2);
            indexList[i] = label2;

            int tmp = 20;
            if(i >= 10)  tmp = 15;

            label2.setBounds(startX + 70 * i + tmp, 90, 30, 30);
        }
    }

    public void resetObject() {
        for (JLabel label : itemList) {
            if (label == null) continue;
            remove(label);
        }

        for (JLabel jLabel : indexList) {
            if (jLabel == null) continue;
            remove(jLabel);
        }

        itemList = null;
        indexList = null;
        arr = null;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
        if (this.arr == null) return;

        String str = "Before array: ";
        for (int i = 0; i < arr.length; i++) {
            str += arr[i] + " | ";
        }
        resultArray.setText(str);
    }

    public void updateSortName(String name) {
        sortName.setText(name);
    }

    public void updateSortLabel(String sortName) {

    }

    public void resetResult() {

    }
}
