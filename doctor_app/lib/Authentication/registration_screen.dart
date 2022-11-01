import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:hospital_app/Authentication/login_screen.dart';
import 'package:hospital_app/pages/home_page.dart';

import 'Methods.dart';

class SignUpScreen extends StatefulWidget {
  @override
  _SignUpScreenState createState() => _SignUpScreenState();
}

class _SignUpScreenState extends State<SignUpScreen> {
  final TextEditingController _name = TextEditingController();
  final TextEditingController _email = TextEditingController();
  final TextEditingController _password = TextEditingController();
  final TextEditingController _phoneNo = TextEditingController();
  final TextEditingController _address = TextEditingController();
  final TextEditingController _confirmPassword = TextEditingController();
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
            onPressed: () => Navigator.of(context).pop(),
          ),
          title: Center(
              child: Text(
            "Hospital App",
            style: TextStyle(color: Colors.white),
          )),
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
          backgroundColor: Color(0xff8f94fb),
        ),
        body: isLoading
            ? Center(child: Container(child: CircularProgressIndicator()))
            : Center(
                child: SingleChildScrollView(
                    padding: EdgeInsets.all(15),
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
                              height: 20,
                            ),
                            Container(
                              height: size.height / 14,
                              width: size.width / 1,
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(10),
                                color: Colors.white,
                              ),
                              child: TextField(
                                  controller: _name,
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
                                      Icons.person_rounded,
                                      color: Color(0xff8f94fb),
                                    ),
                                    contentPadding:
                                        EdgeInsets.fromLTRB(20, 15, 20, 15),
                                    hintText: "Name",
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
                                color: Colors.white,
                              ),
                              child: TextField(
                                  controller: _phoneNo,
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
                                      Icons.call,
                                      color: Color(0xff8f94fb),
                                    ),
                                    contentPadding:
                                        EdgeInsets.fromLTRB(20, 15, 20, 15),
                                    hintText: "Phone",
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
                              child: TextField(
                                  controller: _address,
                                  cursorColor: const Color(0xff8f94fb),
                                  autofocus: false,
                                  textInputAction: TextInputAction.done,
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
                                      Icons.home,
                                      color: Color(0xff8f94fb),
                                    ),
                                    contentPadding:
                                        EdgeInsets.fromLTRB(20, 15, 20, 15),
                                    hintText: "Address",
                                  )),
                            ),
                            SizedBox(height: 20),
                            GestureDetector(
                                onTap: () {
                                  if (_name.text.isNotEmpty &&
                                      _phoneNo.text.isNotEmpty &&
                                      _email.text.isNotEmpty &&
                                      _password.text.isNotEmpty &&
                                      _address.text.isNotEmpty) {
                                    setState(() {
                                      isLoading = true;
                                    });

                                    signUp(
                                            _name.text,
                                            _phoneNo.text,
                                            _email.text,
                                            _password.text,
                                            _address.text)
                                        .then((user) {
                                      if (user != null) {
                                        setState(() {
                                          isLoading = false;
                                        });
                                        Navigator.push(
                                            context,
                                            MaterialPageRoute(
                                                builder: (_) => HomePage()));
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
                                    height: size.height / 16,
                                    width: size.width / 3.2,
                                    decoration: BoxDecoration(
                                      borderRadius: BorderRadius.circular(40),
                                      color: Color(0xff8f94fb),
                                    ),
                                    alignment: Alignment.center,
                                    child: Text("Sign Up",
                                        style: TextStyle(
                                            fontSize: 16,
                                            color: Colors.white,
                                            fontWeight: FontWeight.bold)))),
                          ]),
                    )),
              ));
  }
}
