package main.java.config;

final public class MenuConfig {

    final public static String[][] menu = {
            {
                    "home",
                    "Acasa",
                    "IndexController",
            },
            {
                    "invoices",
                    "Emitere",
                    "InvoiceController",
            },
            {
                    "settings",
                    "Setari",
                    "SettingController",
            },
            {
                    "reports",
                    "Rapoarte",
                    "ReportController",
            },
            {
                    "receipts",
                    "Incasari",
                    "ReceiptController",
            },
            {
                    "catalogs",
                    "Cataloage",
                    "CatalogController",
            },
            {
                    "applicationManager",
                    "Manager Aplicatie",
                    "AplicationManagerController",
            },
    };

    final public static String[][][] submenu = {
            {
                    {
                    }
            },
            {
                    {
                            "invoice",
                            "Factura",
                            "invoiceAction"
                    },
                    {
                            "proforma",
                            "Factura proforma",
                            "proformaAction"
                    },
                    {
                            "storno",
                            "Factura storno",
                            "stornoAction"
                    },
            },
            {
                    {
                            "companyDetails",
                            "Date firma",
                            "companyDetailsAction"
                    },
                    {
                            "rangeDocsConfig",
                            "Configurari serii documente",
                            "rangeDocsConfigAction"
                    },
                    {
                            "appSettings",
                            "Setari program",
                            "appSettingsAction"
                    },
            },
            {
                    {
                            "reportInvoice",
                            "Rapoarte facturi",
                            "reportInvoiceAction"
                    },
                    {
                            "reportProforma",
                            "Rapoarte proforme",
                            "reportProformaAction"
                    },
                    {
                            "reportReceipt",
                            "Rapoarte chitante",
                            "reportReceiptAction"
                    },
                    {
                            "clientChart",
                            "Fisa client",
                            "clientChartAction"
                    },
                    {
                            "charts",
                            "Grafice/statistici",
                            "chartsAction"
                    },
            },
            {
                    {
                            "receiptInvoice",
                            "Chitanta pentru factura",
                            "receiptInvoiceAction"
                    },
                    {
                            "receiptWithoutInvoice",
                            "Chitanta fara factura",
                            "receiptWithoutInvoiceAction"
                    },
                    {
                            "receiptBank",
                            "Incasare prin banca",
                            "receiptBankAction"
                    },
                    {
                            "receiptHistory",
                            "Istoric incasari",
                            "receiptHistoryAction"
                    },
            },
            {
                    {
                            "catalogClients",
                            "Catalog clienti",
                            "catalogClientsAction"
                    },
                    {
                            "catalogProducts",
                            "Catalog produse",
                            "catalogProductsAction"
                    },
                    {
                            "catalogLegalRepresentatives",
                            "Catalog reprezentati legali",
                            "catalogLegalRepresentativesAction"
                    },
                    {
                            "catalogDelegates",
                            "Catalog delegati",
                            "catalogDelegatesAction"
                    },
            },
            {
                    {
                            "trackTransaction",
                            "Istoric operatiuni",
                            "trackTransactionAction"
                    },
                    {
                            "editDocuments",
                            "Modificari documente",
                            "editDocumentsAction"
                    },
                    {
                            "saveData",
                            "Salvare date",
                            "saveDataAction"
                    },
                    {
                            "restoreData",
                            "Restaurare date",
                            "restoreDataAction"
                    },
                    {
                            "myPayments",
                            "Platile mele",
                            "myPaymentsAction"
                    },
            },
    };

}


