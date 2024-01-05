package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui {
    private final int sizeW = 1280;
    private final int sizeH = 900;
    private JFrame mainFrame;
    private MenuGui menuGui;
    private InputGui inputGui;
    private BubbleSortGui bubbleGui;
    private int sortId = 0;



    public MainGui() {
        initializeMainFrame();
        initializeMainMenu();
        initializeInputPanel();
        initializeDemoPanel();
        showMainMenu();
    }

    private void initializeMainFrame() {
        mainFrame = new JFrame("Sorting Demo App");
        mainFrame.setSize(sizeW, sizeH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new CardLayout());
        mainFrame.setLocationRelativeTo(null);
    }

    private void initializeMainMenu() {
        menuGui = new MenuGui(sizeW, sizeH);
        menuGui.startBtn.addActionListener(e -> showInputGui());
        menuGui.helpBtn.addActionListener(e -> showHelp());
        menuGui.quitBtn.addActionListener(e -> confirmQuit());
        mainFrame.add(menuGui, "MenuGui");
    }

    private void initializeInputPanel() {
        inputGui = new InputGui(sizeW, sizeH);
        inputGui.startBtn.addActionListener(e -> startAlgorithm());
        inputGui.backBtn.addActionListener(e -> showMainMenu());

        mainFrame.add(inputGui, "InputGui");
    }

    private void initializeDemoPanel() {
        bubbleGui = new BubbleSortGui(sizeW, sizeH);
        bubbleGui.backBtn.addActionListener(e -> showMainMenu());

        mainFrame.add(bubbleGui, "bubbleGui");
    }

    private void showMainMenu() {

        CardLayout cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
        cardLayout.show(mainFrame.getContentPane(), "MenuGui");
    }

    private void showInputGui() {
        sortId = menuGui.sortList.getSelectedIndex();
        inputGui.updateSortLabel((String) menuGui.sortList.getSelectedItem());
        inputGui.resetResult();
        CardLayout cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
        cardLayout.show(mainFrame.getContentPane(), "InputGui");
    }

    private void showHelp() {
        JOptionPane.showMessageDialog(mainFrame,
                "This program demonstrates various sorting algorithms.\n" +
                        "Select a sort type and start the demo to see the sorting in action.\n" +
                        "Use the 'Create Array' button to generate or input an array.",
                "Help",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void confirmQuit() {
        int result = JOptionPane.showConfirmDialog(mainFrame,
                "Are you sure you want to quit?",
                "Quit Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void startAlgorithm() {
        if(inputGui.arr == null || inputGui.arr.length <= 1) {
            inputGui.showResultErr("Array not created!");
            return;
        }

        bubbleGui.startBtn.setVisible(true);
        bubbleGui.setArr(inputGui.arr);
        bubbleGui.updateSortName((String) menuGui.sortList.getSelectedItem());

        bubbleGui.createObject();
//        demoGui.startAnimation(0,5);
        CardLayout cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
        cardLayout.show(mainFrame.getContentPane(), "bubbleGui");
        // Logic for starting the sorting algorithm goes here
//        JOptionPane.showMessageDialog(mainFrame, "Sorting algorithm started. Showing each step...");
    }

    public void display() {
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGui mainGui = new MainGui();
            mainGui.display();
        });
    }
}

