package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class BubbleSortGui extends DemoGui{
    public HashMap<String, JLabel> labelList = new HashMap<>();

    public BubbleSortGui(int sizeW, int sizeH) {
        super(sizeW, sizeH);

        JLabel label1 = new JLabel("1. Traverse the list from 0 to n.");
        label1.setFont(new Font("Arial", Font.BOLD, 26));
        label1.setBounds(sizeW*1/4, 300, sizeW, 50);
        labelList.put("label1", label1);
        label1.setVisible(false);
        add(label1);

        JLabel label2 = new JLabel("2. Compare two consecutive elements.");
        label2.setFont(new Font("Arial", Font.BOLD, 26));
        label2.setBounds(sizeW*1/4, 350, sizeW, 50);
        labelList.put("label2", label2);
        label2.setVisible(false);
        add(label2);

        JLabel label5 = new JLabel("   If the two elements are not sorted, swap them.");
        label5.setFont(new Font("Arial", Font.BOLD, 26));
        label5.setBounds(sizeW*1/4, 380, sizeW, 50);
        labelList.put("label5", label5);
        label5.setVisible(false);
        add(label5);

        JLabel label3 = new JLabel("3. When reaching the end of the list, the last element is sorted.");
        label3.setFont(new Font("Arial", Font.BOLD, 26));
        label3.setBounds(sizeW*1/4, 430, sizeW, 50);
        labelList.put("label3", label3);
        label3.setVisible(false);
        add(label3);

        JLabel label4 = new JLabel("   Repeat with the remaining list until the sorting is complete.");
        label4.setFont(new Font("Arial", Font.BOLD, 26));
        label4.setBounds(sizeW*1/4, 460, sizeW, 50);
        labelList.put("label4", label4);
        label4.setVisible(false);
        add(label4);
    }

    public void startAnimation() {
        super.startAnimation();
        startSort();
    }

    public void startSort() {
        int step1 = 10;
        int step2 = 20;
        int step3 = 20;
        int step4 = 10;

        timer = new Timer(50/mulSpeed, new ActionListener() {
            int dem1 = 1;
            int dem2 = 1;
            int dem3 = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dem1 <= step1){
                    if(dem1 == step1){
                        labelList.get("label1").setVisible(true);
                    }

                    dem1++;
                }else if(dem2 <= step2){
                    if(dem2 == step2){
                        labelList.get("label2").setVisible(true);
                        labelList.get("label5").setVisible(true);
                    }
                    dem2++;
                }else if(dem3 <= step3){
                    dem3++;
                }else{
                    timer.stop();
                    compareTwoItem(0,1);
                }
            }
        });

        timer.start();
    }

    public void compareTwoItem(int ind1, int ind2) {
        if(maxInd <= 0){
            JLabel item = itemList[arrIndex[maxInd]];
            item.setIcon(greenSquare);
            if(timer != null && timer.isRunning()){
                timer.stop();
            }
            return;
        }
        if(ind1 > maxInd || ind2 > maxInd){
            labelList.get("label3").setVisible(true);
            labelList.get("label4").setVisible(true);
            JLabel item = itemList[arrIndex[maxInd]];
            item.setIcon(greenSquare);
            maxInd--;
            compareTwoItem(0,1);
            return;
        }

        if(ind1 >= itemList.length || ind2 >= itemList.length){
            return;
        }


        JLabel item1 = itemList[arrIndex[ind1]];
        JLabel item2 = itemList[arrIndex[ind2]];

        int step1 = 5;
        int step2 = 10;
        int step3 = 10;
        int step4 = 10;

        timer = new Timer(50/mulSpeed, new ActionListener() {
            int dem0 = 1;
            int dem1 = 1;
            int dem2 = 1;
            int dem3 = 1;
            boolean isSwap = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dem1 <= step1){
                    if(dem1 == step1){
                        item1.setIcon(orangeSquare);
                        item2.setIcon(orangeSquare);
                    }

                    dem1++;
                }else if(dem2 <= step2){
                    if(dem2 == step2){
                        if(arr[arrIndex[ind1]] >= arr[arrIndex[ind2]]){
                            isSwap = true;
                            item1.setIcon(orangeSquareSelected);
                        }else{
                            item2.setIcon(orangeSquareSelected);
                        }
                    }

                    dem2++;
                }else if(dem3 <= step3){
                    if(dem3 == step3){
                        timer.stop();
                        if(isSwap) {
                            swapTwoItem(ind1, ind2);
                        }else{
                            item1.setIcon(whiteSquare);
                            item2.setIcon(whiteSquare);
                            compareTwoItem(ind1+1, ind2+1);
                        }
                    }

                    dem3++;
                }else{
                    timer.stop();
                }
            }
        });

        timer.start();
    }

    public void swapTwoItem(int ind1, int ind2) {
        if(ind1 >= itemList.length || ind2 >= itemList.length){
            return;
        }

        JLabel item1 = itemList[arrIndex[ind1]];
        JLabel item2 = itemList[arrIndex[ind2]];


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

        timer = new Timer(10/mulSpeed, new ActionListener() {
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

                    item1.setIcon(whiteSquare);
                    item2.setIcon(whiteSquare);
                    int tmp = arrIndex[ind1];
                    arrIndex[ind1] = arrIndex[ind2];
                    arrIndex[ind2] = tmp;
                    timer.stop();
                    compareTwoItem(ind1+1, ind2+1);
                }
            }
        });

        timer.start();
    }

    public void resetObject() {
        super.resetObject();

        for (String key : labelList.keySet()) {
            labelList.get(key).setVisible(false);
        }
    }
}
