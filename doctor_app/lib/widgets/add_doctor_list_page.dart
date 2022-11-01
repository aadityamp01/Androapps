import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:hospital_app/HospitalAuthentication/Hospital_Methods.dart';

class AddDoctorListPage extends StatefulWidget {
  const AddDoctorListPage({Key? key}) : super(key: key);

  @override
  _AddDoctorListPageState createState() => _AddDoctorListPageState();
}

class _AddDoctorListPageState extends State<AddDoctorListPage> {
  final TextEditingController _doctorName = TextEditingController();
  final TextEditingController _doctorPost = TextEditingController();
  final TextEditingController _doctorSpeciality = TextEditingController();
  final TextEditingController _doctorEducation = TextEditingController();
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final CollectionReference _firestoreDBDoctorList =
      FirebaseFirestore.instance.collection("users");

  @override
  Widget build(BuildContext context) {
    // final argsDL = ModalRoute.of(context)!.settings.arguments as ScreenArgumentsDoctorList;
    final size = MediaQuery.of(context).size;
    FocusNode myFocusNode = new FocusNode();
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
      body: StreamBuilder<QuerySnapshot>(
          stream: _firestoreDBDoctorList
              .doc(_auth.currentUser!.uid)
              .collection("doctorList")
              .snapshots(),
          builder:
              (BuildContext context, AsyncSnapshot<QuerySnapshot> snapshots) {
            if (snapshots.hasData) {
              return ListView.builder(
                  itemCount: snapshots.data!.docs.length,
                  itemBuilder: (BuildContext context, int index) {
                    Map<String, dynamic> map = snapshots.data!.docs[index]
                        .data() as Map<String, dynamic>;
                    return SingleChildScrollView(
                        child: Padding(
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
                                  color: Colors.white,
                                ),
                                color: Colors.white,
                              ),
                              padding: EdgeInsets.all(12),
                              height: size.height / 4.9,
                              width: double.infinity,
                              child: Column(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: [
                                    Row(
                                        mainAxisAlignment:
                                            MainAxisAlignment.spaceBetween,
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: [
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
                                                      fontSize: 20,
                                                      fontWeight:
                                                          FontWeight.bold),
                                                ),
                                                Text(map['doctorPost'],
                                                    style: TextStyle(
                                                      color: Color.fromARGB(
                                                          255, 155, 155, 155),
                                                      fontStyle:
                                                          FontStyle.italic,
                                                      fontSize: 16,
                                                    )),
                                                Text(map['doctorSpeciality'],
                                                    style: TextStyle(
                                                      color: Color.fromARGB(
                                                          255, 155, 155, 155),
                                                      fontStyle:
                                                          FontStyle.italic,
                                                      fontSize: 16,
                                                    )),
                                                Text(map['doctorEducation'],
                                                    style: TextStyle(
                                                      color: Color.fromARGB(
                                                          255, 155, 155, 155),
                                                      fontStyle:
                                                          FontStyle.italic,
                                                      fontSize: 16,
                                                    )),
                                              ]),
                                          IconButton(
                                              onPressed: () {
                                                String id = snapshots
                                                    .data!.docs[index].id;
                                                _firestoreDBDoctorList
                                                    .doc(_auth.currentUser!.uid)
                                                    .collection('doctorList')
                                                    .doc(id)
                                                    .delete();
                                              },
                                              icon: Icon(
                                                Icons.delete,
                                                color: Color.fromARGB(
                                                    255, 255, 158, 0),
                                              ))
                                        ]),
                                  ]))),
                    ));
                  });
            }
            return Center(child: CircularProgressIndicator());
          }),
      floatingActionButton: Padding(
        padding: EdgeInsets.fromLTRB(0, 0, 0, 30),
        child: FloatingActionButton(
          backgroundColor: Color(0xff8f94fb),
          child: Icon(Icons.add),
          onPressed: () {
            showDialog(
              context: context,
              builder: (ctx) => AlertDialog(
                backgroundColor: Color.fromARGB(255, 248, 243, 247),
                title: Text("Add Doctor Detail"),
                content: SingleChildScrollView(
                  child: Column(
                    children: [
                      SizedBox(height: 10),
                      TextField(
                          controller: _doctorName,
                          cursorColor: const Color(0xff8f94fb),
                          // focusNode: myFocusNode,
                          autofocus: false,
                          keyboardType: TextInputType.text,
                          textInputAction: TextInputAction.next,
                          decoration: InputDecoration(
                            prefixIcon: Icon(
                              Icons.person,
                              color: Color(0xff8f94fb),
                            ),
                            enabledBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: Color(0xff8f94fb)),
                              borderRadius: BorderRadius.circular(7.0),
                            ),
                            focusedBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: Color(0xff8f94fb)),
                            ),
                            contentPadding: EdgeInsets.fromLTRB(20, 15, 20, 15),
                            hintText: "Enter Doctor Name",
                          )),
                      SizedBox(height: 20),
                      TextField(
                          controller: _doctorPost,
                          cursorColor: Color(0xff8f94fb),
                          autofocus: false,
                          keyboardType: TextInputType.text,
                          textInputAction: TextInputAction.next,
                          decoration: InputDecoration(
                            prefixIcon: Icon(Icons.watch_outlined,
                                color: Color(0xff8f94fb)),
                            enabledBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: Color(0xff8f94fb)),
                              borderRadius: BorderRadius.circular(7.0),
                            ),
                            focusedBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: Color(0xff8f94fb)),
                            ),
                            contentPadding: EdgeInsets.fromLTRB(20, 15, 20, 15),
                            hintText: "Enter Post",
                          )),
                      SizedBox(height: 20),
                      TextField(
                          controller: _doctorSpeciality,
                          cursorColor: Color(0xff8f94fb),
                          autofocus: false,
                          keyboardType: TextInputType.text,
                          textInputAction: TextInputAction.next,
                          decoration: InputDecoration(
                            prefixIcon: Icon(Icons.all_inclusive_sharp,
                                color: Color(0xff8f94fb)),
                            enabledBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: Color(0xff8f94fb)),
                              borderRadius: BorderRadius.circular(7.0),
                            ),
                            focusedBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: Color(0xff8f94fb)),
                            ),
                            contentPadding: EdgeInsets.fromLTRB(20, 15, 20, 15),
                            hintText: "Speciality",
                          )),
                      SizedBox(height: 20),
                      TextField(
                          controller: _doctorEducation,
                          cursorColor: Color(0xff8f94fb),
                          autofocus: false,
                          keyboardType: TextInputType.text,
                          textInputAction: TextInputAction.done,
                          decoration: InputDecoration(
                            prefixIcon:
                                Icon(Icons.school, color: Color(0xff8f94fb)),
                            enabledBorder: OutlineInputBorder(
                              borderSide: BorderSide(
                                color: Color(0xff8f94fb),
                              ),
                              borderRadius: BorderRadius.circular(7.0),
                            ),
                            focusedBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: Color(0xff8f94fb)),
                            ),
                            contentPadding: EdgeInsets.fromLTRB(20, 15, 20, 15),
                            hintText: "Enter Education",
                          )),
                    ],
                  ),
                ),
                actions: <Widget>[
                  FlatButton(
                    onPressed: () async {
                      Map<String, dynamic> doctorList = {
                        "doctorName": _doctorName.text,
                        "doctorPost": _doctorPost.text,
                        "doctorSpeciality": _doctorSpeciality.text,
                        "doctorEducation": _doctorEducation.text,
                      };
                      _firestoreDBDoctorList
                          .doc(_auth.currentUser!.uid)
                          .collection('doctorList')
                          .add(doctorList);

                      _doctorName.clear();
                      _doctorPost.clear();
                      _doctorSpeciality.clear();
                      _doctorEducation.clear();

                      Navigator.of(ctx).pop();
                    },
                    child: Text("Done"),
                  ),
                  SizedBox(width: size.width / 5),
                  FlatButton(
                    onPressed: () {
                      _doctorName.clear();
                      _doctorPost.clear();
                      _doctorSpeciality.clear();
                      _doctorEducation.clear();
                      Navigator.of(ctx).pop();
                    },
                    child: Text("Cancel"),
                  ),
                ],
              ),
            );
          },
        ),
      ),
    );
  }
}
