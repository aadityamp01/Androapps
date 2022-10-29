import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:hospital_app/HospitalAuthentication/Hospital_Methods.dart';
import 'package:hospital_app/widgets/doctor_appointment_accepted.dart';
import 'package:hospital_app/widgets/doctor_appointment_requested.dart';

class DoctorAppointment extends StatefulWidget {
  const DoctorAppointment({Key? key}) : super(key: key);

  @override
  _DoctorAppointmentState createState() => _DoctorAppointmentState();
}

class _DoctorAppointmentState extends State<DoctorAppointment> {
  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return DefaultTabController(
      length: 2,
      child: Scaffold(
        backgroundColor: Color.fromARGB(255, 248, 243, 247),
        appBar: AppBar(
          leading: IconButton(
            icon: Icon(Icons.arrow_back_ios_outlined, color: Colors.white),
            onPressed: () => Navigator.of(context).pop(),
          ),
          title: Center(
              child: Text(
            "Hospital App",
            style: TextStyle(color: Colors.white),
          )),
          automaticallyImplyLeading: false,
          actions: [
            Container(
              margin: EdgeInsets.fromLTRB(15, 0, 0, 0),
              padding: EdgeInsets.fromLTRB(15, 0, 0, 0),
              child: IconButton(
                  onPressed: () {
                    hospitalLogOut(context);
                  },
                  icon: Icon(Icons.logout, color: Colors.white)),
            )
          ],
          // elevation: 0,
          backgroundColor: Color(0xff8f94fb),
          bottom: TabBar(
              labelStyle: TextStyle(fontWeight: FontWeight.bold, fontSize: 18),
              indicatorColor: Colors.white,
              tabs: [
                Tab(text: "Accepted"),
                Tab(
                  text: "Requested",
                ),
              ]),
        ),
        body: TabBarView(children: <Widget>[
          DoctorAppointmentAccepted(),
          DoctorAppointmentRequested(),
        ]),
      ),
    );
  }
}
