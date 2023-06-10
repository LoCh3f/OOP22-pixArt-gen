package it.unibo.pixart.view.workspace;

import it.unibo.pixart.controller.workspace.WorkSpaceController;
import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.utilities.parser.PixelsParser;
import it.unibo.pixart.view.AbstractFXView;
import it.unibo.pixart.view.components.MenuItemBuilder;
import it.unibo.pixart.view.components.PixelsPane;
import it.unibo.pixart.view.pages.Pages;
import it.unibo.pixart.view.pages.SceneManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.stream.Collectors;

import static it.unibo.pixart.utilities.variables.FXViewVariables.FX_BORDER_COLOR;
import static it.unibo.pixart.utilities.variables.FXViewVariables.FX_BORDER_WIDTH;

/**
 * View where the user edits his/her projects.
 */
public final class WorkSpace extends AbstractFXView {
    private static final int MAX_WIDTH = 200;
    @FXML
    private MenuBar menubar;
    @FXML
    private BorderPane root;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ListView<ImageView> frames;

    @FXML
    private ChoiceBox<String> toolBox;
    @FXML
    private Slider toolSizeSlider;
    @FXML
    private VBox leftPane;
    @FXML
    private Label toolSizeLabel;

    private PixelsParser pixelsParser;

    /**
     * Method to discard the matrix.
     */
    @FXML
    public void discardMatrix() {
        getWorkSpaceController().resetCurrentFrame();
    }

    /**
     * Method to change selected color.
     */
    @FXML
    public void onColorChanged() {
        this.getWorkSpaceController().selectTool(toolBox.getValue(), colorPicker.getValue(), (int) toolSizeSlider.getValue());
    }

    /**
     * Method to undo actions.
     */
    @FXML
    public void onUndoClicked() {
        this.updateView(this.getWorkSpaceController().getPreviousState());
    }

    /**
     * Method to add a new frame.
     */
    @FXML
    public void onAddFrameClicked() {
        this.getWorkSpaceController().addNewFrame();
        updateView(this.getWorkSpaceController().getCurrentFrame());
        Platform.runLater(this::updateHistoryFrames);
    }

    /**
     * Method to delete a frame.
     */
    @FXML
    public void onDeleteClicked() {
        this.getWorkSpaceController().deleteCurrentFrame();
        updateView(this.getWorkSpaceController().getCurrentFrame());
        Platform.runLater(this::updateHistoryFrames);
    }

    /**
     * Method to switch to Animation view.
     */
    @FXML
    public void onAnimateClicked() {
        SceneManager.getInstance().switchPage(getStage(), Pages.ANIMATION, this.getController().getModel());
    }

    @Override
    public void init() {
        /*Set the first frame and the default tools. Add all the history frames to the list view and add an event listener. */
        this.getWorkSpaceController().setFirstFrame();
        this.getWorkSpaceController().selectTool("PENCIL", colorPicker.getValue(), (int) toolSizeSlider.getValue());

        this.frames.setOnMouseClicked(event -> {
            getWorkSpaceController().setCurrentFrame(frames.getSelectionModel().getSelectedIndex());
            updateView(getWorkSpaceController().getCurrentFrame());
            updateHistoryFrames();
        });

        /*Init toolBox and toolSizeSlider, add event listeners.*/
        this.toolBox.getItems().addAll(this.getWorkSpaceController().getTools());
        this.toolBox.setValue("PENCIL");

        this.toolBox.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> getWorkSpaceController()
                        .selectTool(newValue, colorPicker.getValue(), (int) toolSizeSlider.getValue()));

        this.toolSizeLabel.setText("Size: " + (int) toolSizeSlider.getValue());
        this.toolSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            getWorkSpaceController().selectTool(toolBox.getValue(), colorPicker.getValue(), (int) toolSizeSlider.getValue());
            this.toolSizeLabel.setText("Size: " + (int) toolSizeSlider.getValue());
        });

        /*Init GridPane and add an event listeners to all the buttons. */

        pixelsParser = new PixelsParser();

        final var e = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final var button = (Button) event.getSource();
                getWorkSpaceController().setIsDrawing();

                color(GridPane.getColumnIndex(button),
                        GridPane.getRowIndex(button),
                        (Color) button.getBackground().getFills().get(0).getFill());

                getWorkSpaceController().setIsDrawing();
            }
        };
        root.getChildren().forEach(c -> c.setStyle(FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH));
        leftPane.getChildren().forEach(c -> c.setStyle(FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH));
        final var rows = this.getController().getModel().getProject().getAllFrames().get(0).getRows();
        final var columns = this.getController().getModel().getProject().getAllFrames().get(0).getColumns();
        final GridPane center = new PixelsPane.GridPaneBuilder()
                .setColumns(columns).setRows(rows)
                .setGridLinesVisible(true)
                .setAction(e).build();
        this.root.setCenter(center);

        center.getChildren().forEach(b -> b.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if (getWorkSpaceController().isDrawing()) {
                final var button = (Button) event.getSource();
                color(GridPane.getColumnIndex(button),
                        GridPane.getRowIndex(button),
                        (Color) button.getBackground().getFills().get(0).getFill());
            }
        }));

        center.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                getWorkSpaceController().setIsDrawing();
            }
        });

        center.alignmentProperty().set(Pos.CENTER);
        center.prefWidthProperty().bind(root.widthProperty().subtract(leftPane.widthProperty().add(frames.widthProperty())));
        center.prefHeightProperty().bind(this.root.heightProperty().subtract(menubar.heightProperty()));

        /*Add event listeners to the menu items */
        this.menubar.getMenus().get(0).getItems()
                .add(0, new MenuItemBuilder.Builder().setName("Save").setEventH(event -> saveAndExit()).build());

        this.menubar.getMenus().get(0).getItems().get(1)
                .addEventHandler(ActionEvent.ACTION, event -> SceneManager.getInstance()
                        .switchPage(getStage(), Pages.MENU, getController().getModel()));

        updateView(getWorkSpaceController().getCurrentFrame());
        updateHistoryFrames();
    }


    private void color(final int x, final int y, final Color color) {
        this.getWorkSpaceController().colorGrid(x, y, color);
    }

    /**
     * Method to update the gridpane and the matrix while coloring.
     *
     * @param toUpdate the pixels to update.
     */
    public void updateView(final Set<Pixel> toUpdate) {
        final var center = (GridPane) this.root.getCenter();
        toUpdate.forEach(p -> center.getChildren().forEach(b -> {
            if (Objects.equals(GridPane.getColumnIndex(b), p.getPosition().getX())
                    && Objects.equals(GridPane.getRowIndex(b), p.getPosition().getY())) {
                b.setStyle(pixelsParser.parseColor(p.getColor()));
            }
        }));
    }

    private void saveAndExit() {
        final List<String> choices = new ArrayList<>();
        choices.add("1");
        choices.add("4");
        choices.add("16");

        final ChoiceDialog<String> dialog = new ChoiceDialog<>("1", choices);
        dialog.setTitle("Choose the scale");
        dialog.setHeaderText(null);
        dialog.setContentText("Select the scale:");

        final Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            this.getWorkSpaceController().saveProject(Integer.parseInt(result.get()));
            SceneManager.getInstance().switchPage(getStage(), Pages.MENU, getController().getModel());
        }
    }

    /**
     * Method to update the list of images/historyframes.
     */
    public void updateHistoryFrames() {
        this.frames.getItems().setAll(this.getWorkSpaceController().getHistoryFrames()
                .stream()
                .map(e -> new ImageView(new Image("file:" + e.getPath())))
                .collect(Collectors.toList()));
        frames.getItems().forEach(i -> {
            i.fitHeightProperty().bind(frames.heightProperty());
            i.setFitWidth(MAX_WIDTH);
        });
    }

    private WorkSpaceController getWorkSpaceController() {
        return (WorkSpaceController) getController();
    }
}

