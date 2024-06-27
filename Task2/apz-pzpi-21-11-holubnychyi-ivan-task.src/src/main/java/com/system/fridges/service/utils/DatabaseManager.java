package com.system.fridges.service.utils;

public class DatabaseManager {

    /** Uses "mysqldump" command for backup */
    public boolean backupSuccessful(String backupPath) {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump",
                "-u", Constants.DATABASE_USERNAME,
                "-p" + Constants.DATABASE_PASSWORD,
                "--add-drop-table",
                "--databases", Constants.DATABASE_URL,
                "-r", backupPath
        );

        return createProcessSuccessful(processBuilder);
    }

    /** Uses "mysql" command for restoring */
    public boolean restoreSuccessful(String backupPath) {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql",
                "-u" + Constants.DATABASE_USERNAME,
                "-p" + Constants.DATABASE_PASSWORD,
                Constants.DATABASE_URL,
                "-e", "source " + backupPath
        );

        return createProcessSuccessful(processBuilder);
    }

    private boolean createProcessSuccessful(ProcessBuilder processBuilder) {
        try {
            Process process = processBuilder.start();
            int processComplete = process.waitFor();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
