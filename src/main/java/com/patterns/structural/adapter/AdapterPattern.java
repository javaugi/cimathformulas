/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Adapter pattern works as a bridge between two incompatible interfaces. This
 * type of design pattern comes under structural pattern as this pattern
 * combines the capability of two independent interfaces.
 *
 * This pattern involves a single class which is responsible to join
 * functionalities of independent or incompatible interfaces. A real life
 * example could be a case of card reader which acts as an adapter between
 * memory card and a laptop. You plugin the memory card into card reader and
 * card reader into the laptop so that memory card can be read via laptop.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Step 1
Create interfaces for Media Player and Advanced Media Player.

Step 2
Create concrete classes implementing the AdvancedMediaPlayer interface.

Step 3
Create adapter class implementing the MediaPlayer interface.

Step 4
Create concrete class implementing the MediaPlayer interface.

Step 5
Use the AudioPlayer to play different types of audio formats.
 */
public class AdapterPattern {

    private static final Logger log = LoggerFactory.getLogger(AdapterPattern.class);

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
