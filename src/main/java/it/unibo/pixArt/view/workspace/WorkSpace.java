package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.components.BorderParent;
import it.unibo.pixArt.view.components.CenterPane;
import it.unibo.pixArt.view.components.MenuItemBuilder;
import it.unibo.pixArt.view.components.StageDistribution;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    private BorderPane leftPane;
    @FXML
    private ImageView templateView;
    @FXML
    private Button swapper;
    @FXML
    private BorderPane rightPane;


    private Logic logics;


    @Override
    public void init() {
        this.root.setCenter(new ImageView(IMAGE_PATH + "mainIcon.png"));
        this.logics = new WorkSpaceLogic(this.getController().getModel().getProject().getAllFrames().get(0).getRows(),
                this.getController().getModel().getProject().getAllFrames().get(0).getColumns());
        root.setStyle(BACKGROUND_COLOR);
        root.setPadding(new Insets(5));
        colorPicker.prefWidthProperty().bind(rightPane.widthProperty());
        swapper.prefWidthProperty().bind(rightPane.widthProperty());
        rightPane.setRight(new Button("Start\n(Beta)"));
        rightPane.setLeft(new Button("Stop\n(Beta)"));
        final var e = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final var button = (Button) event.getSource();
                logics.changeState();
                button.setStyle(FX_BACKGROUND_COLOR_START + colorPicker.getValue().toString().replace("0x", "#") + ";" + CenterPane.FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH);
                getController().getModel().getProject().getAllFrames().get(0).getPixels().forEach(p -> {
                    if (p.comparePixel(new ImplPixel(GridPane.getColumnIndex(button), GridPane.getRowIndex(button)))) {
                        p.setColor(new Color(colorPicker.getValue().getRed(), colorPicker.getValue().getGreen(), colorPicker.getValue().getBlue(), colorPicker.getValue().getOpacity()));
                    }
                    logics.changeState();
                });
            }
        };

        final var rows = this.getController().getModel().getProject().getAllFrames().get(0).getRows();
        final var columns = this.getController().getModel().getProject().getAllFrames().get(0).getColumns();

        final GridPane center = new CenterPane.GridPaneBuilder()
                .setColumns(columns).setRows(rows)
                .setGridLinesVisible(true)
                .setAction(e).build().get();
        this.root.setCenter(center);
        root.getChildren().forEach(c -> setStyle(c, "yellow"));

        center.getChildren().forEach(b -> b.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if (logics.isDrawing()) {
                final var button = (Button) event.getSource();
                button.setStyle(FX_BACKGROUND_COLOR_START + colorPicker.getValue().toString().replace("0x", "#") + ";" + CenterPane.FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH);
            }
        }));

        center.alignmentProperty().set(Pos.CENTER);
        center.prefWidthProperty().bind(center.heightProperty());
        center.prefHeightProperty().bind(this.root.heightProperty().subtract(menubar.heightProperty().add(frames.heightProperty())));
        rightPane.getChildren().forEach(c -> setStyle(c, "orange"));


        this.menubar.getMenus().get(0).getItems().add(0, new MenuItemBuilder.Builder().setName("Save").setEventH(event -> PageLoader.getInstance().switchPage(getStage(), Pages.MENU, getController().getModel())).build().get());

        final Stage secondStage = new StageDistribution.ParallelStage(new BorderParent.Builder().setCenter(new ImageView()).build().get(), "AbilityTester", new Image(IMAGE_PATH + MAIN_ICON));
        final var secondRoot = (BorderPane) secondStage.getScene().getRoot();
        final var testerImageView = (ImageView) secondRoot.getCenter();
        testerImageView.setImage(new Image(IMAGE_PATH + IMAGE_VERY_GOOD));
        this.menubar.getMenus().get(0).getItems().add(0, new MenuItemBuilder.Builder().setName("AbilityTester").setEventH(event -> secondStage.show()).build().get());


    }

    private void setStyle(final Node node, final String color) {
        node.setStyle(FX_BACKGROUND_COLOR_START + color);
    }

    @FXML
    private void changeImage() {
        this.templateView.setImage(new Image(logics.getImagePath()));
    }
}
