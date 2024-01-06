package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ShellSortGui extends DemoGui{
    public HashMap<String, JLabel> labelList = new HashMap<>();
    public int gap;
    public int curId;

    public ShellSortGui(int sizeW, int sizeH) {
        super(sizeW, sizeH);

        JLabel label1 = new JLabel("1. Calculate 'gap' for each pass.");
        label1.setFont(new Font("Arial", Font.BOLD, 26));
        label1.setBounds(sizeW*1/5, 370, sizeW, 50);
        labelList.put("label1", label1);
        label1.setVisible(false);
        add(label1);

        JLabel label6 = new JLabel("   Then select the elements towards the right of gap.");
        label6.setFont(new Font("Arial", Font.BOLD, 26));
        label6.setBounds(sizeW*1/5, 400, sizeW, 50);
        labelList.put("label6", label6);
        label6.setVisible(false);
        add(label6);

        JLabel label2 = new JLabel("2. Compare with elements at gap distance of a located to the left.");
        label2.setFont(new Font("Arial", Font.BOLD, 26));
        label2.setBounds(sizeW*1/5, 450, sizeW, 50);
        labelList.put("label2", label2);
        label2.setVisible(false);
        add(label2);

        JLabel label7 = new JLabel("3. Start with gap = n/2.");
        label7.setFont(new Font("Arial", Font.BOLD, 26));
        label7.setBounds(sizeW*1/5, 500, sizeW, 50);
        labelList.put("label7", label7);
        label7.setVisible(false);
        add(label7);

        JLabel label5 = new JLabel("4. Repeat and gap/=2. If (gap <= 0) ==> Array sorted.");
        label5.setFont(new Font("Arial", Font.BOLD, 26));
        label5.setBounds(sizeW*1/5, 550, sizeW, 50);
        labelList.put("label5", label5);
        label5.setVisible(false);
        add(label5);

        JLabel label3 = new JLabel("Current gap = ");
        label3.setFont(new Font("Arial", Font.BOLD, 26));
        label3.setBounds(sizeW*1/5, 280, sizeW, 50);
        labelList.put("label3", label3);
        label3.setVisible(false);
        label3.setForeground(Color.RED);
        add(label3);

        JLabel label4 = new JLabel("Current element id = ");
        label4.setFont(new Font("Arial", Font.BOLD, 26));
        label4.setBounds(sizeW*1/5, 320, sizeW, 50);
        labelList.put("label4", label4);
        label4.setVisible(false);
        label4.setForeground(Color.RED);
        add(label4);
    }

    public void startAnimation() {
        super.startAnimation();
        gap = arr.length/2;
        curId = gap;
        startSort();
    }

    public void startSort() {
        int step1 = 10;
        int step2 = 20;
        int step3 = 20;
        int step4 = 10;

        timer = new Timer(50, new ActionListener() {
            int dem1 = 1;
            int dem2 = 1;
            int dem3 = 1;
            int dem4 = 1;
            int dem5 = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dem1 <= step1){
                    if(dem1 == step1){
                        labelList.get("label1").setVisible(true);
                        labelList.get("label6").setVisible(true);
                    }

                    dem1++;
                }else if(dem2 <= step2){
                    if(dem2 == step2){
                        labelList.get("label2").setVisible(true);
                    }
                    dem2++;
                }else if(dem3 <= step3){
                    if(dem3 == step3){
                        labelList.get("label7").setVisible(true);
                    }
                    dem3++;
                }
                else if(dem4 <= 20){
                    if(dem4 == 20){
                        labelList.get("label3").setVisible(true);
                        labelList.get("label3").setText("Current gap = "+gap);
                    }
                    dem4++;
                }else if(dem5 <= 20){
                    if(dem5 == 20){
                        labelList.get("label4").setVisible(true);
                        labelList.get("label4").setText("Current element id = "+curId);
                    }
                    dem5++;
                }else{
                    timer.stop();
                    compareTwoItem(gap,0);
                }
            }
        });

        timer.start();
    }

    public void compareTwoItem(int chosenId, int compareId) {
        if(compareId < 0){
            itemList[arrIndex[chosenId]].setIcon(whiteSquare);
            if(curId == maxInd){
                labelList.get("label5").setVisible(true);
                gap /= 2;
                labelList.get("label3").setText("Current gap = "+gap);
                if(gap <= 0){
                    updateSorted();
                    return;
                }
                curId = gap;
                labelList.get("label4").setText("Current element id = "+curId);
                compareTwoItem(gap,0);
            }else{
                curId++;
                labelList.get("label3").setText("Current gap = "+gap);
                labelList.get("label4").setText("Current element id = "+curId);
                compareTwoItem(curId, curId-gap);
            }

            return;
        }

        if(chosenId >= itemList.length || compareId >= itemList.length){
            return;
        }


        JLabel item1 = itemList[arrIndex[chosenId]];
        JLabel item2 = itemList[arrIndex[compareId]];

        int step1 = 5;
        int step2 = 10;
        int step3 = 10;
        int step4 = 10;

        timer = new Timer(50, new ActionListener() {
            int dem0 = 1;
            int dem1 = 1;
            int dem2 = 1;
            int dem3 = 1;
            boolean isSwap = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dem1 <= step1){
                    if(dem1 == step1){
                        item1.setIcon(orangeSquareSelected);
                        item2.setIcon(orangeSquare);
                    }

                    dem1++;
                }else if(dem2 <= step2){
                    if(dem2 == step2){
                        if(arr[arrIndex[chosenId]] < arr[arrIndex[compareId]]){
                            isSwap = true;
                        }
                    }

                    dem2++;
                }else if(dem3 <= step3){
                    if(dem3 == step3){
                        timer.stop();
                        if(isSwap) {
                            swapTwoItem(chosenId, compareId);
                        }else{
                            item2.setIcon(whiteSquare);
                            compareTwoItem(chosenId, compareId - gap);
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
                    item2.setIcon(whiteSquare);
                    int tmp = arrIndex[ind1];
                    arrIndex[ind1] = arrIndex[ind2];
                    arrIndex[ind2] = tmp;
                    timer.stop();
                    compareTwoItem(ind2, ind2 - gap);
                }
            }
        });

        timer.start();
    }

    public void updateSorted() {
        for (JLabel label : itemList) {
            if (label == null) continue;
            label.setIcon(greenSquare);
        }
    }

    public void resetObject() {
        super.resetObject();

        for (String key : labelList.keySet()) {
            labelList.get(key).setVisible(false);
        }
    }
}
