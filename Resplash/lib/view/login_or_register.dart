import 'package:flutter/material.dart';
import 'package:wallpaper_selector/view/register_page.dart';

import 'login.dart';

class LoginOrRegister extends StatefulWidget {
  const LoginOrRegister({Key? key}) : super(key: key);

  @override
  State<LoginOrRegister> createState() => _LoginOrRegisterState();
}

class _LoginOrRegisterState extends State<LoginOrRegister> {
  bool showUserLogin = true;

  void onToggle(){
    setState(() {
      showUserLogin = !showUserLogin;
    });
  }

  @override
  Widget build(BuildContext context) {
    if(showUserLogin){
      return LoginPage(onTap: onToggle);
    }
    else{
      return RegisterPage(onTap: onToggle);
    }
  }
}
