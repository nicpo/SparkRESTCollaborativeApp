package com.np.ui;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;

import java.sql.SQLException;

public class PermissionTabAddEditDialog extends Window {
    private SQLContainer sqlContainer = DataSource.getInstance().getPermissionSQLContainer();

    private FieldGroup editorFields = new FieldGroup();
    private TextField idField = new TextField("ID");
    private TextField typeField = new TextField("Type");
    private TextField creatorField = new TextField("Creator");
    private TextField userField = new TextField("User");
    private TextField datasetField = new TextField("Dataset");
    private TextField analysisField = new TextField("Analysis");

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

    public void setObject(Object itemId, Item item)
    {
        if (itemId != null)
            editorFields.setItemDataSource(item);
    }

    public PermissionTabAddEditDialog()
    {
        super("Add/Edit dataset"); // Set window caption
        center();

        // Some basic content for the window
        FormLayout form = new FormLayout();
        form.setMargin(true);
        form.setSpacing(true);

        //  "permission_id", "permission_type", "permission_creator", "permission_user", "permission_dataset", "permission_analysis"});

        form.addComponent(idField);
        form.addComponent(typeField);
        form.addComponent(creatorField);
        form.addComponent(userField);
        form.addComponent(datasetField);
        form.addComponent(analysisField);

        userField.setNullRepresentation("");
        userField.setNullSettingAllowed(true);

        creatorField.setNullRepresentation("");
        creatorField.setNullSettingAllowed(true);

        datasetField.setNullRepresentation("");
        datasetField.setNullSettingAllowed(true);

        analysisField.setNullRepresentation("");
        analysisField.setNullSettingAllowed(true);

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

        editorFields.bind(idField, "permission_id");
        editorFields.bind(typeField, "permission_type");
        editorFields.bind(creatorField, "permission_creator");
        editorFields.bind(userField, "permission_user");
        editorFields.bind(datasetField, "permission_dataset");
        editorFields.bind(analysisField, "permission_analysis");

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
