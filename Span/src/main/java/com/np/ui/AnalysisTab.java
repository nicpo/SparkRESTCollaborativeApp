package com.np.ui;

import com.np.util.Config;
import com.vaadin.data.Property;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

import java.sql.SQLException;

public class AnalysisTab extends Panel {
    private SQLContainer sqlContainer = DataSource.getInstance().getAnalysisSQLContainer();

    private Table items = new Table();
    private Button addNewButton = new Button("New");
    private Button editButton = new Button("Edit");
    private Button removeButton = new Button("Remove");
    private Button refreshButton = new Button("Refresh");

    private AnalysisTabAddEditDialog addEditDialog = new AnalysisTabAddEditDialog();


    public AnalysisTab() {
        initLayout();
        initItemsList();
        initEditor();
        initButtons();
    }

    private void initLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setMargin(false);
        buttonLayout.setSpacing(true);
        buttonLayout.addComponent(addNewButton);
        buttonLayout.addComponent(editButton);
        buttonLayout.addComponent(removeButton);
        buttonLayout.addComponent(refreshButton);
        buttonLayout.setSizeUndefined();

        items.setSizeFull();
        items.setPageLength(0);
        items.setSelectable(true);
        items.setImmediate(true);

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(buttonLayout);
        layout.addComponent(items);
        layout.setExpandRatio(items, 1.0f); // Vertical- and HorizontalLayout distribute space evenly between components, unless you specify expand ratios for the components in the layout.

        setSizeFull();
        setContent(layout);
    }

    private void initItemsList() {
        items.setContainerDataSource(sqlContainer);
        items.setVisibleColumns("analysis_id", "analysis_name", "analysis_type", "analysis_query", "analysis_user", "analysis_request", "analysis_state", "analysis_output", "analysis_starttime", "analysis_endtime", "analysis_description");
        items.setColumnHeaders("Id", "Name", "Type", "Query", "User", "Request", "State", "Output", "Start", "End", "Description");

        items.setConverter("analysis_starttime", new TimestampConverter());
        items.setConverter("analysis_endtime", new TimestampConverter());

        items.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                Object itemId = items.getValue();
                addEditDialog.setObject(itemId, items.getItem(itemId));
            }
        });

    }

    private void initEditor() {
    }

    private void initButtons() {
        addNewButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                Object itemId = sqlContainer.addItem();
                items.getContainerProperty(itemId, "analysis_id").setValue(Config.getShortUUID());
                items.getContainerProperty(itemId, "analysis_name").setValue("");
                items.getContainerProperty(itemId, "analysis_type").setValue("");
                items.getContainerProperty(itemId, "analysis_query").setValue("");
                items.getContainerProperty(itemId, "analysis_user").setValue("");
                items.getContainerProperty(itemId, "analysis_request").setValue("");
                items.getContainerProperty(itemId, "analysis_state").setValue("");
                items.getContainerProperty(itemId, "analysis_output").setValue("");
                items.getContainerProperty(itemId, "analysis_starttime").setValue(System.currentTimeMillis());
                items.getContainerProperty(itemId, "analysis_endtime").setValue(System.currentTimeMillis());
                items.getContainerProperty(itemId, "analysis_description").setValue("");

                items.select(itemId);
                UI.getCurrent().addWindow(addEditDialog);
            }
        });

        editButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                Object itemId = items.getValue();
                if (itemId==null)
                    return; // no item selected

                UI.getCurrent().addWindow(addEditDialog);
            }
        });

        removeButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                Object itemId = items.getValue();
                if (itemId==null)
                    return; // no item selected

                items.removeItem(itemId);
                try {
                    sqlContainer.commit();
                } catch (SQLException e) {
                    removeButton.setComponentError(new UserError(e.getMessage()));
                    e.printStackTrace();
                }

            }
        });

        refreshButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                DataSource.getInstance().getAnalysisSQLContainer().refresh();
            }
        });

    }

}
