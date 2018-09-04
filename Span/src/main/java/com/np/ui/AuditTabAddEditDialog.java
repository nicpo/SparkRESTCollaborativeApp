package com.np.ui;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;

import java.sql.SQLException;

public class AuditTabAddEditDialog extends Window {
    private SQLContainer sqlContainer = DataSource.getInstance().getAuditSQLContainer();

    private FieldGroup editorFields = new FieldGroup();
    private TextField idField = new TextField("ID");
    private TextField typeField = new TextField("Type");
    private TextField userField = new TextField("User");
    private PopupDateField timestampField = new PopupDateField("Timestamp");
    private TextField infoField = new TextField("Info");

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

    public void setObject(Object itemId, Item item)
    {
        if (itemId != null)
            editorFields.setItemDataSource(item);
    }

    public AuditTabAddEditDialog()
    {
        super("Add/Edit dataset"); // Set window caption
        center();

        // Some basic content for the window
        FormLayout form = new FormLayout();
        form.setMargin(true);
        form.setSpacing(true);

        form.addComponent(idField);
        form.addComponent(typeField);
        form.addComponent(userField);
        form.addComponent(timestampField);
        form.addComponent(infoField);

        userField.setNullRepresentation("");
        userField.setNullSettingAllowed(true);

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

        editorFields.bind(idField, "audit_id");
        editorFields.bind(typeField, "audit_type");
        editorFields.bind(userField, "audit_user");
        editorFields.bind(timestampField, "audit_timestamp");
        editorFields.bind(infoField, "audit_info");

        timestampField.setResolution(Resolution.SECOND);

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
