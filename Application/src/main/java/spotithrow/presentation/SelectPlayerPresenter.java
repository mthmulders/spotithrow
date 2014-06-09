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

package spotithrow.presentation;

import javafx.fxml.Initializable;
import spotithrow.services.PlayerService;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Presenter class to select a DLNA player.
 */
public class SelectPlayerPresenter implements Initializable {
    @Inject private PlayerService playerService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Somehow populate with a list of devices that we can stream to.
    }
}
