import 'package:flutter/material.dart';
import 'package:hospital_app/HospitalAuthentication/Hospital_Methods.dart';
import 'package:syncfusion_flutter_pdfviewer/pdfviewer.dart';

class ReportView extends StatelessWidget {
  PdfViewerController? _pdfViewerController;
  final String reportUrl;
  ReportView(this.reportUrl);

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
        backgroundColor: Color.fromARGB(255, 248, 243, 247),
        appBar: AppBar(
          leading: IconButton(
            icon: Icon(Icons.arrow_back_ios_outlined, color: Colors.white),
            onPressed: () => Navigator.of(context).pop(),
          ),
          automaticallyImplyLeading: false,
          // elevation: 0,
          title: Center(
              child: Text(
            "Hospital App",
            style: TextStyle(color: Colors.white),
          )),
          backgroundColor: Color(0xff8f94fb),
          actions: [
            Container(
              child: IconButton(
                  onPressed: () {
                    hospitalLogOut(context);
                  },
                  icon: Icon(
                    Icons.logout,
                    color: Colors.white,
                  )),
            ),
          ],
        ),
        body: SfPdfViewer.network(reportUrl, controller: _pdfViewerController));
  }
}
