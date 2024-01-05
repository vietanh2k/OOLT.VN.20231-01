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
    public int[] arrIndex = null;
    public int maxInd = 0;
    public ImageIcon whiteSquare;
    public ImageIcon greenSquare;
    public ImageIcon orangeSquare;
    public ImageIcon orangeSquareSelected;


    public DemoGui(int sizeW, int sizeH) {
        this.sizeW = sizeW;
        this.sizeH = sizeH;
        setLayout(null);
        setBackground(Color.decode("#C3C3C3"));

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        backBtn.addActionListener(e -> resetObject());
        add(backBtn);
        backBtn.setBounds(0, 0, 80, 40);

        startBtn = new JButton("Start");
        startBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        startBtn.addActionListener(e -> startAnimation());
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

    public void createObject() {
        if (this.arr == null || this.arr.length <= 1) return;
        itemList = new JLabel[arr.length];
        indexList = new JLabel[arr.length];

        int startX = this.sizeW/2 - (arr.length+1)/2 * 70;
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("res/whiteSquare.png"));
        Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        whiteSquare = new ImageIcon(image);

        ImageIcon imageIcon1 = new ImageIcon(getClass().getResource("res/greenSquare.png"));
        Image image1 = imageIcon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        greenSquare = new ImageIcon(image1);

        ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("res/orangeSquare.png"));
        Image image2 = imageIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        orangeSquare = new ImageIcon(image2);

        ImageIcon imageIcon3 = new ImageIcon(getClass().getResource("res/orangeSquareSelected.png"));
        Image image3 = imageIcon3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        orangeSquareSelected = new ImageIcon(image3);

        for (int i = 0; i < arr.length; i++) {
            JLabel label = new JLabel();
            label.setLayout(new BorderLayout());


            label.setIcon(whiteSquare);
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
        if(timer != null && timer.isRunning()){
            timer.stop();
        }

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
        arrIndex = null;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
        if (this.arr == null) return;
        arrIndex = new int[arr.length];
        for(int i=0; i< arr.length;i++){
            arrIndex[i] = i;
        }
        maxInd = arr.length-1;
        String str = "Before array: ";
        for (int i = 0; i < arr.length; i++) {
            str += arr[i] + " | ";
        }
        resultArray.setText(str);
    }

    public void updateSortName(String name) {
        sortName.setText(name);
    }

    public void startAnimation() {
        startBtn.setVisible(false);
    }

    public void resetResult() {

    }
}
