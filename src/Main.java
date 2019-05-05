import team.HabitatController;
import team.HabitatModel;
import team.HabitatView;

public class Main {

    public static void main(String[] args) {

        HabitatView view = new HabitatView(850, 600, 100, 100);
        HabitatModel model = new HabitatModel(0.5, 50, 1,
                1, view);
        HabitatController controllers = new HabitatController(view, model);
    }

}
