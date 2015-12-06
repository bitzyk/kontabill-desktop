package main.java.kontabill.mvc.controller;

import main.java.kontabill.Kontabill;
import main.java.kontabill.mvc.model.index.IndexModel;
import main.java.kontabill.mvc.view.index.IndexView;

/**
 *
 *
 */
public class IndexController extends BaseAbstractController {

    protected IndexModel model;


    public IndexController(Kontabill kontabill) {
        super(kontabill);
    }

    public void indexAction()
    {
        System.out.println(
                "ma-ta din index"
        );

        IndexView indexView = new IndexView(this);
        indexView.render();;

    }


    @Override
    protected void setModelOfController() {
        this.model = new IndexModel();
    }
}
