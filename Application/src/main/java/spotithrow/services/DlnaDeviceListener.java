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

import spotithrow.model.DlnaDevice;

/**
 * Interface for objects that are notified when a DLNA player is discovered or disappears.
 */
public interface DlnaDeviceListener {
    /** Called when the system discovers a new DLNA Device */
    void deviceAdded(DlnaDevice dlnaDevice);

    /** Called when a DLNA Device disappears. */
    void deviceRemoved(DlnaDevice dlnaDevice);
}
