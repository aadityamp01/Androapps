import 'package:flutter/material.dart';
import 'package:hospital_app/HospitalAuthentication/Hospital_Methods.dart';

class NotReport extends StatefulWidget {
  const NotReport({Key? key}) : super(key: key);

  @override
  _NotReportState createState() => _NotReportState();
}

class _NotReportState extends State<NotReport> {
  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 248, 243, 247),
      appBar: AppBar(
        automaticallyImplyLeading: false,
        leading: IconButton(
          icon: Icon(Icons.arrow_back_ios_outlined, color: Colors.white),
          onPressed: () => Navigator.of(context).pop(),
        ),
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
                  //HospitalogOut(context);
                },
                icon: Icon(
                  Icons.logout,
                  color: Color(0xff8f94fb),
                )),
          )
        ],
      ),
      body: Center(
        child: Container(
          height: size.height / 2,
          child: Column(
            children: [
              Image.asset(
                "Assets/images/report.png",
                fit: BoxFit.fitHeight,
                height: size.height / 6,
              ),
              SizedBox(
                height: size.height / 25,
              ),
              Text(
                "No report uploaded yet",
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                  fontStyle: FontStyle.italic,
                  fontSize: 24,
                  color: Color(0xff8f94fb),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
