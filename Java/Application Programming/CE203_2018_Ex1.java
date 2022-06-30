package Assignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;


public class CE203_2018_Ex1 {
    private JFrame frame;
    private JLabel label, label2;
    private JTextField Col1, Col2, Col3;
    private JPanel Center;
    private String reg =" CE203 Assignment 1, submitted by: MM17396 ";

    public static void main(String[] args) {
        CE203_2018_Ex1 ex1 = new CE203_2018_Ex1();
    }


    public CE203_2018_Ex1() {
        //frame
        frame = new JFrame("Ex1");


        //TopPanel & reset Button
        JPanel toPanel = new JPanel(new FlowLayout());
        JButton Rbutton = new JButton("Reset");
        toPanel.add(Rbutton);

        frame.add(toPanel, BorderLayout.NORTH);

        //EndsHereTopanel

        //Center Panel
        Center = new JPanel(new FlowLayout());
        label = new JLabel(reg, JLabel.CENTER);
        label2 = new JLabel(" Error ");


        Center.add(label);
        frame.add(Center, BorderLayout.CENTER);
        //Ends Here

        //Bottom Flowpanel
        JPanel flowPanel = new JPanel(new FlowLayout());
        frame.add(BorderLayout.SOUTH, flowPanel);

        // Assignning TextFields

        Col1 = new JTextField(6);
        Col2 = new JTextField(6);
        Col3 = new JTextField(6);
        JButton Ubutton = new JButton("Update");

        flowPanel.add(Col1);
        flowPanel.add(Col3);
        flowPanel.add(Col2);
        flowPanel.add(Ubutton);


        //ColorKeyValidator
        Col1.addKeyListener(new CustomKeyListener());
        Col2.addKeyListener(new CustomKeyListener());
        Col3.addKeyListener(new CustomKeyListener());


        //Buttons
        Ubutton.addActionListener(new ButtonListener());
        Rbutton.addActionListener(new RButtonListener());


        frame.add(label);

        //szize,visiblitiy & System Exit
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    class CustomKeyListener implements KeyListener {
        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {
            char ch = e.getKeyChar();

            if (Character.isDigit(ch)) {
            }
            else {
                JOptionPane.showMessageDialog(null, "Only numbers are allowed!", "Invalid Key", JOptionPane.ERROR_MESSAGE);
                Col1.setText("");
                Col2.setText("");
                Col3.setText("");
            }
        }
        public void keyReleased(KeyEvent e) {}
    } //class ends CustomListner


    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //Checks if all the input Entered
            if (Col1.getText().isEmpty() || Col2.getText().isEmpty() || Col3.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Values Missing in the Field", "Missing Value", JOptionPane.ERROR_MESSAGE);
                return;     //return from the method to allow the user to edit the JTextField
            } else {
                String wrong = "Invalid Color Code ";

                int R = Integer.parseInt(Col1.getText());
                int G = Integer.parseInt(Col2.getText());
                int B = Integer.parseInt(Col3.getText());
                int min = 0;
                int max = 255;
                //First Color

                if (R > max || R < min || G > max || G < min || B > max || B < min) {


                    label.setText(wrong);
                } else {
                    label.setForeground(new Color(R, G, B));
                }
            }
        }
    }


    // Reset Button Acgtion
    class RButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            // Empty Text field
            Col1.setText("");
            Col2.setText("");
            Col3.setText("");
            label.setForeground(Color.BLACK);
            label.setText(reg);

        }
    }

}











