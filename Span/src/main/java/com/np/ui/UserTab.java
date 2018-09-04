package com.np.ui;

import com.np.metastore.kernel.UserKernel;
import com.np.util.Config;
import com.vaadin.data.Property;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

public class UserTab extends Panel {
    private SQLContainer sqlContainer = DataSource.getInstance().getUserSQLContainer();

    private Table items = new Table();
    private Button addNewButton = new Button("New");
    private Button editButton = new Button("Edit");
    private Button removeButton = new Button("Remove");
    private Button refreshButton = new Button("Refresh");
    private Button passwordButton = new Button("Change password");

    private UserTabAddEditDialog addEditDialog = new UserTabAddEditDialog();
    private UserTabPasswordDialog passwordDialog = new UserTabPasswordDialog(this);


    public UserTab() {
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
        buttonLayout.addComponent(passwordButton);
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
        items.setVisibleColumns("user_id","user_email", "user_name", "user_active", "user_admin");
        items.setColumnHeaders("ID", "Email", "Name", "Active", "Admin");

        items.setConverter("user_active", new CheckboxConverter());
        items.setConverter("user_admin", new CheckboxConverter());

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
                items.getContainerProperty(itemId, "user_id").setValue("a@b.com");
                items.getContainerProperty(itemId, "user_email").setValue("a@b.com");
                items.getContainerProperty(itemId, "user_name").setValue("");
                items.getContainerProperty(itemId, "user_password").setValue("");
                items.getContainerProperty(itemId, "user_salt").setValue("");
                items.getContainerProperty(itemId, "user_active").setValue(1);
                items.getContainerProperty(itemId, "user_admin").setValue(0);
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

        passwordButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                Object itemId = items.getValue();
                if (itemId==null)
                    return; // no item selected
                UI.getCurrent().addWindow(passwordDialog);
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

    public void setPasswordListener(String newPassword) throws NoSuchAlgorithmException, SQLException
    {
        Object itemId = items.getValue();
        String salt = UUID.randomUUID().toString();
        String passwordHash = UserKernel.calcHashForPassword(newPassword, salt);
        items.getContainerProperty(itemId, "user_salt").setValue(salt);
        items.getContainerProperty(itemId, "user_password").setValue(passwordHash);
        items.commit();
        sqlContainer.commit();
    }

}
