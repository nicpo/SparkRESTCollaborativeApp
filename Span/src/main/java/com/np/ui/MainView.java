package com.np.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;


public class MainView extends VerticalLayout implements View {
    public MainView() {
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();

        tabSheet.addTab(new UserTab(), "Users");
        tabSheet.addTab(new DatasetTab(), "Datasets");
        tabSheet.addTab(new AnalysisTab(), "Analysis");
        tabSheet.addTab(new CommentTab(), "Comments");
        tabSheet.addTab(new PermissionTab(), "Permissions");
        tabSheet.addTab(new AuditTab(), "Audit");


        Panel mainPanel = new Panel("Analytics Application Admin Console");
        mainPanel.setSizeFull();
        mainPanel.setContent(tabSheet);

        setSizeFull();
        setMargin(true);
        addComponent(mainPanel);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
