// ignore_for_file: prefer_const_constructors, use_key_in_widget_constructors

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:hospital_app/Authentication/Methods.dart';
import 'package:hospital_app/pages/hospital_detail_page.dart';
import 'package:hospital_app/utils/routes.dart';
import 'package:hospital_app/utils/screen_arguments_doctor_list.dart';
import 'package:hospital_app/widgets/drawer.dart';
import 'package:hospital_app/widgets/drawer.dart';

class HomePage extends StatefulWidget {
  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  var firestoreDB = FirebaseFirestore.instance.collection("users").snapshots();

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 248, 243, 247),
      appBar: AppBar(
        title: Padding(
          padding: const EdgeInsets.fromLTRB(70, 0, 15, 0),
          child: Text(
            "Hospital App",
            style: TextStyle(
              fontWeight: FontWeight.bold,
              fontSize: 24,
              color: Colors.white,
            ),
          ),
        ),
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
        backgroundColor: Color(0xff8f94fb),
        iconTheme: IconThemeData(color: Colors.white),
        //elevation: 0,
      ),
      body: StreamBuilder(
          stream: firestoreDB,
          builder: (context, snapshot) {
            if (!snapshot.hasData)
              return Center(child: CircularProgressIndicator());
            return ListView.builder(
                itemCount: (snapshot.data! as QuerySnapshot).docs.length,
                itemBuilder: (BuildContext context, int index) {
                  if ((snapshot.data! as QuerySnapshot).docs[index]
                          ['profession'] ==
                      "Hospital") {
                    String hospitalEmail =
                        (snapshot.data! as QuerySnapshot).docs[index]['email'];
                    String hospitalPhoneNo = (snapshot.data! as QuerySnapshot)
                        .docs[index]['phoneNo'];
                    String id =
                        (snapshot.data! as QuerySnapshot).docs[index]['uid'];
                    return SingleChildScrollView(
                      padding: EdgeInsets.fromLTRB(10, 10, 10, 5),
                      child: InkWell(
                        onTap: () {
                          Navigator.pushNamed(context, MyRoute.detailRoute,
                              arguments: ScreenArgumentsDoctorList(
                                  id, hospitalEmail, hospitalPhoneNo));
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
                              padding: EdgeInsets.all(10),
                              height: size.height / 6.8,
                              width: double.infinity,
                              child: Column(
                                  mainAxisAlignment: MainAxisAlignment.start,
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    Text(
                                        "${(snapshot.data! as QuerySnapshot).docs[index]['name']}",
                                        style: TextStyle(
                                            //color: Colors.deepOrange,
                                            color: Colors.black,
                                            fontSize: 20,
                                            fontWeight: FontWeight.bold)),
                                    Text(
                                        "${(snapshot.data! as QuerySnapshot).docs[index]['address']}",
                                        style: TextStyle(
                                            color: Color.fromARGB(
                                                255, 155, 155, 155),
                                            fontSize: 16,
                                            fontStyle: FontStyle.italic)),
                                  ]),
                              //   Icon(CupertinoIcons.heart),
                              // ]
                              //   )
                            )),
                      ),
                      //  );
                    );
                  }
                  return Container();
                });
          }),
      drawer: MyDrawer(),
    );
  }
}
