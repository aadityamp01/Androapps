import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AppointmentAccepted extends StatefulWidget {
  const AppointmentAccepted({Key? key}) : super(key: key);

  @override
  _AppointmentAcceptedState createState() => _AppointmentAcceptedState();
}

class _AppointmentAcceptedState extends State<AppointmentAccepted> {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final CollectionReference _firestoreDBApDoctorList =
      FirebaseFirestore.instance.collection("users");

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 248, 243, 247),
      body: StreamBuilder<QuerySnapshot>(
          stream: _firestoreDBApDoctorList
              .doc(_auth.currentUser!.uid)
              .collection("appointmentAcceptedDoctorList")
              .snapshots(),
          builder:
              (BuildContext context, AsyncSnapshot<QuerySnapshot> snapshots) {
            if (!snapshots.hasData)
              return Center(child: CircularProgressIndicator());
            return ListView.builder(
                itemCount: snapshots.data!.docs.length,
                itemBuilder: (BuildContext context, int index) {
                  Map<String, dynamic> map = snapshots.data!.docs[index].data()
                      as Map<String, dynamic>;
                  return SingleChildScrollView(
                      padding: EdgeInsets.fromLTRB(10, 10, 10, 5),
                      child: Card(
                          elevation: 3,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(8.0),
                          ),
                          child: Container(
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(8.0),
                                border: Border.all(
                                  color: Colors.white,
                                ),
                                color: Colors.white,
                              ),
                              padding: EdgeInsets.all(12),
                              height: size.height / 4.2,
                              width: double.infinity,
                              child: Column(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: [
                                    Row(children: [
                                      Column(
                                          mainAxisAlignment:
                                              MainAxisAlignment.start,
                                          crossAxisAlignment:
                                              CrossAxisAlignment.start,
                                          children: [
                                            Text(
                                              map['doctorName'],
                                              style: TextStyle(
                                                  color: Colors.black,
                                                  fontSize: 24,
                                                  fontWeight: FontWeight.bold),
                                            ),
                                            Text(map['doctorPost'],
                                                style: TextStyle(
                                                  color: Color.fromARGB(
                                                      255, 155, 155, 155),
                                                  fontSize: 16,
                                                )),
                                            Text(map['doctorSpeciality'],
                                                style: TextStyle(
                                                  color: Color.fromARGB(
                                                      255, 155, 155, 155),
                                                  fontSize: 16,
                                                )),
                                            Text(map['doctorEducation'],
                                                style: TextStyle(
                                                  color: Color.fromARGB(
                                                      255, 155, 155, 155),
                                                  fontSize: 16,
                                                )),
                                          ]),
                                    ]),
                                    SizedBox(
                                      height: 5,
                                    ),
                                    InkWell(
                                      onTap: () {
                                        showDialog(
                                          context: context,
                                          builder: (ctx) => AlertDialog(
                                            title: Text("Cancel Appointment"),
                                            content: SingleChildScrollView(
                                              child: Column(
                                                children: [
                                                  SizedBox(height: 10),
                                                  Text(
                                                      "Do you want to Cancel appointment to ${map['doctorName']}?",
                                                      style: TextStyle(
                                                        fontSize: 16,
                                                      )),
                                                ],
                                              ),
                                            ),
                                            actions: <Widget>[
                                              FlatButton(
                                                onPressed: () async {
                                                  String id;
                                                  FirebaseFirestore.instance
                                                      .collection("users")
                                                      .doc(map['patientUid'])
                                                      .collection(
                                                          "patientAcceptedList")
                                                      .where("email",
                                                          isEqualTo:
                                                              map['email'])
                                                      .where("doctorName",
                                                          isEqualTo:
                                                              map['doctorName'])
                                                      .where("patientName",
                                                          isEqualTo: map[
                                                              'patientName'])
                                                      .where("patientUid",
                                                          isEqualTo:
                                                              map['patientUid'])
                                                      .get()
                                                      .then((snapshot) {
                                                    id = snapshot.docs[0].id;
                                                    FirebaseFirestore.instance
                                                        .collection("users")
                                                        .doc(map['patientUid'])
                                                        .collection(
                                                            "patientAcceptedList")
                                                        .doc(id)
                                                        .delete();
                                                    print(id);
                                                  });

                                                  String id2;
                                                  FirebaseFirestore.instance
                                                      .collection("users")
                                                      .doc(_auth
                                                          .currentUser!.uid)
                                                      .collection(
                                                          "appointmentAcceptedDoctorList")
                                                      .where("doctorName",
                                                          isEqualTo:
                                                              map['doctorName'])
                                                      .where(
                                                        "hospitalUid",
                                                        isEqualTo:
                                                            map['hospitalUid'],
                                                      )
                                                      .get()
                                                      .then((snapshot) {
                                                    id2 = snapshot.docs[0].id;
                                                    FirebaseFirestore.instance
                                                        .collection("users")
                                                        .doc(_auth
                                                            .currentUser!.uid)
                                                        .collection(
                                                            "appointmentAcceptedDoctorList")
                                                        .doc(id2)
                                                        .delete();
                                                    print(id2);
                                                  });
                                                  Navigator.of(ctx).pop();
                                                },
                                                child: Text("Yes"),
                                              ),
                                              SizedBox(width: size.width / 5),
                                              FlatButton(
                                                onPressed: () {
                                                  Navigator.of(ctx).pop();
                                                },
                                                child: Text("No"),
                                              ),
                                            ],
                                          ),
                                        );
                                      },
                                      child: Container(
                                          margin:
                                              EdgeInsets.fromLTRB(50, 5, 50, 5),
                                          width: double.infinity,
                                          height: 37,
                                          decoration: BoxDecoration(
                                              color: Color(0xff8f94fb),
                                              borderRadius:
                                                  BorderRadius.circular(20.0)),
                                          child: Center(
                                              child: Text("Cancel Appointment",
                                                  style: TextStyle(
                                                    fontSize: 19,
                                                    color: Colors.white,
                                                  )))),
                                    )
                                  ]))));
                });
          }),
    );
  }
}
