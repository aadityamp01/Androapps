import 'dart:io';
import 'dart:math';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:file_picker/file_picker.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_phone_direct_caller/flutter_phone_direct_caller.dart';
import 'package:hospital_app/HospitalAuthentication/Hospital_Methods.dart';
import 'package:hospital_app/pages/chat_room.dart';
import 'package:hospital_app/pages/not_report.dart';
import 'package:hospital_app/pages/report_view.dart';
import 'package:hospital_app/utils/routes.dart';
import 'package:hospital_app/utils/screen_arguments_appointment.dart';

class DoctorAppointmentDetailPage extends StatefulWidget {
  @override
  State<DoctorAppointmentDetailPage> createState() =>
      _DoctorAppointmentDetailPageState();
}

class _DoctorAppointmentDetailPageState
    extends State<DoctorAppointmentDetailPage> {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final CollectionReference _firestoreDBPatientRequestList =
      FirebaseFirestore.instance.collection("users");
  String reportUrl = "";
  int? number;
  late String patientApListId;
  late String patientNameAp;
  bool isReport = false;
  late bool present;
  String date = "";
  String patientName = "";
  String doctorName = "";
  String patientUid = "";
  String patientEmail = "";
  String patientPhoneNo = "";
  Map<String, dynamic>? userMap;
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  bool isLoading = false;

  void onClick() async {
    FirebaseFirestore _firestore = FirebaseFirestore.instance;

    setState(() {
      isLoading = true;
    });

    await _firestore
        .collection("users")
        .where("email", isEqualTo: patientEmail)
        .get()
        .then((value) {
      setState(() {
        userMap = value.docs[0].data();
        isLoading = false;
      });
      print(userMap);
      print(patientEmail);
    });
  }

  String chatRoomId(String user1, String user2) {
    if (user1[0].toLowerCase().codeUnits[0] >
        user2.toLowerCase().codeUnits[0]) {
      return "$user1$user2";
    } else {
      return "$user2$user1";
    }
  }

  @override
  Widget build(BuildContext context) {
    final argsAp = ModalRoute.of(context)!.settings.arguments
        as ScreenArgumentsAppointment;
    final size = MediaQuery.of(context).size;

    date = argsAp.date;
    patientName = argsAp.patientName;
    doctorName = argsAp.doctorName;
    patientUid = argsAp.patientUid;
    patientEmail = argsAp.email;
    patientPhoneNo = argsAp.phoneNo;

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
                  hospitalLogOut(context);
                },
                icon: Icon(
                  Icons.logout,
                  color: Colors.white,
                )),
          )
        ],
      ),
      body: SingleChildScrollView(
          padding: EdgeInsets.all(15),
          child: Column(
            children: [
              SizedBox(
                height: 5,
              ),
              Container(
                padding: EdgeInsets.fromLTRB(15, 0, 0, 0),
                alignment: Alignment.centerLeft,
                child: Text("Name: ${argsAp.patientName}",
                    style: TextStyle(
                        color: Colors.black,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontStyle: FontStyle.italic)),
              ),
              SizedBox(height: 10),
              Container(
                padding: EdgeInsets.fromLTRB(15, 0, 0, 0),
                alignment: Alignment.centerLeft,
                child: Text("Email: ${argsAp.email}",
                    style: TextStyle(
                        color: Colors.black,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontStyle: FontStyle.italic)),
              ),
              SizedBox(height: 10),
              Container(
                padding: EdgeInsets.fromLTRB(15, 0, 0, 0),
                alignment: Alignment.centerLeft,
                child: Text("Doctor: ${argsAp.doctorName}",
                    style: TextStyle(
                        color: Colors.black,
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontStyle: FontStyle.italic)),
              ),
              SizedBox(height: 10),
              Container(
                  padding: EdgeInsets.fromLTRB(15, 0, 0, 0),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Icon(
                        Icons.date_range,
                        color: Color.fromARGB(255, 155, 155, 155),
                      ),
                      Text(" Date: ${argsAp.date}",
                          style: TextStyle(
                              color: Color.fromARGB(255, 155, 155, 155),
                              fontSize: 16,
                              fontWeight: FontWeight.bold,
                              fontStyle: FontStyle.italic)),
                    ],
                  )),
              SizedBox(height: 20),
              Container(
                  padding: EdgeInsets.fromLTRB(15, 0, 0, 0),
                  child: Row(
                    children: [
                      Icon(
                        Icons.lock_clock,
                        color: Color.fromARGB(255, 155, 155, 155),
                      ),
                      Text(" Time: ${argsAp.fromTime} - ${argsAp.toTime}",
                          style: TextStyle(
                              color: Color.fromARGB(255, 155, 155, 155),
                              fontSize: 16,
                              fontWeight: FontWeight.bold,
                              fontStyle: FontStyle.italic)),
                    ],
                  )),
              SizedBox(height: 50),
              Card(
                  elevation: 0,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20.0),
                  ),
                  child: Container(
                      color: Color.fromARGB(255, 248, 243, 247),
                      padding: EdgeInsets.all(12),
                      height: size.height / 6.5,
                      width: double.infinity,
                      child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            InkWell(
                              onTap: uploadReportToFirebase,
                              child: Icon(
                                Icons.upload_file,
                                size: 45,
                                color: Color.fromARGB(255, 255, 158, 0),
                              ),
                            ),
                            Text("Upload Report",
                                style: TextStyle(
                                  fontSize: 18,
                                  color: Color.fromARGB(255, 255, 158, 0),
                                  fontWeight: FontWeight.bold,
                                  fontStyle: FontStyle.italic,
                                ))
                          ]))),
              SizedBox(
                height: 20,
              ),
              StreamBuilder(
                  stream: _firestoreDBPatientRequestList
                      .doc(_auth.currentUser!.uid)
                      .collection("reportFileList")
                      .doc("${date}${patientName}${doctorName}")
                      .snapshots(),
                  builder: (context, AsyncSnapshot snapshot) {
                    var x = snapshot.data;

                    if (!snapshot.hasData) {
                      return Center(child: CircularProgressIndicator());
                    }
                    if (snapshot.hasData) {
                      return InkWell(
                          onTap: () {
                            try {
                              if (x['reportFileUrl'] != null) {
                                print("hi");
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) =>
                                            ReportView(x['reportFileUrl'])));
                              }
                            } catch (e) {
                              print("upload report");
                              Navigator.pushNamed(
                                  context, MyRoute.notReportRoute);
                            }
                          },
                          child: Container(
                              width: 120,
                              height: 37,
                              decoration: BoxDecoration(
                                  color: Color(0xff8f94fb),
                                  borderRadius: BorderRadius.circular(8.0)),
                              child: Center(
                                  child: Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceEvenly,
                                children: [
                                  Icon(
                                    Icons.remove_red_eye,
                                    color: Colors.white,
                                  ),
                                  Padding(
                                    padding: EdgeInsets.only(right: 9),
                                    child: Text("Report",
                                        style: TextStyle(
                                            fontSize: 20,
                                            color: CupertinoColors.white)),
                                  ),
                                ],
                              ))));
                    }
                    //  }
                    return Center(child: SizedBox(height: 40));
                  }),
              SizedBox(
                height: 20,
              ),
              InkWell(
                  onTap: () async {
                    _firestoreDBPatientRequestList
                        .doc(_auth.currentUser!.uid)
                        .collection('patientHistoryList')
                        .add({
                      "patientName": patientName,
                      "email": patientEmail,
                      "patientUid": patientUid,
                      "doctorName": doctorName,
                      "date": date,
                      "fromTime": argsAp.fromTime,
                      "toTime": argsAp.toTime
                    });
                    _firestoreDBPatientRequestList
                        .doc(patientUid)
                        .collection('appointmentHistoryDoctorList')
                        .add({
                      "patientUid": patientUid,
                      "hospitalUid": _auth.currentUser!.uid,
                      "doctorName": doctorName,
                      "email": _auth.currentUser!.email,
                      "hospitalName": _auth.currentUser!.displayName,
                      "date": date,
                      "fromTime": argsAp.fromTime,
                      "toTime": argsAp.toTime
                    });

                    String id;
                    FirebaseFirestore.instance
                        .collection("users")
                        .doc(_auth.currentUser!.uid)
                        .collection("patientAcceptedList")
                        .where("email", isEqualTo: patientEmail)
                        .where("doctorName", isEqualTo: argsAp.doctorName)
                        .where("patientName", isEqualTo: argsAp.patientName)
                        .where("patientUid", isEqualTo: argsAp.patientUid)
                        .get()
                        .then((snapshot) {
                      id = snapshot.docs[0].id;
                      FirebaseFirestore.instance
                          .collection("users")
                          .doc(_auth.currentUser!.uid)
                          .collection("patientAcceptedList")
                          .doc(id)
                          .delete();
                      print(id);
                    });

                    String id2;
                    FirebaseFirestore.instance
                        .collection("users")
                        .doc(argsAp.patientUid)
                        .collection("appointmentAcceptedDoctorList")
                        .where("doctorName", isEqualTo: argsAp.doctorName)
                        .where("hospitalUid", isEqualTo: _auth.currentUser!.uid)
                        .get()
                        .then((snapshot) {
                      id2 = snapshot.docs[0].id;
                      FirebaseFirestore.instance
                          .collection("users")
                          .doc(argsAp.patientUid)
                          .collection("appointmentAcceptedDoctorList")
                          .doc(id2)
                          .delete();
                      print(id2);
                    });

                    Navigator.pop(context);
                  },
                  child: Container(
                      width: 120,
                      height: 37,
                      decoration: BoxDecoration(
                          color: Color(0xff8f94fb),
                          borderRadius: BorderRadius.circular(8.0)),
                      child: Center(
                          child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          Icon(
                            Icons.done,
                            color: Colors.white,
                          ),
                          Padding(
                            padding: EdgeInsets.only(right: 9),
                            child: Text("Done",
                                style: TextStyle(
                                    fontSize: 20,
                                    color: CupertinoColors.white)),
                          ),
                        ],
                      )))),
            ],
          )),
      floatingActionButton: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Padding(
            padding: EdgeInsets.fromLTRB(60, 0, 0, 30),
            child: FloatingActionButton(
              elevation: 3,
              backgroundColor: Color(0xff8f94fb),
              child: Icon(
                Icons.call,
                color: Colors.white,
              ),
              onPressed: () async {
                await FlutterPhoneDirectCaller.callNumber(patientPhoneNo);
              },
            ),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(0, 0, 28, 30),
            child: FloatingActionButton(
              elevation: 3,
              backgroundColor: Color(0xff8f94fb),
              child: Icon(
                Icons.messenger_rounded,
                color: Colors.white,
              ),
              onPressed: () {
                onClick();
                if (userMap != null) {
                  String roomId = chatRoomId(
                      _auth.currentUser!.displayName!, userMap!['name']);
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (_) =>
                              ChatRoom(chatRoomId: roomId, userMap: userMap!)));
                }
              },
            ),
          ),
        ],
      ),
    );
  }

  uploadReportToFirebase() async {
    FilePickerResult? result = await FilePicker.platform.pickFiles();
    File pick = File(result!.files.single.path.toString());
    var file = pick.readAsBytesSync();
    String name = DateTime.now().millisecondsSinceEpoch.toString();

    var reportFile = FirebaseStorage.instance.ref().child(name).child("/.pdf");
    UploadTask task = reportFile.putData(file);
    TaskSnapshot snapshot = await task;
    reportUrl = await snapshot.ref.getDownloadURL();

    await _firestoreDBPatientRequestList
        .doc(_auth.currentUser!.uid)
        .collection("reportFileList")
        .doc("${date}${patientName}${doctorName}")
        .set({
      "reportFileUrl": reportUrl,
      "num": "Report-${date}${patientName}${doctorName}"
    });
    await _firestoreDBPatientRequestList
        .doc(patientUid)
        .collection("reportFileList")
        .doc("${date}${patientName}${doctorName}")
        .set({
      "reportFileUrl": reportUrl,
      "num": "Report-${date}${patientName}${doctorName}"
    });
  }
}
