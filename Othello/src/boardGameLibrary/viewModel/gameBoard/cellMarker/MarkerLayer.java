package boardGameLibrary.viewModel.gameBoard.cellMarker;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by August on 2016-10-22.
 */
public class MarkerLayer extends StackPane {

    private List<CellMarker> markers = new ArrayList<>();

    public void addMarker(CellMarker... markers){
        this.markers = Arrays.stream(markers)
                .collect(Collectors
                        .toList());

        System.out.println(markers.toString());

        Node[] nodes = this.markers.stream()
                .map(CellMarker::getShape)
                .toArray(Node[]::new);

        System.out.println(nodes.toString());

        this.getChildren().addAll(nodes);
    }

    public void setSingleMarker(CellMarker marker){
        this.getChildren().clear();
        this.addMarker(marker);
    }

    public void removeMarker(CellMarker marker){
        this.getChildren().remove(marker.getShape());
        this.markers.remove(marker);
    }

    public List<CellMarker> getMarkers(){
        return this.markers;
    }
}
