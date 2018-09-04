package com.np.ui;

import com.vaadin.data.util.sqlcontainer.query.generator.StatementHelper;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class NullReferenceHelper extends StatementHelper {

    @Override
    protected boolean handleUnrecognizedTypeNullValue(int i, PreparedStatement pstmt, Map<Integer, Class<?>> dataTypes)
            throws SQLException {

        pstmt.setString(i + 1, null);
        return true;
    }
}
