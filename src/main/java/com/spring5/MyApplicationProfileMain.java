/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import java.util.Arrays;
import java.util.List;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class MyApplicationProfileMain {
    private static final String MONGO_PROFILE = "mongo";
    private static final String FILE_PROFILE = "file";

    public void setupProfiles(ApplicationEnvironmentPreparedEvent event) {
        List<String> activeProfiles = Arrays.asList(event.getEnvironment().getActiveProfiles());
        boolean errorOccurred = false;

        if (activeProfiles.isEmpty()) {
            errorOccurred = true;
        } else if (!containsOperationModeProfile(activeProfiles)) {
            errorOccurred = true;
        }

        if (errorOccurred) {
            try {
                Thread.sleep(1000);
                System.exit(1);
            } catch (InterruptedException ex) {                
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public boolean containsOperationModeProfile(List<String> activeProfiles) {
        return activeProfiles.contains(MONGO_PROFILE)
                || activeProfiles.contains(FILE_PROFILE);
    }
}
