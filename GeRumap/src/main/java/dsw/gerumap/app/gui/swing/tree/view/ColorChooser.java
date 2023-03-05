package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.messageGenerator.MessageGenerator;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramConcept;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramConceptPainter;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Logger;

import static dsw.gerumap.app.core.messageGenerator.EventType.FAILED_TO_PARSE_STR_TO_FLOAT;

public class ColorChooser extends JPanel implements ChangeListener {
    protected JColorChooser colorChooser;
    protected JLabel south;
    protected JDialog dialog ;

    public ColorChooser(ArrayList<DiagramConceptPainter> concepts) {
        super(new BorderLayout());

        //Set up color chooser for setting text color
        colorChooser = new JColorChooser(concepts.get(0).getConcept().getColor());
        colorChooser.getSelectionModel().addChangeListener(this);
        colorChooser.setBorder(BorderFactory.createTitledBorder("Choose Color"));

        //Create buttons
        JButton save = new JButton("save");
        save.setBounds(50,100,95,30);
        JButton exit = new JButton("exit");
        exit.setBounds(50,100,95,30);
        JTextField nameField = new JTextField(concepts.get(0).getConcept().getTopicName());
        JTextField strokeLineWidthField = new JTextField(Float.toString(concepts.get(0).getConcept().getLineWidth()));

        south = new JLabel();
        south.setPreferredSize(new Dimension(100, 65));
        south.setBorder(new LineBorder(Color.GRAY, 1));
        south.setLayout(new BorderLayout());
        south.setFont(new Font("SansSerif", Font.BOLD, 24));

        south.add(save, BorderLayout.LINE_START);
        JPanel middlePanel = new JPanel(new GridLayout(1, 2));
        middlePanel.add(nameField);
        middlePanel.add(strokeLineWidthField);
        south.add(middlePanel, BorderLayout.CENTER);
        south.add(exit, BorderLayout.LINE_END);

        add(south,BorderLayout.SOUTH);
        add(colorChooser, BorderLayout.CENTER);

        save.addActionListener(e -> {
            if(dialog != null && dialog.isVisible()){
                for (DiagramConceptPainter concept : concepts) {
                    concept.getConcept().setTopicName(nameField.getText());
                    concept.getConcept().setColor(new dsw.gerumap.app.gui.swing.tree.view.graphics.elements.Color(colorChooser.getColor()));
                    try {
                        float newLine = Float.parseFloat(strokeLineWidthField.getText());
                        if (newLine > 0) {
                            concept.getConcept().setLineWidth(newLine);
                        }

                    } catch (Exception ignore) {
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage(FAILED_TO_PARSE_STR_TO_FLOAT);
                    } finally {continue;}
                }
                dialog.setVisible(false);
            }
        });
        exit.addActionListener(e -> {
            if(dialog != null && dialog.isVisible()){
                dialog.setVisible(false);
            }
        });
    }
    @Override
    public void stateChanged(ChangeEvent e) {
    }

    public void createAndShowGUI() {
        //Create and set up the window.
        dialog = new JDialog();
        //Create and set up the content pane.
        JComponent newContentPane = this;
        newContentPane.setOpaque(true); //content panes must be opaque

        dialog.setTitle("Color Picker");
        dialog.setContentPane(newContentPane);

        //Display the window.
        dialog.pack();
        dialog.setVisible(true);
    }

}
