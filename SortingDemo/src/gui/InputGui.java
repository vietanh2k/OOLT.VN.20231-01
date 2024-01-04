package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class InputGui extends JPanel {
    public JTextField inputField;
    //    public JButton createArrayBtn;
    public JButton startBtn;
    public JButton backBtn;
    public JLabel resultLength;
    public JLabel resultArray;
    private int sizeW = 0;
    private int sizeH = 0;

    public InputGui(int sizeW, int sizeH) {
        this.sizeW =sizeW;
        this.sizeH = sizeH;
        setLayout(null);
        JLabel inputLabel = new JLabel("Enter Array");
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // TÄƒng cá»¡ chá»¯ lÃªn
//        GridBagConstraints inputConstraints = new GridBagConstraints();
//        inputConstraints.gridx = 0;
//        inputConstraints.gridy = 0;
//        inputConstraints.insets = new Insets(5, 5, 5, 5);
        add(inputLabel);
        inputLabel.setBounds(sizeW/2 - 50, 100, 300, 50);

        inputField = new JTextField(20);
        inputField.setFont(new Font("Arial", Font.PLAIN, 24)); // TÄƒng cá»¡ chá»¯ lÃªn
//        GridBagConstraints inputFieldConstraints = new GridBagConstraints();
//        inputFieldConstraints.gridx = 0;
//        inputFieldConstraints.gridy = 1;
//        inputFieldConstraints.gridwidth = 2;
//        inputFieldConstraints.insets = new Insets(5, 5, 10, 5);
        add(inputField);
        inputField.setBounds(sizeW/2 - sizeW*1/3, 160, sizeW*2/3, 50);

        JButton createArrayBtn = new JButton("Create Array");
        createArrayBtn.setFont(new Font("Arial", Font.PLAIN, 24)); // TÄƒng cá»¡ chá»¯ lÃªn
        createArrayBtn.addActionListener(e -> createArray());
//        GridBagConstraints createArrayBtnConstraints = new GridBagConstraints();
//        createArrayBtnConstraints.gridx = 0;
//        createArrayBtnConstraints.gridy = 2;
//        createArrayBtnConstraints.gridwidth = 1;
//        createArrayBtnConstraints.insets = new Insets(10, 5, 5, 5);
        add(createArrayBtn);
        createArrayBtn.setBounds(sizeW/2 - 210, 220, 200, 50);

        JButton createRandomBtn = new JButton("Random Array");
        createRandomBtn.setFont(new Font("Arial", Font.PLAIN, 24)); // TÄƒng cá»¡ chá»¯ lÃªn
        createRandomBtn.addActionListener(e -> randomArray());
//        GridBagConstraints createRandomBtnConstraints = new GridBagConstraints();
//        createRandomBtnConstraints.gridx = 1;
//        createRandomBtnConstraints.gridy = 2;
//        createRandomBtnConstraints.gridwidth = 2;
//        createRandomBtnConstraints.insets = new Insets(10, 5, 5, 5);
        add(createRandomBtn);
        createRandomBtn.setBounds(sizeW/2 + 10, 220, 200, 50);

        startBtn = new JButton("Start Algorithm");
        startBtn.setFont(new Font("Arial", Font.PLAIN, 24)); // TÄƒng cá»¡ chá»¯ lÃªn
//        startBtn.addActionListener(e -> startAlgorithm());
//        GridBagConstraints startBtnConstraints = new GridBagConstraints();
//        startBtnConstraints.gridx = 0;
//        startBtnConstraints.gridy = 3;
//        startBtnConstraints.gridwidth = 2;
//        startBtnConstraints.insets = new Insets(10, 5, 5, 5);
        add(startBtn);
        startBtn.setBounds(sizeW/2-100, 280, 200, 50);

        backBtn = new JButton("Back to Main Menu");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 24)); // TÄƒng cá»¡ chá»¯ lÃªn
//        backBtn.addActionListener(e -> showMainMenu());
//        GridBagConstraints backBtnConstraints = new GridBagConstraints();
//        backBtnConstraints.gridx = 0;
//        backBtnConstraints.gridy = 4;
//        backBtnConstraints.gridwidth = 2;
//        backBtnConstraints.insets = new Insets(10, 5, 5, 5);
        add(backBtn);
        backBtn.setBounds(sizeW/2-150, 340, 300, 50);

        JLabel resultInput = new JLabel("Result");
        resultInput.setFont(new Font("Arial", Font.PLAIN, 30)); // TÄƒng cá»¡ chá»¯ lÃªn
//        GridBagConstraints resultInputConstraints = new GridBagConstraints();
//        resultInputConstraints.gridx = 0;
//        resultInputConstraints.gridy = 5;
//        resultInputConstraints.insets = new Insets(5, 5, 5, 5);
        add(resultInput);
        resultInput.setBounds(sizeW/2-50, 400, 200, 50);

        resultLength = new JLabel();
        resultLength.setFont(new Font("Arial", Font.PLAIN, 24));
//        GridBagConstraints resultLengthConstraints = new GridBagConstraints();
//        resultLengthConstraints.gridx = 0;
//        resultLengthConstraints.gridy = 6;
//        resultLengthConstraints.gridwidth = 2;
//        resultLengthConstraints.insets = new Insets(10, 5, 5, 5);
        add(resultLength);
        resultLength.setBounds(sizeW/2-100, 460, 500, 50);

        resultArray = new JLabel();
        resultArray.setFont(new Font("Arial", Font.PLAIN, 24));
//        GridBagConstraints resultArrayConstraints = new GridBagConstraints();
//        resultArrayConstraints.gridx = 0;
//        resultArrayConstraints.gridy = 7;
//        resultArrayConstraints.gridwidth = 2;
//        resultArrayConstraints.insets = new Insets(10, 5, 5, 5);
        add(resultArray);
        resultArray.setBounds(sizeW/2-50, 520, sizeW, 50);
    }

    private void createArray() {
        String arrayInput = inputField.getText();

        String[] numberArray = arrayInput.split("\\s+");
        String output = "Length array: " + numberArray.length;
        String output2 = "Array: ";
        try {
            resultLength.setForeground(Color.BLACK);
            for (int i = 0; i < numberArray.length; i++) {
                System.out.println(numberArray[i]);
                int number1 = Integer.parseInt(numberArray[i]);
                output2 += numberArray[i] + " | ";
            }
            updateResult(numberArray.length);
        } catch (NumberFormatException e) {
            output = "ban da nhap sai!";
            output2 = "";
            resultLength.setForeground(Color.RED);
        }
        // Logic for creating an array goes here
        resultLength.setText(output);
        resultArray.setText(output2);

//        JOptionPane.showMessageDialog(mainFrame, "Array created or input successfully!");
    }

    private void randomArray() {
        Random random = new Random();
        int num = random.nextInt(6) + 10;
        resultLength.setForeground(Color.BLACK);
        String output = "Length array: " + num;
        String output2 = "Array: ";
        for (int i = 0; i < num; i++) {
            int value = random.nextInt(100);
            output2 += value + " | ";
        }
        // Logic for creating an array goes here
        resultLength.setText(output);
        resultArray.setText(output2);
        updateResult(num);
//        JOptionPane.showMessageDialog(mainFrame, "Array created or input successfully!");
    }

    private void updateResult(int length) {
        int tmp = length/2;
        resultArray.setLocation(sizeW/2 - 50 - tmp * 45, resultArray.getY());
//        JOptionPane.showMessageDialog(mainFrame, "Array created or input successfully!");
    }
}
