import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:hospital_app/utils/routes.dart';
import 'package:hospital_app/utils/screen_arguments_appointment.dart';

//DoctorAppointmentAccepted
class DoctorAppointmentAccepted extends StatefulWidget {
  const DoctorAppointmentAccepted({Key? key}) : super(key: key);

  @override
  State<DoctorAppointmentAccepted> createState() =>
      _DoctorAppointmentAcceptedState();
}

class _DoctorAppointmentAcceptedState extends State<DoctorAppointmentAccepted> {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final CollectionReference _firestoreDBPatientAppointment =
      FirebaseFirestore.instance.collection("users");

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
        backgroundColor: const Color.fromARGB(255, 248, 243, 247),
        body: StreamBuilder<QuerySnapshot>(
            stream: _firestoreDBPatientAppointment
                .doc(_auth.currentUser!.uid)
                .collection('patientAcceptedList')
                .snapshots(),
            builder:
                (BuildContext context, AsyncSnapshot<QuerySnapshot> snapshot) {
              if (!snapshot.hasData)
                return const Center(child: CircularProgressIndicator());
              return ListView.builder(
                  itemCount: snapshot.data!.docs.length,
                  itemBuilder: (BuildContext context, int index) {
                    Map<String, dynamic> _map = snapshot.data!.docs[index]
                        .data() as Map<String, dynamic>;
                    return SingleChildScrollView(
                        padding: const EdgeInsets.fromLTRB(6, 10, 6, 5),
                        child: InkWell(
                          onTap: () {
                            Navigator.pushNamed(
                                context, MyRoute.doctorAppointmentDetailRoute,
                                arguments: ScreenArgumentsAppointment(
                                  _map["patientName"],
                                  _map["email"],
                                  _map["phoneNo"],
                                  _map["patientUid"],
                                  _map["hospitalUid"],
                                  _map["doctorName"],
                                  _map["doctorPost"],
                                  _map["doctorSpeciality"],
                                  _map["doctorEducation"],
                                  _map["date"],
                                  _map["fromTime"],
                                  _map["toTime"],
                                  _firestoreDBPatientAppointment
                                      .doc(_auth.currentUser!.uid)
                                      .collection('patientAcceptedList')
                                      .doc()
                                      .id,
                                  snapshot.data!.docs[index].id,
                                ));
                          },
                          child: Card(
                              elevation: 3,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(8.0),
                              ),
                              child: Container(
                                  decoration: BoxDecoration(
                                    borderRadius: BorderRadius.circular(8),
                                    border: Border.all(
                                      color: Colors.white,
                                    ),
                                    color: Colors.white,
                                  ),
                                  padding: const EdgeInsets.all(10),
                                  height: size.height / 6.8,
                                  width: double.infinity,
                                  child: Column(
                                      mainAxisAlignment:
                                          MainAxisAlignment.start,
                                      crossAxisAlignment:
                                          CrossAxisAlignment.start,
                                      children: [
                                        Text(_map['patientName'],
                                            style: const TextStyle(
                                                color: Colors.black,
                                                fontSize: 20,
                                                fontWeight: FontWeight.bold)),
                                        Text("Date: ${_map['date']}",
                                            style: const TextStyle(
                                                color: Color.fromARGB(
                                                    255, 155, 155, 155),
                                                fontSize: 16,
                                                fontStyle: FontStyle.italic)),
                                        Text(
                                            "Time: ${_map['fromTime']} - ${_map['toTime']}",
                                            style: const TextStyle(
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
