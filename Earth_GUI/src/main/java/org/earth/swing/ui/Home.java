/*Home
 *Version 1.0
 *4-04-2023
 *Copyright notice
 */

package org.earth.swing.ui;
import lombok.extern.java.Log;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a class that represents Home for Earth_GUI
 * Graphical User Interface (GUI) has been implemented using Java Swing in this package of the Earth Resource Database Project
 * The class provides OPTIONS for running methods for running different operations USING GUI on database data
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */


@Log
public class Home implements ActionListener {
    JFrame frame = new JFrame();
    JButton insert_button =  new JButton();
    JButton view_button =  new JButton();
    JButton update_button =  new JButton();
    JButton delete_button =  new JButton();
    JButton exit_button =  new JButton();
    ImageIcon logo = new ImageIcon("Earth_GUI/src/main/resources/earth_icon_1.png");

    public void gui_func() {

        JLabel title = new JLabel("Earth Resource Database", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.PLAIN, 20));
        title.setForeground(Color.white);
        title.setBounds(350, 25, 300, 50);
        frame.add(title);

        Image scaledImage = logo.getImage().getScaledInstance(title.getHeight(), title.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(scaledImage);
        title.setIcon(scaledLogo);

        insert_button.setBounds(400,100,200,50);
        insert_button.setText("Insert new Data");
        insert_button.setFocusable(false);
        insert_button.addActionListener(this::actionPerformed);
        frame.add(insert_button);

        view_button.setBounds(400,200,200,50);
        view_button.setText("View Existing Data");
        view_button.setFocusable(false);
        view_button.addActionListener(this::actionPerformed);
        frame.add(view_button);

        update_button.setBounds(400,300,200,50);
        update_button.setText("Update Existing Data");
        update_button.setFocusable(false);
        update_button.addActionListener(this::actionPerformed);
        frame.add(update_button);

        delete_button.setBounds(400,400,200,50);
        delete_button.setText("Delete Existing Data");
        delete_button.setFocusable(false);
        delete_button.addActionListener(this::actionPerformed);
        frame.add(delete_button);

        exit_button.setBounds(400,500,200,50);
        exit_button.setText("Exit the Application");
        exit_button.setFocusable(false);
        exit_button.addActionListener(this::actionPerformed);
        frame.add(exit_button);

        frame.setTitle("Earth Resource Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(1000,800);
        frame.setVisible(true);
        ImageIcon logo2 =  new ImageIcon("Earth_GUI/src/main/resources/earth_icon_2.png");
        frame.setIconImage(logo2.getImage());
        frame.getContentPane().setBackground(new Color(17, 66, 117, 234));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==insert_button) {
            InsertionPage  I1 = new InsertionPage();
            I1.gui_func();
            frame.dispose();
            log.info(" User Selected 'Insert new Data' option ");
        }
        if(e.getSource()==view_button) {
            ViewPage  V1 = new ViewPage();
            V1.gui_func();
            frame.dispose();
            log.info(" User Selected 'View Existing Data' option ");
        }

        if(e.getSource()==update_button) {
            UpdatePage1 U1 = new UpdatePage1();
            U1.gui_func();
            frame.dispose();
            log.info(" User Selected 'Update Existing Data' option ");
        }
        if(e.getSource()==delete_button) {
            DeletePage  D1 = new DeletePage();
            D1.gui_func();
            frame.dispose();
            log.info(" User Selected 'Delete Existing Data' option ");
        }
        if(e.getSource()==exit_button) {
            System.exit(0);
            log.info(" User Selected 'Exit the Application' option ");
        }

    }


}
