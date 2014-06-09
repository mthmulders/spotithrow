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
import javafx.scene.layout.VBox;
import spotithrow.views.SelectPlayerView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Presenter class for the Spotithrow Application
 */
public class SpotithrowPresenter implements Initializable {
    @FXML
    private VBox leftSideBar;

    private SelectPlayerPresenter selectPlayerPresenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SelectPlayerView selectPlayerView = new SelectPlayerView();
        selectPlayerPresenter = (SelectPlayerPresenter) selectPlayerView.getPresenter();
        leftSideBar.getChildren().add(selectPlayerView.getView());
    }
}
