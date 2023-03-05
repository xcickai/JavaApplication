package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.messageGenerator.EventType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.implementation.MindMapView;
import lombok.Getter;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class EditDialog extends JDialog {
    private JButton renameButton;
    private JTextField nameTextField;
    private JTextField authorTextField;
    private JDialog dialog;

    public EditDialog(MapTreeItem forChild, DefaultTreeModel treeModel) {
        // Dialog prep
        dialog = new JDialog();
        dialog.setTitle("Edit Item");
        dialog.setSize(300, 120);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        JPanel panel = new JPanel(new GridLayout(3,2,10,10));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // New Name
        // Label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(new JLabel("New name:"), constraints);
        // TextField
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        nameTextField = new JTextField(10);
        nameTextField.setText(forChild.getMapNode().getName());
        panel.add(nameTextField, constraints);

        if (forChild.getMapNode() instanceof MindMapView) {
            dialog.setSize(300, 200);
            // New Author
            // Label
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            panel.add(new JLabel("New Author:"), constraints);
            // TextField
            constraints.gridx = 1;
            constraints.gridwidth = 2;
            authorTextField = new JTextField(10);
            authorTextField.setText(((MindMapView)forChild.getMapNode()).getAuthor());
            panel.add(authorTextField, constraints);
        }

        // Buttons
        renameButton = new JButton("Rename");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(renameButton, constraints);
        JButton cancelButton = new JButton("Cancel");
        constraints.gridx = 1;
        panel.add(cancelButton, constraints);

        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel panel2 = new JPanel();
        panel2.add(panel);
        dialog.add(panel2);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
                String renamedText = nameTextField.getText();
                if (renamedText.isEmpty()) {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.NODE_NAME_EMPTY);
                    return;
                }
                if (selected.getMapNode() instanceof MindMapView) {
                    String authorText = authorTextField.getText();
                    ((MindMapView) selected.getMapNode()).setAuthor(authorText);
                }
                selected.getMapNode().setName(renamedText);

                String expansionState = MainFrame.getInstance().getMapTree().getExpansionState();
                treeModel.reload();
                MainFrame.getInstance().getMapTree().setExpansionState(expansionState);
            }
        });
    }
}
