package com.np.ui;

import com.vaadin.server.UserError;
import com.vaadin.ui.*;

public class UserTabPasswordDialog extends Window {
    private PasswordField password1 = new PasswordField("Password");
    private PasswordField password2 = new PasswordField("Re-enter password");

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");


    public UserTabPasswordDialog(final UserTab userTab) {
        super("Change password"); // Set window caption
        center();

        // Some basic content for the window
        FormLayout form = new FormLayout();
        form.setMargin(true);
        form.setSpacing(true);

        form.addComponent(password1);
        form.addComponent(password2);

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

        saveButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                try {
                    if (password1.getValue().equals(password2.getValue())) {
                        userTab.setPasswordListener(password1.getValue()); // store password to DB
                        saveButton.setComponentError(null);
                        close();
                    } else {
                        saveButton.setComponentError(new UserError("Password doesn't match"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    saveButton.setComponentError(new UserError(e.getMessage()));
                }
            }
        });

        cancelButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                saveButton.setComponentError(null);
                close();
            }
        });

        // Disable the close button
        setClosable(false);
        setModal(true);
    }

}
