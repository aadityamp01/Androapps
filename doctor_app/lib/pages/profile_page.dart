// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:hospital_app/Authentication/Methods.dart';
import 'package:hospital_app/utils/storage_service.dart';
import 'package:hospital_app/widgets/profile_widget.dart';
import 'package:uuid/uuid.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_storage/firebase_storage.dart';

class ProfilePage extends StatefulWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  // ignore: no_logic_in_create_state
  _ProfilePageState createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  late final Map<String, dynamic> userMap;
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final FirebaseFirestore _firestoreDBUserProf = FirebaseFirestore.instance;
  final Storage storage = Storage();

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
          ),
        ],
      ),
      body: StreamBuilder<DocumentSnapshot>(
          stream: _firestoreDBUserProf
              .collection("users")
              .doc(_auth.currentUser!.uid)
              .snapshots(),
          builder: (BuildContext context, snapshot) {
            if (!snapshot.hasData) return CircularProgressIndicator();
            return ListView(physics: BouncingScrollPhysics(), children: [
              Container(
                color: Colors.white,
                child: buildName(snapshot),
              ),
              const SizedBox(height: 8),
              Container(
                color: Colors.white,
                child: Column(
                  children: [
                    bulidPhoneNo(snapshot),
                    Divider(),
                    buildAddress(snapshot),
                  ],
                ),
              ),
            ]);
          }),
    );
  }

  buildName(snapshot) => Padding(
        padding: const EdgeInsets.fromLTRB(8, 0, 8, 8),
        child: Column(
          children: [
            const SizedBox(height: 24),
            CircleAvatar(
                backgroundColor: Color(0xff8f94fb),
                backgroundImage: AssetImage("Assets/images/patient_2.png"),
                radius: 50),
            const SizedBox(height: 10),
            Text(
              snapshot.data!['name'],
              style: TextStyle(
                color: Colors.black,
                fontWeight: FontWeight.bold,
                fontSize: 20,
              ),
            ),
            const SizedBox(height: 4),
            Text(
              snapshot.data!['email'],
              style: TextStyle(
                color: Color.fromARGB(255, 155, 155, 155),
                fontWeight: FontWeight.bold,
                fontSize: 16,
              ),
            )
          ],
        ),
      );

  buildAddress(snapshot) => Padding(
        padding: const EdgeInsets.fromLTRB(8, 8, 8, 8),
        child: Container(
          padding: EdgeInsets.symmetric(horizontal: 12),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Icon(
                    CupertinoIcons.building_2_fill,
                    color: Colors.black,
                  ),
                  Text(
                    ' Address',
                    style: TextStyle(
                        color: Colors.black,
                        fontSize: 20,
                        fontWeight: FontWeight.bold),
                  ),
                ],
              ),
              Text(
                snapshot.data!['address'],
                style: TextStyle(
                    color: Color.fromARGB(255, 155, 155, 155),
                    fontWeight: FontWeight.bold,
                    fontSize: 16,
                    height: 1.4),
              )
            ],
          ),
        ),
      );

  bulidPhoneNo(snapshot) => Padding(
        padding: const EdgeInsets.fromLTRB(8, 8, 8, 0),
        child: Container(
          color: Colors.white,
          padding: EdgeInsets.symmetric(horizontal: 12),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Icon(
                    Icons.call,
                    color: Colors.black,
                  ),
                  Text(
                    ' Phone number',
                    style: TextStyle(
                        color: Colors.black,
                        fontSize: 20,
                        fontWeight: FontWeight.bold),
                  ),
                ],
              ),
              Text(
                snapshot.data!['phoneNo'],
                style: TextStyle(
                    color: Color.fromARGB(255, 155, 155, 155),
                    fontWeight: FontWeight.bold,
                    fontSize: 16,
                    height: 1.4),
              )
            ],
          ),
        ),
      );
}
