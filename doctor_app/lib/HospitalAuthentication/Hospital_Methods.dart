import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:hospital_app/HospitalAuthentication/hospital_login_screen.dart';

Future<User?> hospitalSignUp(String name, String phoneNo, String email,
    String password, String address) async {
  FirebaseAuth _auth = FirebaseAuth.instance;

  FirebaseFirestore _firestore = FirebaseFirestore.instance;

  try {
    UserCredential userCredential = await _auth.createUserWithEmailAndPassword(
        email: email, password: password);

    print("Account Created Successfully");

    userCredential.user!.updateDisplayName(name);

    await _firestore.collection('users').doc(_auth.currentUser!.uid).set({
      "name": name,
      "phoneNo": phoneNo,
      "email": email,
      "profession": "Hospital",
      "address": address,
      "status": "unavailable",
      "uid": _auth.currentUser!.uid
    });
    return userCredential.user;
  } catch (e) {
    print(e);
    return null;
  }
}

Future<User?> hospitalLogIn(String email, String password) async {
  FirebaseAuth _auth = FirebaseAuth.instance;
  FirebaseFirestore _firestore = FirebaseFirestore.instance;

  try {
    UserCredential userCredential = await _auth.signInWithEmailAndPassword(
        email: email, password: password);

    print("Login Successfully");

    _firestore
        .collection('users')
        .doc(_auth.currentUser!.uid)
        .get()
        .then((value) {
      userCredential.user!.updateDisplayName(value['name']);
    });

    return userCredential.user;
  } catch (e) {
    print(e);
    return null;
  }
}

Future hospitalLogOut(BuildContext context) async {
  FirebaseAuth _auth = FirebaseAuth.instance;

  try {
    await _auth.signOut().then((value) {
      Navigator.push(
          context, MaterialPageRoute(builder: (_) => HospitalLoginScreen()));
    });
  } catch (e) {
    print(e);
  }
}
