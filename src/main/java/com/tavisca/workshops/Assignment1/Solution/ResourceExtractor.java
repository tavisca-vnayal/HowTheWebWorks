package com.tavisca.workshops.Assignment1.Solution;

import java.io.File;

public class ResourceExtractor {
    private String resourceName;

    public void extractResourceFromHeader(String header) {
        if(header.split(" ")[1].length() == 1) {
            this.resourceName = "home.html";
        }
        else{
            this.resourceName = header.split(" ")[1].split("/")[1];
        }
    }

    public String getResourceName() {
        return resourceName;
    }

    public boolean isResourceAvailable() {
        return (new File(this.resourceName)).exists();
    }

}
