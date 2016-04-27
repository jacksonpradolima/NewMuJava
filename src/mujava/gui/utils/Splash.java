/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mujava.gui.utils;

/**
 *
 * @author Jackson Lima
 */
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Splash extends JFrame {

    private JLabel imglabel;
    private ImageIcon img;
    private static JProgressBar pbar;
    Thread t = null;

    public Splash() {
        super("Splash");
        setSize(420, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        img = new ImageIcon(getClass().getResource("/mujava/resource/loadingAzul.gif"));
        imglabel = new JLabel(img);
        add(imglabel);
        setLayout(null);
        pbar = new JProgressBar();
        pbar.setMinimum(0);
        pbar.setMaximum(100);
        pbar.setStringPainted(true);
        pbar.setForeground(Color.LIGHT_GRAY);
        imglabel.setBounds(0, 0, 420, 200);
        add(pbar);
        pbar.setPreferredSize(new Dimension(200, 30));
        pbar.setBounds(0, 210, 420, 20);

        Thread t = new Thread() {

            public void run() {
                int i = 0;
                while (i <= 100) {
                    pbar.setValue(i);

                    if (i < 20) {
                        pbar.setString(i + "% Init...");
                    }
                    
                    if (i > 20 && i < 40) {
                        pbar.setString(i + "% Loading...");
                    }

                    if (i > 40 && i < 60) {
                        pbar.setString(i + "% Applying Configs...");
                    }
                    
                    if (i > 80) {
                        pbar.setString(i + "% Starting app...");
                    }
                    try {
                        sleep(90);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    i++;
                }
            }
        };
        t.start();
    }
}
