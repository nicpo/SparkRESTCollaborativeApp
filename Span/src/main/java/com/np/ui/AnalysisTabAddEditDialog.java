package com.np.ui;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;

import java.sql.SQLException;

public class AnalysisTabAddEditDialog extends Window {
    private SQLContainer sqlContainer = DataSource.getInstance().getDatasetSQLContainer();

//    "analysis_id", "analysis_name", "analysis_type", "analysis_query", "analysis_user", "analysis_request", "analysis_state", "analysis_output", "analysis_starttime", "analysis_endtime"});

    private FieldGroup editorFields = new FieldGroup();
    private TextField idField = new TextField("ID");
    private TextField nameField = new TextField("Name");
    private TextField typeField = new TextField("Type");
    private TextField queryField = new TextField("Query");
    private TextField userField = new TextField("Type");
    private TextField requestField = new TextField("Request");
    private TextField stateField = new TextField("State");
    private TextField outputField = new TextField("Output");
    private PopupDateField startTimeField = new PopupDateField("Start");
    private PopupDateField endTimeField = new PopupDateField("End");
    private TextField descriptionField = new TextField("Description");

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

    public void setObject(Object itemId, Item item)
    {
        if (itemId != null)
            editorFields.setItemDataSource(item);
    }

    public AnalysisTabAddEditDialog()
    {
        super("Add/Edit dataset"); // Set window caption
        center();

        // Some basic content for the window
        FormLayout form = new FormLayout();
        form.setMargin(true);
        form.setSpacing(true);

        form.addComponent(idField);
        form.addComponent(nameField);
        form.addComponent(typeField);
        form.addComponent(queryField);
        form.addComponent(userField);
        form.addComponent(requestField);
        form.addComponent(stateField);
        form.addComponent(outputField);
        form.addComponent(startTimeField);
        form.addComponent(endTimeField);
        form.addComponent(descriptionField);

        outputField.setNullRepresentation("");
        outputField.setNullSettingAllowed(true);

        descriptionField.setNullRepresentation("");
        descriptionField.setNullSettingAllowed(true);


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

        editorFields.bind(idField, "analysis_id");
        editorFields.bind(nameField, "analysis_name");
        editorFields.bind(typeField, "analysis_type");
        editorFields.bind(queryField, "analysis_query");
        editorFields.bind(userField, "analysis_user");
        editorFields.bind(requestField, "analysis_request");
        editorFields.bind(stateField, "analysis_state");
        editorFields.bind(outputField, "analysis_output");
        editorFields.bind(startTimeField, "analysis_starttime");
        editorFields.bind(endTimeField, "analysis_endtime");
        editorFields.bind(descriptionField, "analysis_description");

        startTimeField.setResolution(Resolution.MINUTE);
        endTimeField.setResolution(Resolution.MINUTE);

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
