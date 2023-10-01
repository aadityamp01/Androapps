import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:google_sign_in/google_sign_in.dart';

class AuthService{
  signInWithGoogle() async{
    final GoogleSignInAccount? gUser = await GoogleSignIn().signIn();

    final GoogleSignInAuthentication gAuth = await gUser!.authentication;

    final credential = GoogleAuthProvider.credential(
      accessToken: gAuth.accessToken,
      idToken: gAuth.idToken,
    );
    return FirebaseAuth.instance.signInWithCredential(credential).then((value) =>
    {
      FirebaseFirestore.instance.collection("UserData").doc(value.user?.uid).set(
          {"name": value.user?.displayName,"email": value.user?.email})
    });
  }
}