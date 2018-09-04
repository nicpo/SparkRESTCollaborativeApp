package com.np.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

@Title("AnalyticsApplication")
/* @Theme("valo") */
public class AdminUI extends UI {
    Navigator navigator = new Navigator(this, this);


    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("AnalyticsApplication Admin Console");
        navigator.addView("login", new LoginView(this));
        navigator.addView("main", new MainView());
        navigator.navigateTo("login");
    }

    public Navigator getNavigator()
    {
        return navigator;
    }
}
