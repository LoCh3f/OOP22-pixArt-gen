package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class WorkSpace extends AbstractFXView {

    @FXML
    public MenuBar menubar;
    @FXML
    private BorderPane root;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ListView<ImageView> frames;

    private static final String BACKGROUND_COLOR = "-fx-background-color:pink";

    private static final String FX_BACKGROUND_COLOR = "-fx-background-color:";

    private static final String FX_BORDER_WIDTH = "-fx-border-width:1";


    @Override
    public void init() {
        root.setStyle(BACKGROUND_COLOR);
        final var e = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final var button = (Button) event.getSource();
                button.setStyle(FX_BACKGROUND_COLOR + colorPicker.getValue().toString().replace("0x", "#") + ";" + CenterPane.FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH);
                getController().getModel().getProject().getAllFrames().get(0).getPixels().forEach(p -> {
                    if (p.comparePixel(new ImplPixel(GridPane.getColumnIndex(button), GridPane.getRowIndex(button)))) {
                        p.setColor(new Color(colorPicker.getValue().getRed(), colorPicker.getValue().getGreen(), colorPicker.getValue().getBlue(), colorPicker.getValue().getOpacity()));
                    }
                });
            }
        };

        final var rows = this.getController().getModel().getProject().getAllFrames().get(0).getRows();
        final var columns = this.getController().getModel().getProject().getAllFrames().get(0).getColumns();

        final GridPane center = new CenterPane.GridPaneBuilder()
                .setColumns(columns).setRows(rows)
                .setGridLinesVisible(true)
                .setAction(e).build().get();
        center.alignmentProperty().set(Pos.CENTER);
        center.prefWidthProperty().bind(center.heightProperty());
        center.prefHeightProperty().bind(this.root.heightProperty().subtract(menubar.heightProperty().add(frames.heightProperty())));
        this.root.setCenter(center);


        this.menubar.getMenus().get(0).getItems().add(0, new MenuItemBuilder.Builder().setName("Save").setEventH(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                PageLoader.getInstance().switchPage(getStage(), Pages.MENU, getController().getModel());
            }
        }).build().get());


    }
}
