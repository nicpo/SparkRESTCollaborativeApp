package com.np.ui;

import com.np.util.Config;
import com.vaadin.data.Property;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

import java.sql.SQLException;

public class CommentTab extends Panel {
    private SQLContainer sqlContainer = DataSource.getInstance().getCommentSQLContainer();

    private Table items = new Table();
    private Button addNewButton = new Button("New");
    private Button editButton = new Button("Edit");
    private Button removeButton = new Button("Remove");
    private Button refreshButton = new Button("Refresh");

    private CommentTabAddEditDialog addEditDialog = new CommentTabAddEditDialog();


    public CommentTab() {
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
        items.setVisibleColumns("comment_id", "comment_user", "comment_created", "comment_updated", "comment_text", "comment_dataset", "comment_analysis", "comment_target", "comment_reference");
        items.setColumnHeaders("ID", "User", "Created", "Updated", "Text", "Dataset", "Analysis", "Target", "Reference");

        items.setConverter("comment_created", new TimestampConverter());
        items.setConverter("comment_updated", new TimestampConverter());

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
                items.getContainerProperty(itemId, "comment_id").setValue(Config.getShortUUID());
                items.getContainerProperty(itemId, "comment_user").setValue(null);
                items.getContainerProperty(itemId, "comment_created").setValue(System.currentTimeMillis());
                items.getContainerProperty(itemId, "comment_updated").setValue(null);
                items.getContainerProperty(itemId, "comment_text").setValue("");
                items.getContainerProperty(itemId, "comment_dataset").setValue(null);
                items.getContainerProperty(itemId, "comment_analysis").setValue(null);
                items.getContainerProperty(itemId, "comment_target").setValue("");
                items.getContainerProperty(itemId, "comment_reference").setValue(null);

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
                DataSource.getInstance().getUserSQLContainer().refresh();
            }
        });

    }


}
