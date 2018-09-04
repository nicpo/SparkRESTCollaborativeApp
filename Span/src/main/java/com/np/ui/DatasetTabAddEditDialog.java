package com.np.ui;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;

import java.sql.SQLException;

public class DatasetTabAddEditDialog extends Window {
    private SQLContainer sqlContainer = DataSource.getInstance().getDatasetSQLContainer();

    //  "dataset_table", "dataset_query", "dataset_description", "dataset_info", "dataset_timestamp", "dataset_user"});

    private FieldGroup editorFields = new FieldGroup();
    private TextField idField = new TextField("ID");
    private TextField nameField = new TextField("Name");
    private TextField urlField = new TextField("URL");
    private TextField tableField = new TextField("Table");
    private TextField queryField = new TextField("Query");
    private TextField descriptionField = new TextField("Description");
    private TextField infoField = new TextField("Info");
    private PopupDateField timestampField = new PopupDateField("Timestamp");
    private TextField userField = new TextField("User");
    private TextField sizeField = new TextField("Size");


    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

    public void setObject(Object itemId, Item item)
    {
        if (itemId != null)
            editorFields.setItemDataSource(item);
    }

    public DatasetTabAddEditDialog()
    {
        super("Add/Edit dataset"); // Set window caption
        center();

        // Some basic content for the window
        FormLayout form = new FormLayout();
        form.setMargin(true);
        form.setSpacing(true);

        form.addComponent(idField);
        form.addComponent(nameField);
        form.addComponent(urlField);
        form.addComponent(tableField);
        form.addComponent(queryField);
        form.addComponent(descriptionField);
        form.addComponent(infoField);
        form.addComponent(timestampField);
        form.addComponent(userField);
        form.addComponent(sizeField);

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

        editorFields.bind(idField, "dataset_id");
        editorFields.bind(nameField, "dataset_name");
        editorFields.bind(urlField, "dataset_url");
        editorFields.bind(tableField, "dataset_table");
        editorFields.bind(queryField, "dataset_query");
        editorFields.bind(descriptionField, "dataset_description");
        editorFields.bind(infoField, "dataset_info");
        editorFields.bind(timestampField, "dataset_timestamp");
        editorFields.bind(userField, "dataset_user");
        editorFields.bind(sizeField, "dataset_size");

        timestampField.setResolution(Resolution.MINUTE);

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
