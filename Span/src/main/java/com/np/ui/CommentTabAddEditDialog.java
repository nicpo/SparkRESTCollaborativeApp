package com.np.ui;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;

import java.sql.SQLException;

public class CommentTabAddEditDialog extends Window {
    private SQLContainer sqlContainer = DataSource.getInstance().getCommentSQLContainer();

    private FieldGroup editorFields = new FieldGroup();
    private TextField idField = new TextField("ID");
    private TextField userField = new TextField("Type");
    private PopupDateField createdField = new PopupDateField("Created");
    private PopupDateField updatedField = new PopupDateField("Updated");
    private TextField textField = new TextField("Text");
    private TextField datasetField = new TextField("Dataset");
    private TextField analysisField = new TextField("Analysis");
    private TextField targetField = new TextField("Target");
    private TextField referenceField = new TextField("Reference");

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

    public void setObject(Object itemId, Item item)
    {
        if (itemId != null)
            editorFields.setItemDataSource(item);
    }

    public CommentTabAddEditDialog()
    {
        super("Add/Edit dataset"); // Set window caption
        center();

        // Some basic content for the window
        FormLayout form = new FormLayout();
        form.setMargin(true);
        form.setSpacing(true);

        form.addComponent(idField);
        form.addComponent(userField);
        form.addComponent(createdField);
        form.addComponent(updatedField);
        form.addComponent(textField);
        form.addComponent(datasetField);
        form.addComponent(analysisField);
        form.addComponent(targetField);
        form.addComponent(referenceField);

        datasetField.setNullRepresentation("");
        datasetField.setNullSettingAllowed(true);

        analysisField.setNullRepresentation("");
        analysisField.setNullSettingAllowed(true);

        referenceField.setNullRepresentation("");
        referenceField.setNullSettingAllowed(true);


        HorizontalLayout hl = new HorizontalLayout();
        hl.setSpacing(true);
        hl.setMargin(true);
        hl.setSizeUndefined();
        hl.addComponent(saveButton);
        hl.addComponent(cancelButton);

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(form);
        layout.addComponent(hl);

        setContent(layout);

        editorFields.bind(idField, "comment_id");
        editorFields.bind(userField, "comment_user");
        editorFields.bind(createdField, "comment_created");
        editorFields.bind(updatedField, "comment_updated");
        editorFields.bind(textField, "comment_text");
        editorFields.bind(datasetField, "comment_dataset");
        editorFields.bind(analysisField, "comment_analysis");
        editorFields.bind(targetField, "comment_target");
        editorFields.bind(referenceField, "comment_reference");

        createdField.setResolution(Resolution.MINUTE);
        updatedField.setResolution(Resolution.MINUTE);

        editorFields.setBuffered(true);

        saveButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                try {
                    editorFields.commit();
                    sqlContainer.commit();
                    close();
                    saveButton.setComponentError(null);
                } catch (FieldGroup.CommitException e) {
                    saveButton.setComponentError(new UserError(e.getMessage()));
                    e.printStackTrace();
                } catch (SQLException e) {
                    saveButton.setComponentError(new UserError(e.getMessage()));
                    e.printStackTrace();
                }
            }
        });

        cancelButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                close();
                saveButton.setComponentError(null);
                editorFields.discard();
                try {
                    sqlContainer.rollback();
                } catch (SQLException e) {
                    e.printStackTrace(); // ignore this
                }
            }
        });

        // Disable the close button
        setClosable(false);
        setModal(true);

    }
}
