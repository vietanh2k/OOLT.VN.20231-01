package gui;

import gui.business.LineObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;

public class HeapSortGui extends DemoGui{
    public JLabel label1;
    public HashMap<Integer, JLabel> circleList = new HashMap<>();
    public HashMap<String, LineObject> lineList = new HashMap<>();
    public HashSet<String> lineSet = new HashSet<>();

    public ImageIcon whiteCircle;
    public ImageIcon orangeCircle;

    public int maxInd = 0;

    public HeapSortGui(int sizeW, int sizeH) {
        super(sizeW, sizeH);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("res/whiteCircle.png"));
        Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        whiteCircle = new ImageIcon(image);

        ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("res/orangeCircle.png"));
        Image image2 = imageIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        orangeCircle = new ImageIcon(image2);


        label1 = new JLabel("Build Heap.");
        label1.setFont(new Font("Arial", Font.BOLD, 26));
        label1.setBounds(sizeW*1/5, 200, sizeW, 50);
        label1.setVisible(false);
        add(label1);

    }

    private void drawHeapTree() {
        maxInd = arr.length - 1;
        int soTang = (int)Math.floor(Math.log(arr.length + 1) / Math.log(2));

        for (int i=0; i<arr.length; i++){
            int parentX = this.sizeW/2 - 50, parentY = 300;
            int parentInd = (i-1)/2;
            if(i > 0){
                JLabel circleParent = circleList.get(parentInd);
                if(circleParent == null) return;
                parentX = circleParent.getX();
                parentY = circleParent.getY();

            }

            JLabel circle = getCircle(arr[i], i, parentX, parentY, soTang);
            add(circle);
            circleList.put(i, circle);
            circle.setVisible(false);

            if(i > 0){
                String name = parentInd+"_"+i;
                LineObject line = new LineObject(circle.getX(), circle.getY(), parentX, parentY);
                lineList.put(name, line);
            }

        }

        label1.setVisible(true);
        label1.setText("Build heap tree.");

        timer = new Timer(500, new ActionListener() {
            int dem1 = 0;
            int ind = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ind < arr.length){
                    if(ind > 0) itemList[ind-1].setIcon(whiteSquare);
                    itemList[ind].setIcon(orangeSquare);
                    circleList.get(ind).setVisible(true);

                    int parentInd = (ind-1)/2;
                    if(parentInd >= 0){
                        lineSet.add(parentInd+"_"+ind);
                        repaint();
                    }
                }


                ind++;
                if(ind == arr.length+1){
                    itemList[arr.length-1].setIcon(whiteSquare);
                }

                if (ind > arr.length+1) {
                    dem1 ++;
                    label1.setText("Create max heap.");
                }

                if(dem1 >= 3){
                    timer.stop();
                    buildMaxHeap(arr.length/2 -1 );
                }
            }
        });

        timer.start();
    }

    public void buildMaxHeap(int i1) {
        heapify(arr.length / 2);
    }

    public void heapify(int i) {
        if(i<0) {
            if(maxInd > 0) {
                swapAndRemove();
                label1.setText("Swap first and last node and delete the last node.");
            }else{
                itemList[arrIndex[0]].setIcon(greenSquare);
                JLabel circle = circleList.get(arrIndex[0]);
                circle.setVisible(false);
                label1.setText("Array sorted.");
            }
            return;
        }

        label1.setText("Create max heap.");
        circleList.get(arrIndex[i]).setIcon(orangeCircle);
        int largest = i; // Khởi tạo largest là gốc
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        if (left <= maxInd && arr[arrIndex[left]] > arr[arrIndex[largest]]) {
            largest = left;
        }

        if (right <= maxInd && arr[arrIndex[right]] > arr[arrIndex[largest]]) {
            largest = right;
        }

        if (largest != i) {
            circleList.get(arrIndex[largest]).setIcon(orangeCircle);
            swapTwoItemCircle(i, largest, 1);
        }else{
            circleList.get(arrIndex[i]).setIcon(whiteCircle);
            heapify(i-1);
        }
    }

    public void swapAndRemove() {
        timer = new Timer(10, new ActionListener() {
            int dem1 = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dem1 <= 100){
                    if(dem1 == 100){
                        timer.stop();
                        swapTwoItemCircle(0, maxInd, 2);
                    }

                    dem1++;
                }
            }
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        float strokeWidth = 3.0f;
        ((Graphics2D) g).setStroke(new BasicStroke(strokeWidth));
        veLine(g);
    }

    private void veLine(Graphics g) {
        for (String element : lineSet) {
            if (lineList.get(element) != null) {
                lineList.get(element).draw(g, 25,25);
            }
        }
    }

    private JLabel getCircle(int value, int ind, int parentX, int parentY, int soTang) {
        if(soTang <= 3) soTang = 4;
        JLabel label = new JLabel();
        label.setLayout(new BorderLayout());
        String pathName = "res/whiteCircle.png";

        ImageIcon imageIcon = new ImageIcon(getClass().getResource(pathName));
        Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        label.setIcon(scaledIcon);

        label.setText("<html><center>"+value+"</center></html>");
        label.setFont(new Font("Arial", Font.BOLD, 26));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);

        if(ind == 0){
            label.setBounds(parentX, parentY, 50, 50);
        }else{
            int tang = (int)Math.floor(Math.log(ind + 1) / Math.log(2));
            int mul = (int)Math.pow(soTang- tang, 2) +1;
            if(ind % 2 == 0){
                label.setBounds(parentX + 25 * mul, parentY+100, 50, 50);
            }else{
                label.setBounds(parentX - 25 * mul, parentY+100, 50, 50);
            }
        }

        return label;
    }

    public void startAnimation() {
        super.startAnimation();
        drawHeapTree();
    }

    public void swapTwoItemCircle(int ind1, int ind2, int nextAction) {
        if(ind1 >= itemList.length || ind2 >= itemList.length){
            return;
        }

        JLabel circle1 = circleList.get(arrIndex[ind1]);
        JLabel circle2 = circleList.get(arrIndex[ind2]);

        int posX1 = circle1.getX();
        int posY1 = circle1.getY();
        int posX2 = circle2.getX();
        int posY2 = circle2.getY();


        int stepsNum = 15;
        int stepSizeX = (posX2 - posX1) / stepsNum;
        int stepSizeY = (posY2 - posY1) / stepsNum;

        timer = new Timer(10, new ActionListener() {
            int dem0 = 1;
            int dem1 = 1;
            int dem2 = 1;
            int dem3 = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dem0 <= 5){
                    dem0++;
                }else if(dem1 <= stepsNum){
                    if(dem1 == stepsNum){
                        circle1.setLocation(posX2, posY2);
                        circle2.setLocation(posX1, posY1);
                    }else{
                        circle1.setLocation(circle1.getX() + stepSizeX, circle1.getY() + stepSizeY);
                        circle2.setLocation(circle2.getX() - stepSizeX, circle2.getY() - stepSizeY);
                    }

                    dem1++;

                }else{
                    circleList.get(arrIndex[ind1]).setIcon(whiteCircle);
                    circleList.get(arrIndex[ind2]).setIcon(whiteCircle);
                    timer.stop();

                    swapTwoItem(ind1, ind2, nextAction);
                }
            }
        });

        timer.start();
    }

    public void swapTwoItem(int ind1, int ind2, int nextAction) {
        if(ind1 >= itemList.length || ind2 >= itemList.length){
            return;
        }

        JLabel item1 = itemList[arrIndex[ind1]];
        JLabel item2 = itemList[arrIndex[ind2]];
        item1.setIcon(orangeSquare);
        item2.setIcon(orangeSquare);

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
            int dem4 = 1;
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
                }else if(dem4 <= 50){
                    if(dem4 == 2){
                        item1.setIcon(whiteSquare);
                        item2.setIcon(whiteSquare);
                        int tmp = arrIndex[ind1];
                        arrIndex[ind1] = arrIndex[ind2];
                        arrIndex[ind2] = tmp;
                        if(nextAction == 1){
                            timer.stop();
                            heapify(ind2);
                        }else if(nextAction == 2){
                            item1.setIcon(greenSquare);
                        }
                    }else if(dem4 == 50){
                        if(nextAction == 2){
                            timer.stop();
                            removeHeap();
                        }
                    }

                    dem4++;
                }
            }
        });

        timer.start();
    }

    public void removeHeap() {
        timer = new Timer(10, new ActionListener() {
            int dem0 = 1;
            int dem1 = 1;
            int dem2 = 1;
            int dem3 = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dem0 <= 5){
                    dem0++;
                }else if(dem1 <= 10){
                    if(dem1 == 10){
                        JLabel circle = circleList.get(arrIndex[maxInd]);
                        circle.setVisible(false);
                        int parentInd = (maxInd-1)/2;

                        if(parentInd >= 0){
                            lineSet.remove(parentInd+"_"+maxInd);
                            repaint();
                        }
                        maxInd--;
                    }

                    dem1++;

                }else if(dem2 <= 100){
                    dem2++;
                    if(dem2 == 100){
                        timer.stop();
                        heapify(maxInd/2+1);
                    }
                }else{
                    timer.stop();
                }
            }
        });

        timer.start();

    }

    public void resetObject() {
        super.resetObject();
        label1.setVisible(false);
        for (Integer key : circleList.keySet()) {
            if (circleList.get(key) == null) continue;
            remove(circleList.get(key));
        }

        lineList.clear();
        lineSet.clear();

    }
}
