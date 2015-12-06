package main.java.kontabill.mvc.controller;

import main.java.kontabill.Kontabill;
import main.java.kontabill.mvc.model.report.ReportModel;
import main.java.kontabill.mvc.view.report.ReportView;

/**
 *
 *
 */
public class ReportController extends BaseAbstractController {

    protected ReportModel model;


    public ReportController(Kontabill kontabill) {
        super(kontabill);
    }

    public void reportInvoiceAction()
    {
        System.out.println(
                "Hello from report invoice action"
        );

        ReportView reportView = new ReportView(this);
        reportView.render();
    }

    public void reportProformaAction()
    {
        System.out.println(
                "Hello from report proforma action"
        );

        ReportView reportView = new ReportView(this);
        reportView.render();;
    }


    @Override
    protected void setModelOfController() {
        model = new ReportModel();
    }
}
