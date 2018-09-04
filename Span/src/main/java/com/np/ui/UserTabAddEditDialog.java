package com.np.ui;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

import java.sql.SQLException;

public class UserTabAddEditDialog extends Window {
    private SQLContainer sqlContainer = DataSource.getInstance().getUserSQLContainer();

    private FieldGroup editorFields = new FieldGroup();
    private TextField idField = new TextField("ID");
    private TextField emailField = new TextField("E-mail");
    private TextField nameField = new TextField("Name");
    private TextField activeField = new TextField("Active");
    private TextField adminField = new TextField("Admin");

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

    public void setObject(Object itemId, Item item)
    {
        if (itemId != null)
            editorFields.setItemDataSource(item);
    }

    public UserTabAddEditDialog()
    {
        super("Add/Edit user"); // Set window caption
        center();

        // Some basic content for the window
        FormLayout form = new FormLayout();
        form.setMargin(true);
        form.setSpacing(true);

        form.addComponent(idField);
        form.addComponent(emailField);
        form.addComponent(nameField);
        form.addComponent(activeField);
        form.addComponent(adminField);

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

        editorFields.bind(idField, "user_id");
        editorFields.bind(emailField, "user_email");
        editorFields.bind(nameField, "user_name");
        editorFields.bind(activeField, "user_active");
        editorFields.bind(adminField, "user_admin");

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
