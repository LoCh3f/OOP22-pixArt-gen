package it.unibo.pixArt.view.game;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.unibo.pixArt.controller.game.GameController;
import it.unibo.pixArt.model.game.GameType;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.timer.TimerThread;


import it.unibo.pixArt.utilities.parser.GridPaneParser;
import it.unibo.pixArt.utilities.parser.PixelsParser;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.abilitytest.Logic;
import it.unibo.pixArt.view.abilitytest.TesterEnum;
import it.unibo.pixArt.view.abilitytest.TesterLogic;
import it.unibo.pixArt.view.components.PixelsPane;
import it.unibo.pixArt.view.components.StageDistribution;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import static it.unibo.pixArt.utilities.variables.FXViewVariables.*;

public class GameView extends AbstractFXView {

    @FXML
    private Label timer;

    @FXML
    private BorderPane root;

    @FXML
    private Label pixelsField;

    @FXML
    private Label colorField;

    @FXML
    private VBox colorBtnBox;

    private Color selectedColor;
    private Logic tester;
    private Stage secondStage;


    private List<Color> availableColors;

    @FXML
    public void onMenuClick() {
        if (this.getGameController().getTimer().isRunning()) {
            this.getGameController().getTimer().stop();
        }
        PageLoader.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
    }

    @Override
    public void init() {

        this.getGameController().setColorStack();
        final GridPane center = new PixelsPane.GridPaneBuilder()
                .setColumns(this.getGameController().getFrameSize())
                .setRows(this.getGameController().getFrameSize())
                .setGridLinesVisible(true)
                .setAction(getEvent())
                .build();
        this.root.setCenter(center);
        this.colorBtnBox.getChildren().addAll(createColorPane());



        center.getChildren().forEach(b -> b.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if (getGameController().getIsDrawing()) {
                colorButton(event);
            }
        }));

        center.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                getGameController().setIsDrawing();
            }
        });

        if (getGameController().getType() == GameType.COLORBOOK) {
            this.getGameController().getTimer().start();
            new TimerThread(this.getGameController().getTimer(), this::onTimeFinish, this::OnTimeUpdate).start();
            associateButton(center);

        } else {
            tester = new TesterLogic();
            final var root = new HBox();
            secondStage = new StageDistribution
                    .ParallelStage(root,"AbilityTester",new Image(IMAGE_PATH + MAIN_ICON));
            final var parallelGrid = new PixelsPane.GridPaneBuilder()
                    .setColumns(center.getColumnCount()).setRows(center.getRowCount())
                    .setAction(event -> {
                                final var b = (Button) event.getSource();
                                b.setStyle(new PixelsParser().parseColor(selectedColor));
                    }).setGridLinesVisible(true).build();
            final var incredibleView = new ImageView(new Image(tester.test(new GridPaneParser().apply(parallelGrid),
                    getGameController().getModel().getProject().getAllFrames().get(0).getPixels())));
                    root.getChildren().add(0,incredibleView);
            root.getChildren().add(1,parallelGrid);
            incredibleView.fitHeightProperty().bind(root.heightProperty());
            incredibleView.fitWidthProperty().bind(root.widthProperty().divide(2));
            parallelGrid.prefHeightProperty().bind(root.heightProperty());
            parallelGrid.prefWidthProperty().bind(root.widthProperty().divide(2));
            parallelGrid.getChildren().forEach(b -> b.addEventHandler(ActionEvent.ACTION,event -> {
                incredibleView.setImage(new Image(tester.test(new GridPaneParser().apply(parallelGrid),
                        getGameController().getModel().getProject().getAllFrames().get(0).getPixels())));
            }));


            secondStage.show();


        }
        setSelectedColor(availableColors.get(0));
    }

    private void OnTimeUpdate() {
        Platform.runLater(() -> {
            this.timer.setText(timeToString(this.getGameController().getTimer().getRemainingTime()));
        });
    }

    private void onTimeFinish() {
        Platform.runLater(this::onGameFinish);
    }

    private void onGameFinish() {
        this.getGameController().getTimer().stop();
        this.gameOverPopUp();
        this.root.getCenter().setDisable(true);
    }

    private void gameOverPopUp() {
        String percentage = String.format("%.2f", this.getGameController().getPercentage());
        final GameOverPopUp gameOverPopUp = new GameOverPopUp(percentage);
        gameOverPopUp.onHomeClick(() -> {
            gameOverPopUp.close();
            Platform.runLater(() -> PageLoader.getInstance().switchPage(this.getStage(), Pages.MENU, this.getController().getModel()));
        });
        gameOverPopUp.onNewGameClick(() -> {
            gameOverPopUp.close();
            Platform.runLater(() -> PageLoader.getInstance().switchPage(this.getStage(), Pages.GAMESETUP, this.getController().getModel()));
        });
        gameOverPopUp.show();
    }

    private String timeToString(final double remainingTime) {
        double minutes = remainingTime / 60;
        double seconds = remainingTime % 60;
        return String.format("%02d:%02d", (int) minutes, (int) seconds);
    }

    /*Try to use streams */
    private List<Button> createColorPane() {
        this.availableColors = getGameController().getColorStack().entrySet()
                .stream()
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        final List<Button> btnList = new LinkedList<>();
        for (var elem : availableColors) {
            final Button btn = new Button();
            btn.setText(Integer.toString(availableColors.indexOf(elem)));
            btn.setStyle("-fx-background-color: #" + elem.toString().substring(2) + ";" + FX_BORDER_WIDTH + ";" + FX_BORDER_COLOR);
            btn.addEventHandler(MouseEvent.MOUSE_CLICKED, h -> setSelectedColor(elem));
            btn.setMinWidth(200);
            btnList.add(btn);
        }
        return btnList;
    }

    private void associateButton(final GridPane grid) {
        for (var btn : grid.getChildren()) {
            for (var entry : getGameController().getColorStack().entrySet()) {
                for (var pixel : entry.getValue()) {
                    if (pixel.getPosition().getX() == GridPane.getColumnIndex(btn) && pixel.getPosition().getY() == GridPane.getRowIndex(btn)) {
                        final int number = this.availableColors.indexOf(entry.getKey());
                        ((Button) btn).setText(Integer.valueOf(number).toString());
                    }
                }
            }
        }
    }

    private void setSelectedColor(final Color color) {
        this.selectedColor = color;
        this.colorField.setText("Selected Color: " + color.toString());
        setPixelsLeft();
    }

    private void setPixelsLeft() {
        final String numPixelsLeft = Integer.valueOf(this.getGameController().getColorStack().get(this.selectedColor).size()).toString();
        this.pixelsField.setText("Pixels left: " + numPixelsLeft);
    }

    private EventHandler<ActionEvent> getEvent() {
        final var e = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                colorButton(event);
            }
        };
        return e;
    }

    private void colorButton(final Event event) {
        final var button = (Button) event.getSource();
        boolean checkPixel = getGameController().checkPixel(GridPane.getColumnIndex(button), GridPane.getRowIndex(button), selectedColor);
        if (checkPixel) {
            button.setStyle("-fx-background-color: #" + selectedColor.toString().substring(2) + ";" + FX_BORDER_WIDTH + ";" + FX_BORDER_COLOR);
            button.setText("");
            setPixelsLeft();
        }
        if (getGameController().colorStackIsEmpty()) {
            onGameFinish();
        }
    }

    private GameController getGameController() {
        return (GameController) this.getController();
    }

}
