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

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import mujava.*;
import mujava.enums.DebugType;
import mujava.gui.utils.tablemodel.CMOTableModel;
import mujava.gui.utils.tablemodel.FileTableModel;
import mujava.gui.utils.tablemodel.TMOTableModel;
import mujava.util.Debug;
import mujava.util.FilesUtils;

/**
 * <p>
 * Template for generating mutants</p>
 * <p>
 * supporting function: (1) choose Java program(s) to be tested, (2) choose
 * mutation operator(s) to applied </p>
 *
 * @author Yu-Seung Ma
 * @version 1.0
 */
public class MutantsGenPanel extends JPanel {

    private static final long serialVersionUID = 103L;
    JButton classAllB = new JButton("All");
    JButton classNoneB = new JButton("None");
    JTable classOpTable = new JTable();
    JButton fileAllB = new JButton("All");
    JButton fileNoneB = new JButton("None");
    JTable fileTable = new JTable();
    JComboBox logCB = new JComboBox(new String[]{
        DebugType.EMPTY_LEVEL.getDescription(),
        DebugType.SIMPLE_LEVEL.getDescription(),
        DebugType.DETAILED_LEVEL.getDescription()
    });
    GenMutantsMain parent_frame;
    //<editor-fold defaultstate="collapsed" desc="Components">
    JButton runB = new JButton("Generate");
    JButton traditionalAllB = new JButton("All");
    JButton traditionalNoneB = new JButton("None");
    JTable traditionalOpTable = new JTable();

    //</editor-fold>
    public MutantsGenPanel(GenMutantsMain parent_frame) {
        try {
            this.parent_frame = parent_frame;
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    protected void initColumnSizes(JTable table, AbstractTableModel model) {
        initTripleColumnWidth(table, model, 30, 90, 80);
    }
    protected void initFileColumnSizes(JTable table, AbstractTableModel model) {
        initTripleColumnWidth(table, model, 30, 700, 80);
    }

    protected void initTripleColumnWidth(JTable table, AbstractTableModel model,
            int w1, int w2, int w3) {
        TableColumn column = null;

        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setMaxWidth(w1);
                    break;
                case 1:
                    column.setMaxWidth(w2);
                    break;
                case 2:
                    column.setMaxWidth(w3);
                    break;
            }
        }
    }
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Initialization
     */
    void jbInit() {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        //<editor-fold defaultstate="collapsed" desc="LEFT part">
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        JPanel usagePanel = new JPanel();
        usagePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        //<editor-fold defaultstate="collapsed" desc="Instruction">
        JPanel tempP = new JPanel();
        JLabel temp = new JLabel("   Usage : ");
        temp.setForeground(Color.gray);
        tempP.add(temp);
        tempP.setPreferredSize(new Dimension(70, 70));
        tempP.setBorder(new EtchedBorder());

        JPanel usgeContentP = new JPanel();
        usgeContentP.setLayout(new BoxLayout(usgeContentP, BoxLayout.PAGE_AXIS));
        usgeContentP.add(new JLabel(" [1] Select files to test."));
        usgeContentP.add(new JLabel(" [2] Select mutation operators to apply."));
        usgeContentP.add(new JLabel(" [3] Push \"Generate\" button."));
        usgeContentP.add(new JLabel(" [4] Wait with endurance. The process can last a along time."));
        usagePanel.add(tempP);
        usagePanel.add(usgeContentP);
        usagePanel.setBorder(new EtchedBorder());
        leftPanel.add(usagePanel);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="List of (target) files to be tested">
        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BorderLayout());
        JScrollPane fileSP = new JScrollPane();
        FileTableModel fTableModel = new FileTableModel(MutationSystem.getNewTragetFiles());
        fileTable = new JTable(fTableModel);
        initFileColumnSizes(fileTable, fTableModel);
        fileSP.getViewport().add(fileTable, null);
        fileSP.setPreferredSize(new Dimension(500, 500));
        leftPanel.add(fileSP);

        leftPanel.add(Box.createRigidArea(new Dimension(10, 10)));

        JPanel fileBP = new JPanel();
        fileBP.setLayout(new BoxLayout(fileBP, BoxLayout.LINE_AXIS));
        fileBP.add(Box.createRigidArea(new Dimension(10, 10)));
        fileBP.add(fileNoneB);
        fileNoneB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                onMouseClicked_fileNoneB(e);
            }
        });
        fileBP.add(Box.createRigidArea(new Dimension(10, 10)));
        fileBP.add(fileAllB);
        fileAllB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                onMouseClicked_fileAllB(e);
            }
        });

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Debug level Add">
        JPanel logP = new JPanel();
        JLabel logL = new JLabel("Log level ");
        logP.add(logL);
        logP.add(logCB);
        logCB.setPreferredSize(new Dimension(100, 25));
        logCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onChangeLogLevel();
            }
        });
        leftPanel.add(logP);

        fileBP.add(Box.createHorizontalGlue());

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Generate button">
        runB.setBackground(Color.YELLOW);
        fileBP.add(runB);
        runB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    onMouseClicked_Generate(e);
                } catch (IOException ex) {
                    Logger.getLogger(MutantsGenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        leftPanel.add(fileBP);
        fileBP.add(Box.createRigidArea(new Dimension(10, 10)));
        leftPanel.add(Box.createRigidArea(new Dimension(20, 20)));

        //</editor-fold>
        //</editor-fold>        
        //<editor-fold defaultstate="collapsed" desc="RIGHT part">
        rightPanel.setPreferredSize(new Dimension(300, 660));
        rightPanel.setMaximumSize(new Dimension(300, 660));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBorder(new EtchedBorder());

        JTextField titleOP = new JTextField(" Java Mutation Operator");
        titleOP.setBackground(Color.black);
        titleOP.setPreferredSize(new Dimension(300, 30));
        titleOP.setMaximumSize(new Dimension(300, 30));
        titleOP.setEnabled(false);
        rightPanel.add(titleOP);

        //<editor-fold defaultstate="collapsed" desc="method-level operator selection">
        JPanel traditional_operator_panel = new JPanel();
        traditional_operator_panel.setBorder(new TitledBorder("Method-level"));
        traditional_operator_panel.setPreferredSize(new Dimension(140, 550));
        traditional_operator_panel.setMaximumSize(new Dimension(140, 550));

        traditional_operator_panel.setLayout(new BoxLayout(traditional_operator_panel, BoxLayout.PAGE_AXIS));
        JScrollPane traditional_operator_scrollP = new JScrollPane();
        traditional_operator_panel.add(traditional_operator_scrollP);
        TMOTableModel tmTableModel = new TMOTableModel();
        traditionalOpTable = new JTable(tmTableModel);
        initColumnSizes(traditionalOpTable, tmTableModel);
        traditional_operator_scrollP.getViewport().add(traditionalOpTable, null);
        traditional_operator_scrollP.setPreferredSize(new Dimension(115, 400));
        traditional_operator_scrollP.setMaximumSize(new Dimension(115, 400));

        traditional_operator_panel.add(traditional_operator_scrollP);
        JPanel traditionalBPanel = new JPanel();
        traditionalBPanel.setLayout(new FlowLayout());
        traditionalBPanel.add(traditionalNoneB);
        traditionalNoneB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                onMouseClicked_traditionalNoneB(e);
            }
        });
        traditionalBPanel.add(traditionalAllB);
        traditionalAllB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                onMouseClicked_traditionalAllB(e);
            }
        });
        traditional_operator_panel.add(traditionalBPanel);
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="class-level operator selection">
JPanel class_operator_panel = new JPanel();
class_operator_panel.setBorder(new TitledBorder("Class-level"));
class_operator_panel.setPreferredSize(new Dimension(140, 550));
class_operator_panel.setMaximumSize(new Dimension(140, 550));
class_operator_panel.setLayout(new BoxLayout(class_operator_panel, BoxLayout.PAGE_AXIS));
JScrollPane class_operator_scrollP = new JScrollPane();
class_operator_panel.add(class_operator_scrollP);
CMOTableModel cmTableModel = new CMOTableModel();
classOpTable = new JTable(cmTableModel);

initColumnSizes(classOpTable, cmTableModel);
class_operator_scrollP.getViewport().add(classOpTable, null);
class_operator_scrollP.setPreferredSize(new Dimension(115, 550));
class_operator_scrollP.setMaximumSize(new Dimension(115, 550));

class_operator_panel.add(class_operator_scrollP);
JPanel classBPanel = new JPanel();
classBPanel.setLayout(new FlowLayout());
classBPanel.add(classNoneB);
classNoneB.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        onMouseClicked_classNoneB(e);
    }
});
classBPanel.add(classAllB);
classAllB.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        onMouseClicked_classAllB(e);
    }
});
class_operator_panel.add(classBPanel);
//</editor-fold>

JPanel operator_panel = new JPanel();
operator_panel.setLayout(new FlowLayout());
operator_panel.add(traditional_operator_panel);
operator_panel.add(class_operator_panel);
rightPanel.add(operator_panel);
//</editor-fold>

this.add(leftPanel);
this.add(Box.createRigidArea(new Dimension(10, 10)));
this.add(rightPanel);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Events">
    /**
     * Event for when change de log level
     */
    void onChangeLogLevel() {
        if (logCB.getSelectedItem().toString() == null) {
            return;
        }

        DebugType selectedLevel = DebugType.getEnumByDescription(logCB.getSelectedItem().toString());
        Debug.setDebugLevel(Integer.parseInt(selectedLevel.getValue()));
    }
    /**
     * Generate Mutants
     *
     * @param e
     */
    void onMouseClicked_Generate(MouseEvent e) throws IOException {
        // If there is some error for display        
        String errorMessage = "";

        //check if any files are selected, return an error message if no files have been selected
        FileTableModel fTableModel = (FileTableModel) fileTable.getModel();
        String[] file_list = fTableModel.getSelectedFiles();

        if (file_list == null || file_list.length == 0) {
            errorMessage = "No class is selected. Please select one or more .java files for which you'd like to generate mutants.";
            System.err.println("[ERROR] " + errorMessage);
            JOptionPane.showMessageDialog(null, errorMessage, "Advise", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //check if any operators are selected, return an error message if no files have been selected        
        String[] class_ops = ((CMOTableModel) classOpTable.getModel()).getSelectedOprators();
        String[] traditional_ops = ((TMOTableModel) traditionalOpTable.getModel()).getSelectedOprators();

        if ((class_ops == null || class_ops.length == 0) && (traditional_ops == null || traditional_ops.length == 0)) {
            errorMessage = "No operators are selected. Please select one or more mutation operators.";
            System.out.println("[ERROR] " + errorMessage);
            JOptionPane.showMessageDialog(null, errorMessage, "Advise", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //disable the button
        runB.setEnabled(false);

        for (int i = 0; i < file_list.length; i++) {
            // file_name = ABSTRACT_PATH - MutationSystem.SRC_PATH
            // For example: org/apache/bcel/Class.java
            String file_name = file_list[i];
            try {
                // [1] Examine if the target class is interface or abstract class
                //     In that case, we can't apply mutation testing.
                
                // Generate class name from file_name                
                String class_name = FilesUtils.generateClassNameFromFileName(file_name);

                int class_type = MutationSystem.getClassType(class_name);

                if (class_type == MutationSystem.NORMAL) {
                    // do nothing?
                } else if (class_type == MutationSystem.MAIN) {
                    errorMessage = " -- " + file_name + " class contains 'static void main()' method.\n    Please note that mutants are not generated for the 'static void main()' method";
                    System.out.println(errorMessage);
                    JOptionPane.showMessageDialog(null, errorMessage, "Advise", JOptionPane.ERROR_MESSAGE);
                } //Added on 1/19/2013, no mutants will be generated for a class having only one main method
                else if (class_type == MutationSystem.MAIN_ONLY) {
                    errorMessage = "Class " + file_name + " has only the 'static void main()' method and no mutants will be generated.";
                    System.out.println(errorMessage);
                    JOptionPane.showMessageDialog(null, errorMessage, "Advise", JOptionPane.ERROR_MESSAGE);

                    break;
                }

                // [2] Apply mutation testing
                MutationSystem.setMutationSystemPathFor(file_name);

                File original_file = new File(MutationSystem.SRC_PATH, file_name);

                System.out.println("==== Generating Mutants ==== ");
                //do not generate class mutants if no class mutation operator is selected
                if (class_ops != null) {
                    System.out.println("#Class Mutation Operator");
                    ClassMutantsGenerator cmGenEngine;
                    cmGenEngine = new ClassMutantsGenerator(original_file, class_ops);
                    cmGenEngine.makeMutants();
                    cmGenEngine.compileMutants();
                    System.out.println("Generated!\n");
                }

                //do not generate traditional mutants if no class traditional operator is selected
                if (traditional_ops != null) {
                    System.out.println("#Traditional Mutation Operator");
                    TraditionalMutantsGenerator tmGenEngine;
                    tmGenEngine = new TraditionalMutantsGenerator(original_file, traditional_ops);
                    tmGenEngine.makeMutants();
                    tmGenEngine.compileMutants();
                    System.out.println("Generated!\n");
                }

                // List all class files generated less the original file
                int total_mutants = FilesUtils.getFiles(new File(MutationSystem.MUTANT_HOME + "/" + class_name), "", ".class").size() - 1;

                System.out.println("------------------------------------------------------------------");
                System.out.println("Total mutants gnerated for " + file_name + ": " + Integer.toString(total_mutants));               
            } catch (OpenJavaException oje) {
                System.out.println("[OJException] " + file_name + " " + oje.toString());
                MutationSystem.deleteDirectory();
            } catch (Exception exp) {
                System.out.println("[Exception] " + file_name + " " + exp.toString());
                exp.printStackTrace();
                MutationSystem.deleteDirectory();
            } catch (Error er) {
                System.out.println("[Error] " + file_name + " " + er.toString());
                System.out.println("MutantsGenPanel: ");
                er.printStackTrace();
                MutationSystem.deleteDirectory();
            }
        }

        runB.setEnabled(true);
        parent_frame.cvPanel.refreshEnv();
        parent_frame.tvPanel.refreshEnv();
        System.out.println("------------------------------------------------------------------");
        System.out.println("All files are handled");
        JOptionPane.showMessageDialog(null, "All files are handled.", "Advise", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Select All Class-Levels Operators
     *
     * @param e
     */
    void onMouseClicked_classAllB(MouseEvent e) {
        CMOTableModel table = (CMOTableModel) classOpTable.getModel();
        table.setAllSelectValue(true);
        classOpTable.setModel(table);
        classOpTable.repaint();
    }
    /**
     * Unselect All Class-Levels Operators
     *
     * @param e
     */
    void onMouseClicked_classNoneB(MouseEvent e) {
        CMOTableModel table = (CMOTableModel) classOpTable.getModel();
        table.setAllSelectValue(false);
        classOpTable.setModel(table);
        classOpTable.repaint();
    }
    
    //</editor-fold>

    /**
     * Select All Files
     *
     * @param e
     */
    void onMouseClicked_fileAllB(MouseEvent e) {
        FileTableModel table = (FileTableModel) fileTable.getModel();
        table.setAllSelectValue(true);
        fileTable.setModel(table);
        fileTable.repaint();
    }

    /**
     * Unselect All Files
     *
     * @param e
     */
    void onMouseClicked_fileNoneB(MouseEvent e) {
        FileTableModel table = (FileTableModel) fileTable.getModel();
        table.setAllSelectValue(false);
        fileTable.setModel(table);
        fileTable.repaint();
    }


    /**
     * Select All Method-Levels Operators
     *
     * @param e
     */
    void onMouseClicked_traditionalAllB(MouseEvent e) {
        TMOTableModel table = (TMOTableModel) traditionalOpTable.getModel();
        table.setAllSelectValue(true);
        traditionalOpTable.setModel(table);
        traditionalOpTable.repaint();
    }

    /**
     * Unselect All Method-Levels Operators
     *
     * @param e
     */
    void onMouseClicked_traditionalNoneB(MouseEvent e) {
        TMOTableModel table = (TMOTableModel) traditionalOpTable.getModel();
        table.setAllSelectValue(false);
        traditionalOpTable.setModel(table);
        traditionalOpTable.repaint();
    }

}
