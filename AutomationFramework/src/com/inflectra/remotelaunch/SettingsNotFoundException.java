package com.inflectra.remotelaunch;

/**
 * Thrown when the settings file could not be loaded
 * @author Inflectra Corporation
 *
 */
public class SettingsNotFoundException extends RuntimeException
{
    public SettingsNotFoundException(String s)
    {
        super(s);
    }
}
