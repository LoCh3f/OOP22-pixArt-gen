package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.controller.workspace.WorkSpaceController;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.FileHandler;
import it.unibo.pixArt.utilities.parser.GridPaneParser;
import it.unibo.pixArt.utilities.parser.PixelsParser;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.components.MenuItemBuilder;
import it.unibo.pixArt.view.components.PixelsPane;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
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

import java.io.IOException;
import java.util.Set;

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
    private PixelsParser pixelsParser;
    private GridPaneParser paneParser;


    @Override
    public void init() {
        this.getWorkSpaceController().setCurrentFrame(0);//Set the first frame
        this.getWorkSpaceController().selectTool("PENCIL", colorPicker.getValue(), (int) toolSizeSlider.getValue());//select the default tool.
        this.frames.getItems().addAll(this.getWorkSpaceController().getHistoryFrames().stream().map(e -> new ImageView(new Image(e.getPath()))).toList());
        this.frames.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            getWorkSpaceController().setCurrentFrame(frames.getSelectionModel().getSelectedIndex());
            updateView(getWorkSpaceController().getCurrentFrame());
        });
        this.toolBox.getItems().addAll(this.getWorkSpaceController().getTools());//Init toolBox and add event listeners

        this.toolBox.setValue("PENCIL");
        this.toolBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> getWorkSpaceController().selectTool(newValue, colorPicker.getValue(), (int) toolSizeSlider.getValue()));

        this.toolSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            final int newSize = (int) toolSizeSlider.getValue();
            getWorkSpaceController().selectTool(toolBox.getValue(), colorPicker.getValue(), newSize);
        });
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
        frames.getItems().forEach(i -> {
            i.fitHeightProperty().bind(frames.heightProperty());
            i.setFitWidth(200);
        });


        this.menubar.getMenus().get(0).getItems().add(0, new MenuItemBuilder.Builder().setName("Save").setEventH(event -> PageLoader.getInstance().switchPage(getStage(), Pages.MENU, getController().getModel()))
                .setEventH(event -> {
                    try {
                        FileHandler.getInstance().fromProjectToJson(this.getController().getModel().getProject(), this.getController().getModel().getUser());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }).build());


        updateView(getWorkSpaceController().getCurrentFrame());
       
        
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
        this.updateView(this.getWorkSpaceController().addNewFrame());
        this.frames.getItems().setAll(this.getWorkSpaceController().getHistoryFrames().stream().map(e -> new ImageView(new Image(e.getPath()))).toList());
        frames.getItems().forEach(i -> {
            i.fitHeightProperty().bind(frames.heightProperty());
            i.setFitWidth(200);
        });
    }
    
    @FXML
    private void onDeleteClicked() {
        this.getWorkSpaceController().deleteCurrentFrame(); 
        this.frames.getItems().setAll(this.getWorkSpaceController().getHistoryFrames().stream().map(e -> new ImageView(new Image(e.getPath()))).toList());
        frames.getItems().forEach(i -> {
            i.fitHeightProperty().bind(frames.heightProperty());
            i.setFitWidth(200);
        });
    }

    private void color(final int x, final int y, final Color color) {
        this.getWorkSpaceController().colorGrid(x, y, color);
    }

    public void updateView(final Set<Pixel> toUpdate) {
        final var center = (GridPane) this.root.getCenter();
        toUpdate.forEach(p -> center.getChildren().forEach(b -> {
            if (GridPane.getColumnIndex(b) == p.getPosition().getX() && GridPane.getRowIndex(b) == p.getPosition().getY()) {
                b.setStyle(pixelsParser.parseColor(p.getColor()));
            }
        }));
    }

    private WorkSpaceController getWorkSpaceController() {
        return (WorkSpaceController) getController();
    }
}

