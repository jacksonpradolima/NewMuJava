/**
 * Copyright (C) 2015  the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mujava.gui;

import mujava.gui.utils.Splash;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import mujava.MutationSystem;
import mujava.util.Debug;

/**
 * <p>
 * GUI program for running mutants against test cases</p>
 *
 * @author Yu-Seung Ma
 * @version 1.0
 */
public class RunTestMain extends JFrame {

    private static final long serialVersionUID = 107L;

    JTabbedPane testTabbedPane = new JTabbedPane();

    /**
     * Panel for running mutants
     */
    RunTestPanel runPanel;

    /**
     * Panel for viewing detail of class mutants
     */
    ClassMutantsViewerPanel cvPanel;

    /**
     * Panel for viewing detail of traditional mutants
     */
    TraditionalMutantsViewerPanel tvPanel;

    /**
     * Panel for viewing detail of exceltion-related mutants (not used)
     */
    //ExceptionMutantsViewerPanel evPanel;
    public RunTestMain() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <
     * p>
     * Main program for running mutants (no parameter required for run).</p>
     * <p>
     * - supporting functions: (1) selection of test suite of Java class format,
     * (2) selection of a Java f whose mutants are to run </p>
     */
    public static void main(String[] args) throws InterruptedException {
        Debug.setDebugLevel(2);
        MutationSystem.setJMutationStructure();
        RunTestMain main = new RunTestMain();
        main.pack();

        Splash s = new Splash();
        s.setVisible(true);
        Thread t = Thread.currentThread();
        t.sleep(9980);
        s.dispose();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //opening the main application
                main.setVisible(true);
                main.setLocationRelativeTo(null);
            }
        });

    }

    private void jbInit() throws Exception {
        runPanel = new RunTestPanel();
        cvPanel = new ClassMutantsViewerPanel();
        tvPanel = new TraditionalMutantsViewerPanel();
        //evPanel = new ExceptionMutantsViewerPanel();

        testTabbedPane.add("TestCase Runner", runPanel);
        testTabbedPane.add("Traditional Mutants Viewer", tvPanel);
        testTabbedPane.add("Class Mutants Viewer", cvPanel);
        //testTabbedPane.add("Exception Mutants Viewer", evPanel);

        this.getContentPane().add(testTabbedPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("MuJava - Test");

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
    }

    void this_windowClosing(WindowEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Advise", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmed == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }
}
