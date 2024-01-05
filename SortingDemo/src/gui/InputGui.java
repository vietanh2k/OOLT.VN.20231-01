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
    public JLabel sortLabel;
    public int[] arr;
    private int sizeW = 0;
    private int sizeH = 0;

    public InputGui(int sizeW, int sizeH) {
        this.sizeW = sizeW;
        this.sizeH = sizeH;
        setLayout(null);

        sortLabel = new JLabel("Sort Name");
        sortLabel.setFont(new Font("Arial Bold", Font.PLAIN, 34)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
        add(sortLabel);
        sortLabel.setBounds(sizeW / 2 - 100, 30, 300, 50);

        JLabel inputLabel = new JLabel("Enter Array");
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
        add(inputLabel);
        inputLabel.setBounds(sizeW / 2 - 60, 100, 300, 50);

        inputField = new JTextField(20);
        inputField.setFont(new Font("Arial", Font.PLAIN, 24)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
        add(inputField);
        inputField.setBounds(sizeW / 2 - sizeW * 1 / 3, 160, sizeW * 2 / 3, 50);

        JButton createArrayBtn = new JButton("Create Array");
        createArrayBtn.setFont(new Font("Arial", Font.PLAIN, 24)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
        createArrayBtn.addActionListener(e -> createArray());

        add(createArrayBtn);
        createArrayBtn.setBounds(sizeW / 2 - 210, 220, 200, 50);

        JButton createRandomBtn = new JButton("Random Array");
        createRandomBtn.setFont(new Font("Arial", Font.PLAIN, 24)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
        createRandomBtn.addActionListener(e -> randomArray());
        add(createRandomBtn);
        createRandomBtn.setBounds(sizeW / 2 + 10, 220, 200, 50);

        startBtn = new JButton("Start Algorithm");
        startBtn.setFont(new Font("Arial", Font.PLAIN, 24)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
//        startBtn.addActionListener(e -> startAlgorithm());
        add(startBtn);
        startBtn.setBounds(sizeW / 2 - 100, 280, 200, 50);

        backBtn = new JButton("Back to Main Menu");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 24)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
//        backBtn.addActionListener(e -> showMainMenu());
        add(backBtn);
        backBtn.setBounds(sizeW / 2 - 150, 340, 300, 50);

        JLabel resultInput = new JLabel("Result");
        resultInput.setFont(new Font("Arial", Font.PLAIN, 30)); // TÃ„Æ’ng cÃ¡Â»Â¡ chÃ¡Â»Â¯ lÃƒÂªn
        add(resultInput);
        resultInput.setBounds(sizeW / 2 - 50, 400, 200, 50);

        resultLength = new JLabel();
        resultLength.setFont(new Font("Arial", Font.PLAIN, 24));
        add(resultLength);
        resultLength.setBounds(sizeW / 2 - 100, 460, 500, 50);

        resultArray = new JLabel();
        resultArray.setFont(new Font("Arial", Font.PLAIN, 24));
        add(resultArray);
        resultArray.setBounds(sizeW / 2 - 50, 520, sizeW, 50);
    }

    private void createArray() {
        String arrayInput = inputField.getText();

        String[] numberArray = arrayInput.split("\\s+");
        String output = "Length array: " + numberArray.length;
        String output2 = "Array: ";

        if (numberArray.length < 2 || numberArray.length > 15) {
            output = "Array length >=2, <=15";
            showResultErr(output);
            return;
        }
        try {
            arr = new int[numberArray.length];
            resultLength.setForeground(Color.BLACK);
            for (int i = 0; i < numberArray.length; i++) {
                System.out.println(numberArray[i]);
                int number1 = Integer.parseInt(numberArray[i]);
                arr[i] = number1;
                output2 += numberArray[i] + " | ";
                if(number1 >= 999) {
                    showResultErr("Gia tri ko qua 999");
                    return;
                }
            }
            updateResult(numberArray.length);
        } catch (NumberFormatException e) {
            showResultErr("ban da nhap sai!");
            return;
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
        arr = new int[num];
        for (int i = 0; i < num; i++) {
            int value = random.nextInt(100);
            output2 += value + " | ";
            arr[i] = value;
        }
        // Logic for creating an array goes here
        resultLength.setText(output);
        resultArray.setText(output2);
        updateResult(num);
//        JOptionPane.showMessageDialog(mainFrame, "Array created or input successfully!");
    }

    private void updateResult(int length) {
        int tmp = length / 2;
        resultArray.setLocation(sizeW / 2 - 50 - tmp * 45, resultArray.getY());
//        JOptionPane.showMessageDialog(mainFrame, "Array created or input successfully!");
    }

    public void updateSortLabel(String sortName) {
        sortLabel.setText(sortName);
        sortLabel.setLocation((int) (sizeW / 2 - 8 * sortName.length()), sortLabel.getY());
//        JOptionPane.showMessageDialog(mainFrame, "Array created or input successfully!");
    }

    public void resetResult() {
        resultLength.setText("");
        resultArray.setText("");
    }

    public void showResultErr(String str) {
        resultLength.setText(str);
        resultArray.setText("");
        resultLength.setForeground(Color.RED);
        arr = null;
    }

}
