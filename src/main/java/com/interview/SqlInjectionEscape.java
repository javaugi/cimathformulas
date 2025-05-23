/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.interview;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class SqlInjectionEscape {

    private static final Logger log = LoggerFactory.getLogger(SqlInjectionEscape.class);

    public static final class ESCAPE_MAP_CLASS extends HashMap {

        public ESCAPE_MAP_CLASS() {
            super.put("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/? ]", "");
            super.put("\\\\", "\\\\\\\\");
            super.put("\\n", "\\\\n");
            super.put("\\r", "\\\\r");
            super.put("\\t", "\\\\t");
            super.put("\\00", "\\\\0");
            super.put("'", "\\\\'");
            super.put("\\\"", "\\\\\"");
            super.put("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/?\\\\\"' ]", "");
        }
    }

    public static final ESCAPE_MAP_CLASS ESCAPE_MAP = new ESCAPE_MAP_CLASS();

    /**
     * Escape string to protected against SQL Injection
     *
     * You must add a single quote ' around the result of this function for
     * data, or a backtick ` around table and row identifiers. If this function
     * returns null than the result should be changed to "NULL" without any
     * quote or backtick.
     *
     * @param link
     * @param str
     * @return
     * @throws Exception
     */
    public static String mysql_real_escape_string(java.sql.Connection link, String str)
            throws Exception {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }

        String key;
        Set keys = ESCAPE_MAP.keySet();
        Iterator<String> iter = keys.iterator();
        while (iter.hasNext()) {
            key = iter.next();
            str = str.replaceAll(key, (String) ESCAPE_MAP.get(key));

            if (str == null || str.trim().isEmpty()) {
                return null;
            }
        }


        /*
        if (str.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/? ]", "").length() < 1) {
            return str;
        }
        String clean_string = str;
        clean_string = clean_string.replaceAll("\\\\", "\\\\\\\\");
        clean_string = clean_string.replaceAll("\\n", "\\\\n");
        clean_string = clean_string.replaceAll("\\r", "\\\\r");
        clean_string = clean_string.replaceAll("\\t", "\\\\t");
        clean_string = clean_string.replaceAll("\\00", "\\\\0");
        clean_string = clean_string.replaceAll("'", "\\\\'");
        clean_string = clean_string.replaceAll("\\\"", "\\\\\"");

        if (clean_string.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/?\\\\\"' ]",
                "").length() < 1) {
            return clean_string;
        }
        // */
        java.sql.Statement stmt = link.createStatement();
        String qry = "SELECT QUOTE('" + str + "')";

        stmt.executeQuery(qry);
        java.sql.ResultSet resultSet = stmt.getResultSet();
        resultSet.first();
        String r = resultSet.getString(1);
        return r.substring(1, r.length() - 1);
    }

    /**
     * Escape data to protected against SQL Injection
     *
     * @param link
     * @param str
     * @return
     * @throws Exception
     */
    public static String quoteEscapeData(java.sql.Connection link, String str)
            throws Exception {
        if (str == null || str.trim().isEmpty()) {
            return "NULL";
        }
        return "'" + mysql_real_escape_string(link, str) + "'";
    }

    /**
     * Escape identifier to protected against SQL Injection
     *
     * @param link
     * @param str
     * @return
     * @throws Exception
     */
    public static String nameQuoteEscapeIdentifier(java.sql.Connection link, String str)
            throws Exception {
        if (str == null || str.trim().isEmpty()) {
            return "NULL";
        }
        return "`" + mysql_real_escape_string(link, str) + "`";
    }
}
