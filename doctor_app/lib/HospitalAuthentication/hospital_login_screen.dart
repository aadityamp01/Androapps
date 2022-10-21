import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:hospital_app/HospitalAuthentication/Hospital_Methods.dart';
import 'package:hospital_app/HospitalAuthentication/hospital_registration_screen.dart';
import 'package:hospital_app/pages/DoctorHomePage.dart';
import 'package:hospital_app/pages/home_page.dart';

class HospitalLoginScreen extends StatefulWidget {
  @override
  _HospitalLoginScreenState createState() => _HospitalLoginScreenState();
}

class _HospitalLoginScreenState extends State<HospitalLoginScreen> {
  final TextEditingController _email = TextEditingController();
  final TextEditingController _password = TextEditingController();
  bool isLoading = false;
  @override
  Widget build(BuildContext context) {
    bool obs_text = true;
    final size = MediaQuery.of(context).size;

    return Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          automaticallyImplyLeading: false,
          leading: IconButton(
            icon: Icon(Icons.arrow_back_ios_outlined, color: Colors.white),
            onPressed: () => Navigator.pushNamed(context, "/"),
          ),
          title: Center(
            child: Text(
              "Hospital App",
              style: TextStyle(color: Colors.white),
            ),
          ),
          actions: [
            Container(
              child: IconButton(
                  onPressed: () {},
                  icon: Icon(
                    Icons.logout,
                    color: Color(0xff8f94fb),
                  )),
            )
          ],
          elevation: 0,
          backgroundColor: Color(0xff8f94fb),
        ),
        body: isLoading
            ? Center(child: Container(child: CircularProgressIndicator()))
            : SingleChildScrollView(
                padding: EdgeInsets.all(15.0),
                child: Padding(
                  padding: const EdgeInsets.all(38.0),
                  child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: <Widget>[
                        Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Image.asset(
                            "Assets/images/password.png",
                            fit: BoxFit.fitHeight,
                            height: 146,
                          ),
                        ),
                        SizedBox(
                          height: 35,
                        ),
                        Container(
                          height: size.height / 14,
                          width: size.width / 1,
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(10),
                            color: Colors.white,
                          ),
                          child: TextField(
                              controller: _email,
                              cursorColor: const Color(0xff8f94fb),
                              autofocus: false,
                              textInputAction: TextInputAction.next,
                              decoration: InputDecoration(
                                enabledBorder: UnderlineInputBorder(
                                  borderSide:
                                      BorderSide(color: Color(0xff8f94fb)),
                                ),
                                focusedBorder: UnderlineInputBorder(
                                  borderSide:
                                      BorderSide(color: Color(0xff8f94fb)),
                                ),
                                prefixIcon: Icon(
                                  Icons.email,
                                  color: Color(0xff8f94fb),
                                ),
                                contentPadding:
                                    EdgeInsets.fromLTRB(20, 15, 20, 15),
                                hintText: "Email",
                              )),
                        ),
                        SizedBox(height: 20),
                        Container(
                          height: size.height / 14,
                          width: size.width / 1,
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(10),
                            color: Colors.white,
                          ),
                          child: TextField(
                              controller: _password,
                              cursorColor: const Color(0xff8f94fb),
                              obscureText: true,
                              autofocus: false,
                              textInputAction: TextInputAction.next,
                              decoration: InputDecoration(
                                enabledBorder: UnderlineInputBorder(
                                  borderSide:
                                      BorderSide(color: Color(0xff8f94fb)),
                                ),
                                focusedBorder: UnderlineInputBorder(
                                  borderSide:
                                      BorderSide(color: Color(0xff8f94fb)),
                                ),
                                prefixIcon: Icon(
                                  Icons.vpn_key,
                                  color: Color(0xff8f94fb),
                                ),
                                contentPadding:
                                    EdgeInsets.fromLTRB(20, 15, 20, 15),
                                hintText: "Password",
                              )),
                        ),
                        SizedBox(
                          height: 20,
                        ),
                        Container(
                          height: size.height / 14,
                          width: size.width / 1,
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(10),
                          ),
                        ),
                        SizedBox(height: 20),
                        GestureDetector(
                            onTap: () {
                              if (_email.text.isNotEmpty &&
                                  _password.text.isNotEmpty) {
                                setState(() {
                                  isLoading = true;
                                });

                                hospitalLogIn(_email.text, _password.text)
                                    .then((user) {
                                  if (user != null) {
                                    setState(() {
                                      isLoading = false;
                                    });
                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                            builder: (_) => DoctorHomePage()));
                                  } else {
                                    setState(() {
                                      isLoading = false;
                                    });
                                  }
                                });
                              } else {
                                print("please fill the form correctly");
                              }
                            },
                            child: Container(
                                height: size.height / 14,
                                width: size.width / 3.2,
                                decoration: BoxDecoration(
                                    borderRadius: BorderRadius.circular(40),
                                    color: Color(0xff8f94fb)),
                                alignment: Alignment.center,
                                child: Text("Log In",
                                    style: TextStyle(
                                        fontSize: 16,
                                        color: Colors.white,
                                        fontWeight: FontWeight.bold)))),
                        SizedBox(
                          height: 20,
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text("Don't have an account?"),
                            GestureDetector(
                                onTap: () {
                                  Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                          builder: (_) =>
                                              HospitalSignUpScreen()));
                                },
                                child: Container(
                                    alignment: Alignment.center,
                                    child: Text("Sign Up",
                                        style: TextStyle(
                                            fontSize: 16,
                                            color: Color(0xff8f94fb),
                                            fontWeight: FontWeight.bold)))),
                          ],
                        ),
                      ]),
                )));
  }
}
