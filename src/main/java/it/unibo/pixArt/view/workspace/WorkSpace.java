package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.controller.workspace.WorkSpaceController;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.parser.GridPaneParser;
import it.unibo.pixArt.utilities.parser.PixelsParser;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.components.MenuItemBuilder;
import it.unibo.pixArt.view.components.PixelsPane;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static it.unibo.pixArt.utilities.variables.FXViewVariables.*;


public class WorkSpace extends AbstractFXView {

    @FXML
    public MenuBar menubar;
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
    private GridPaneParser paneParser;


    @Override
    public void init() {
        /*Set the first frame and the default tool. Add all the historyframes to the list view and add an event listener. */
        this.getWorkSpaceController().setFirstFrame();
        this.getWorkSpaceController().selectTool("PENCIL", colorPicker.getValue(), (int) toolSizeSlider.getValue());//select the default tool.

        this.frames.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                getWorkSpaceController().setCurrentFrame(frames.getSelectionModel().getSelectedIndex());
                updateView(getWorkSpaceController().getCurrentFrame());
                updateHistoryFrames();
            }
            
        });

        /*Init toolBox and toolSizeSlider, add event listeners.*/
        this.toolBox.getItems().addAll(this.getWorkSpaceController().getTools());
        this.toolBox.setValue("PENCIL");
        this.toolBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> getWorkSpaceController().selectTool(newValue, colorPicker.getValue(), (int) toolSizeSlider.getValue()));
        this.toolSizeLabel.setText("Size: " + Integer.toString((int) toolSizeSlider.getValue()));
        this.toolSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            getWorkSpaceController().selectTool(toolBox.getValue(), colorPicker.getValue(), (int) toolSizeSlider.getValue());
            this.toolSizeLabel.setText("Size: " + Integer.toString((int) toolSizeSlider.getValue()));
        });

        /*Init GridPane and add an event listeners to all the buttons. */
        paneParser = new GridPaneParser();
        pixelsParser = new PixelsParser();
        this.root.setCenter(new ImageView(IMAGE_PATH + MAIN_ICON));

        final var e = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final var button = (Button) event.getSource();
                getWorkSpaceController().setIsDrawing();
                color(GridPane.getColumnIndex(button), GridPane.getRowIndex(button), (Color) button.getBackground().getFills().get(0).getFill());
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
            if (getWorkSpaceController().getIsDrawing()) {
                final var button = (Button) event.getSource();
                color(GridPane.getColumnIndex(button), GridPane.getRowIndex(button), (Color) button.getBackground().getFills().get(0).getFill());
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
        this.menubar.getMenus().get(0).getItems().add(0, new MenuItemBuilder.Builder().setName("Save").setEventH(event -> {
            saveAndExit();
        }).build());
        this.menubar.getMenus().get(0).getItems().get(1).addEventHandler(ActionEvent.ACTION, event -> PageLoader.getInstance().switchPage(getStage(), Pages.MENU, getController().getModel()));
        
        updateView(getWorkSpaceController().getCurrentFrame());
        updateHistoryFrames();
    }

    @FXML
    private void discardMatrix() {
        final var grid = (GridPane) this.root.getCenter();
        grid.getChildren().forEach(b -> b.setStyle(FX_BACKGROUND_COLOR_START + "transparent" + ";" + FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH));
    }

    @FXML
    private void onColorChanged(final ActionEvent event) {
        this.getWorkSpaceController().selectTool(toolBox.getValue(), colorPicker.getValue(), (int) toolSizeSlider.getValue());
    }

    @FXML
    private void onUndoClicked() {
        this.updateView(this.getWorkSpaceController().getPreviousState());
    }

    @FXML
    private void onAddFrameClicked() {
        this.getWorkSpaceController().addNewFrame();
        updateView(this.getWorkSpaceController().getCurrentFrame());
        Platform.runLater(() -> updateHistoryFrames());
    }

    @FXML
    private void onDeleteClicked() {
        this.getWorkSpaceController().deleteCurrentFrame();
        updateView(this.getWorkSpaceController().getCurrentFrame());
        Platform.runLater(() -> updateHistoryFrames());
    }

    @FXML
    private void onAnimateClicked() {
        PageLoader.getInstance().switchPage(getStage(), Pages.ANIMATION, this.getController().getModel());
    }

    private void color(final int x, final int y, final Color color) {
        this.getWorkSpaceController().colorGrid(x, y, color);
    }

    public void updateView(final Set<Pixel> toUpdate) {
        final var center = (GridPane) this.root.getCenter();
        toUpdate.forEach(p -> center.getChildren().forEach(b -> {
            if (Objects.equals(GridPane.getColumnIndex(b), p.getPosition().getX()) && Objects.equals(GridPane.getRowIndex(b), p.getPosition().getY())) {
                b.setStyle(pixelsParser.parseColor(p.getColor()));
            }
        }));
    }

    private void saveAndExit() {
        List<String> choices = new ArrayList<>();
        choices.add("1");
        choices.add("4");
        choices.add("16");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("1", choices);
        dialog.setTitle("Scelta scala");
        dialog.setHeaderText(null);
        dialog.setContentText("Seleziona la scala:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            this.getWorkSpaceController().saveProject(Integer.parseInt(result.get()));
            PageLoader.getInstance().switchPage(getStage(), Pages.MENU, getController().getModel());
        }
    }

    public void updateHistoryFrames() {
        this.frames.getItems().setAll(this.getWorkSpaceController().getHistoryFrames().stream().map(e -> new ImageView(new Image("file:" + e.getPath()))).collect(Collectors.toList()));
        frames.getItems().forEach(i -> {
            i.fitHeightProperty().bind(frames.heightProperty());
            i.setFitWidth(200);
        });
    }

    private WorkSpaceController getWorkSpaceController() {
        return (WorkSpaceController) getController();
    }
}

