package Assignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class CE203_2018_Ex2 extends JFrame {

    private JLabel LWordadd = new JLabel("CE203 Assignment 1, submitted by: MM17396 ");
    private JFrame frame;
    private JButton addAword, DispAword, SearFword, RemoveAWord, RemLWord, ClearWord;
    private JPanel CenterPanelp;
    private JLabel enterWord;
    private JTextField AddwordTF;
    private JTextArea centerText = new JTextArea();
    private LinkedList<String> Linkedwordlist = new LinkedList();


    private String empty = "Cant find a word Your List is empty.";


    //Main Method
    public static void main(String[] args) {

        CE203_2018_Ex2 ex2 = new CE203_2018_Ex2();
        ex2.setTitle("CE203_2018_Ex2");
        ex2.setSize(600, 600);
        ex2.setVisible(true);
        ex2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public CE203_2018_Ex2() {


        JPanel Controlpanel = new JPanel();
        JPanel panel = new JPanel();

        Controlpanel.setLayout(new BorderLayout());
        panel.setSize(300, 300);

        GridLayout layout = new GridLayout(0, 3);
        layout.setHgap(3);
        layout.setVgap(3);

        addAword = new JButton("Add new word");
        DispAword = new JButton("Display Words");
        SearFword = new JButton("Search Word & index");
        RemLWord = new JButton("Remove last Occurrence");
        RemoveAWord = new JButton("Remove all Occurrence");
        ClearWord = new JButton("Clear List");

        panel.setLayout(layout);
        panel.add(addAword);
        panel.add(DispAword);
        panel.add(SearFword);
        panel.add(RemoveAWord);
        panel.add(RemLWord);
        panel.add(ClearWord);
        Controlpanel.add(panel);
        add(Controlpanel, BorderLayout.NORTH);
        //top panel


        //Center Panel
        CenterPanelp = new JPanel();
        CenterPanelp.setLayout(new GridBagLayout());
        CenterPanelp.add(LWordadd);
        add(CenterPanelp, BorderLayout.CENTER);


        //Bottom Panel
        AddwordTF = new JTextField(12);
        enterWord = new JLabel("Enter the word");

        JPanel flowPanel = new JPanel();
        flowPanel.add(enterWord);
        flowPanel.add(AddwordTF);
        add(flowPanel, BorderLayout.SOUTH);

        //  Button Listeners
        addAword.addActionListener(new addHandler());
        DispAword.addActionListener(new DisplayHandler());
        ClearWord.addActionListener(new ClearHandler());
        RemoveAWord.addActionListener(new removeAllOccure());
        RemLWord.addActionListener(new removelastocc());
        SearFword.addActionListener(new searchWords());
    }




        //Add Word Button
    class addHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            addWord();
        }


        // Add words Method
        public void addWord() {

            removeCenterTP();
            try {
                if (AddwordTF.getText().isEmpty()) {
                    LWordadd.setText("Text field Is Empty.");
                } else if (AddwordTF.getText().matches("\\d+"))    //check for the right input
                {
                    LWordadd.setText("The string " + AddwordTF.getText() + " was not added to the list as it is not a valid word.");
                } else {

                    Linkedwordlist.add(AddwordTF.getText());
                    LWordadd.setText("Word " + "'" + AddwordTF.getText() + "'" + " has been added to the list.");
                    CenterPanelp.add(LWordadd, new GridBagConstraints());
                    revalidate();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

        //DisplaY Button Handler

    class DisplayHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displaywords();
        }
        //DisplayWords Method

        public void displaywords() {


            try {
                removeCenterTP();

                if (AddwordTF.getText().isEmpty()) {
                    LWordadd.setText("Text field Is Empty.");
                } else if (Linkedwordlist.isEmpty()) {                     //Checks If list is empty
                    LWordadd.setText(empty);
                } else {
                    String SearchedWord = AddwordTF.getText();
                    centerText.setEditable(false);
                    for (String word : Linkedwordlist) {
                        if (word.endsWith(SearchedWord.toLowerCase()) || (word.endsWith(SearchedWord.toUpperCase()))) {
                            CenterPanelp.add(centerText);
                            centerText.append(word + "\n");
                            LWordadd.setText("");
                        } else {
                            removeCenterTP();
                            LWordadd.setText("Not in the list.");
                            revalidate();
                        }
                    }

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    //Search Word Action Listeners
    class searchWords implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            searchWord();

        }
        //Search Word Method

        public void searchWord() {

            try {
                removeCenterTP();

                int size = Linkedwordlist.size();                    //Get the size & Convert In String & total
                String total = String.valueOf(size);


                if (Linkedwordlist.isEmpty()) {                     //Checks If list is empty
                    LWordadd.setText(empty);
                } else if (AddwordTF.getText().isEmpty()) {

                    LWordadd.setText("Nothing searched out of total " + total + " words");

                } else {

                    int count = 0;
                    for (String word : Linkedwordlist) {
                        int pos = Linkedwordlist.indexOf(AddwordTF.getText());
                        if (word.equals(AddwordTF.getText())) {
                            count++;
                            LWordadd.setText("Found Word " + count + " times  in the list .");

                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Display TextArea Removal
    public void removeCenterTP() {
        CenterPanelp.remove(centerText);
        centerText.setText("");
        CenterPanelp.repaint();
        CenterPanelp.revalidate();
    }


    class ClearHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clearlist();
        }

        //clear list method

        public void clearlist() {
            removeCenterTP();
            Linkedwordlist.clear();
            LWordadd.setText(" Word List Cleared Succesfully ");
            AddwordTF.setText("");
            revalidate();
        }
    }


    //Remove Class
    class removelastocc implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            removelastoc();
        }

        //Remove Last Occurence

        public void removelastoc() {
            removeCenterTP();

            try {
                if (AddwordTF.getText().isEmpty()) {
                    LWordadd.setText("Text field Is Empty.");
                } else if (Linkedwordlist.isEmpty())       //Checks If list is empty
                {
                    LWordadd.setText(empty);
                } else if (Linkedwordlist.size() == 1) {

                    LWordadd.setText("Only One Word Is In the List");
                } else {
                    Linkedwordlist.remove(AddwordTF.getText());
                    LWordadd.setText("The last occurrence of word " + "'" + AddwordTF.getText() + "'" + " is removed from the list.");
                    AddwordTF.setText("");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Remove All Occurences


    class removeAllOccure implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            removeallocc();
        }

        public void removeallocc() {
            removeCenterTP();

            try {
                if (AddwordTF.getText().isEmpty()) {
                    LWordadd.setText("Text field Is Empty.");
                } else if (Linkedwordlist.isEmpty()) { //Checks If list is empty
                    LWordadd.setText(empty);
                } else if (Linkedwordlist.size() == 1) {

                    LWordadd.setText("Only One Word Is In the List");
                } else {

                    String wordsOc = AddwordTF.getText();

                    while (Linkedwordlist.contains(wordsOc)) {     //Checking for the same word
                        Linkedwordlist.remove(wordsOc);
                    }
                    LWordadd.setText(" All occurrences of a word " + "'" + wordsOc + "'" + " has been Removed .");
                    AddwordTF.setText("");
                    revalidate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}//Class Ends













