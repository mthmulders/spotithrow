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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import spotithrow.model.DlnaDevice;
import spotithrow.services.DlnaDeviceListener;
import spotithrow.services.PlayerService;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Presenter class to select a DLNA player.
 */
public class SelectPlayerPresenter implements Initializable, DlnaDeviceListener {
    @FXML TilePane playersTilePane;
    @Inject PlayerService playerService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerService.addDlnaListener(this);
    }

    @Override
    public void deviceAdded(DlnaDevice dlnaDevice) {
        URL iconUrl = dlnaDevice.getDisplayIcon();
        ImageView imageView = new ImageView(new Image(iconUrl.toString()));
        if (imageView.getFitWidth() > 120) {
            imageView.setFitWidth(120);
        }
        playersTilePane.getChildren().add(imageView);
    }

    @Override
    public void deviceRemoved(DlnaDevice dlnaDevice) {
        playersTilePane.getChildren().stream()
                .filter(node -> node instanceof Text)
                .filter(node -> ((Text) node).getText().equals(dlnaDevice.getDisplayName()))
                .forEach(node -> playersTilePane.getChildren().remove(node));
    }
}
