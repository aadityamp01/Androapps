import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:hospital_app/Authentication/Methods.dart';
import 'package:hospital_app/utils/screen_argument_appointment_list.dart';
import 'package:hospital_app/utils/screen_arguments_doctor_list.dart';
import 'package:hospital_app/widgets/appointment_accepted.dart';
import 'package:hospital_app/widgets/appointment_requested.dart';

class AppointmentListPage extends StatefulWidget {
  @override
  State<AppointmentListPage> createState() => _AppointmentListPageState();
}

class _AppointmentListPageState extends State<AppointmentListPage> {
  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return DefaultTabController(
      length: 2,
      child: Scaffold(
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
          actions: [
            Container(
              margin: EdgeInsets.fromLTRB(15, 0, 0, 0),
              padding: EdgeInsets.fromLTRB(15, 0, 0, 0),
              child: IconButton(
                  onPressed: () {
                    logOut(context);
                  },
                  icon: Icon(Icons.logout, color: Colors.white)),
            )
          ],
          backgroundColor: Color(0xff8f94fb),
          bottom: TabBar(
              labelStyle: TextStyle(fontWeight: FontWeight.bold, fontSize: 18),
              indicatorColor: Colors.white,
              tabs: [
                Tab(text: "Requested"),
                Tab(
                  text: "Accepted",
                ),
              ]),
        ),
        body: TabBarView(children: <Widget>[
          AppointmentRequested(),
          AppointmentAccepted(),
        ]),
      ),
    );
  }
}
