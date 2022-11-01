import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_phone_direct_caller/flutter_phone_direct_caller.dart';
import 'package:hospital_app/Authentication/Methods.dart';
import 'package:hospital_app/pages/chat_room.dart';
import 'package:hospital_app/utils/routes.dart';
import 'package:hospital_app/utils/screen_argument_appointment_list.dart';
import 'package:hospital_app/utils/screen_arguments_doctor_list.dart';
import 'package:url_launcher/url_launcher.dart';

class HospitalDetailPage extends StatefulWidget {
  late final String name;

  @override
  State<HospitalDetailPage> createState() => _HospitalDetailPageState();
}

class _HospitalDetailPageState extends State<HospitalDetailPage> {
  final CollectionReference _firestoreDBDoctorList =
      FirebaseFirestore.instance.collection("users");
  final FirebaseAuth _auth = FirebaseAuth.instance;
  bool getAppointment = false;
  String hospitalEmail = "";
  String hospitalPhoneNo = "";
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
        .where("email", isEqualTo: hospitalEmail)
        .get()
        .then((value) {
      setState(() {
        userMap = value.docs[0].data();
        isLoading = false;
      });
      print(userMap);
      print(hospitalEmail);
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
    final argsDL =
        ModalRoute.of(context)!.settings.arguments as ScreenArgumentsDoctorList;
    String id = argsDL.id;
    hospitalEmail = argsDL.hospitalEmail;
    hospitalPhoneNo = argsDL.hospitalPhoneNo;
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
          ),
        ),
        backgroundColor: Color(0xff8f94fb),
        actions: [
          Container(
            child: IconButton(
                onPressed: () {
                  logOut(context);
                },
                icon: Icon(
                  Icons.logout,
                  color: Colors.white,
                )),
          )
        ],
      ),
      body: StreamBuilder(
          stream: _firestoreDBDoctorList
              .doc(id)
              .collection("doctorList")
              .snapshots(),
          builder: (BuildContext context, snapshots) {
            if (!snapshots.hasData)
              return Center(child: CircularProgressIndicator());
            return ListView.builder(
                itemCount: (snapshots.data! as QuerySnapshot).docs.length,
                itemBuilder: (BuildContext context, int index) {
                  Map<String, dynamic> map = (snapshots.data! as QuerySnapshot)
                      .docs[index]
                      .data() as Map<String, dynamic>;

                  return SingleChildScrollView(
                      padding: EdgeInsets.fromLTRB(10, 10, 10, 5),
                      child: Card(
                          elevation: 3,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(8.0),
                          ),
                          child: Container(
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(8),
                                border: Border.all(
                                  // color: Colors.deepOrange,
                                  color: Colors.white,
                                ),
                                //color: Colors.blueAccent,
                                color: Colors.white,
                              ),
                              padding: EdgeInsets.all(12),
                              height: size.height / 4.2,
                              width: double.infinity,
                              //  padding: EdgeInsets.all(15),
                              child: Column(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: [
                                    Row(children: [
                                      // Image.asset(
                                      //   "assets/images/user.png",
                                      // ),
                                      Column(
                                          mainAxisAlignment:
                                              MainAxisAlignment.start,
                                          crossAxisAlignment:
                                              CrossAxisAlignment.start,
                                          children: [
                                            Text(
                                              map['doctorName'],
                                              style: TextStyle(
                                                  // color: Colors.white,
                                                  color: Colors.black,
                                                  fontSize: 20,
                                                  fontWeight: FontWeight.bold),
                                            ),
                                            Text(map['doctorPost'],
                                                style: TextStyle(
                                                  //color: Colors.yellow,
                                                  color: Color.fromARGB(
                                                      255, 155, 155, 155),
                                                  fontSize: 16,
                                                )),
                                            Text(map['doctorSpeciality'],
                                                style: TextStyle(
                                                  //color: Colors.yellow,
                                                  color: Color.fromARGB(
                                                      255, 155, 155, 155),
                                                  fontSize: 16,
                                                )),
                                            Text(map['doctorEducation'],
                                                style: TextStyle(
                                                  //color: Colors.yellow,
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
                                            title: Text("Get Appointment"),
                                            content: SingleChildScrollView(
                                              child: Column(
                                                children: [
                                                  SizedBox(height: 10),
                                                  Text(
                                                      "Do you want to send request for appointment to ${map['doctorName']}?",
                                                      style: TextStyle(
                                                        fontSize: 16,
                                                      )),
                                                ],
                                              ),
                                            ),
                                            actions: <Widget>[
                                              FlatButton(
                                                onPressed: () async {
                                                  Map<String, dynamic>
                                                      doctorList = {
                                                    "doctorName":
                                                        map['doctorName'],
                                                    "doctorPost":
                                                        map['doctorPost'],
                                                    "doctorSpeciality":
                                                        map['doctorSpeciality'],
                                                    "doctorEducation":
                                                        map['doctorEducation'],
                                                    "hospitalUid": id,
                                                  };
                                                  _firestoreDBDoctorList
                                                      .doc(_auth
                                                          .currentUser!.uid)
                                                      .collection(
                                                          'appointmentRequestedDoctorList')
                                                      .add(doctorList);

                                                  var _firestore =
                                                      FirebaseFirestore
                                                          .instance
                                                          .collection("users")
                                                          .doc(_auth
                                                              .currentUser!
                                                              .uid);
                                                  DocumentSnapshot snapshot =
                                                      await _firestore.get();
                                                  FirebaseFirestore.instance
                                                      .collection("users")
                                                      .doc(id)
                                                      .collection(
                                                          'patientRequestList')
                                                      .add({
                                                    'patientName':
                                                        snapshot['name'],
                                                    'phoneNo':
                                                        snapshot['phoneNo'],
                                                    'email': snapshot['email'],
                                                    'uid':
                                                        _auth.currentUser!.uid,
                                                    'doctorName':
                                                        map['doctorName'],
                                                    "doctorPost":
                                                        map['doctorPost'],
                                                    "doctorSpeciality":
                                                        map['doctorSpeciality'],
                                                    "doctorEducation":
                                                        map['doctorEducation'],
                                                  });

                                                  Navigator.pushNamed(
                                                      context,
                                                      MyRoute
                                                          .appointmentListRoute);
                                                },
                                                child: Text("Done"),
                                              ),
                                              SizedBox(width: size.width / 5),
                                              FlatButton(
                                                onPressed: () {
                                                  Navigator.of(ctx).pop();
                                                },
                                                child: Text("Cancel"),
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
                                              child: Text("Get Appointment",
                                                  style: TextStyle(
                                                      fontSize: 19,
                                                      color: CupertinoColors
                                                          .white)))),
                                    )
                                  ]))));
                });
          }),
      floatingActionButton:
          Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [
        Padding(
          padding: EdgeInsets.fromLTRB(32, 0, 0, 0),
          child: Align(
            alignment: Alignment.bottomRight,
            child: FloatingActionButton(
              backgroundColor: Color(0xff8f94fb),
              child: Icon(
                Icons.call,
                color: Colors.white,
              ),
              onPressed: () async {
                await FlutterPhoneDirectCaller.callNumber(hospitalPhoneNo);
              },
            ),
          ),
        ),
        Align(
          alignment: Alignment.bottomLeft,
          child: FloatingActionButton(
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
      ]),
    );
  }
}
