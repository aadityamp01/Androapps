import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:hospital_app/HospitalAuthentication/Hospital_Methods.dart';
import 'package:hospital_app/utils/screen_arguments.dart';

class DoctorRequestDetailPage extends StatefulWidget {
  @override
  State<DoctorRequestDetailPage> createState() =>
      _DoctorRequestDetailPageState();
}

class _DoctorRequestDetailPageState extends State<DoctorRequestDetailPage> {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final CollectionReference firestoreDBPatientList =
      FirebaseFirestore.instance.collection('users');

  late DateTime pickedDate;
  late TimeOfDay time1;
  late TimeOfDay time2;

  @override
  void initState() {
    super.initState();
    pickedDate = DateTime.now();
    time1 = TimeOfDay.now();
    time2 = TimeOfDay.now();
  }

  @override
  Widget build(BuildContext context) {
    String date = "${pickedDate.day}/${pickedDate.month}/${pickedDate.year}";
    String fromTime = "${time1.hour} : ${time1.minute}";
    String toTime = "${time2.hour} : ${time2.minute}";

    final args = ModalRoute.of(context)!.settings.arguments as ScreenArguments;
    final size = MediaQuery.of(context).size;
    String patientListId = args.patientRequestListId;

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
          )
        ],
      ),
      body: SingleChildScrollView(
          padding: EdgeInsets.all(15),
          child: Column(children: [
            SizedBox(
              height: 5,
            ),
            Container(
              padding: EdgeInsets.fromLTRB(15, 0, 0, 0),
              alignment: Alignment.centerLeft,
              child: Text("Name: ${args.patientName}",
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
              child: Text("Email: ${args.patientEmail}",
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
              child: Text("Doctor: ${args.doctorName}",
                  style: TextStyle(
                      color: Colors.black,
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                      fontStyle: FontStyle.italic)),
            ),
            SizedBox(height: 30),
            Card(
              margin: EdgeInsets.fromLTRB(15, 3.5, 15, 3.5),
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(15.0),
              ),
              child: Container(
                  width: double.infinity,
                  padding: EdgeInsets.fromLTRB(25, 0, 15, 0),
                  height: 60,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(15.0),
                    border: Border.all(
                      color: Colors.white,
                    ),
                    color: Colors.white,
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        date,
                        style: TextStyle(
                            color: Color.fromARGB(255, 255, 158, 0),
                            fontSize: 30),
                      ),
                      IconButton(
                          onPressed: () async {
                            DateTime? date = await showDatePicker(
                                context: context,
                                firstDate: DateTime(DateTime.now().year - 5),
                                lastDate: DateTime(DateTime.now().year + 5),
                                initialDate: pickedDate,
                                builder: (context, child) => Theme(
                                      data: ThemeData.light().copyWith(
                                        primaryColor: const Color(0xff8f94fb),
                                        accentColor: const Color(0xff8f94fb),
                                        colorScheme: ColorScheme.light(
                                          primary: const Color(0xff8f94fb),
                                        ),
                                        buttonTheme: ButtonThemeData(
                                            textTheme: ButtonTextTheme.primary),
                                      ),
                                      child: child!,
                                    ));

                            if (date != null) {
                              setState(() {
                                pickedDate = date;
                              });
                            }
                          },
                          icon: Icon(
                            Icons.date_range,
                            color: Color.fromARGB(255, 255, 158, 0),
                          )),
                    ],
                  )),
            ),
            SizedBox(height: 10),
            Card(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(15.0),
              ),
              margin: EdgeInsets.fromLTRB(15, 3.5, 15, 3.5),
              child: Container(
                width: double.infinity,
                padding: EdgeInsets.fromLTRB(25, 0, 15, 0),
                height: 60,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(15.0),
                  border: Border.all(
                    color: Colors.white,
                  ),
                  color: Colors.white,
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(fromTime,
                        style: TextStyle(
                            color: Color.fromARGB(255, 255, 158, 0),
                            fontSize: 30)),
                    IconButton(
                        onPressed: () async {
                          TimeOfDay? t1 = await showTimePicker(
                              context: context,
                              initialTime: time1,
                              builder: (context, child) => Theme(
                                    data: ThemeData.light().copyWith(
                                      primaryColor: const Color(0xff8f94fb),
                                      accentColor: const Color(0xff8f94fb),
                                      colorScheme: ColorScheme.light(
                                          primary: const Color(0xff8f94fb)),
                                      buttonTheme: ButtonThemeData(
                                          textTheme: ButtonTextTheme.primary),
                                    ),
                                    child: child!,
                                  ));

                          if (t1 != null) {
                            setState(() {
                              time1 = t1;
                            });
                          }
                        },
                        icon: Icon(
                          Icons.lock_clock,
                          color: Color.fromARGB(255, 255, 158, 0),
                        )),
                  ],
                ),
              ),
            ),
            SizedBox(height: 10),
            Card(
              margin: EdgeInsets.fromLTRB(15, 3.5, 15, 3.5),
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(15.0),
              ),
              child: Container(
                width: double.infinity,
                padding: EdgeInsets.fromLTRB(25, 0, 15, 0),
                height: 60,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(15.0),
                  border: Border.all(
                    color: Colors.white,
                  ),
                  color: Colors.white,
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(toTime,
                        style: TextStyle(
                            color: Color.fromARGB(255, 255, 158, 0),
                            fontSize: 30)),
                    IconButton(
                        onPressed: () async {
                          TimeOfDay? t2 = await showTimePicker(
                              context: context,
                              initialTime: time2,
                              builder: (context, child) => Theme(
                                    data: ThemeData.light().copyWith(
                                      primaryColor: const Color(0xff8f94fb),
                                      accentColor: const Color(0xff8f94fb),
                                      colorScheme: ColorScheme.light(
                                          primary: const Color(0xff8f94fb)),
                                      buttonTheme: ButtonThemeData(
                                          textTheme: ButtonTextTheme.primary),
                                    ),
                                    child: child!,
                                  ));

                          if (t2 != null) {
                            setState(() {
                              time2 = t2;
                            });
                          }
                        },
                        icon: Icon(
                          Icons.lock_clock,
                          color: Color.fromARGB(255, 255, 158, 0),
                        )),
                  ],
                ),
              ),
            ),
            SizedBox(height: 40),
            Padding(
              padding: EdgeInsets.all(11.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  InkWell(
                      onTap: () async {
                        firestoreDBPatientList
                            .doc(_auth.currentUser!.uid)
                            .collection('patientAcceptedList')
                            .add({
                          "patientName": args.patientName,
                          "email": args.patientEmail,
                          "phoneNo": args.patientPhoneNumber,
                          "patientUid": args.patientUid,
                          "doctorName": args.doctorName,
                          "doctorPost": args.doctorPost,
                          "doctorSpeciality": args.doctorSpeciality,
                          "doctorEducation": args.doctorEducation,
                          "date": date,
                          "fromTime": fromTime,
                          "toTime": toTime
                        });
                        firestoreDBPatientList
                            .doc(args.patientUid)
                            .collection('appointmentAcceptedDoctorList')
                            .add({
                          "patientUid": args.patientUid,
                          "hospitalUid": _auth.currentUser!.uid,
                          "doctorName": args.doctorName,
                          "doctorPost": args.doctorPost,
                          "doctorSpeciality": args.doctorSpeciality,
                          "doctorEducation": args.doctorEducation,
                          "date": date,
                          "fromTime": fromTime,
                          "toTime": toTime
                        });

                        String id;
                        FirebaseFirestore.instance
                            .collection("users")
                            .doc(_auth.currentUser!.uid)
                            .collection("patientRequestList")
                            .where("email", isEqualTo: args.patientEmail)
                            .where("doctorName", isEqualTo: args.doctorName)
                            .where("patientName", isEqualTo: args.patientName)
                            .where("uid", isEqualTo: args.patientUid)
                            .get()
                            .then((snapshot) {
                          id = snapshot.docs[0].id;
                          FirebaseFirestore.instance
                              .collection("users")
                              .doc(_auth.currentUser!.uid)
                              .collection("patientRequestList")
                              .doc(id)
                              .delete();
                          print(id);
                        });

                        String id2;
                        FirebaseFirestore.instance
                            .collection("users")
                            .doc(args.patientUid)
                            .collection("appointmentRequestedDoctorList")
                            .where("doctorName", isEqualTo: args.doctorName)
                            .where("hospitalUid",
                                isEqualTo: _auth.currentUser!.uid)
                            .get()
                            .then((snapshot) {
                          id2 = snapshot.docs[0].id;
                          FirebaseFirestore.instance
                              .collection("users")
                              .doc(args.patientUid)
                              .collection("appointmentRequestedDoctorList")
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
                                child: Text("Accept",
                                    style: TextStyle(
                                        fontSize: 20,
                                        color: CupertinoColors.white)),
                              ),
                            ],
                          )))),
                  InkWell(
                      onTap: () async {
                        String id;
                        FirebaseFirestore.instance
                            .collection("users")
                            .doc(_auth.currentUser!.uid)
                            .collection("patientRequestList")
                            .where("email", isEqualTo: args.patientEmail)
                            .where("doctorName", isEqualTo: args.doctorName)
                            .where("patientName", isEqualTo: args.patientName)
                            .where("uid", isEqualTo: args.patientUid)
                            .get()
                            .then((snapshot) {
                          id = snapshot.docs[0].id;
                          FirebaseFirestore.instance
                              .collection("users")
                              .doc(_auth.currentUser!.uid)
                              .collection("patientRequestList")
                              .doc(id)
                              .delete();
                          print(id);
                        });

                        String id2;
                        FirebaseFirestore.instance
                            .collection("users")
                            .doc(args.patientUid)
                            .collection("appointmentRequestedDoctorList")
                            .where("doctorName", isEqualTo: args.doctorName)
                            .where("hospitalUid",
                                isEqualTo: _auth.currentUser!.uid)
                            .get()
                            .then((snapshot) {
                          id2 = snapshot.docs[0].id;
                          FirebaseFirestore.instance
                              .collection("users")
                              .doc(args.patientUid)
                              .collection("appointmentRequestedDoctorList")
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
                                Icons.delete,
                                color: Colors.white,
                              ),
                              Padding(
                                padding: EdgeInsets.only(right: 9),
                                child: Text("Delete",
                                    style: TextStyle(
                                        fontSize: 20,
                                        color: CupertinoColors.white)),
                              ),
                            ],
                          )))),
                ],
              ),
            ),
          ])),
    );
  }
}
