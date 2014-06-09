/*
 * Copyright 2014 Maarten  Mulders
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spotithrow.services;

import org.fourthline.cling.UpnpService;
import org.fourthline.cling.UpnpServiceImpl;
import org.fourthline.cling.model.message.header.STAllHeader;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.Icon;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.registry.DefaultRegistryListener;
import org.fourthline.cling.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spotithrow.model.DlnaDevice;

import javax.annotation.PreDestroy;
import java.net.URL;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

/**
 * Service that finds devices that can play a streamed song.
 */
public class PlayerService {
    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
    private UpnpService upnpService;

    public PlayerService() {
        upnpService = new UpnpServiceImpl();
        logger.info("Starting search for player devices...");
        upnpService.getControlPoint().search(new STAllHeader());
    }

    private URL findBestIcon(RemoteDevice device) {
        int bestWidth = -1;
        Icon bestIcon = null;

        for (Icon icon : device.findIcons()) {
            if (icon.getWidth() > bestWidth) {
                bestWidth = icon.getWidth();
                bestIcon = icon;
            }
        }

        logger.debug("Found icon {}", device.normalizeURI(bestIcon.getUri()));
        return device.normalizeURI(bestIcon.getUri());
    }

    public void addDlnaListener(final DlnaDeviceListener listener) {
        // Inform about already known devices.
        upnpService.getRegistry().getDevices().stream()
                .filter(d -> d instanceof RemoteDevice)
                .forEach(d -> listener.deviceAdded(new DlnaDevice(d.getDisplayString(), findBestIcon((RemoteDevice) d))));

        upnpService.getRegistry().addListener(new DefaultRegistryListener() {
            @Override
            public void deviceAdded(Registry registry, Device device) {
                if (device instanceof RemoteDevice) {
                    listener.deviceAdded(new DlnaDevice(device.getDisplayString(), findBestIcon((RemoteDevice) device)));
                }
            }

            @Override
            public void deviceRemoved(Registry registry, Device device) {
                if (device instanceof RemoteDevice) {
                    listener.deviceRemoved(new DlnaDevice(device.getDisplayString(), findBestIcon((RemoteDevice) device)));
                }
            }
        });
    };

    @PreDestroy
    public void shutdownService() {
        upnpService.shutdown();
    }
}