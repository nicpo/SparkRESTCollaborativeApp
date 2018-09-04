package com.np.ui;

import com.np.metastore.data.User;
import com.np.metastore.management.CoreManagement;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

public class LoginView extends Panel implements View {
    private final VerticalLayout verticalLayout = new VerticalLayout();
    private final Panel loginPanel = new Panel("Login...");
    private final VerticalLayout formLayout = new VerticalLayout();
    private final TextField emailField = new TextField("E-mail");
    private final PasswordField passwordField = new PasswordField("Password");
    private final Button loginButton = new Button("Login");

    public LoginView(final AdminUI admin) {
        emailField.setRequired(true);
        passwordField.setRequired(true);
        loginButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                User user = new User();
                user.setEmail(emailField.getValue());
                user.setPassword(passwordField.getValue());
                user.setAdmin(true);
                try {
                    CoreManagement.login(user);
                    admin.getNavigator().navigateTo("main");
                } catch (Exception e) {
                    loginButton.setComponentError(new UserError("Incorrect login/password"));
                }
            }
        });

        formLayout.addComponent(emailField);
        formLayout.addComponent(passwordField);
        formLayout.addComponent(loginButton);
        formLayout.setSpacing(true);
        formLayout.setMargin(true);

        loginPanel.setContent(formLayout);
        loginPanel.setSizeUndefined(); // minimize sizeByUrl, it should just wrap fields

        verticalLayout.addComponent(loginPanel);
        verticalLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        verticalLayout.setSizeFull(); // maximize sizeByUrl, it should take full window

        setSizeFull(); // maximize it too. So minimized loginPanel should be aligned inside maximized formLayout
        setContent(verticalLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
