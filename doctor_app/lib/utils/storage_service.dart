// import 'dart:io';
// import 'package:firebase_storage/firebase_storage.dart' as firebase_storage;
// import 'package:firebase_core/firebase_core.dart' as firebase_core;
// import 'package:firebase_storage/firebase_storage.dart';
// import 'package:flutter/cupertino.dart';
//
// class Storage {
//   final firebase_storage.FirebaseStorage storage =
//       firebase_storage.FirebaseStorage.instance;
//
//   Future<void> uploadFile(
//     String filePath,
//     String fileName,
//   ) async {
//     File file = File(filePath);
//     // Reference ref = storage.ref('/test').child('Profile_pic.');
//     // UploadTask uploadTask = ref.putFile(file);
//     // uploadTask.then((res) {
//     //   res.ref.getDownloadURL();
//     // });
//     // var dowurl = uploadTask.toString();
//     // return dowurl;
//
//     try {
//       await storage.ref('test/$fileName').putFile(file);
//     } on firebase_core.FirebaseException catch (e) {
//       print(e);
//     }
//   }
//
//   Future<String> downloadURL(String imageName) async {
//     String downloadURL = await storage.ref('test/$imageName').getDownloadURL();
//
//     return downloadURL;
//   }
// }
import 'dart:io';
import 'package:firebase_storage/firebase_storage.dart' as firebase_storage;
import 'package:firebase_core/firebase_core.dart' as firebase_core;
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/cupertino.dart';

class Storage {
  final firebase_storage.FirebaseStorage storage =
      firebase_storage.FirebaseStorage.instance;

  Future<void> uploadFile(
      String filePath,
      String fileName,
      ) async {
    File file = File(filePath);
    // Reference ref = storage.ref('/test').child('Profile_pic.');
    // UploadTask uploadTask = ref.putFile(file);
    // uploadTask.then((res) {
    //   res.ref.getDownloadURL();
    // });
    // var dowurl = uploadTask.toString();
    // return dowurl;

    try {
      await storage.ref('test/$fileName').putFile(file);
    } on firebase_core.FirebaseException catch (e) {
      print(e);
    }
  }

  Future<String> downloadURL(String imageName) async {
    String downloadURL = await storage.ref('test/$imageName').getDownloadURL();

    return downloadURL;
  }
}