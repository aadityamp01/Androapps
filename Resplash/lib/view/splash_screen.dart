import 'dart:async';

import 'package:flutter/material.dart';
import 'package:wallpaper_selector/view/auth_page.dart';

class SplashScreen extends StatefulWidget {
  const SplashScreen({Key? key}) : super(key: key);

  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {

  @override
  void initState() {
    super.initState();
    Timer(const Duration(seconds: 1), (){
      Navigator.pushReplacement(context, MaterialPageRoute(builder: (context)=>const AuthPage(),));
    });
  }
  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      backgroundColor: Color(0xFFA6E3E9),
      body: Center(
          child: Text("Resplash", style: TextStyle(
            fontSize: 30,
            fontWeight: FontWeight.w700,
            color: Colors.white,
          ),),
        ),
    );
  }
}
