package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

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


    private Logic logics;


    @Override
    public void init() {
        this.root.setCenter(new ImageView("image/mainIcon.png"));
        this.logics = new WorkSpaceLogic(this.getController().getModel().getProject().getAllFrames().get(0).getRows(),
                this.getController().getModel().getProject().getAllFrames().get(0).getColumns());
        root.setStyle(BACKGROUND_COLOR);
        getStage().setFullScreen(true);
        root.setPadding(new Insets(5));
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

        center.getChildren().forEach(b -> b.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if (logics.isDrawing()) {
                final var button = (Button) event.getSource();
                button.setStyle(FX_BACKGROUND_COLOR_START + colorPicker.getValue().toString().replace("0x", "#") + ";" + CenterPane.FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH);
            }
        }));

        center.alignmentProperty().set(Pos.CENTER);
        center.prefWidthProperty().bind(center.heightProperty());
        center.prefHeightProperty().bind(this.root.heightProperty().subtract(menubar.heightProperty().add(frames.heightProperty())));


        this.menubar.getMenus().get(0).getItems().add(0, new MenuItemBuilder.Builder().setName("Save").setEventH(event -> PageLoader.getInstance().switchPage(getStage(), Pages.MENU, getController().getModel())).build().get());

    }
}
