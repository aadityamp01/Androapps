import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:hospital_app/utils/routes.dart';
import 'package:hospital_app/utils/screen_arguments.dart';

class DoctorAppointmentRequested extends StatefulWidget {
  @override
  State<DoctorAppointmentRequested> createState() =>
      _DoctorAppointmentRequestedState();
}

class _DoctorAppointmentRequestedState
    extends State<DoctorAppointmentRequested> {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final CollectionReference _firestoreDBPatientList =
      FirebaseFirestore.instance.collection("users");

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
        backgroundColor: Color.fromARGB(255, 248, 243, 247),
        body: StreamBuilder<QuerySnapshot>(
            stream: _firestoreDBPatientList
                .doc(_auth.currentUser!.uid)
                .collection('patientRequestList')
                .snapshots(),
            builder:
                (BuildContext context, AsyncSnapshot<QuerySnapshot> snapshot) {
              if (!snapshot.hasData)
                return Center(child: CircularProgressIndicator());
              return ListView.builder(
                  itemCount: snapshot.data!.docs.length,
                  itemBuilder: (BuildContext context, int index) {
                    Map<String, dynamic> _map = snapshot.data!.docs[index]
                        .data() as Map<String, dynamic>;
                    return SingleChildScrollView(
                        padding: EdgeInsets.fromLTRB(6, 10, 6, 5),
                        child: InkWell(
                          onTap: () async {
                            Navigator.pushNamed(
                              context,
                              MyRoute.doctorRequestDetailRoute,
                              arguments: ScreenArguments(
                                _map['patientName'],
                                _map['email'],
                                _map['phoneNo'],
                                _map['uid'],
                                _map['doctorName'],
                                _map['doctorPost'],
                                _map['doctorSpeciality'],
                                _map['doctorEducation'],
                                _firestoreDBPatientList
                                    .doc(_auth.currentUser!.uid)
                                    .collection('patientRequestList')
                                    .doc()
                                    .id,
                                snapshot.data!.docs[index].id,
                              ),
                            );
                          },
                          child: Card(
                              elevation: 3,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(8),
                              ),
                              child: Container(
                                  decoration: BoxDecoration(
                                    borderRadius: BorderRadius.circular(8),
                                    border: Border.all(
                                      color: Colors.white,
                                    ),
                                    color: Colors.white,
                                  ),
                                  padding: EdgeInsets.all(10),
                                  height: size.height / 6.8,
                                  width: double.infinity,
                                  child: Column(
                                      mainAxisAlignment:
                                          MainAxisAlignment.start,
                                      crossAxisAlignment:
                                          CrossAxisAlignment.start,
                                      children: [
                                        Text(_map['patientName'],
                                            style: TextStyle(
                                                color: Colors.black,
                                                fontSize: 20,
                                                fontWeight: FontWeight.bold)),
                                        Text(
                                          "Date: --",
                                          style: TextStyle(
                                              color: Color.fromARGB(
                                                  255, 155, 155, 155),
                                              fontSize: 16,
                                              fontStyle: FontStyle.italic),
                                        ),
                                        Text("Time: --",
                                            style: TextStyle(
                                                color: Color.fromARGB(
                                                    255, 155, 155, 155),
                                                fontSize: 16,
                                                fontStyle: FontStyle.italic))
                                      ]))),
                        ));
                  });
            }));
  }
}
