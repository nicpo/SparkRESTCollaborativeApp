package com.np.ui;

import com.np.metastore.kernel.UserKernel;
import com.np.util.Config;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.DateToLongConverter;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class DatasetTab extends Panel {
    private SQLContainer sqlContainer = DataSource.getInstance().getDatasetSQLContainer();

    private Table items = new Table();
    private Button addNewButton = new Button("New");
    private Button editButton = new Button("Edit");
    private Button removeButton = new Button("Remove");
    private Button refreshButton = new Button("Refresh");

    private DatasetTabAddEditDialog addEditDialog = new DatasetTabAddEditDialog();


    public DatasetTab() {
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
        items.setVisibleColumns("dataset_id", "dataset_name", "dataset_url", "dataset_table", "dataset_query", "dataset_description", "dataset_info", "dataset_timestamp", "dataset_user", "dataset_size");
        items.setColumnHeaders("Id", "Name", "URL", "Table", "Query", "Description", "Info", "Timestamp", "User", "Size");

        items.setConverter("dataset_timestamp", new TimestampConverter());

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
                items.getContainerProperty(itemId, "dataset_id").setValue(Config.getShortUUID());
                items.getContainerProperty(itemId, "dataset_name").setValue("");
                items.getContainerProperty(itemId, "dataset_url").setValue("");
                items.getContainerProperty(itemId, "dataset_table").setValue("");
                items.getContainerProperty(itemId, "dataset_query").setValue("");
                items.getContainerProperty(itemId, "dataset_description").setValue("");
                items.getContainerProperty(itemId, "dataset_info").setValue("");
                items.getContainerProperty(itemId, "dataset_timestamp").setValue(System.currentTimeMillis());
                items.getContainerProperty(itemId, "dataset_user").setValue("");
                items.getContainerProperty(itemId, "dataset_size").setValue("");

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
                DataSource.getInstance().getDatasetSQLContainer().refresh();
            }
        });
    }

}
