// // // ignore_for_file: prefer_const_constructors, use_key_in_widget_constructors
// //
// // import 'package:cloud_firestore/cloud_firestore.dart';
// // import 'package:firebase_auth/firebase_auth.dart';
// // import 'package:flutter/cupertino.dart';
// // import 'package:flutter/material.dart';
// // import 'package:hospital_app/Authentication/Methods.dart';
// // import 'package:hospital_app/utils/routes.dart';
// // import 'package:hospital_app/utils/storage_service.dart';
// //
// // class MyDrawer extends StatefulWidget {
// //   @override
// //   State<MyDrawer> createState() => _MyDrawerState();
// // }
// //
// // class _MyDrawerState extends State<MyDrawer> {
// //   final Storage storage = Storage();
// //   late final Map<String, dynamic> userMap;
// //   final FirebaseAuth _auth = FirebaseAuth.instance;
// //   final FirebaseFirestore _firestoreDBUserProf = FirebaseFirestore.instance;
// //   @override
// //   Widget build(BuildContext context) {
// //     return Drawer(
// //         child: Container(
// //           color: Color.fromRGBO(254, 23, 72, 1),
// //           child: ListView(padding: EdgeInsets.zero, children: [
// //             Container(
// //               color: Color.fromRGBO(254, 23, 72, 1),
// //               child: DrawerHeader(
// //                 padding: EdgeInsets.zero,
// //                 child: StreamBuilder<DocumentSnapshot>(
// //                   stream: _firestoreDBUserProf
// //                       .collection("users")
// //                       .doc(_auth.currentUser!.uid)
// //                       .snapshots(),
// //                   builder: (BuildContext context, AsyncSnapshot snapshot) {
// //                     future:
// //                     storage.downloadURL('Profile_pic.jpg');
// //                     // if (!snapshot.hasData) return CircularProgressIndicator();
// //                     if (snapshot.connectionState == ConnectionState.done &&
// //                         snapshot.hasData) {
// //                       UserAccountsDrawerHeader(
// //                         decoration: BoxDecoration(color: Colors.blue),
// //                         accountName: Text(
// //                           snapshot.data!['name'],
// //                           style: TextStyle(fontSize: 5, color: Colors.white),
// //                         ),
// //                         accountEmail: Text(
// //                           snapshot.data!['email'],
// //                           style: TextStyle(fontSize: 5, color: Colors.black),
// //                         ),
// //                         currentAccountPicture: CircleAvatar(
// //                           radius: 10,
// //                           backgroundImage: NetworkImage(
// //                             snapshot.data!,
// //                           ),
// //                           //'https://images.ctfassets.net/6rsj5ae0g75g/6nf3rNaaVaUqYcoAcciSeC/a43b6f3da7352837e0db54dc86339420/Last_few_hours_more_for_FlutterLive._Join_us_from_anywhere_around_the_world._Flutter_Excitement_flutterio.jpg?w=450&fl=progressive&q=100',
// //                         ),
// //                       );
// //                     }
// //                     // if (snapshot.connectionState == ConnectionState.waiting ||
// //                     //   !snapshot.hasData)
// //                     return Container(
// //                         alignment: Alignment.center,
// //                         child: CircularProgressIndicator(
// //                           color: Colors.white,
// //                         ));
// //                   },
// //                 ),
// //               ),
// //             ),
// //             ListTile(
// //               leading: Icon(
// //                 CupertinoIcons.profile_circled,
// //                 color: Colors.white,
// //               ),
// //               title: Text("Profile",
// //                   textScaleFactor: 1.2,
// //                   style: TextStyle(
// //                     color: Colors.white,
// //                   )),
// //               onTap: () {
// //                 Navigator.pushNamed(context, MyRoute.profileRoute);
// //               },
// //             ),
// //             ListTile(
// //               leading: Icon(
// //                 CupertinoIcons.doc_plaintext,
// //                 color: Colors.white,
// //               ),
// //               title: Text("Appointment",
// //                   textScaleFactor: 1.2,
// //                   style: TextStyle(
// //                     color: Colors.white,
// //                   )),
// //               onTap: () {
// //                 Navigator.pushNamed(context, MyRoute.appointmentListRoute);
// //               },
// //             ),
// //             ListTile(
// //               leading: Icon(
// //                 CupertinoIcons.doc_person,
// //                 color: Colors.white,
// //               ),
// //               title: Text("Report",
// //                   textScaleFactor: 1.2,
// //                   style: TextStyle(
// //                     color: Colors.white,
// //                   )),
// //               onTap: () {
// //                 Navigator.pushNamed(context, MyRoute.reportRoute);
// //               },
// //             ),
// //             // ListTile(
// //             //   leading: Icon(
// //             //     CupertinoIcons.bell,
// //             //     color: Colors.white,
// //             //   ),
// //             //   title: Text("Reminder",
// //             //       textScaleFactor: 1.2,
// //             //       style: TextStyle(
// //             //         color: Colors.white,
// //             //       )),
// //             //   onTap: () {},
// //             // ),
// //             ListTile(
// //               leading: Icon(
// //                 CupertinoIcons.settings,
// //                 color: Colors.white,
// //               ),
// //               title: Text("Settings",
// //                   textScaleFactor: 1.2,
// //                   style: TextStyle(
// //                     color: Colors.white,
// //                   )),
// //               onTap: () {
// //                 Navigator.pushNamed(context, MyRoute.settingRoute);
// //               },
// //             ),
// //             ListTile(
// //               leading: Icon(
// //                 Icons.logout,
// //                 color: Colors.white,
// //               ),
// //               title: Text("Log Out",
// //                   textScaleFactor: 1.2,
// //                   style: TextStyle(
// //                     color: Colors.white,
// //                   )),
// //               onTap: () {
// //                 logOut(context);
// //               },
// //             ),
// //           ]),
// //         ));
// //   }
// //
// // // Widget error(snapshot) {
// // //   if (snapshot.connectionState == ConnectionState.waiting ||
// // //       !snapshot.hasData) {
// // //     return Container(
// // //       alignment: Alignment.center,
// // //       width: 10,
// // //       height: 10,
// // //       child: CircularProgressIndicator(
// // //         color: Colors.white,
// // //       ),
// // //     );
// // //   }
// // //   ;
// // // }
// // }
//
// // ignore_for_file: prefer_const_constructors, use_key_in_widget_constructors, use_function_type_syntax_for_parameters
//
// //import 'dart:html';
//
// import 'package:cloud_firestore/cloud_firestore.dart';
// import 'package:firebase_auth/firebase_auth.dart';
// import 'package:firebase_storage/firebase_storage.dart';
// import 'package:flutter/cupertino.dart';
// import 'package:flutter/material.dart';
// import 'package:flutter_launcher_icons/utils.dart';
// import 'package:hospital_app/Authentication/Methods.dart';
// import 'package:hospital_app/utils/routes.dart';
// import 'package:hospital_app/utils/storage_service.dart';
// //import {getStorage , ref} from 'firebase/storage';
//
// class MyDrawer extends StatefulWidget {
//   @override
//   State<MyDrawer> createState() => _MyDrawerState();
// }
//
// class _MyDrawerState extends State<MyDrawer> {
//   var _image1;
//   final Storage storage = Storage();
//   late final Map<String, dynamic> userMap;
//   final FirebaseAuth _auth = FirebaseAuth.instance;
//   final FirebaseFirestore _firestoreDBUserProf = FirebaseFirestore.instance;
//   @override
//   Widget build(BuildContext context) {
//     return Drawer(
//         child: Container(
// <<<<<<< HEAD
//       color: Colors.blueAccent,
//       //color: Color.fromRGBO(254, 23, 72, 1),
//       child: ListView(padding: EdgeInsets.zero, children: [
//         DrawerHeader(
//           padding: EdgeInsets.zero,
//           child: StreamBuilder<DocumentSnapshot>(
//             stream: _firestoreDBUserProf
//                 .collection("users")
//                 .doc(_auth.currentUser!.uid)
//                 .snapshots(),
//             builder: (BuildContext context, AsyncSnapshot snapshot) {
//               future:
//               storage.downloadURL('Profile_pic.jpg');
//               // if (!snapshot.hasData) return CircularProgressIndicator();
//               if (snapshot.hasData) {
//                 return UserAccountsDrawerHeader(
//                   decoration: BoxDecoration(
//                     color: Colors.blueAccent,
//                     //color: Color.fromRGBO(254, 23, 72, 1)
//                   ),
//                   accountName: Text(
//                     snapshot.data!['name'],
//                     style: TextStyle(fontSize: 20, color: Colors.white),
//                   ),
//                   accountEmail: Text(
//                     snapshot.data!['email'],
//                     style: TextStyle(fontSize: 15, color: Colors.white),
//                   ),
//                   currentAccountPicture: CircleAvatar(
//                     radius: 10,
//                     backgroundImage: NetworkImage(
//                       storage
//                           .downloadURL('Profile_pic.jpg')
//                           .toString(), //picture().toString(),
//                     ),
//                     //'https://images.ctfassets.net/6rsj5ae0g75g/6nf3rNaaVaUqYcoAcciSeC/a43b6f3da7352837e0db54dc86339420/Last_few_hours_more_for_FlutterLive._Join_us_from_anywhere_around_the_world._Flutter_Excitement_flutterio.jpg?w=450&fl=progressive&q=100',
//                   ),
//                 );
//               } else {
//                 return Container(
//                     alignment: Alignment.center,
//                     child: CircularProgressIndicator(
//                       color: Colors.white,
//                     ));
//               }
//             },
//           ),
//         ),
//         ListTile(
//           leading: Icon(
//             CupertinoIcons.profile_circled,
//             color: Colors.white,
//           ),
//           title: Text("Profile",
//               textScaleFactor: 1.2,
//               style: TextStyle(
// =======
//           color: Color.fromRGBO(254, 23, 72, 1),
//           child: ListView(padding: EdgeInsets.zero, children: [
//             Container(
//               color: Color.fromRGBO(254, 23, 72, 1),
//               child: DrawerHeader(
//                 padding: EdgeInsets.zero,
//                 child: StreamBuilder<DocumentSnapshot>(
//                   stream: _firestoreDBUserProf
//                       .collection("users")
//                       .doc(_auth.currentUser!.uid)
//                       .snapshots(),
//                   builder: (BuildContext context, AsyncSnapshot snapshot) {
//                     future:
//                     storage.downloadURL('Profile_pic.jpg');
//                     // if (!snapshot.hasData) return CircularProgressIndicator();
//                     if (snapshot.hasData) {
//                       return UserAccountsDrawerHeader(
//                         decoration: BoxDecoration(color: Color.fromRGBO(254, 23, 72, 1),
//                             image: DecorationImage(
//                                 image: AssetImage("Assets/images/doctor.png"),
//                                 fit: BoxFit.fitHeight),
//                         ),
//                         accountName: Text(
//                           snapshot.data!['name'],
//                           style: TextStyle(fontSize: 20, color: Colors.white),
//                         ),
//                         accountEmail: Text(
//                           snapshot.data!['email'],
//                           style: TextStyle(fontSize: 15, color: Colors.white),
//                         ),
//                         // currentAccountPictureSize: Size.square(75),
//                         // currentAccountPicture: CircleAvatar(
//                         //   radius: 20,
//                         //     backgroundImage:
//                         //     NetworkImage("https://github.com/Tushar-Shirbhate/Hospital-App/blob/main/Assets/images/doctor.png")
//                             // backgroundImage: DecorationImage(
//                             //     image: AssetImage("Assets/images/doctor.png"),
//                             //     fit: BoxFit.cover),
//                           // backgroundImage: NetworkImage(
//                           //   storage
//                           //       .downloadURL('Profile_pic.jpg')
//                           //       .toString(), //picture().toString(),
//                           // ),
//                           //'https://images.ctfassets.net/6rsj5ae0g75g/6nf3rNaaVaUqYcoAcciSeC/a43b6f3da7352837e0db54dc86339420/Last_few_hours_more_for_FlutterLive._Join_us_from_anywhere_around_the_world._Flutter_Excitement_flutterio.jpg?w=450&fl=progressive&q=100',
//                         // ),
//                       );
//                     } else {
//                       return Container(
//                           alignment: Alignment.center,
//                           child: CircularProgressIndicator(
//                             color: Colors.white,
//                           ));
//                     }
//                   },
//                 ),
//               ),
//             ),
//             ListTile(
//               leading: Icon(
//                 CupertinoIcons.profile_circled,
// >>>>>>> ad3d5c6c7b8152a10fae5493cd0488cd7cf05fca
//                 color: Colors.white,
//               ),
//               title: Text("Profile",
//                   textScaleFactor: 1.2,
//                   style: TextStyle(
//                     color: Colors.white,
//                   )),
//               onTap: () {
//                 Navigator.pushNamed(context, MyRoute.profileRoute);
//               },
//             ),
//             // ListTile(
//             //   leading: Icon(
//             //     CupertinoIcons.heart_fill,
//             //     color: Colors.white,
//             //   ),
//             //   title: Text("Favourite",
//             //       textScaleFactor: 1.2,
//             //       style: TextStyle(
//             //         color: Colors.white,
//             //       )),
//             //   onTap: () {
//             //     Navigator.pushNamed(context, MyRoute.starredRoute);
//             //   },
//             // ),
//             ListTile(
//               leading: Icon(
//                 CupertinoIcons.doc_plaintext,
//                 color: Colors.white,
//               ),
//               title: Text("Appointment",
//                   textScaleFactor: 1.2,
//                   style: TextStyle(
//                     color: Colors.white,
//                   )),
//               onTap: () {
//                 Navigator.pushNamed(context, MyRoute.appointmentListRoute);
//               },
//             ),
//             ListTile(
//               leading: Icon(
//                 CupertinoIcons.doc_person,
//                 color: Colors.white,
//               ),
//               title: Text("Report",
//                   textScaleFactor: 1.2,
//                   style: TextStyle(
//                     color: Colors.white,
//                   )),
//               onTap: () {
//                 Navigator.pushNamed(context, MyRoute.reportRoute);
//               },
//             ),
//             // ListTile(
//             //   leading: Icon(
//             //     CupertinoIcons.bell,
//             //     color: Colors.white,
//             //   ),
//             //   title: Text("Reminder",
//             //       textScaleFactor: 1.2,
//             //       style: TextStyle(
//             //         color: Colors.white,
//             //       )),
//             //   onTap: () {},
//             // ),
//             ListTile(
//               leading: Icon(
//                 CupertinoIcons.settings,
//                 color: Colors.white,
//               ),
//               title: Text("Settings",
//                   textScaleFactor: 1.2,
//                   style: TextStyle(
//                     color: Colors.white,
//                   )),
//               onTap: () {
//                 Navigator.pushNamed(context, MyRoute.settingRoute);
//               },
//             ),
//             ListTile(
//               leading: Icon(
//                 Icons.logout,
//                 color: Colors.white,
//               ),
//               title: Text("Log Out",
//                   textScaleFactor: 1.2,
//                   style: TextStyle(
//                     color: Colors.white,
//                   )),
//               onTap: () {
//                 logOut(context);
//               },
//             ),
//           ]),
//         ));
//   }
//
//   // Future<String> picture() {
//   //   FirebaseStorage storage = FirebaseStorage.instance;
//   //   Reference ref = storage.ref('/test').child('Profile_pic.');
//   //   Future<String> downloadTask = ref.getDownloadURL();
//   //   // downloadTask.then((res) {
//   //   //   res.ref.getDownloadURL();
//   //   // });
//   //   var dowurl = downloadTask;
//   //   return dowurl;
//   // }
//   Future<String> picture() {
//     FirebaseStorage storage = FirebaseStorage.instance;
//     Reference ref = storage.ref('/test/Profile_pic.jpg');
//     Future<String> downloadTask = ref.getDownloadURL();
//     // downloadTask.then((res) {
//     //   res.ref.getDownloadURL();
//     // });
//     var dowurl = downloadTask;
//     return dowurl;
//   }
// }
// // ignore_for_file: prefer_const_constructors, use_key_in_widget_constructors
//
// import 'package:cloud_firestore/cloud_firestore.dart';
// import 'package:firebase_auth/firebase_auth.dart';
// import 'package:flutter/cupertino.dart';
// import 'package:flutter/material.dart';
// import 'package:hospital_app/Authentication/Methods.dart';
// import 'package:hospital_app/utils/routes.dart';
// import 'package:hospital_app/utils/storage_service.dart';
//
// class MyDrawer extends StatefulWidget {
//   @override
//   State<MyDrawer> createState() => _MyDrawerState();
// }
//
// class _MyDrawerState extends State<MyDrawer> {
//   final Storage storage = Storage();
//   late final Map<String, dynamic> userMap;
//   final FirebaseAuth _auth = FirebaseAuth.instance;
//   final FirebaseFirestore _firestoreDBUserProf = FirebaseFirestore.instance;
//   @override
//   Widget build(BuildContext context) {
//     return Drawer(
//         child: Container(
//           color: Color.fromRGBO(254, 23, 72, 1),
//           child: ListView(padding: EdgeInsets.zero, children: [
//             Container(
//               color: Color.fromRGBO(254, 23, 72, 1),
//               child: DrawerHeader(
//                 padding: EdgeInsets.zero,
//                 child: StreamBuilder<DocumentSnapshot>(
//                   stream: _firestoreDBUserProf
//                       .collection("users")
//                       .doc(_auth.currentUser!.uid)
//                       .snapshots(),
//                   builder: (BuildContext context, AsyncSnapshot snapshot) {
//                     future:
//                     storage.downloadURL('Profile_pic.jpg');
//                     // if (!snapshot.hasData) return CircularProgressIndicator();
//                     if (snapshot.connectionState == ConnectionState.done &&
//                         snapshot.hasData) {
//                       UserAccountsDrawerHeader(
//                         decoration: BoxDecoration(color: Colors.blue),
//                         accountName: Text(
//                           snapshot.data!['name'],
//                           style: TextStyle(fontSize: 5, color: Colors.white),
//                         ),
//                         accountEmail: Text(
//                           snapshot.data!['email'],
//                           style: TextStyle(fontSize: 5, color: Colors.black),
//                         ),
//                         currentAccountPicture: CircleAvatar(
//                           radius: 10,
//                           backgroundImage: NetworkImage(
//                             snapshot.data!,
//                           ),
//                           //'https://images.ctfassets.net/6rsj5ae0g75g/6nf3rNaaVaUqYcoAcciSeC/a43b6f3da7352837e0db54dc86339420/Last_few_hours_more_for_FlutterLive._Join_us_from_anywhere_around_the_world._Flutter_Excitement_flutterio.jpg?w=450&fl=progressive&q=100',
//                         ),
//                       );
//                     }
//                     // if (snapshot.connectionState == ConnectionState.waiting ||
//                     //   !snapshot.hasData)
//                     return Container(
//                         alignment: Alignment.center,
//                         child: CircularProgressIndicator(
//                           color: Colors.white,
//                         ));
//                   },
//                 ),
//               ),
//             ),
//             ListTile(
//               leading: Icon(
//                 CupertinoIcons.profile_circled,
//                 color: Colors.white,
//               ),
//               title: Text("Profile",
//                   textScaleFactor: 1.2,
//                   style: TextStyle(
//                     color: Colors.white,
//                   )),
//               onTap: () {
//                 Navigator.pushNamed(context, MyRoute.profileRoute);
//               },
//             ),
//             ListTile(
//               leading: Icon(
//                 CupertinoIcons.doc_plaintext,
//                 color: Colors.white,
//               ),
//               title: Text("Appointment",
//                   textScaleFactor: 1.2,
//                   style: TextStyle(
//                     color: Colors.white,
//                   )),
//               onTap: () {
//                 Navigator.pushNamed(context, MyRoute.appointmentListRoute);
//               },
//             ),
//             ListTile(
//               leading: Icon(
//                 CupertinoIcons.doc_person,
//                 color: Colors.white,
//               ),
//               title: Text("Report",
//                   textScaleFactor: 1.2,
//                   style: TextStyle(
//                     color: Colors.white,
//                   )),
//               onTap: () {
//                 Navigator.pushNamed(context, MyRoute.reportRoute);
//               },
//             ),
//             // ListTile(
//             //   leading: Icon(
//             //     CupertinoIcons.bell,
//             //     color: Colors.white,
//             //   ),
//             //   title: Text("Reminder",
//             //       textScaleFactor: 1.2,
//             //       style: TextStyle(
//             //         color: Colors.white,
//             //       )),
//             //   onTap: () {},
//             // ),
//             ListTile(
//               leading: Icon(
//                 CupertinoIcons.settings,
//                 color: Colors.white,
//               ),
//               title: Text("Settings",
//                   textScaleFactor: 1.2,
//                   style: TextStyle(
//                     color: Colors.white,
//                   )),
//               onTap: () {
//                 Navigator.pushNamed(context, MyRoute.settingRoute);
//               },
//             ),
//             ListTile(
//               leading: Icon(
//                 Icons.logout,
//                 color: Colors.white,
//               ),
//               title: Text("Log Out",
//                   textScaleFactor: 1.2,
//                   style: TextStyle(
//                     color: Colors.white,
//                   )),
//               onTap: () {
//                 logOut(context);
//               },
//             ),
//           ]),
//         ));
//   }
//
// // Widget error(snapshot) {
// //   if (snapshot.connectionState == ConnectionState.waiting ||
// //       !snapshot.hasData) {
// //     return Container(
// //       alignment: Alignment.center,
// //       width: 10,
// //       height: 10,
// //       child: CircularProgressIndicator(
// //         color: Colors.white,
// //       ),
// //     );
// //   }
// //   ;
// // }
// }

// ignore_for_file: prefer_const_constructors, use_key_in_widget_constructors, use_function_type_syntax_for_parameters

//import 'dart:html';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_launcher_icons/utils.dart';
import 'package:hospital_app/Authentication/Methods.dart';
import 'package:hospital_app/utils/routes.dart';
import 'package:hospital_app/utils/storage_service.dart';
//import {getStorage , ref} from 'firebase/storage';

class MyDrawer extends StatefulWidget {
  @override
  State<MyDrawer> createState() => _MyDrawerState();
}

class _MyDrawerState extends State<MyDrawer> {
  var _image1;
  final Storage storage = Storage();
  late final Map<String, dynamic> userMap;
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final FirebaseFirestore _firestoreDBUserProf = FirebaseFirestore.instance;
  @override
  Widget build(BuildContext context) {
    return Drawer(
        child: Container(
      color: Color(0xff8f94fb),
      child: ListView(padding: EdgeInsets.zero, children: [
        Container(
          color: Color(0xff8f94fb),
          child: DrawerHeader(
            padding: EdgeInsets.zero,
            child: StreamBuilder<DocumentSnapshot>(
              stream: _firestoreDBUserProf
                  .collection("users")
                  .doc(_auth.currentUser!.uid)
                  .snapshots(),
              builder: (BuildContext context, AsyncSnapshot snapshot) {
                future:
                storage.downloadURL('Profile_pic.jpg');

                if (snapshot.hasData) {
                  return UserAccountsDrawerHeader(
                    decoration: BoxDecoration(
                      color: Color(0xff8f94fb),
                    ),
                    accountName: Text(
                      snapshot.data!['name'],
                      style: TextStyle(fontSize: 20, color: Colors.white),
                    ),
                    accountEmail: Text(
                      snapshot.data!['email'],
                      style: TextStyle(fontSize: 15, color: Colors.white),
                    ),
                  );
                } else {
                  return Container(
                      alignment: Alignment.center,
                      child: CircularProgressIndicator(
                        color: Colors.white,
                      ));
                }
              },
            ),
          ),
        ),
        ListTile(
          leading: Icon(
            CupertinoIcons.profile_circled,
            color: Colors.white,
          ),
          title: Text("Profile",
              textScaleFactor: 1.2,
              style: TextStyle(
                color: Colors.white,
              )),
          onTap: () {
            Navigator.pushNamed(context, MyRoute.profileRoute);
          },
        ),
        ListTile(
          leading: Icon(
            CupertinoIcons.doc_plaintext,
            color: Colors.white,
          ),
          title: Text("Appointment",
              textScaleFactor: 1.2,
              style: TextStyle(
                color: Colors.white,
              )),
          onTap: () {
            Navigator.pushNamed(context, MyRoute.appointmentListRoute);
          },
        ),
        ListTile(
          leading: Icon(
            CupertinoIcons.doc_person,
            color: Colors.white,
          ),
          title: Text("Report",
              textScaleFactor: 1.2,
              style: TextStyle(
                color: Colors.white,
              )),
          onTap: () {
            Navigator.pushNamed(context, MyRoute.reportRoute);
          },
        ),
        ListTile(
          leading: Icon(
            Icons.logout,
            color: Colors.white,
          ),
          title: Text("Log Out",
              textScaleFactor: 1.2,
              style: TextStyle(
                color: Colors.white,
              )),
          onTap: () {
            logOut(context);
          },
        ),
      ]),
    ));
  }
}
