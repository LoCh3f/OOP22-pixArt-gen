package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.controller.workspace.WorkSpaceController;
import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.GridPaneParser;
import it.unibo.pixArt.utilities.PixelsParser;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.components.BorderParent;
import it.unibo.pixArt.view.components.MenuItemBuilder;
import it.unibo.pixArt.view.components.PixelsPane;
import it.unibo.pixArt.view.components.StageDistribution;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Set;

import javafx.beans.value.ChangeListener;

import static it.unibo.pixArt.utilities.FXStyleVariable.*;


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
    private ImageView templateView;
    @FXML
    private Button swapper;
    @FXML
    private BorderPane rightPane;

    @FXML
    private ChoiceBox<String> toolBox;

    @FXML
    private Slider toolSizeSlider;

    private PixelsParser pixelsParser;
    private GridPaneParser paneParser;
    private Logic logics;


    @Override
    public void init() {
        this.getWorkSpaceController().setCurrentFrame(0);//Set the first frame
        this.getWorkSpaceController().selectTool("PENCIL", colorPicker.getValue(),(int) toolSizeSlider.getValue());//select the default tool.

        this.toolBox.getItems().addAll(this.getWorkSpaceController().getTools());//Init toolBox and add event listeneers
        this.toolBox.setValue("PENCIL");
        this.toolBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                getWorkSpaceController().selectTool(newValue, colorPicker.getValue(),(int) toolSizeSlider.getValue());
            }
            
        });

        this.toolSizeSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                final int newSize = (int) toolSizeSlider.getValue();
                getWorkSpaceController().selectTool(toolBox.getValue(), colorPicker.getValue(), newSize);
            }
            
        });
        paneParser = new GridPaneParser();
        pixelsParser = new PixelsParser();
        this.root.setCenter(new ImageView(IMAGE_PATH + "mainIcon.png"));
        this.logics = new WorkSpaceLogic(this.getController().getModel().getProject().getAllFrames().get(0).getRows(),
                this.getController().getModel().getProject().getAllFrames().get(0).getColumns());
        colorPicker.prefWidthProperty().bind(rightPane.widthProperty());
        swapper.prefWidthProperty().bind(rightPane.widthProperty());

        final Stage secondStage = new StageDistribution.ParallelStage(new BorderParent.Builder().setCenter(new ImageView()).build(), "AbilityTester", new Image(IMAGE_PATH + MAIN_ICON));
        final var secondRoot = (BorderPane) secondStage.getScene().getRoot();
        final var testerImageView = (ImageView) secondRoot.getCenter();
        testerImageView.setImage(new Image(IMAGE_PATH + IMAGE_VERY_BAD));

        final var e = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final var button = (Button) event.getSource();
                logics.changeState();
                button.setStyle(FX_BACKGROUND_COLOR_START + colorPicker.getValue().toString().replace("0x", "#") + ";" + FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH);
                getController().getModel().getProject().getAllFrames().get(0).getPixels().forEach(p -> {
                    if (p.comparePixel(new ImplPixel(GridPane.getColumnIndex(button), GridPane.getRowIndex(button)))) {
                        p.setColor(new Color(colorPicker.getValue().getRed(), colorPicker.getValue().getGreen(), colorPicker.getValue().getBlue(), colorPicker.getValue().getOpacity()));
                    }
                    logics.changeState();
                });
            }
        };
        rightPane.getChildren().forEach(n -> n.setStyle(FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH));
        menubar.setStyle(FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH);

        final var rows = this.getController().getModel().getProject().getAllFrames().get(0).getRows();
        final var columns = this.getController().getModel().getProject().getAllFrames().get(0).getColumns();

        final GridPane center = new PixelsPane.GridPaneBuilder()
                .setColumns(columns).setRows(rows)
                .setGridLinesVisible(true)
                .setAction(e).build();
        this.root.setCenter(center);


        center.getChildren().forEach(b -> b.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if (logics.isDrawing()) {
                final var button = (Button) event.getSource();
                button.setStyle(FX_BACKGROUND_COLOR_START + colorPicker.getValue().toString().replace("0x", "#") + ";" + FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH);
            }
            if (secondStage.isShowing()) {
                testerImageView.setImage(new Image(logics.test(paneParser.apply(center))));
            }
        }));

        center.alignmentProperty().set(Pos.CENTER);
        center.prefWidthProperty().bind(center.heightProperty());
        center.prefHeightProperty().bind(this.root.heightProperty().subtract(menubar.heightProperty().add(frames.heightProperty())));


        this.menubar.getMenus().get(0).getItems().add(0, new MenuItemBuilder.Builder().setName("Save").setEventH(event -> PageLoader.getInstance().switchPage(getStage(), Pages.MENU, getController().getModel())).build());


        this.menubar.getMenus().get(0).getItems().add(0, new MenuItemBuilder.Builder().setName("AbilityTester").setEventH(event -> secondStage.show()).build());


    }

    public void updateView(final Set<Pixel> toUpdate) {
        final var center = (GridPane) this.root.getCenter();
        toUpdate.forEach(p -> center.getChildren().forEach(b -> {
            if (GridPane.getColumnIndex(b) == p.getPosition().getX() && GridPane.getRowIndex(b) == p.getPosition().getY()) {
                b.setStyle(pixelsParser.parseColor(p.getColor()));
            }
        }));
    }


    @FXML
    private void discardMatrix() {
        final var grid = (GridPane) this.root.getCenter();
        grid.getChildren().forEach(b -> b.setStyle(FX_BACKGROUND_COLOR_START + "transparent" + ";" + FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH));
    }

    @FXML
    private void changeImage() {
        this.templateView.setImage(new Image(logics.getImagePath()));
    }

    @FXML
    private void onColorChanged(final ActionEvent event) {
        this.getWorkSpaceController().selectTool(toolBox.getValue(), colorPicker.getValue(),(int) toolSizeSlider.getValue());
    }

    private void color(final int x, int y) {
        this.getWorkSpaceController().colorGrid(x, y);
    }

   /*  @FXML
    private void rubberActivate() {
        colorPicker.setValue(Color.TRANSPARENT);
    }*/

    private WorkSpaceController getWorkSpaceController() {
        return (WorkSpaceController) getController();
    }
}

